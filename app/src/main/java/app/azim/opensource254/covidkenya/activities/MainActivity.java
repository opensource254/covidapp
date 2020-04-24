package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.azim.opensource254.covidkenya.R;
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

                Country countryData;

                if(response.isSuccessful()) {

                    String jsonString = response.body().toString();
                    System.out.println("Response "+jsonString);
//                    countryData = new Gson().fromJson(jsonString, Country.class);
//                    System.out.println("Response "+ countryData);

                }

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
