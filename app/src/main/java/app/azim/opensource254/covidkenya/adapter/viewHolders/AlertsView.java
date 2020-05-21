package app.azim.opensource254.covidkenya.adapter.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;

public class AlertsView extends RecyclerView.ViewHolder{
    public TextView txt_title, txt_detail;

    public AlertsView(@NonNull View alertitemView) {
        super(alertitemView);

        txt_title = alertitemView.findViewById(R.id.text_alert_title);
        txt_detail = alertitemView.findViewById(R.id.text_alert_detail);


    }


}
