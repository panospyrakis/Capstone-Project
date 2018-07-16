package com.udacity.spyrakis.capstoneapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Place implements Parcelable {

    @SerializedName("address")
    private String address;

    @SerializedName("lng")
    private double lng;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;

    @SerializedName("details")
    private String details;

    @SerializedName("id")
    private int id;

    @SerializedName("category")
    private String category;

    @SerializedName("lat")
    private double lat;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return
                "Place{" +
                        "address = '" + address + '\'' +
                        ",lng = '" + lng + '\'' +
                        ",name = '" + name + '\'' +
                        ",location = '" + location + '\'' +
                        ",details = '" + details + '\'' +
                        ",id = '" + id + '\'' +
                        ",category = '" + category + '\'' +
                        ",lat = '" + lat + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeDouble(this.lng);
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeString(this.details);
        dest.writeInt(this.id);
        dest.writeString(this.category);
        dest.writeDouble(this.lat);
    }

    public Place() {
    }

    protected Place(Parcel in) {
        this.address = in.readString();
        this.lng = in.readDouble();
        this.name = in.readString();
        this.location = in.readString();
        this.details = in.readString();
        this.id = in.readInt();
        this.category = in.readString();
        this.lat = in.readDouble();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}