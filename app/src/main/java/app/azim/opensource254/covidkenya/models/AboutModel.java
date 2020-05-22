package app.azim.opensource254.covidkenya.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AboutModel implements Parcelable {

    private String title;
    private String content;

    public AboutModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
