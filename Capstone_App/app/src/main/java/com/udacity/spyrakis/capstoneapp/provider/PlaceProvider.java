package com.udacity.spyrakis.capstoneapp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by pspyrakis on 18/7/18.
 */
public class PlaceProvider extends ContentProvider {
    private static final String LOG_TAG = PlaceProvider.class.getSimpleName();
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private PlaceDBHelper mOpenHelper;

    // Codes for the UriMatcher //////
    private static final int RECIPE = 100;
    private static final int RECIPE_WITH_ID = 200;
    ////////

    private static UriMatcher buildUriMatcher() {
        // Build a UriMatcher by adding a specific code to return based on a match
        // It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PlaceContract.CONTENT_AUTHORITY;

        // add a code for each type of URI you want
        matcher.addURI(authority, PlaceContract.PlaceEntry.TABLE_PLACES, RECIPE);
        matcher.addURI(authority, PlaceContract.PlaceEntry.TABLE_PLACES + "/#", RECIPE_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new PlaceDBHelper(getContext());

        return true;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case RECIPE: {
                return PlaceContract.PlaceEntry.CONTENT_DIR_TYPE;
            }
            case RECIPE_WITH_ID: {
                return PlaceContract.PlaceEntry.CONTENT_ITEM_TYPE;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            // All recipes selected
            case RECIPE: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        PlaceContract.PlaceEntry.TABLE_PLACES,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                return retCursor;
            }
            // Individual recipe based on Id selected
            case RECIPE_WITH_ID: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        PlaceContract.PlaceEntry.TABLE_PLACES,
                        projection,
                        PlaceContract.PlaceEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                return retCursor;
            }
            default: {
                // By default, we assume a bad URI
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        switch (sUriMatcher.match(uri)) {
            case RECIPE: {
                long _id = db.insert(PlaceContract.PlaceEntry.TABLE_PLACES, null, values);
                // insert unless it is already contained in the database
                if (_id > 0) {
                    returnUri = PlaceContract.PlaceEntry.buildPlaceUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into: " + uri);
                }
                break;
            }

            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);

            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int numDeleted;
        switch (match) {
            case RECIPE:
                numDeleted = db.delete(
                        PlaceContract.PlaceEntry.TABLE_PLACES, selection, selectionArgs);
                // reset _ID
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                        PlaceContract.PlaceEntry.TABLE_PLACES + "'");
                break;
            case RECIPE_WITH_ID:
                numDeleted = db.delete(PlaceContract.PlaceEntry.TABLE_PLACES,
                        PlaceContract.PlaceEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                // reset _ID
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                        PlaceContract.PlaceEntry.TABLE_PLACES + "'");

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return numDeleted;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RECIPE:
                // allows for multiple transactions
                db.beginTransaction();

                // keep track of successful inserts
                int numInserted = 0;
                try {
                    for (ContentValues value : values) {
                        if (value == null) {
                            throw new IllegalArgumentException("Cannot have null content values");
                        }
                        long _id = -1;
                        try {
                            _id = db.insertOrThrow(PlaceContract.PlaceEntry.TABLE_PLACES,
                                    null, value);
                        } catch (SQLiteConstraintException e) {
                            Log.w(LOG_TAG, "Attempting to insert " +
                                    value.getAsString(
                                            PlaceContract.PlaceEntry._ID)
                                    + " but value is already in database.");
                        }
                        if (_id != -1) {
                            numInserted++;
                        }
                    }
                    if (numInserted > 0) {
                        // If no errors, declare a successful transaction.
                        // database will not populate if this is not called
                        db.setTransactionSuccessful();
                    }
                } finally {
                    // all transactions occur at once
                    db.endTransaction();
                }
                if (numInserted > 0) {
                    // if there was successful insertion, notify the content resolver that there
                    // was a change
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return numInserted;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int numUpdated = 0;

        if (contentValues == null) {
            throw new IllegalArgumentException("Cannot have null content values");
        }

        switch (sUriMatcher.match(uri)) {
            case RECIPE: {
                numUpdated = db.update(PlaceContract.PlaceEntry.TABLE_PLACES,
                        contentValues,
                        selection,
                        selectionArgs);
                break;
            }
            case RECIPE_WITH_ID: {
                numUpdated = db.update(PlaceContract.PlaceEntry.TABLE_PLACES,
                        contentValues,
                        PlaceContract.PlaceEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }

        if (numUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numUpdated;
    }

}