package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Facebook implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.fanCount);
        dest.writeInt(this.talkingAboutCount);
        dest.writeInt(this.checkins);
        dest.writeInt(this.wereHereCount);
    }

    public Facebook() {
    }

    protected Facebook(Parcel in) {
        this.fanCount = in.readInt();
        this.talkingAboutCount = in.readInt();
        this.checkins = in.readInt();
        this.wereHereCount = in.readInt();
    }

    public static final Parcelable.Creator<Facebook> CREATOR = new Parcelable.Creator<Facebook>() {
        @Override
        public Facebook createFromParcel(Parcel source) {
            return new Facebook(source);
        }

        @Override
        public Facebook[] newArray(int size) {
            return new Facebook[size];
        }
    };
}