package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import app.azim.opensource254.covidkenya.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hiding status bar
        View decorView = getWindow().getDecorView();
        // Hide the status bar and bottom navbar
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        //set status to transparent
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
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
