package app.azim.opensource254.covidkenya.activities;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.SituationRecyclerAdapter;
import app.azim.opensource254.covidkenya.api.publicdata.ApiServices;
import app.azim.opensource254.covidkenya.api.publicdata.CoronaNinjaInstance;
import app.azim.opensource254.covidkenya.models.SituationModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SituationsFragment extends Fragment  {


    private SituationRecyclerAdapter mrecyclerAdapter;
    private RecyclerView situationRecyclerView;
    ApiServices service;
    CompositeDisposable mcompositeDisposable = new CompositeDisposable();
    //List<SituationModel> SituationModelList;

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_situation, container, false);

        //init the api
        Retrofit mretrofit = CoronaNinjaInstance.getNinjaRetrofitInstance();
         service =  mretrofit.create(ApiServices.class);



        //view
        situationRecyclerView = v.findViewById(R.id.recycler_situation);
        //recyclerAdapter = new HealthUnitsRecyclerAdapter(healthUnitsList);
       // situationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        situationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        situationRecyclerView.setHasFixedSize(true);


       // SituationModelList = new ArrayList<>();


        fetchData();




        return v;

    }


    private void fetchData() {

        mcompositeDisposable.add(CoronaNinjaInstance.getApiNinjaService().getCountryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<SituationModel>() {

                                @Override
                                public void accept(SituationModel situationModel) throws Exception {

                                    displadata(situationModel);

                                }
                            }
                , error -> {
                   Toast.makeText(getContext(), "Error failed to fetch situation data network error", Toast.LENGTH_SHORT).show();
                    // System.out.println("response  Error  "+ t.getMessage());
                }));


    }

    private void displadata(SituationModel situationModel) {

     //   mrecyclerAdapter = new SituationRecyclerAdapter(this,situationModel);
     //   situationRecyclerView.setAdapter(mrecyclerAdapter);
    }


    @Override
    public void onStop() {
        mcompositeDisposable.clear();
        super.onStop();

    }




}
