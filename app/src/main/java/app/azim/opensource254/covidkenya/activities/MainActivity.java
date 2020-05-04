package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.SituationRecyclerAdapter;
import app.azim.opensource254.covidkenya.api.publicdata.ApiServices;
import app.azim.opensource254.covidkenya.api.publicdata.CoronaNinjaInstance;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    final static String mMainActivity = "MainActivity";
    CompositeDisposable disposable;
    Bundle bundle;

    SituationsFragment situationsFragment;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        disposable = new CompositeDisposable();
        bundle = new Bundle();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //setting dark text and white ontouch bottom ui
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        } else {
            //for lollipop and below use default dark theme
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        CardView mnews = findViewById(R.id.card_news);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //setting up home fragment to be default
        situationsFragment = new SituationsFragment();
        fetchDataForCountry();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }

    private void fetchDataForCountry() {
        CoronaNinjaInstance.getApiNinjaService().getCountryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                        //progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(Object response) {
                        Log.d(mMainActivity, ""+response);
                        bundle.putSerializable("response", (Serializable) response);
                        situationsFragment.setArguments(bundle);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Error failed to fetch data", Toast.LENGTH_SHORT).show();
                        System.out.println("response  Error  "+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                       // progressBar.setVisibility(View.GONE);
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
                selectedFragment = situationsFragment;

        }
        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
        return  true;

    };

    @Override
    public void onStop() {
        disposable.clear();
        super.onStop();
    }
}
