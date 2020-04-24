package app.azim.opensource254.covidkenya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import app.azim.opensource254.covidkenya.api.RetrofitServiceInstance;
import app.azim.opensource254.covidkenya.models.Country;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchDataForCountry("kenya");

        CardView mnews = findViewById(R.id.card_news);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //setting up home fragment to be default
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        }

    private void fetchDataForCountry(String country) {
        RetrofitServiceInstance.getApiService().getCountryData().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                //progressDialog.show();
                Country  selectCountryData = (Country) response.body();
                System.out.println("Response "+ selectCountryData.toString());

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error failed to fetch data", Toast.LENGTH_SHORT).show();
                System.out.println("response  Error  "+ t.getMessage());
            }
        });
    }


    //handling bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {

            case R.id.navigation_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.navigation_alerts:
                selectedFragment = new AlertFragment();
                break;
            case R.id.navigation_situations:
                selectedFragment = new UpdatesFragment();

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
        return  true;

    };

}
