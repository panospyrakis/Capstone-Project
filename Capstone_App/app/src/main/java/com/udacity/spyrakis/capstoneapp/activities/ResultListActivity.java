package com.udacity.spyrakis.capstoneapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.udacity.spyrakis.capstoneapp.R;
import com.udacity.spyrakis.capstoneapp.engine.OnListItemClickListener;
import com.udacity.spyrakis.capstoneapp.engine.ResultsListAdapter;
import com.udacity.spyrakis.capstoneapp.models.Place;
import com.udacity.spyrakis.capstoneapp.models.placeDetails.PlaceDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultListActivity extends BaseActivity implements OnListItemClickListener {

    ProgressDialog progress;
    static final String EXTRA_ITEM_DETAILS = "EXTRA_ITEM_DETAILS";
    static final String EXTRA_RESULT_LIST_DETAILS = "EXTRA_RESULT_LIST_DETAILS";
    ArrayList<Place> places;
    ArrayList<PlaceDetails> placesDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getApplicationContext().getString(R.string.results));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (places == null && placesDetails == null){
            places = new ArrayList<>();
            placesDetails = new ArrayList<>();
            setUpNetworkCalls();
            getResults();
        }else{
            setUpList();
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        placesDetails = savedInstanceState.getParcelableArrayList(EXTRA_RESULT_LIST_DETAILS);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_RESULT_LIST_DETAILS,placesDetails);
    }

    private void getResults() {
        if (getIntent().hasExtra(MenuActivity.EXTRA_RESULT_LIST)) {
            places = getIntent().getParcelableArrayListExtra(MenuActivity.EXTRA_RESULT_LIST);
            getDetails();
        }
    }

    private void getDetails() {
        progress = new ProgressDialog(this);
        progress.setTitle(getApplicationContext().getString(R.string.loading));
        progress.setMessage(getApplicationContext().getString(R.string.wait));
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        for (Place place : places) {
            getItem(place.getId());
        }
    }

    private void setUpList() {
        RecyclerView list = findViewById(R.id.results_list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setAdapter(new ResultsListAdapter(placesDetails, this));
    }

    @Override
    public void onItemClick(int position) {
        PlaceDetails place = placesDetails.get(position);
        Intent resultsIntent = new Intent(getActivity(), DetailsActivity.class);
        resultsIntent.putExtra(EXTRA_ITEM_DETAILS, place);
        getActivity().startActivity(resultsIntent);
    }

    private void getItem(int id) {

        Call<PlaceDetails> call = service.placeDetails(id);

        call.enqueue(new Callback<PlaceDetails>() {
            @Override
            public void onResponse(Call<PlaceDetails> call, Response<PlaceDetails> response) {
                PlaceDetails place = response.body();
                placesDetails.add(place);
                if (placesDetails.size() == places.size()) {
                    progress.dismiss();
                    setUpList();
                }
            }

            @Override
            public void onFailure(Call<PlaceDetails> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
