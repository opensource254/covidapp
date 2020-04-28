package app.azim.opensource254.covidkenya.models;

import java.util.List;

public class TweetResponse {
    List<Tweet> tweets;

    public TweetResponse() {
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public String toString() {
        return "TweetResponse{" +
                "tweets=" + tweets +
                '}';
    }
}
