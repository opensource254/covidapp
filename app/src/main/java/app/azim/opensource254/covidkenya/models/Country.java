package app.azim.opensource254.covidkenya.models;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("Province/State")
    private String state;
    @SerializedName("Country/Region")
    private String country;
    @SerializedName("Lat")
    private String lattitude;
    @SerializedName("Long")
    private String longitude;
    @SerializedName("4/21/20")
    private DataStream dataStream;
    @SerializedName("total")
    private DataStream total;

    public Country() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
