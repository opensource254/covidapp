package app.azim.opensource254.covidkenya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TipsActivity extends AppCompatActivity {

    private static final String URL = "https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/androidweb.php";
    private List<TipsData> tipsData;
    private TipsAdapter adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        tipsData = new ArrayList<>();
        adapter = new TipsAdapter(tipsData, getApplicationContext());
        getURLData();
    }

    public void getURLData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsnObj = jsonArray.getJSONObject(i);

                        TipsData tips = new TipsData(jsnObj.getString("imageurl"));
                        tipsData.add(tips);
                    }
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TipsActivity.this,"An Error occurred",Toast.LENGTH_LONG).show();
            }
    });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
