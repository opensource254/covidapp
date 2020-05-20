package app.azim.opensource254.covidkenya.models;


public class AlertsModel {
    public int id;
    public String title;
    public String detail;

    public AlertsModel() {
    }



    public AlertsModel(int i, int id, String title, String detail) {

        this.id = id;
        this.title = title;
        this.detail = detail;

    }
}
