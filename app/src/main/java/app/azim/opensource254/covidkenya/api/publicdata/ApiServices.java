package app.azim.opensource254.covidkenya.api.publicdata;



import app.azim.opensource254.covidkenya.models.SituationModel;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {


    @GET("kenya")
    Observable<SituationModel> getCountryData();


}
