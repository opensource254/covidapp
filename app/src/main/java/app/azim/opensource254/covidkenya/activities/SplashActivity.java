package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import app.azim.opensource254.covidkenya.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    updateUI(new Intent(SplashActivity.this, MainActivity.class));

    }

    private void updateUI(Intent intent) {
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    next(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private void next(Intent intent) {

        startActivity(intent);
        finish();
    }
}
