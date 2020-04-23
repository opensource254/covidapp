package app.azim.opensource254.covidkenya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView mnews = findViewById(R.id.card_news);
        //starting News activity
        mnews.setOnClickListener(this::card_btn_news);

        }

    //onclick  for news card
    public void  card_btn_news(View v) {
        startActivity(new Intent(MainActivity.this, NewsActivity.class));
    }

    //handling bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                // return true;
            case R.id.navigation_alerts:
                //   startActivity(new Intent(MainActivity.this, AlertsActivity.class));
                //   finish();
                return true;
            case R.id.navigation_updates:
                //  startActivity(new Intent(MainActivity.this, updatesActivity.class));
                //  finish();
        }
        return false;
    };

}
