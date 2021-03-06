package com.udacity.spyrakis.capstoneapp.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pspyrakis on 18/7/18.
 */
public class PlaceDBHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = PlaceDBHelper.class.getSimpleName();

    //name & version
    private static final String DATABASE_NAME = "places.db";
    private static final int DATABASE_VERSION = 12;

    public PlaceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE " +
                PlaceContract.PlaceEntry.TABLE_PLACES + "(" + PlaceContract.PlaceEntry._ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PlaceContract.PlaceEntry.NAME +
                " TEXT NOT NULL, " +
                PlaceContract.PlaceEntry.DESCRIPTION +
                " TEXT NOT NULL, " +
                PlaceContract.PlaceEntry.LAT +
                " TEXT NOT NULL, " +
                PlaceContract.PlaceEntry.LONG +
                " TEXT NOT NULL, " +
                PlaceContract.PlaceEntry.ICON +
                " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_RECIPE_TABLE);
    }

    // Upgrade database when version is changed.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to " +
                newVersion + ". OLD DATA WILL BE DESTROYED");
        // Drop the table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PlaceContract.PlaceEntry.TABLE_PLACES);
        sqLiteDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                PlaceContract.PlaceEntry.TABLE_PLACES + "'");

        // re-create database
        onCreate(sqLiteDatabase);
    }
}