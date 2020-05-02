package app.azim.opensource254.covidkenya.api.publicdata;



import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("/kenya")
    Call<Object> getCountryData();




}
