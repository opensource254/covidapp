package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.HealthUnitsAdapter;
import app.azim.opensource254.covidkenya.api.privatedata.ServiceInstance;
import app.azim.opensource254.covidkenya.api.privatedata.ApiServicesInterface;
import app.azim.opensource254.covidkenya.models.HealthUnitModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static app.azim.opensource254.covidkenya.activities.MainActivity.mMainActivity;

public class HealthUnitsActivity extends AppCompatActivity {
   final  static  String mHealthunitsActivity = "HealthUnitsActivity";
    private Toolbar mtoolbar;
    private HealthUnitsAdapter mrecyclerAdapter;
    private RecyclerView healthRecyclerView;
    private ProgressBar progressBar;
    ApiServicesInterface mservice;
    CompositeDisposable disposable;
    List<HealthUnitModel> healthUnitModelList;
    HealthUnitModel healthUnitModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_units);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //setting dark text and white ontouch bottom ui
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        } else {
            //for lollipop and below use default dark theme
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        //setting up main toolbar
        mtoolbar = findViewById(R.id.health_units_tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Health Centers");

        //init the api
        Retrofit mretrofit = ServiceInstance.getRetrofitInstance();
        mservice = mretrofit.create(ApiServicesInterface.class);
        disposable = new CompositeDisposable();
        healthUnitModelList = new ArrayList<>();

        //view
        progressBar = findViewById(R.id.progress_bar);

        healthRecyclerView = findViewById(R.id.health_units_recycler_view);
        healthRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        healthRecyclerView.setHasFixedSize(true);

        fetchDataForHealth();
    }

    private void fetchDataForHealth() {
        ServiceInstance.getApiService().getHealthUnits()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(Object response) {
                        Log.d(mHealthunitsActivity, ""+response);
                        healthUnitModelList = jsonData(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Error failed to fetch data", Toast.LENGTH_SHORT).show();
                        System.out.println("response  Error  "+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mrecyclerAdapter = new HealthUnitsAdapter(healthUnitModelList, getApplicationContext());
                        healthRecyclerView.setAdapter(mrecyclerAdapter);

                        healthRecyclerView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private ArrayList<HealthUnitModel> jsonData(Object response) {
        healthUnitModel = new HealthUnitModel();
        try {
            JSONObject healthResponse = new JSONObject(new Gson().toJson(response));
            JSONArray healthDataArray = healthResponse.getJSONArray("data");
            Log.d(mHealthunitsActivity, "" + healthDataArray);

            for (int i=0; i < healthDataArray.length();i++){

                JSONObject healthData = healthDataArray.getJSONObject(i);
                Log.d(mHealthunitsActivity, "" + healthData);

                int id = healthData.getInt("id");
                String title = healthData.getString("title");
                String lat = healthData.getString("lat");
                String lon = healthData.getString("lon");
                String open = healthData.getString("open");
                String description = healthData.getString("description");

                healthUnitModel =
                        new HealthUnitModel(0,id,title, lat, lon, open,
                                description);

                healthUnitModelList.add(healthUnitModel);
            }

        } catch (JSONException e) {
            Log.d(mHealthunitsActivity, "Json error: " + e.getMessage());
        }
        return (ArrayList<HealthUnitModel>) healthUnitModelList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    //setting navigate up button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.health_units_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //mrecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
