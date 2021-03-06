package app.azim.opensource254.covidkenya.api.privatedata;


import java.util.List;

import app.azim.opensource254.covidkenya.models.HealthUnitModel;
import app.azim.opensource254.covidkenya.models.TweetResponse;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServicesInterface {

    //using rx java

    @GET("tweets")
    Observable<TweetResponse> getNews();

    @GET("hospitals")
    Observable<Object> getHealthUnits();

    @GET("alerts")
    Observable<Object> getAlerts();


}
