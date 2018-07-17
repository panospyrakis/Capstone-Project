package com.udacity.spyrakis.capstoneapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;
import com.udacity.spyrakis.capstoneapp.R;
import com.udacity.spyrakis.capstoneapp.models.Place;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    static final int PLACE_PICKER_REQUEST = 1;
    static final int MAPS_REQUEST_CODE = 2;

    static final String EXTRA_LOCATION = "EXTRA_LOCATION";

    Button whereButton;
    Button searchButton;
    private LatLng searchLocation;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setUpNetworkCalls();
        setUpWhereButton();
        setUpSpinner();
        setUpSearchButton();
    }

    private void setUpWhereButton() {
        whereButton = findViewById(R.id.where_button);

        // Currently you can search for places from 8 locations: Amsterdam, Barcelona, Berlin, Dubai, London, Paris, Rome and Tuscany.
        // A Places search returns a list of Places along with summary information about each Place.
        searchLocation = new LatLng(51.507330, -0.127788);
        category = getApplicationContext().getResources().getStringArray(R.array.categories)[0];
        whereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whereButton.setEnabled(false);
                Intent startMapsActivity = new Intent(getActivity(), MapsActivity.class);
                getActivity().startActivityForResult(startMapsActivity, 1);
            }
        });
    }

    private void setUpSearchButton() {
        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whereButton.setEnabled(false);
                findPlaces();
            }
        });
    }

    private void findPlaces() {

        LatLngBounds bounds = toBounds(150.0);


        Call<ArrayList<Place>> call = service.places(bounds.southwest.latitude,
                bounds.northeast.latitude,
                bounds.southwest.longitude,
                bounds.northeast.longitude,
                category);

        call.enqueue(new Callback<ArrayList<Place>>() {
            @Override
            public void onResponse(Call<ArrayList<Place>> call, Response<ArrayList<Place>> response) {
                Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                ArrayList<Place> places = response.body();

            }

            @Override
            public void onFailure(Call<ArrayList<Place>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LatLngBounds toBounds(double radiusInMeters) {
        double distanceFromCenterToCorner = radiusInMeters * Math.sqrt(2.0);
        LatLng southwestCorner = SphericalUtil.computeOffset(searchLocation, distanceFromCenterToCorner, 225.0);
        LatLng northeastCorner = SphericalUtil.computeOffset(searchLocation, distanceFromCenterToCorner, 45.0);
        return new LatLngBounds(southwestCorner, northeastCorner);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) return;

        if (requestCode == MAPS_REQUEST_CODE) {
            whereButton.setEnabled(true);
            if (data.hasExtra(EXTRA_LOCATION)) {
                searchLocation = data.getParcelableExtra(EXTRA_LOCATION);
                Toast.makeText(getApplicationContext(), searchLocation.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PLACE_PICKER_REQUEST) {
            whereButton.setEnabled(true);
            if (resultCode == RESULT_OK) {
                String toastMsg = String.format("Place: %s", data.toString());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }

    }

    private void setUpSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout

        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        category = (String) parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
