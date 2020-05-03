package app.azim.opensource254.covidkenya.adapter.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;

public class SituationView extends RecyclerView.ViewHolder  {


    public TextView txt_total_cases, txt_new_cases, txt_new_deaths, txt_total_recovered, txt_active_cases,txt_critical_cases,txt_today_cases;

    public SituationView(@NonNull View itemView) {
        super(itemView);

        txt_total_cases = itemView.findViewById(R.id.txt_total_cases);
        txt_new_cases = itemView.findViewById(R.id.txt_new_cases);
        txt_new_deaths = itemView.findViewById(R.id.txt_new_deaths);
        txt_total_recovered = itemView.findViewById(R.id.txt_total_recovered);
        txt_active_cases = itemView.findViewById(R.id.txt_active_cases);
        txt_critical_cases = itemView.findViewById(R.id.txt_critical_cases);
       // txt_today_cases = itemView.findViewById(R.id.txt_today_cases);





    }
}
