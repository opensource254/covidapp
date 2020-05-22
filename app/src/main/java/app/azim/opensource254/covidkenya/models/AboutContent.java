package app.azim.opensource254.covidkenya.models;

import android.os.Parcel;
import android.os.Parcelable;

public class AboutContent implements Parcelable {

    private  String content;

    public AboutContent() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    protected AboutContent(Parcel in) {
        content = in.readString();
    }

    public static final Creator<AboutContent> CREATOR = new Creator<AboutContent>() {
        @Override
        public AboutContent createFromParcel(Parcel in) {
            return new AboutContent(in);
        }

        @Override
        public AboutContent[] newArray(int size) {
            return new AboutContent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
    }
}
