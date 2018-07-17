package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import com.google.gson.annotations.SerializedName;

public class Description {

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
}