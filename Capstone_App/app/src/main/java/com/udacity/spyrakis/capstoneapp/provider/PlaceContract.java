package com.udacity.spyrakis.capstoneapp.provider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by pspyrakis on 18/7/18.
 */
public class PlaceContract {

    public static final String CONTENT_AUTHORITY = "com.udacity.spyrakis.capstoneapp.app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final class PlaceEntry implements BaseColumns {
        // table name
        public static final String TABLE_PLACES = "places";
        // columns
        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String LAT = "lat";
        public static final String LONG = "long";
        public static final String ICON = "icon";

        // create content uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_PLACES).build();
        // create cursor of base type directory for multiple entries
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_PLACES;
        // create cursor of base type item for single entry
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + TABLE_PLACES;

        // for building URIs on insertion
        public static Uri buildPlaceUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}