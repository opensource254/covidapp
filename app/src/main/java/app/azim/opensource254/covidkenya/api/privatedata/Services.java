package app.azim.opensource254.covidkenya.api.privatedata;



import app.azim.opensource254.covidkenya.models.TweetResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {

    @GET("/api/v1/tweets")
    Call<TweetResponse> getNews();

}