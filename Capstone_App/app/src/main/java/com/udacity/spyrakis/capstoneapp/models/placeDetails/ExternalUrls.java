package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import com.google.gson.annotations.SerializedName;

public class ExternalUrls {

    @SerializedName("GooglePlaces")
    private String googlePlaces;

    @SerializedName("Booking")
    private String booking;

    @SerializedName("Facebook")
    private String facebook;

    @SerializedName("Foursquare")
    private String foursquare;

    public void setGooglePlaces(String googlePlaces) {
        this.googlePlaces = googlePlaces;
    }

    public String getGooglePlaces() {
        return googlePlaces;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getBooking() {
        return booking;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFoursquare(String foursquare) {
        this.foursquare = foursquare;
    }

    public String getFoursquare() {
        return foursquare;
    }

    @Override
    public String toString() {
        return
                "ExternalUrls{" +
                        "googlePlaces = '" + googlePlaces + '\'' +
                        ",booking = '" + booking + '\'' +
                        ",facebook = '" + facebook + '\'' +
                        ",foursquare = '" + foursquare + '\'' +
                        "}";
    }
}