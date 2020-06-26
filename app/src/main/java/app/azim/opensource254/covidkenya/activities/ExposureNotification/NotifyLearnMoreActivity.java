package app.azim.opensource254.covidkenya.activities.ExposureNotification;

import androidx.appcompat.app.AppCompatActivity;
import app.azim.opensource254.covidkenya.R;

import android.os.Bundle;
import android.view.View;

public class NotifyLearnMoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_learn_more);

        View upButton = findViewById(android.R.id.home);
        upButton.setContentDescription(getString(R.string.navigate_up));
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotifyLearnMoreActivity.this.onBackPressed();
            }
        });
    }
}