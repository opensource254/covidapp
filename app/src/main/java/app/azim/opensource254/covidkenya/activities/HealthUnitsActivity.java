package app.azim.opensource254.covidkenya.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.HealthUnitsRecyclerAdapter;
import app.azim.opensource254.covidkenya.models.HealthUnit;

public class HealthUnitsActivity extends AppCompatActivity {
    //custom  top toolbar
    private Toolbar mtoolbar;

    private RecyclerView recyclerView;
    private HealthUnitsRecyclerAdapter recyclerAdapter;

    List<HealthUnit> healthUnitsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_units);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //setting dark text and white ontouch bottom ui
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        } else {
            //for lollipop and below use default dark theme
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }



        //setting up main toolbar
        mtoolbar = findViewById(R.id.health_units_tool_bar);
        setSupportActionBar(mtoolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        healthUnitsList = new ArrayList<HealthUnit>();

        getHealthUnitsList();

        recyclerView = findViewById(R.id.health_units_recycler_view);
        recyclerAdapter = new HealthUnitsRecyclerAdapter(healthUnitsList);
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private List<HealthUnit> getHealthUnitsList(){
        HealthUnit healthUnit = new HealthUnit();
        healthUnit.setOpen("open");
        healthUnit.setTitle("Aga Khan");
        healthUnit.setDescription("this hospital is located in...");
        healthUnitsList.add(healthUnit);

        HealthUnit healthUnit1 = new HealthUnit();
        healthUnit1.setOpen("open");
        healthUnit1.setTitle("Kenyatta National Referral Hospital");
        healthUnit1.setDescription("this hospital is located in...");
        healthUnitsList.add(healthUnit1);

        HealthUnit healthUnit2 = new HealthUnit();
        healthUnit2.setOpen("closed");
        healthUnit2.setTitle("Mbagathi Referral Hospital");
        healthUnit2.setDescription("this hospital is located in...");
        healthUnitsList.add(healthUnit2);

        HealthUnit healthUnit3 = new HealthUnit();
        healthUnit3.setOpen("open");
        healthUnit3.setTitle("Mama Lucy");
        healthUnit3.setDescription("this hospital is located in...");
        healthUnitsList.add(healthUnit3);
        return healthUnitsList;
    }

    //setting navigate up button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.health_units_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
