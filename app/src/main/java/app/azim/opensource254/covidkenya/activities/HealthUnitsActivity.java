package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.HealthUnitsAdapter;
import app.azim.opensource254.covidkenya.api.privatedata.ServiceInstance;
import app.azim.opensource254.covidkenya.api.privatedata.ApiServicesInterface;
import app.azim.opensource254.covidkenya.models.HealthUnitModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HealthUnitsActivity extends AppCompatActivity {

    private Toolbar mtoolbar;
    private HealthUnitsAdapter mrecyclerAdapter;
    private RecyclerView healthRecyclerView;
    ApiServicesInterface mservice;
    CompositeDisposable mcompositeDisposable = new CompositeDisposable();
    List<HealthUnitModel> healthUnitModelList;


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


//view
        healthRecyclerView = findViewById(R.id.health_units_recycler_view);
        //recyclerAdapter = new HealthUnitsRecyclerAdapter(healthUnitsList);
        healthRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        healthRecyclerView.setHasFixedSize(true);
        healthUnitModelList = new ArrayList<>();


        fetchData();


    }

    private void fetchData() {

        mcompositeDisposable.add(ServiceInstance.getApiService().getHealthUnits()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    mrecyclerAdapter = new HealthUnitsAdapter(healthUnitModelList);
                    healthRecyclerView.setAdapter(mrecyclerAdapter);

                }, error -> {
                    Toast.makeText(getApplicationContext(), "Error failed to fetch data network error", Toast.LENGTH_SHORT).show();
                    // System.out.println("response  Error  "+ t.getMessage());
                }));


    }


    @Override
    public void onStop() {
        mcompositeDisposable.clear();
        super.onStop();

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
                //adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
