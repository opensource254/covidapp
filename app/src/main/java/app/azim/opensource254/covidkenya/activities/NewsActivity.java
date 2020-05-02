package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.NewsRecyclerAdapter;
import app.azim.opensource254.covidkenya.api.privatedata.ServiceInstance;
import app.azim.opensource254.covidkenya.api.publicdata.RetrofitServiceInstance;
import app.azim.opensource254.covidkenya.models.NewsTweet;
import app.azim.opensource254.covidkenya.models.Tweet;
import app.azim.opensource254.covidkenya.models.TweetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    //custom  top toolbar
    private Toolbar mtoolbar;

    private RecyclerView recyclerView;
    private NewsRecyclerAdapter recyclerAdapter;

    List<NewsTweet> newsTweetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

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
        mtoolbar = findViewById(R.id.news_tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newsTweetList = new ArrayList<>();

        getNewsList();

        recyclerView = findViewById(R.id.news_recycler_view);


    }

    private void getNewsList() {
            ServiceInstance.getApiService().getNews().enqueue(new Callback<TweetResponse>() {
                @Override
                public void onResponse(Call<TweetResponse> call, Response<TweetResponse> response) {

                    if(response.isSuccessful()) {
                        newsTweetList = response.body().getTweets();
                        recyclerAdapter = new NewsRecyclerAdapter(newsTweetList);
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                }

                @Override
                public void onFailure(Call<TweetResponse> call, Throwable t) {
                    //progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Error failed to fetch data", Toast.LENGTH_SHORT).show();
                    System.out.println("response  Error  "+ t.getMessage());
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
        getMenuInflater().inflate(R.menu.news_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
