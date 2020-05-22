package app.azim.opensource254.covidkenya.models;

public class HealthUnitModel {
    public int unit_id;
    public int id;
    public String title;
    public String lat;
    public String lon;
    public String open;
    public String description;

    public HealthUnitModel() {

    }

    public HealthUnitModel(int unit_id, int id, String title, String
            lat, String lon, String open, String description) {

        this.id = id;
        this.title = title;
        this.lat = lat;
        this.lon = lon;
        this.open = open;
        this.description = description;
        this.unit_id = unit_id;


    }

}
