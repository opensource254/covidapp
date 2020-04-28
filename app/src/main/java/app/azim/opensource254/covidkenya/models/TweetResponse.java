package app.azim.opensource254.covidkenya.models;

import java.util.List;

public class TweetResponse {
    List<NewsTweet> tweets;

    public TweetResponse() {
    }

    public List<NewsTweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<NewsTweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public String toString() {
        return "TweetResponse{" +
                "tweets=" + tweets +
                '}';
    }
}
