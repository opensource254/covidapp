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
import app.azim.opensource254.covidkenya.activities.SituationsFragment;
import app.azim.opensource254.covidkenya.adapter.viewHolders.SituationView;
import app.azim.opensource254.covidkenya.models.SituationModel;

public class SituationRecyclerAdapter extends RecyclerView.Adapter<SituationView> {
    Context context;
    public List<SituationModel> situationModelList;

    public SituationRecyclerAdapter(List<SituationModel> situationModelList, Context context) {
        this.context = context;
        this.situationModelList = situationModelList;

    }

    @NonNull
    @Override
    public SituationView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.situation_recycler_row, parent, false);
        return new SituationView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SituationView holder, int position) {

        //removing decimals
        String mcases = (String.valueOf(situationModelList.get(position).cases)).split("\\.")[0];
        String mtodayCases = (String.valueOf(situationModelList.get(position).todayCases)).split("\\.")[0];
        String mtodayDeaths = (String.valueOf(situationModelList.get(position).todayDeaths)).split("\\.")[0];
        String mrecovered = (String.valueOf(situationModelList.get(position).recovered)).split("\\.")[0];
        String mactive = (String.valueOf(situationModelList.get(position).active)).split("\\.")[0];
        String mcritical = (String.valueOf(situationModelList.get(position).critical)).split("\\.")[0];


        //setting values to an holder
        holder.txt_total_cases.setText(mcases);
        holder.txt_new_cases.setText(mtodayCases);
        holder.txt_new_deaths.setText(mtodayDeaths);
        //  holder.txt_deaths_.setText(String.valueOf(situationModelList.get(position).cases));
        holder.txt_total_recovered.setText(mrecovered);
        holder.txt_active_cases.setText(mactive);
        holder.txt_critical_cases.setText(mcritical);


    }

    @Override
    public int getItemCount() {
        return situationModelList.size();
    }
}