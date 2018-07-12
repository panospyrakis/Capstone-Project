package com.udacity.spyrakis.capstoneapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MenuActivity extends BaseActivity {

    static final int MAPS_REQUEST_CODE = 1;
    static final String EXTRA_LOCATION = "EXTRA_LOCATION";

    Button whereButton;
    private LatLng searchLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setUpWhereButton();
    }

    private void setUpWhereButton() {
        whereButton = findViewById(R.id.where_button);

        whereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whereButton.setEnabled(false);
                Intent startMapsActivity = new Intent(getActivity(), MapsActivity.class);
                getActivity().startActivityForResult(startMapsActivity, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MAPS_REQUEST_CODE) {
            whereButton.setEnabled(true);
            if (resultCode == RESULT_OK) {
                if (data.hasExtra(EXTRA_LOCATION)) {
                    searchLocation = data.getParcelableExtra(EXTRA_LOCATION);
                    Toast.makeText(getApplicationContext(), searchLocation.toString(), Toast.LENGTH_SHORT);
                }
            }
        }
    }
}
