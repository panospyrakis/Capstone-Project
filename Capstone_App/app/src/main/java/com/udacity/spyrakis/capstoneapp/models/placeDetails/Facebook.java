package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import com.google.gson.annotations.SerializedName;

public class Facebook {

    @SerializedName("fan_count")
    private int fanCount;

    @SerializedName("talking_about_count")
    private int talkingAboutCount;

    @SerializedName("checkins")
    private int checkins;

    @SerializedName("were_here_count")
    private int wereHereCount;

    public void setFanCount(int fanCount) {
        this.fanCount = fanCount;
    }

    public int getFanCount() {
        return fanCount;
    }

    public void setTalkingAboutCount(int talkingAboutCount) {
        this.talkingAboutCount = talkingAboutCount;
    }

    public int getTalkingAboutCount() {
        return talkingAboutCount;
    }

    public void setCheckins(int checkins) {
        this.checkins = checkins;
    }

    public int getCheckins() {
        return checkins;
    }

    public void setWereHereCount(int wereHereCount) {
        this.wereHereCount = wereHereCount;
    }

    public int getWereHereCount() {
        return wereHereCount;
    }

    @Override
    public String toString() {
        return
                "Facebook{" +
                        "fan_count = '" + fanCount + '\'' +
                        ",talking_about_count = '" + talkingAboutCount + '\'' +
                        ",checkins = '" + checkins + '\'' +
                        ",were_here_count = '" + wereHereCount + '\'' +
                        "}";
    }
}