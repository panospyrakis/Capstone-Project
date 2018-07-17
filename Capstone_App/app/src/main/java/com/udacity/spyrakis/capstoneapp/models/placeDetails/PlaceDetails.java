package com.udacity.spyrakis.capstoneapp.models.placeDetails;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceDetails {

    @SerializedName("website")
    private String website;

    @SerializedName("address")
    private String address;

    @SerializedName("lng")
    private double lng;

    @SerializedName("icon")
    private String icon;

    @SerializedName("numReviews")
    private int numReviews;

    @SerializedName("description")
    private Description description;

    @SerializedName("services")
    private Services services;

    @SerializedName("reviews")
    private List<String> reviews;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("id")
    private int id;

    @SerializedName("category")
    private String category;

    @SerializedName("external_urls")
    private ExternalUrls externalUrls;

    @SerializedName("lat")
    private double lat;

    @SerializedName("international_phone_number")
    private String internationalPhoneNumber;

    @SerializedName("statistics")
    private Statistics statistics;

    @SerializedName("polarity")
    private int polarity;

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
}