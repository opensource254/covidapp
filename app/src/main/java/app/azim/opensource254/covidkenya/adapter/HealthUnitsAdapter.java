package app.azim.opensource254.covidkenya.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.viewHolders.HealthUnitsView;
import app.azim.opensource254.covidkenya.models.HealthUnitModel;

public class HealthUnitsAdapter extends RecyclerView.Adapter<HealthUnitsView> {
    Context context;
    public List<HealthUnitModel> healthUnitModelList;


    public HealthUnitsAdapter(List<HealthUnitModel> healthUnitModelList, Context context) {
        this.context = context;
        this.healthUnitModelList = healthUnitModelList;

    }


    @NonNull
    @Override
    public HealthUnitsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.health_units_recycler_row, parent, false);
        return new HealthUnitsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthUnitsView holder, int position) {

        String healthStatus = (String.valueOf(healthUnitModelList.get(position).open));

        if (healthStatus.equals("true")) {
            holder.txt_open.setText("open");


        } else {
            holder.txt_open.setText("closed");
        }


        holder.txt_title.setText(String.valueOf(healthUnitModelList.get(position).title));
        holder.txt_description.setText(String.valueOf(healthUnitModelList.get(position).description));


    }

    @Override
    public int getItemCount() {
        return healthUnitModelList.size();
    }
}
