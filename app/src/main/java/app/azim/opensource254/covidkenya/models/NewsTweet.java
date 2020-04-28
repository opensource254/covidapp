package app.azim.opensource254.covidkenya.models;

public class NewsTweet {
      String timestamp;
    String   img_urls;
    String    _id;
    String   text;
    String   username;
    String   screen_name;
    int          _v;

    public NewsTweet() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImg_urls() {
        return img_urls;
    }

    public void setImg_urls(String img_urls) {
        this.img_urls = img_urls;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public int get_v() {
        return _v;
    }

    public void set_v(int _v) {
        this._v = _v;
    }

    @Override
    public String toString() {
        return "NewsTweet{" +
                "timestamp='" + timestamp + '\'' +
                ", img_urls='" + img_urls + '\'' +
                ", _id='" + _id + '\'' +
                ", text='" + text + '\'' +
                ", username='" + username + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", _v=" + _v +
                '}';
    }
}
