package app.azim.opensource254.covidkenya.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.CountiesAdapter;
import app.azim.opensource254.covidkenya.adapter.TipsAdapter;
import app.azim.opensource254.covidkenya.models.CountiesData;
import app.azim.opensource254.covidkenya.models.TipsData;
import io.reactivex.disposables.CompositeDisposable;

public class CountiesStats extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CountiesAdapter adapter;
    private List<CountiesData> countiesData;

    private static final String URL_DAT = "https://api.covid19kenya.site/api/v1/counties";

    //custom  top toolbar
    private Toolbar mtoolbar;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counties_stats);

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
         mtoolbar = findViewById(R.id.stats_tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Counties Statistics");

        //for searching a county
        editTextSearch = findViewById(R.id.searchEdittext);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


        recyclerView = findViewById(R.id.counties_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        countiesData = new ArrayList<>();
        loadURLs();
    }

    //filtering function for searching a county
    public void filter(String e){
        ArrayList<CountiesData> filteredlist = new ArrayList<>();

        for(CountiesData item : countiesData){
            if(item.getCounty().toLowerCase().contains(e.toLowerCase())){
                filteredlist.add(item);
            }
        }
        adapter.filterList(filteredlist);
    }


    //fetching the county statistics
    private void loadURLs(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DAT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    RecyclerView recyclerView = findViewById(R.id.counties_recycler_view);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        CountiesData data = new CountiesData(jobj.getString("county"),jobj.getString("cases")
                                ,jobj.getString("recoveries"),jobj.getString("tests"),jobj.getString("deaths"));
                        countiesData.add(data);
                    }
                    adapter = new CountiesAdapter(countiesData, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CountiesStats.this,"Error: "+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue  = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //setting navigate up button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
