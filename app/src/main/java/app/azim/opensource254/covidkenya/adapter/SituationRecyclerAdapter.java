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


   // private static final String TAG = "SituationRecyclerAdapter";
    Context context;
    public List<SituationModel> situationModelList;



    public SituationRecyclerAdapter(List<SituationModel> situationModelList,Context context) {

        this.context = context;
        this.situationModelList = situationModelList;

       // situationModelModelListAll = new ArrayList<>();
       // situationModelModelListAll.addAll(situationModelList);
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
        holder.txt_total_cases.setText(String.valueOf(situationModelList.get(position).cases));
       // holder.txt_today_cases.setText(String.valueOf(situationModelList.get(position).todayCases));
        holder.txt_new_deaths.setText(String.valueOf(situationModelList.get(position).todayCases));
      //  holder.txt_deaths_.setText(String.valueOf(situationModelList.get(position).cases));
        holder.txt_total_recovered.setText(String.valueOf(situationModelList.get(position).recovered));
        holder.txt_active_cases.setText(String.valueOf(situationModelList.get(position).active));
        holder.txt_critical_cases.setText(String.valueOf(situationModelList.get(position).critical));



    }




    @Override
    public int getItemCount() {
        return situationModelList.size();
    }
}