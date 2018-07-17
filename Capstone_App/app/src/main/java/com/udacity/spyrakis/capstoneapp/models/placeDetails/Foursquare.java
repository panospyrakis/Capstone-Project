package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import com.google.gson.annotations.SerializedName;

public class Foursquare {

    @SerializedName("hereNow")
    private HereNow hereNow;

    @SerializedName("checkinsCount")
    private int checkinsCount;

    @SerializedName("price")
    private Object price;

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

    public void setPrice(Object price) {
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
}