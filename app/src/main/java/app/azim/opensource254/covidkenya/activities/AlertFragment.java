package app.azim.opensource254.covidkenya.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.HealthUnitsRecyclerAdapter;
import app.azim.opensource254.covidkenya.models.HealthUnit;

public class AlertFragment extends Fragment {

    List<HealthUnit> healthUnitsList;
    private RecyclerView recyclerView;
    private HealthUnitsRecyclerAdapter recyclerAdapter;

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert,container,false);

        healthUnitsList = new ArrayList<HealthUnit>();


        getHealthUnitsList();

        recyclerView = view.findViewById(R.id.health_units_recycler_view);
        recyclerAdapter = new HealthUnitsRecyclerAdapter(healthUnitsList);
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
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
}
