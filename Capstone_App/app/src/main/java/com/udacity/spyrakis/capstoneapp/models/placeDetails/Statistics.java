package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import com.google.gson.annotations.SerializedName;

public class Statistics {

    @SerializedName("Facebook")
    private Facebook facebook;

    @SerializedName("Foursquare")
    private Foursquare foursquare;

    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    public Facebook getFacebook() {
        return facebook;
    }

    public void setFoursquare(Foursquare foursquare) {
        this.foursquare = foursquare;
    }

    public Foursquare getFoursquare() {
        return foursquare;
    }

    @Override
    public String toString() {
        return
                "Statistics{" +
                        "facebook = '" + facebook + '\'' +
                        ",foursquare = '" + foursquare + '\'' +
                        "}";
    }
}