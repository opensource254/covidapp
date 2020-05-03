package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
import app.azim.opensource254.covidkenya.adapter.TipsAdapter;
import app.azim.opensource254.covidkenya.models.TipsData;

public class TipsActivity extends AppCompatActivity implements TipsAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private TipsAdapter adapter;
    private List<TipsData> tipsData;
    private ShimmerFrameLayout mShimmerViewContainer;

    private static final String URL_DAT = "https://api.covid19kenya.site/api/v1/tips";

    public static final String EXTRA_IMAGE_URL= "imageUrl";
    public static final String EXTRA_DETAILS = "details";
    //custom  top toolbar
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

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
        mtoolbar = findViewById(R.id.tips_tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tips");

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        tipsData = new ArrayList<>();
        loadURLs();
    }

    private void loadURLs(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DAT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Stopping Shimmer Effect's animation after data is loaded to ListView
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                try {
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        TipsData tipsData1 = new TipsData(jobj.getString("id"),jobj.getString("title")
                                ,jobj.getString("detail"),jobj.getString("thumbnail"));
                        tipsData.add(tipsData1);
                    }
                    adapter = new TipsAdapter(tipsData, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(TipsActivity.this);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TipsActivity.this,"Error: "+error.toString(),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,TipsDetails.class);
        TipsData clickedItem = tipsData.get(position);
        detailIntent.putExtra(EXTRA_IMAGE_URL, clickedItem.getImage());
        detailIntent.putExtra(EXTRA_DETAILS, clickedItem.getDetail());

        startActivity(detailIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

}