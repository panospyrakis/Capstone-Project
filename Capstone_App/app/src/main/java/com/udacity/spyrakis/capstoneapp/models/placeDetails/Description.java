package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Description implements Parcelable {

    @SerializedName("de")
    private String de;

    @SerializedName("en")
    private String en;

    @SerializedName("unidentified")
    private String unidentified;

    @SerializedName("it")
    private String it;

    @SerializedName("fr")
    private String fr;

    @SerializedName("nl")
    private String nl;

    @SerializedName("es")
    private String es;

    public void setDe(String de) {
        this.de = de;
    }

    public String getDe() {
        return de;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getEn() {
        return en;
    }

    public void setUnidentified(String unidentified) {
        this.unidentified = unidentified;
    }

    public String getUnidentified() {
        return unidentified;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getIt() {
        return it;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getFr() {
        return fr;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getNl() {
        return nl;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getEs() {
        return es;
    }

    @Override
    public String toString() {
        return
                "Description{" +
                        "de = '" + de + '\'' +
                        ",en = '" + en + '\'' +
                        ",unidentified = '" + unidentified + '\'' +
                        ",it = '" + it + '\'' +
                        ",fr = '" + fr + '\'' +
                        ",nl = '" + nl + '\'' +
                        ",es = '" + es + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.de);
        dest.writeString(this.en);
        dest.writeString(this.unidentified);
        dest.writeString(this.it);
        dest.writeString(this.fr);
        dest.writeString(this.nl);
        dest.writeString(this.es);
    }

    public Description() {
    }

    protected Description(Parcel in) {
        this.de = in.readString();
        this.en = in.readString();
        this.unidentified = in.readString();
        this.it = in.readString();
        this.fr = in.readString();
        this.nl = in.readString();
        this.es = in.readString();
    }

    public static final Parcelable.Creator<Description> CREATOR = new Parcelable.Creator<Description>() {
        @Override
        public Description createFromParcel(Parcel source) {
            return new Description(source);
        }

        @Override
        public Description[] newArray(int size) {
            return new Description[size];
        }
    };
}