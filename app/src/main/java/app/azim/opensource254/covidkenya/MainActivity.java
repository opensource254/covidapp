package app.azim.opensource254.covidkenya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {


    private BottomAppBar mBottomAppbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomAppbar = findViewById(R.id.bottom_app_bar);
         setSupportActionBar(mBottomAppbar);    }

    //creating option menu for bottom appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
      //  getMenuInflater().inflate(R.menu.bottom_app_bar_menu,menu);
        return true;
    }

}
