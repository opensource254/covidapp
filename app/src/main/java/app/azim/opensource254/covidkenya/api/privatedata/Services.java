package app.azim.opensource254.covidkenya.api.privatedata;



import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {

    @GET("/api/v1/tweets")
    Call<Object> getNews();

}
