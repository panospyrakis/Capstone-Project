package com.udacity.spyrakis.capstoneapp.activities;

import android.content.Intent;
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

public class DetailsActivity extends BaseActivity {

    private PlaceDetails place;
    ImageView starOn;
    ImageView starOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getIntent().hasExtra(ResultListActivity.EXTRA_ITEM_DETAILS)) {
            place = getIntent().getParcelableExtra(ResultListActivity.EXTRA_ITEM_DETAILS);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(place.getName());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextView description = findViewById(R.id.description);
        description.setText(getDescription());

        ImageView image = findViewById(R.id.image);
        Picasso.get().load(place.getIcon()).placeholder(R.drawable.placeholder).into(image);

        setUpFavouritesButton();
        setUpFabButton();
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
                String uri = getApplicationContext().getString(R.string.geo_uri,place.getLat(),place.getLng(),place.getName());
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
            starOff.setVisibility(View.INVISIBLE);
            starOn.setVisibility(View.VISIBLE);
        } else {
            starOff.setVisibility(View.VISIBLE);
            starOn.setVisibility(View.INVISIBLE);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
