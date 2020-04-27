package app.azim.opensource254.covidkenya.models;

import androidx.annotation.NonNull;

public class HealthUnit {
    private int unit_id;
    private int id;
    private String title;
    private String lat;
    private String lon;
    private String open;
    private String description;

    public int getUnitId() {
        return unit_id;
    }

    public void setUnitId(int unit_id) {
        this.unit_id = unit_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "Country{" +
                "unit_id='" + unit_id + '\'' +
                ", id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", open=" + open +
                ", description=" + description +
                '}';
    }
}
