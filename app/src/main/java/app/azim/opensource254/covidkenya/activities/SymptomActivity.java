package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.activities.FirebaseChat.ChatActivity;
import app.azim.opensource254.covidkenya.activities.FirebaseChat.LoginActivity;
import app.azim.opensource254.covidkenya.activities.FirebaseChat.RegisterActivity;
import app.azim.opensource254.covidkenya.activities.FirebaseChat.UsersActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
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


    //setting up menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.symptoms_menu, menu);
        return true;
    }

    //handling item menu clicks on nav bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_login) {
            Intent intent1 = new Intent(this, LoginActivity.class);
            this.startActivity(intent1);
            return true;
        }if (id == R.id.action_register) {
            Intent intent1 = new Intent(this, RegisterActivity.class);
            this.startActivity(intent1);
            return true;
        }if (id == R.id.action_user) {
            Intent intent1 = new Intent(this, UsersActivity.class);
            this.startActivity(intent1);
            return true;
        }if (id == R.id.action_chat) {
            Intent intent1 = new Intent(this, ChatActivity.class);
            this.startActivity(intent1);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }



}
