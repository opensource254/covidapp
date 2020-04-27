package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.models.TipsAdapter;
import app.azim.opensource254.covidkenya.models.TipsData;

public class TipsActivity extends AppCompatActivity implements TipsAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private TipsAdapter adapter;
    private List<TipsData> tipsData;

    private static final String URL_DATA = "https://api.github.com/search/users?q=language:android+location:kenya";
    private static final String URL_DAT = "https://jsonplaceholder.typicode.com/photos";

    public static final String EXTRA_IMAGE_URL= "imageUrl";
    public static final String EXTRA_DETAILS = "details";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        tipsData = new ArrayList<>();
        loadURLs();
    }

    private void loadURLs(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        TipsData tipsData1 = new TipsData(jobj.getString("id"),jobj.getString("login")
                                ,jobj.getString("html_url"),jobj.getString("avatar_url"));
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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,TipsDetails.class);
        TipsData clickedItem = tipsData.get(position);
        detailIntent.putExtra(EXTRA_IMAGE_URL, clickedItem.getImage());
        detailIntent.putExtra(EXTRA_DETAILS, clickedItem.getDetail());

        startActivity(detailIntent);
    }
}