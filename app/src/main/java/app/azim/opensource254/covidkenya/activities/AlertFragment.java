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
//import app.azim.opensource254.covidkenya.adapter.HealthUnitsRecyclerAdapter;
import app.azim.opensource254.covidkenya.models.HealthUnitModel;

public class AlertFragment extends Fragment {

    List<HealthUnitModel> healthUnitsListModel;
    private RecyclerView recyclerView;
    // private HealthUnitsRecyclerAdapter recyclerAdapter;

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alert, container, false);

        healthUnitsListModel = new ArrayList<HealthUnitModel>();


        //  getHealthUnitsListModel();

        recyclerView = view.findViewById(R.id.health_units_recycler_view);
        // recyclerAdapter = new HealthUnitsRecyclerAdapter(this, healthUnitsList);
        //  recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }


}

