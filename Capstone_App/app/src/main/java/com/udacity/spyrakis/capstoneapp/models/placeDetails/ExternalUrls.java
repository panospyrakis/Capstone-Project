package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ExternalUrls implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.googlePlaces);
        dest.writeString(this.booking);
        dest.writeString(this.facebook);
        dest.writeString(this.foursquare);
    }

    public ExternalUrls() {
    }

    protected ExternalUrls(Parcel in) {
        this.googlePlaces = in.readString();
        this.booking = in.readString();
        this.facebook = in.readString();
        this.foursquare = in.readString();
    }

    public static final Parcelable.Creator<ExternalUrls> CREATOR = new Parcelable.Creator<ExternalUrls>() {
        @Override
        public ExternalUrls createFromParcel(Parcel source) {
            return new ExternalUrls(source);
        }

        @Override
        public ExternalUrls[] newArray(int size) {
            return new ExternalUrls[size];
        }
    };
}