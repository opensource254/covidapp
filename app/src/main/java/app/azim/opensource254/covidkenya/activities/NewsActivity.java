package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.NewsRecyclerAdapter;
import app.azim.opensource254.covidkenya.models.Tweet;

public class NewsActivity extends AppCompatActivity {
    //custom  top toolbar
    private Toolbar mtoolbar;

    private RecyclerView recyclerView;
    private NewsRecyclerAdapter recyclerAdapter;

    List<Tweet> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //setting up main toolbar
        mtoolbar = findViewById(R.id.news_tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newsList = new ArrayList<Tweet>();

        getNewsList();

        recyclerView = findViewById(R.id.news_recycler_view);
        recyclerAdapter = new NewsRecyclerAdapter(newsList);
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private List<Tweet> getNewsList(){
        Tweet tweet = new Tweet();
        tweet.setId(1);
        tweet.setHead("Ministry Of Health Kenya");
        tweet.setHandle("@mohkenya");
        tweet.setTweet("the government has …...");
        tweet.setTime("20/4/2020");
        tweet.setImage("https://tweet/image");
        newsList.add(tweet);

        Tweet tweet1 = new Tweet();
        tweet1.setId(2);
        tweet1.setHead("Ministry Of Health Kenya");
        tweet1.setHandle("@mohkenya");
        tweet1.setTweet("the senate has …...");
        tweet1.setTime("20/4/2020");
        tweet1.setImage("https://tweet/image");
        newsList.add(tweet1);

        Tweet tweet2 = new Tweet();
        tweet2.setId(3);
        tweet2.setHead("Ministry Of Health Kenya");
        tweet2.setHandle("@mohkenya");
        tweet2.setTweet("Parliament has …...");
        tweet2.setTime("20/4/2020");
        tweet2.setImage("https://tweet/image");
        newsList.add(tweet2);

        Tweet tweet3 = new Tweet();
        tweet3.setId(4);
        tweet3.setHead("Ministry Of Health Kenya");
        tweet3.setHandle("@mohkenya");
        tweet3.setTweet("the Nairobi County Assembly has …...");
        tweet3.setTime("20/4/2020");
        tweet3.setImage("https://tweet/image");
        newsList.add(tweet3);
        return newsList;
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
