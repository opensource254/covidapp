package app.azim.opensource254.covidkenya.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import app.azim.opensource254.covidkenya.R;

import static app.azim.opensource254.covidkenya.activities.TipsActivity.EXTRA_DETAILS;
import static app.azim.opensource254.covidkenya.activities.TipsActivity.EXTRA_IMAGE_URL;
import static app.azim.opensource254.covidkenya.activities.TipsActivity.EXTRA_TITLE;


public class TipsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_details);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //setting dark text and white ontouch bottom ui
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        } else {
            //for lollipop and below use default dark theme
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }


        Intent intent = getIntent();
        String details = intent.getStringExtra(EXTRA_DETAILS);
        String image = intent.getStringExtra(EXTRA_IMAGE_URL);
        String title = intent.getStringExtra(EXTRA_TITLE);

        TextView text_title = findViewById(R.id.text_tips_title);
        TextView text_details = findViewById(R.id.text_tips_detail);
        ImageView imageView = findViewById(R.id.img_tips);

         //setting up image and text for tips detail activity
        Picasso.get().load(image).fit().centerInside().into(imageView);
        text_title.setText(title);
        text_details.setText(details);
    }
}
