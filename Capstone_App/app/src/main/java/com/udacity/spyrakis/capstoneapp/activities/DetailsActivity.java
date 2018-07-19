package com.udacity.spyrakis.capstoneapp.activities;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.spyrakis.capstoneapp.R;
import com.udacity.spyrakis.capstoneapp.models.placeDetails.Description;
import com.udacity.spyrakis.capstoneapp.models.placeDetails.PlaceDetails;
import com.udacity.spyrakis.capstoneapp.provider.PlaceContract;
import com.udacity.spyrakis.capstoneapp.widget.PlaceAppWidgetProvider;

public class DetailsActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private PlaceDetails place;
    ImageView starOn;
    ImageView starOff;
    private static final int CURSOR_LOADER_ID = 0;
    private static final int CURSOR_LOADER_ID_FROM_WIDGET = 1;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getIntent().hasExtra(ResultListActivity.EXTRA_ITEM_DETAILS)) {
            place = getIntent().getParcelableExtra(ResultListActivity.EXTRA_ITEM_DETAILS);
            continueOnCreate();
            checkIfFavourite();
        } else if (getIntent().hasExtra(PlaceAppWidgetProvider.EXTRA_ITEM)) {
            id = getIntent().getIntExtra(PlaceAppWidgetProvider.EXTRA_ITEM, 0);
            getLoaderManager().initLoader(CURSOR_LOADER_ID_FROM_WIDGET, null, this);
        }


    }

    private void continueOnCreate() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(place.getName());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView description = findViewById(R.id.description);
        description.setText(getDescription());

        ImageView image = findViewById(R.id.image);
        if (place.getIcon().trim().length() != 0) {
            Picasso.get().load(place.getIcon()).placeholder(R.drawable.placeholder).into(image);
        }
        setUpFavouritesButton();
        setUpFabButton();
    }

    private void checkIfFavourite() {
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        place = savedInstanceState.getParcelable(ResultListActivity.EXTRA_ITEM_DETAILS);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ResultListActivity.EXTRA_ITEM_DETAILS, place);
    }

    private void setUpFabButton() {
        FloatingActionButton fab = findViewById(R.id.directions_fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = getApplicationContext().getString(R.string.geo_uri, place.getLat(), place.getLng(), place.getName());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private void setUpFavouritesButton() {
        FrameLayout favouriteButton = findViewById(R.id.favourite);
        starOn = findViewById(R.id.star_on);
        starOff = findViewById(R.id.star_off);

        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleStars();
            }
        });
    }

    private void toggleStars() {
        if (starOff.getVisibility() == View.VISIBLE) {
            showStarOn();
            addItemInProvider();
        } else {
            showStarOff();
            removeItemFromProvider();
        }
    }

    private void showStarOff() {
        starOff.setVisibility(View.VISIBLE);
        starOn.setVisibility(View.INVISIBLE);
    }

    private void showStarOn() {
        starOff.setVisibility(View.INVISIBLE);
        starOn.setVisibility(View.VISIBLE);
    }

    private String getDescription() {
        Description desc = place.getDescription();

        if (desc.getEn() != null) {
            return desc.getEn();
        }
        if (desc.getDe() != null) {
            return desc.getEn();
        }
        if (desc.getEs() != null) {
            return desc.getEn();
        }
        if (desc.getFr() != null) {
            return desc.getEn();
        }
        if (desc.getIt() != null) {
            return desc.getEn();
        }
        if (desc.getNl() != null) {
            return desc.getEn();
        }
        if (desc.getUnidentified() != null) {
            return desc.getEn();
        }
        return getApplicationContext().getString(R.string.no_details);
    }

    private void addItemInProvider() {

        ContentValues itemToAdd = new ContentValues();
        itemToAdd.put(PlaceContract.PlaceEntry.NAME, place.getName());
        itemToAdd.put(PlaceContract.PlaceEntry.DESCRIPTION, getDescription());
        itemToAdd.put(PlaceContract.PlaceEntry.LAT, place.getLat());
        itemToAdd.put(PlaceContract.PlaceEntry.LONG, place.getLng());
        itemToAdd.put(PlaceContract.PlaceEntry.ICON, place.getIcon());
        getContentResolver().insert(PlaceContract.PlaceEntry.CONTENT_URI, itemToAdd);

        PlaceAppWidgetProvider.sendRefreshBroadcast(getApplicationContext());
    }

    private void removeItemFromProvider() {
        String where = PlaceContract.PlaceEntry.NAME + "=?";
        String[] whereVal = {place.getName()};
        getContentResolver().delete(PlaceContract.PlaceEntry.CONTENT_URI, where, whereVal);

        PlaceAppWidgetProvider.sendRefreshBroadcast(getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if (id == CURSOR_LOADER_ID) {
            String query = PlaceContract.PlaceEntry.NAME + "=?";
            String[] queryName = {place.getName()};
            return new CursorLoader(getApplicationContext(), PlaceContract.PlaceEntry.CONTENT_URI, null, query, queryName, null);
        } else {
            String query = PlaceContract.PlaceEntry._ID + "=?";
            String[] queryId = {this.id + ""};
            return new CursorLoader(getApplicationContext(), PlaceContract.PlaceEntry.CONTENT_URI, null, query, queryId, null);
        }

    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
        if (loader.getId() == CURSOR_LOADER_ID) {
            if (cursor.getCount() > 0) showStarOn();
        } else if (loader.getId() == CURSOR_LOADER_ID_FROM_WIDGET) {
            if (cursor.getCount() <= 0) return;
            cursor.moveToFirst();
            place = new PlaceDetails();
            int iconIndex = cursor.getColumnIndex(PlaceContract.PlaceEntry.ICON);
            place.setIcon(cursor.getString(iconIndex));
            int nameIndex = cursor.getColumnIndex(PlaceContract.PlaceEntry.NAME);
            place.setName(cursor.getString(nameIndex));
            int latIndex = cursor.getColumnIndex(PlaceContract.PlaceEntry.LAT);
            place.setLat(cursor.getDouble(latIndex));
            int lngIndex = cursor.getColumnIndex(PlaceContract.PlaceEntry.LONG);
            place.setLng(cursor.getDouble(lngIndex));
            int descIndex = cursor.getColumnIndex(PlaceContract.PlaceEntry.DESCRIPTION);
            Description description = new Description();
            description.setEn(cursor.getString(descIndex));
            place.setDescription(description);
            continueOnCreate();
            showStarOn();
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }


}
