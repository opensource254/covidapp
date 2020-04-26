package app.azim.opensource254.covidkenya.models;

public class TipsData {
    private String tipId;
    private String title;
    private String detail;
    private String image;


    public String getTipId(){ return  tipId;}

    public String getTitle() {
        return title;
    }

    public String getDetail(){ return detail;}

    public String getImage() {
        return image;
    }

    public TipsData(String tipId,String title,String detail,String image) {
        this.tipId = tipId;
        this.title = title;
        this.detail = detail;
        this.image = image;
    }
}
