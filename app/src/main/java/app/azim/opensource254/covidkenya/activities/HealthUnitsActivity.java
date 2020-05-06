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
import android.widget.Toast;

import com.google.gson.Gson;

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
    ApiServicesInterface mservice;
    CompositeDisposable disposable;
    List<HealthUnitModel> healthUnitModelList;
    HealthUnitModel healthUnitModel;
    Object response;

    Bundle bundle = new Bundle();

    //Bundle bundle;

    HealthUnitsActivity healthUnitsActivity;

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
        healthRecyclerView = findViewById(R.id.health_units_recycler_view);
        healthRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        healthRecyclerView.setHasFixedSize(true);


        try {

        response = this.bundle.getSerializable("response");
        } catch (Exception e) {
            Log.d(mHealthunitsActivity, "Bundle error: " + e.getMessage());
            response = null;
        }
        healthUnitModel = jsonData(response);

      //  Log.d(mHealthunitsActivity, "" + HealthUnitModel.title);
        healthUnitModelList.add(healthUnitModel);

        mrecyclerAdapter = new HealthUnitsAdapter(healthUnitModelList, this);
        healthRecyclerView.setAdapter(mrecyclerAdapter);
        healthRecyclerView.setVisibility(View.VISIBLE);

        fetchDataForHealth();

        healthUnitsActivity = new HealthUnitsActivity();



    }

    private HealthUnitModel jsonData(Object response) {
        healthUnitModel = new HealthUnitModel();
        try {
            JSONObject healthData = new JSONObject(new Gson().toJson(response));
            Log.d(mHealthunitsActivity, "" + healthData);

           // int unit_id = healthData.getInt("unit_id");
            int id = healthData.getInt("id");
            String title = healthData.getString("title");
            String lat = healthData.getString("lat");
            String lon = healthData.getString("lon");
            String open = healthData.getString("open");
            String description = healthData.getString("description");



            healthUnitModel =
                    new HealthUnitModel(0,id,title, lat, lon, open,
                            description);

        } catch (JSONException e) {
            Log.d(mHealthunitsActivity, "Json error: " + e.getMessage());
        }
        return healthUnitModel;
    }


    private void fetchDataForHealth() {
        ServiceInstance.getApiService().getHealthUnits()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                        //progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(Object response) {
                        Log.d(mHealthunitsActivity, ""+response);
                        bundle.putSerializable("response", (Serializable) response);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Error failed to fetch data", Toast.LENGTH_SHORT).show();
                        System.out.println("response  Error  "+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // progressBar.setVisibility(View.GONE);
                    }
                });
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
