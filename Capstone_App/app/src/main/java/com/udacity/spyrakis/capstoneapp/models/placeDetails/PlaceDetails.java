package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceDetails implements Parcelable {

    @SerializedName("website")
    private transient String website;

    @SerializedName("address")
    private String address;

    @SerializedName("lng")
    private transient double lng;

    @SerializedName("icon")
    private String icon;

    @SerializedName("numReviews")
    private transient int numReviews;

    @SerializedName("description")
    private Description description;

    @SerializedName("services")
    private transient Services services;

    @SerializedName("reviews")
    private transient List<String> reviews;

    @SerializedName("name")
    private  String name;

    @SerializedName("location")
    private transient String location;

    @SerializedName("phone_number")
    private transient String phoneNumber;

    @SerializedName("id")
    private int id;

    @SerializedName("category")
    private transient String category;

    @SerializedName("external_urls")
    private transient ExternalUrls externalUrls;

    @SerializedName("lat")
    private transient double lat;

    @SerializedName("international_phone_number")
    private transient String internationalPhoneNumber;

    @SerializedName("statistics")
    private transient Statistics statistics;

    @SerializedName("polarity")
    private transient int polarity;

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Services getServices() {
        return services;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public List<String> getReviews() {
        return reviews;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }

    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setInternationalPhoneNumber(String internationalPhoneNumber) {
        this.internationalPhoneNumber = internationalPhoneNumber;
    }

    public String getInternationalPhoneNumber() {
        return internationalPhoneNumber;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setPolarity(int polarity) {
        this.polarity = polarity;
    }

    public int getPolarity() {
        return polarity;
    }

    @Override
    public String toString() {
        return
                "PlaceDetails{" +
                        "website = '" + website + '\'' +
                        ",address = '" + address + '\'' +
                        ",lng = '" + lng + '\'' +
                        ",icon = '" + icon + '\'' +
                        ",numReviews = '" + numReviews + '\'' +
                        ",description = '" + description + '\'' +
                        ",services = '" + services + '\'' +
                        ",reviews = '" + reviews + '\'' +
                        ",name = '" + name + '\'' +
                        ",location = '" + location + '\'' +
                        ",phone_number = '" + phoneNumber + '\'' +
                        ",id = '" + id + '\'' +
                        ",category = '" + category + '\'' +
                        ",external_urls = '" + externalUrls + '\'' +
                        ",lat = '" + lat + '\'' +
                        ",international_phone_number = '" + internationalPhoneNumber + '\'' +
                        ",statistics = '" + statistics + '\'' +
                        ",polarity = '" + polarity + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.website);
        dest.writeString(this.address);
        dest.writeDouble(this.lng);
        dest.writeString(this.icon);
        dest.writeInt(this.numReviews);
        dest.writeParcelable(this.description, flags);
        dest.writeParcelable(this.services, flags);
        dest.writeStringList(this.reviews);
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeString(this.phoneNumber);
        dest.writeInt(this.id);
        dest.writeString(this.category);
        dest.writeParcelable(this.externalUrls, flags);
        dest.writeDouble(this.lat);
        dest.writeString(this.internationalPhoneNumber);
        dest.writeParcelable(this.statistics, flags);
        dest.writeInt(this.polarity);
    }

    public PlaceDetails() {
    }

    protected PlaceDetails(Parcel in) {
        this.website = in.readString();
        this.address = in.readString();
        this.lng = in.readDouble();
        this.icon = in.readString();
        this.numReviews = in.readInt();
        this.description = in.readParcelable(Description.class.getClassLoader());
        this.services = in.readParcelable(Services.class.getClassLoader());
        this.reviews = in.createStringArrayList();
        this.name = in.readString();
        this.location = in.readString();
        this.phoneNumber = in.readString();
        this.id = in.readInt();
        this.category = in.readString();
        this.externalUrls = in.readParcelable(ExternalUrls.class.getClassLoader());
        this.lat = in.readDouble();
        this.internationalPhoneNumber = in.readString();
        this.statistics = in.readParcelable(Statistics.class.getClassLoader());
        this.polarity = in.readInt();
    }

    public static final Parcelable.Creator<PlaceDetails> CREATOR = new Parcelable.Creator<PlaceDetails>() {
        @Override
        public PlaceDetails createFromParcel(Parcel source) {
            return new PlaceDetails(source);
        }

        @Override
        public PlaceDetails[] newArray(int size) {
            return new PlaceDetails[size];
        }
    };
}