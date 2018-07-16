package com.udacity.spyrakis.capstoneapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    static final int PLACE_PICKER_REQUEST = 1;
    static final int MAPS_REQUEST_CODE = 2;

    static final String EXTRA_LOCATION = "EXTRA_LOCATION";

    Button whereButton;
    Button searchButton;
    private String searchLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setUpNetworkCalls();
        setUpWhereButton();
        setUpSearchButton();
    }

    private void setUpWhereButton() {
        whereButton = findViewById(R.id.where_button);

        // Currently you can search for places from 8 locations: Amsterdam, Barcelona, Berlin, Dubai, London, Paris, Rome and Tuscany.
        // A Places search returns a list of Places along with summary information about each Place.
        searchLocation = "London";

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

    private void findPlaces(){

        String category = "restaurant";
        Call<List<Place>> call = service.places(searchLocation,category);

        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_SHORT).show();
                List<Place> places = response.body();

            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) return;

        if (requestCode == MAPS_REQUEST_CODE ) {
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.connection_fail), Toast.LENGTH_SHORT).show();
    }
}
