package com.udacity.spyrakis.capstoneapp.engine;


import com.udacity.spyrakis.capstoneapp.models.Place;
import com.udacity.spyrakis.capstoneapp.models.placeDetails.PlaceDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pspyrakis on 16/7/18.
 */
public interface ServiceInterface {

    @GET("api/getPlacesByArea")
    Call<ArrayList<Place>> places(@Query("S") Double south,
                                  @Query("N") Double north,
                                  @Query("W") Double west,
                                  @Query("E") Double east,
                                  @Query("category") String category);

    @GET("api/getPlaceDetails")
    Call<PlaceDetails> placeDetails(@Query("id") int id);

}
