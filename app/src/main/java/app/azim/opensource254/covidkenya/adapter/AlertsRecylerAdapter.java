package app.azim.opensource254.covidkenya.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import app.azim.opensource254.covidkenya.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.adapter.viewHolders.AlertsView;
import app.azim.opensource254.covidkenya.models.AlertsModel;

public class AlertsRecylerAdapter extends RecyclerView.Adapter<AlertsView> {

    Context context;
    public List<AlertsModel> alertsModelList;


    public AlertsRecylerAdapter(List<AlertsModel> alertsModelList, Context context) {
        this.context = context;
        this.alertsModelList = alertsModelList;

    }



    @NonNull
    @Override
    public AlertsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alert_recycler_row, parent, false);
        return new AlertsView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AlertsView holder, int position) {

        holder.txt_title.setText(String.valueOf(alertsModelList.get(position).title));
        holder.txt_detail.setText(String.valueOf(alertsModelList.get(position).detail));

    }

    @Override
    public int getItemCount() {
        return alertsModelList.size();
    }
}
