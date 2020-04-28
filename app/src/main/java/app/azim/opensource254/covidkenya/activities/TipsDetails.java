package app.azim.opensource254.covidkenya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import app.azim.opensource254.covidkenya.R;

import static app.azim.opensource254.covidkenya.activities.TipsActivity.EXTRA_DETAILS;
import static app.azim.opensource254.covidkenya.activities.TipsActivity.EXTRA_IMAGE_URL;


public class TipsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_details);


        Intent intent = getIntent();
        String details = intent.getStringExtra(EXTRA_IMAGE_URL);
        String image = intent.getStringExtra(EXTRA_IMAGE_URL);

        TextView text = findViewById(R.id.text_tips_title);
        ImageView imageView = findViewById(R.id.img_tips);

         //setting up image and text for tips detail activity
        Picasso.get().load(image).fit().centerInside().into(imageView);
        text.setText(details);
    }
}
