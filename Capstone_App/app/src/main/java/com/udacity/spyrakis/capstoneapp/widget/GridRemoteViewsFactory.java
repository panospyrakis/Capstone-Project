package com.udacity.spyrakis.capstoneapp.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.spyrakis.capstoneapp.R;
import com.udacity.spyrakis.capstoneapp.provider.PlaceContract;

/**
 * Created by pspyrakis on 18/7/18.
 */
public class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    Cursor mCursor;

    public GridRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (mCursor != null) {
            mCursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();
        Uri uri = PlaceContract.PlaceEntry.CONTENT_URI;
        mCursor = mContext.getContentResolver().query(uri,
                null,
                null,
                null,
                PlaceContract.PlaceEntry._ID + " DESC");

        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Override
    public int getCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == AdapterView.INVALID_POSITION ||
                mCursor == null || !mCursor.moveToPosition(position)) {
            return null;
        }

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.collection_widget_list_item);
        int nameIndex = mCursor.getColumnIndex(PlaceContract.PlaceEntry.NAME);
        rv.setTextViewText(R.id.widgetItemTaskNameLabel, mCursor.getString(nameIndex));

        int idIndex = mCursor.getColumnIndex(PlaceContract.PlaceEntry._ID);

        Intent fillInIntent = new Intent();
        fillInIntent.putExtra(PlaceAppWidgetProvider.EXTRA_ITEM, mCursor.getInt(idIndex));
        rv.setOnClickFillInIntent(R.id.widgetItemContainer, fillInIntent);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        int idIndex = mCursor.getColumnIndex(PlaceContract.PlaceEntry._ID);
        return mCursor.moveToPosition(position) ? mCursor.getLong(idIndex) : position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}