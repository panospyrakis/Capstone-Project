package com.udacity.spyrakis.capstoneapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pspyrakis on 16/7/18.
 */
public interface ServiceInterface {

    @GET("api/getPlaces")
    Call<List<Place>> places(@Query("location") String location, @Query("category") String category);

}
