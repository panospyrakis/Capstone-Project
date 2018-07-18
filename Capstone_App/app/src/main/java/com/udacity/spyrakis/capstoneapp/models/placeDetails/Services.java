package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import android.os.Parcel;
import android.os.Parcelable;


public class Services implements Parcelable {

    protected Services(Parcel in) {
    }

    public static final Creator<Services> CREATOR = new Creator<Services>() {
        @Override
        public Services createFromParcel(Parcel in) {
            return new Services(in);
        }

        @Override
        public Services[] newArray(int size) {
            return new Services[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}