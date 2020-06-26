package app.azim.opensource254.covidkenya.activities.ExposureNotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.activities.HomeFragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class ExposureNotificationActivity extends AppCompatActivity {

    private static final String SAVED_INSTANCE_STATE_FRAGMENT_KEY =
            "ExposureNotificationActivity.SAVED_INSTANCE_STATE_FRAGMENT_KEY";

    public static final String EXPOSURE_FRAGMENT_TAG =
            "ExposureNotificationActivity.EXPOSURE_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposure_notification);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //setting dark text and white ontouch bottom ui
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            //for lollipop and below use default dark theme
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }


        if (savedInstanceState != null) {
            // If this is a configuration change such as rotation, restore the HomeFragment that was
            // previously saved in onSaveInstanceState
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(
                    R.id.home_fragment,
                    Objects.requireNonNull(
                            getSupportFragmentManager()
                                    .getFragment(savedInstanceState, SAVED_INSTANCE_STATE_FRAGMENT_KEY)),
                    EXPOSURE_FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {
            // This is a fresh launch.
//            ExposureNotificationSharedPreferences prefs = new ExposureNotificationSharedPreferences(this);
//            if (prefs.getOnboardedState() == OnboardingStatus.UNKNOWN) {
//                // If the user has not seen the onboarding flow, show it when the app resumes.
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.home_fragment, new OnboardingStartFragment(), HOME_FRAGMENT_TAG)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commitNow();
//            } else {
                // Otherwise transition to the home UI.
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Intent intent = getIntent();
                ExposureFragment exposureFragment;
                    // If we're started by the exposure notification we should show the Exposures tab,
                    // otherwise
                    // show the default.
                    //homeFragment = HomeFragment.newInstance(HomeFragment.TAB_EXPOSURES);
                    exposureFragment = ExposureFragment.newInstance();

                fragmentTransaction.replace(R.id.home_fragment, exposureFragment, EXPOSURE_FRAGMENT_TAG);
                fragmentTransaction.commit();
           // }
        }

    }
}