package app.azim.opensource254.covidkenya.models;

public class TipsData {
    private String title;
    private String image_url;


    public String getDetails() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public TipsData(String title,String avatar_url) {
        this.title = title;
        this.image_url = avatar_url;

    }
}
