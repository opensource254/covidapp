package app.azim.opensource254.covidkenya.models;

import androidx.annotation.NonNull;

public class Tweet {


    private int tweet_id;
    private int id;
    private String head;
    private String handle;
    private String tweet;
    private String time;
    private String image;

    public int getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(int tweet_id) {
        this.tweet_id = tweet_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return "Tweet{" +
                "id ='" + id + '\'' +
                ", head='" + head + '\'' +
                ", handle='" + handle + '\'' +
                ", tweet='" + tweet + '\'' +
                ", time=" + time +
                ", image=" + image +
                '}';
    }
}
