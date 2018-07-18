package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Statistics implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.facebook, flags);
        dest.writeParcelable(this.foursquare, flags);
    }

    public Statistics() {
    }

    protected Statistics(Parcel in) {
        this.facebook = in.readParcelable(Facebook.class.getClassLoader());
        this.foursquare = in.readParcelable(Foursquare.class.getClassLoader());
    }

    public static final Parcelable.Creator<Statistics> CREATOR = new Parcelable.Creator<Statistics>() {
        @Override
        public Statistics createFromParcel(Parcel source) {
            return new Statistics(source);
        }

        @Override
        public Statistics[] newArray(int size) {
            return new Statistics[size];
        }
    };
}