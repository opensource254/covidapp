package app.azim.opensource254.covidkenya.activities;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.HealthUnitsAdapter;
import app.azim.opensource254.covidkenya.adapter.SituationRecyclerAdapter;
import app.azim.opensource254.covidkenya.api.publicdata.ApiServices;
import app.azim.opensource254.covidkenya.api.publicdata.CoronaNinjaInstance;
import app.azim.opensource254.covidkenya.models.SituationModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SituationsFragment extends Fragment  {

    private SituationRecyclerAdapter mrecyclerAdapter;
    private RecyclerView situationRecyclerView;
    private ProgressBar progressBar;
    ApiServices service;
    CompositeDisposable disposable;
    List<SituationModel> situationModelList;
    SituationModel situationModel;

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_situation, container, false);

        //init the api
        Retrofit mretrofit = CoronaNinjaInstance.getNinjaRetrofitInstance();
        service =  mretrofit.create(ApiServices.class);
        disposable = new CompositeDisposable();

        situationModelList = new ArrayList<>();

        //view
        progressBar = v.findViewById(R.id.progress_bar);
        situationRecyclerView = v.findViewById(R.id.recycler_situation);

        situationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        situationRecyclerView.setHasFixedSize(true);

        Object response = this.getArguments().getSerializable("response");

        situationModel = jsonData(response);

        Log.d("cilo2", ""+situationModel.cases);
        situationModelList.add(situationModel);

        mrecyclerAdapter = new SituationRecyclerAdapter(situationModelList, getContext());
        situationRecyclerView.setAdapter(mrecyclerAdapter);

        situationRecyclerView.setVisibility(View.VISIBLE);

        //fetchData();
        return v;
    }

    private void fetchData() {
        CoronaNinjaInstance.getApiNinjaService().getCountryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Object response) {
                        Log.d("cilo", ""+response);
                        situationModel = jsonData(response);

                        Log.d("cilo2", ""+situationModel.cases);
                        situationModelList.add(situationModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),
                                "Error failed to fetch situation data network error",
                                Toast.LENGTH_SHORT).show();
                        // System.out.println("response  Error  "+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mrecyclerAdapter = new SituationRecyclerAdapter(situationModelList, getContext());
                        situationRecyclerView.setAdapter(mrecyclerAdapter);

                        situationRecyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private SituationModel jsonData(Object response){
        try {
            JSONObject countryData = new JSONObject(new Gson().toJson(response));
            Log.d("cilo", ""+countryData);

            String updated = countryData.getString("updated");
            Log.d("cilo", ""+updated);

            String cases = countryData.getString("cases");
            Log.d("cilo", ""+cases);

            String todayCases = countryData.getString("todayCases");
            Log.d("cilo", ""+updated+", "+cases+", "+todayCases);

            String deaths = countryData.getString("deaths");
            String todayDeaths = countryData.getString("todayDeaths");
            String recovered = countryData.getString("recovered");
            String active = countryData.getString("active");
            String critical = countryData.getString("critical");
            String casesPerOneMillion = countryData.getString("casesPerOneMillion");
            String deathsPerOneMillion = countryData.getString("deathsPerOneMillion");
            String tests = countryData.getString("tests");
            String testsPerOneMillion = countryData.getString("testsPerOneMillion");

            situationModel =
                    new SituationModel(0,cases,todayCases,deaths,todayDeaths,
                            recovered,active,critical,casesPerOneMillion,
                            deathsPerOneMillion,tests,testsPerOneMillion);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return situationModel;
    }

//    @Override
//    public void onStop() {
//        disposable.clear();
//        super.onStop();
//    }
}
