package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import app.azim.opensource254.covidkenya.R;

import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SymptomActivity extends AppCompatActivity implements View.OnClickListener {

    ListView mSymptoms;
    Button mshare, mclear;


    List<String> symptomsSelectionList;
    ArrayAdapter<String> symptomAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //setting dark text and white ontouch bottom ui
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        } else {
            //for lollipop and below use default dark theme
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        // setting up main toolbar
        Toolbar mtoolbar = findViewById(R.id.symptoms_tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Symptoms");

        String[] arraySymptoms
                = {"Fever","Abdominal Pain","Chills","Cough,Diarrhea," +
                "Difficulty breathing(not severe)," +
                "Headache","Soar throat","Vomiting"};

        mSymptoms = findViewById(R.id.symptoms_list);
        mshare = findViewById(R.id.btn_symptom_share);
        mclear = findViewById(R.id.btn_symptom_clear);
        mclear.setOnClickListener(this);

        symptomAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,arraySymptoms);
        mSymptoms.setAdapter(symptomAdapter);


    }

    //setting navigate up button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_symptom_share) {
            symptomsSelectionList = new ArrayList<>();
            SparseBooleanArray itemChecked = mSymptoms.getCheckedItemPositions();
            for (int i = 0;i<itemChecked.size(); i++) {
                int key = itemChecked.keyAt(i);
                boolean value = itemChecked.get(key);
                if (value)
                    symptomsSelectionList.add(mSymptoms.getItemAtPosition(key).toString());

            }

        }
    }
}
