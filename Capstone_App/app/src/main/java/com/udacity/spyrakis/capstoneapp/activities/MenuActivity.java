package com.udacity.spyrakis.capstoneapp.activities;

import android.app.ProgressDialog;
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

    static final int MAPS_REQUEST_CODE = 2;

    static final String EXTRA_LOCATION = "EXTRA_LOCATION";
    static final String EXTRA_RESULT_LIST = "EXTRA_RESULT_LIST";

    Button whereButton;
    Button searchButton;
    private LatLng searchLocation;
    private String category;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setUpNetworkCalls();
        setUpWhereButton();
        setUpSpinner();
        setUpSearchButton();
    }

    @Override
    public void onStart() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
        super.onStart();
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
                findPlaces();
            }
        });
    }

    private void findPlaces() {

        progress = new ProgressDialog(this);
        progress.setTitle(getApplicationContext().getString(R.string.loading));
        progress.setMessage(getApplicationContext().getString(R.string.wait));
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        LatLngBounds bounds = toBounds(150.0);


        Call<ArrayList<Place>> call = service.places(bounds.southwest.latitude,
                bounds.northeast.latitude,
                bounds.southwest.longitude,
                bounds.northeast.longitude,
                category.toLowerCase());

        call.enqueue(new Callback<ArrayList<Place>>() {
            @Override
            public void onResponse(Call<ArrayList<Place>> call, Response<ArrayList<Place>> response) {
                progress.dismiss();
                ArrayList<Place> places = response.body();
                Intent resultsIntent = new Intent(getActivity(), ResultListActivity.class);
                resultsIntent.putParcelableArrayListExtra(EXTRA_RESULT_LIST, places);
                getActivity().startActivity(resultsIntent);
            }

            @Override
            public void onFailure(Call<ArrayList<Place>> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.error), Toast.LENGTH_SHORT).show();
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

        if (data == null) return;

        if (requestCode == MAPS_REQUEST_CODE) {
            if (data.hasExtra(EXTRA_LOCATION)) {
                searchLocation = data.getParcelableExtra(EXTRA_LOCATION);
                Toast.makeText(getApplicationContext(), searchLocation.toString(), Toast.LENGTH_SHORT).show();
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
