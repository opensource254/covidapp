package app.azim.opensource254.covidkenya.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
//import app.azim.opensource254.covidkenya.adapter.HealthUnitsRecyclerAdapter;
import app.azim.opensource254.covidkenya.adapter.AlertsRecylerAdapter;
import app.azim.opensource254.covidkenya.api.privatedata.ApiServicesInterface;
import app.azim.opensource254.covidkenya.api.privatedata.ServiceInstance;
import app.azim.opensource254.covidkenya.api.publicdata.ApiServices;
import app.azim.opensource254.covidkenya.models.AlertsModel;
import app.azim.opensource254.covidkenya.models.HealthUnitModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AlertFragment extends Fragment {

    final  static  String mAlertFragment = "AlertFragment";
    private AlertsRecylerAdapter mrecyclerAdapter;
    private RecyclerView alertsRecyclerView;
    ApiServicesInterface service;
    CompositeDisposable disposable;
    List<AlertsModel> alertsModelList;
    AlertsModel alertModel;
    Object response;

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert, container, false);

        alertsModelList = new ArrayList<>();

        //init the api
        Retrofit mretrofit = ServiceInstance.getRetrofitInstance();
        service = mretrofit.create(ApiServicesInterface.class);
        disposable = new CompositeDisposable();
        alertsRecyclerView = view.findViewById(R.id.alert_recycler_view);
        alertsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        alertsRecyclerView.setHasFixedSize(true);


        fetchDataForAlert();

        return view;
    }

    private void fetchDataForAlert() {
        ServiceInstance.getApiService().getAlerts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable di) {
                        disposable.add(di);
                    }

                    @Override
                    public void onNext(Object response) {
                        Log.d(mAlertFragment, "" + response);
                        alertsModelList = jsonData(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText( getContext(), "Error failed to fetch data", Toast.LENGTH_SHORT).show();
                        System.out.println("response  Error  " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mrecyclerAdapter = new AlertsRecylerAdapter(alertsModelList,  getContext());
                        alertsRecyclerView.setAdapter(mrecyclerAdapter);

                        alertsRecyclerView.setVisibility(View.VISIBLE);
                    }
                });

    }

        private ArrayList<AlertsModel> jsonData(Object response) {
            alertModel = new AlertsModel();
            try {
                JSONObject alertsResponse = new JSONObject(new Gson().toJson(response));
                JSONArray alertsDataArray = alertsResponse.getJSONArray("data");
                Log.d(mAlertFragment, "" + alertsDataArray);

                for (int i=0; i < alertsDataArray.length();i++){

                    JSONObject alertsData = alertsDataArray.getJSONObject(i);
                    Log.d(mAlertFragment, "" + alertsData);

                    int id = alertsData.getInt("id");
                    String title = alertsData.getString("title");
                    String detail = alertsData.getString("detail");


                    alertModel =
                            new AlertsModel(0,id,title, detail);

                    alertsModelList.add(alertModel);
                }

            } catch (JSONException e) {
                Log.d(mAlertFragment, "Json error: " + e.getMessage());
            }
            return (ArrayList<AlertsModel>) alertsModelList;
        }


}

