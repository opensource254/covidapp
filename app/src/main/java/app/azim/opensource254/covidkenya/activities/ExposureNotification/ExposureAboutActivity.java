/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package app.azim.opensource254.covidkenya.activities.ExposureNotification;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import app.azim.opensource254.covidkenya.R;

/**
 * Activity for information about exposure notifications in general.
 */
public class ExposureAboutActivity extends AppCompatActivity {

  private static final String TAG = "ExposureAboutActivity";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      //setting dark text and white ontouch bottom ui
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
      getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
      getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
    } else {
      //for lollipop and below use default dark theme
      getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }


    setContentView(R.layout.activity_exposure_about);

    View upButton = findViewById(android.R.id.home);
    upButton.setContentDescription(getString(R.string.navigate_up));
    upButton.setOnClickListener((v) -> onBackPressed());

    Button settingsButton = findViewById(R.id.exposure_about_settings_button);
    settingsButton.setOnClickListener(v -> settingsAction());
  }

  private void settingsAction() {
//    Intent intent = new Intent(ACTION_EXPOSURE_NOTIFICATION_SETTINGS);
//    startActivity(intent);
  }

}
