package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Foursquare implements Parcelable {

    @SerializedName("hereNow")
    private HereNow hereNow;

    @SerializedName("checkinsCount")
    private int checkinsCount;

    @SerializedName("price")
    private int price;

    @SerializedName("usersCount")
    private int usersCount;

    @SerializedName("tipCount")
    private int tipCount;

    @SerializedName("likes")
    private int likes;

    public void setHereNow(HereNow hereNow) {
        this.hereNow = hereNow;
    }

    public HereNow getHereNow() {
        return hereNow;
    }

    public void setCheckinsCount(int checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Object getPrice() {
        return price;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setTipCount(int tipCount) {
        this.tipCount = tipCount;
    }

    public int getTipCount() {
        return tipCount;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return
                "Foursquare{" +
                        "hereNow = '" + hereNow + '\'' +
                        ",checkinsCount = '" + checkinsCount + '\'' +
                        ",price = '" + price + '\'' +
                        ",usersCount = '" + usersCount + '\'' +
                        ",tipCount = '" + tipCount + '\'' +
                        ",likes = '" + likes + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.hereNow, flags);
        dest.writeInt(this.checkinsCount);
        dest.writeInt(this.price);
        dest.writeInt(this.usersCount);
        dest.writeInt(this.tipCount);
        dest.writeInt(this.likes);
    }

    public Foursquare() {
    }

    protected Foursquare(Parcel in) {
        this.hereNow = in.readParcelable(HereNow.class.getClassLoader());
        this.checkinsCount = in.readInt();
        this.price = in.readInt();
        this.usersCount = in.readInt();
        this.tipCount = in.readInt();
        this.likes = in.readInt();
    }

    public static final Parcelable.Creator<Foursquare> CREATOR = new Parcelable.Creator<Foursquare>() {
        @Override
        public Foursquare createFromParcel(Parcel source) {
            return new Foursquare(source);
        }

        @Override
        public Foursquare[] newArray(int size) {
            return new Foursquare[size];
        }
    };
}