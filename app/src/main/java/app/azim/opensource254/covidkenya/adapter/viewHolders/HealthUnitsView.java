package app.azim.opensource254.covidkenya.adapter.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.models.NewsTweet;

public class HealthUnitsView extends RecyclerView.ViewHolder {
    public TextView txt_title, txt_lat, txt_lon, txt_open, txt_description;

    public HealthUnitsView(View itemview) {

        super(itemview);

        txt_title = itemview.findViewById(R.id.text_view_title);
        txt_open = itemview.findViewById(R.id.text_view_open);
        txt_description = itemview.findViewById(R.id.text_view_description);
    }


}
