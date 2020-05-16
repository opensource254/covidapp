package app.azim.opensource254.covidkenya.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.activities.CountiesStats;
import app.azim.opensource254.covidkenya.models.CountiesData;
import app.azim.opensource254.covidkenya.models.TipsData;



public class CountiesAdapter extends RecyclerView.Adapter<CountiesAdapter.ViewHolder> {
    private List<CountiesData> countiesData;
    private Context context;

    private TipsAdapter.OnItemClickListener mListener;

    public CountiesAdapter(List<CountiesData> countiesData, Context context) {
        //Initialize the List and Context
        this.countiesData = countiesData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_stats_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final CountiesData data = countiesData.get(position);
        holder.county.setText(data.getCounty());
        holder.deaths.setText(data.getDeaths());
        holder.cases.setText(data.getCases());
        holder.tests.setText(data.getTests());
        holder.recoveries.setText(data.getRecoveries());
    }

    @Override
    public int getItemCount() {
        return countiesData.size();
    }


    public void filterList(ArrayList<CountiesData> countiesDat){
        countiesData = countiesDat;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       TextView county,cases,deaths,tests,recoveries;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            county = itemView.findViewById(R.id.county_name);
            cases = itemView.findViewById(R.id.confirmed_stats);
            deaths = itemView.findViewById(R.id.deaths_stats);
            tests = itemView.findViewById(R.id.tests_stats);
            recoveries = itemView.findViewById(R.id.recoveries_stats);
        }
    }
}
