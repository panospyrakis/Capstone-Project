package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import com.google.gson.annotations.SerializedName;

public class Services {

    @SerializedName("de")
    private Object de;

    @SerializedName("en")
    private Object en;

    @SerializedName("it")
    private Object it;

    @SerializedName("fr")
    private Object fr;

    @SerializedName("nl")
    private Object nl;

    @SerializedName("es")
    private Object es;

    public void setDe(Object de) {
        this.de = de;
    }

    public Object getDe() {
        return de;
    }

    public void setEn(Object en) {
        this.en = en;
    }

    public Object getEn() {
        return en;
    }

    public void setIt(Object it) {
        this.it = it;
    }

    public Object getIt() {
        return it;
    }

    public void setFr(Object fr) {
        this.fr = fr;
    }

    public Object getFr() {
        return fr;
    }

    public void setNl(Object nl) {
        this.nl = nl;
    }

    public Object getNl() {
        return nl;
    }

    public void setEs(Object es) {
        this.es = es;
    }

    public Object getEs() {
        return es;
    }

    @Override
    public String toString() {
        return
                "Services{" +
                        "de = '" + de + '\'' +
                        ",en = '" + en + '\'' +
                        ",it = '" + it + '\'' +
                        ",fr = '" + fr + '\'' +
                        ",nl = '" + nl + '\'' +
                        ",es = '" + es + '\'' +
                        "}";
    }
}