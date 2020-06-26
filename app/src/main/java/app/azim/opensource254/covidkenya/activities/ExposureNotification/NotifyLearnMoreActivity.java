package app.azim.opensource254.covidkenya.activities.ExposureNotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import app.azim.opensource254.covidkenya.R;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class NotifyLearnMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_learn_more);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //setting dark text and white ontouch bottom ui
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            //for lollipop and below use default dark theme
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }


        View upButton = findViewById(android.R.id.home);
        upButton.setContentDescription(getString(R.string.navigate_up));
        upButton.setOnClickListener(v -> NotifyLearnMoreActivity.this.onBackPressed());
    }
}