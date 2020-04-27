package app.azim.opensource254.covidkenya.models;

import com.google.gson.annotations.SerializedName;

public class Alerts {
    @SerializedName("title")
    private String title;
    @SerializedName("time")
    private String time;
    @SerializedName("detail")
    private String detail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }



    @Override
    public String toString() {
        return "Alerts{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

}
