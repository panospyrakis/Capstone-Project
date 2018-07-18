package com.udacity.spyrakis.capstoneapp.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.udacity.spyrakis.capstoneapp.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LatLng chosenLocation;
    private String chosenCity = "";
    private Geocoder geocoder;

    private static final String EXTRA_LOCATION = "EXTRA_LOCATION";

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getApplicationContext().getString(R.string.where));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(chosenLocation == null){
            // Currently you can search for places from 8 locations: Amsterdam, Barcelona, Berlin, Dubai, London, Paris, Rome and Tuscany.
            // A Places search returns a list of Places along with summary information about each Place.
            LatLng london = new LatLng(51.507330, -0.127788);
            chosenLocation = london;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        chosenLocation = savedInstanceState.getParcelable(EXTRA_LOCATION);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_LOCATION, chosenLocation);
    }

    private void returnResult() {
        Intent data = new Intent();
        data.putExtra(MenuActivity.EXTRA_LOCATION, chosenLocation);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        returnResult();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses = null; // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String city = "";
        try {
            addresses = geocoder.getFromLocation(chosenLocation.latitude, chosenLocation.longitude, 1);
            city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.addMarker(new MarkerOptions()
                .position(chosenLocation)
                .title(city)
                .draggable(true));


        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                if (marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                }
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                List<Address> addresses; // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                chosenCity = "";

                try {
                    addresses = geocoder.getFromLocation(marker.getPosition().latitude, marker.getPosition().longitude, 1);
                    if (addresses.size() <= 0) {
                        return;
                    }
                    if (addresses.get(0).getLocality() != null) {
                        chosenCity = addresses.get(0).getLocality();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                marker.setTitle(chosenCity);
                chosenLocation = marker.getPosition();
            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLng(chosenLocation));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            returnResult();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
