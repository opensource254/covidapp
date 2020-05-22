package app.azim.opensource254.covidkenya.adapter.viewHolders;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import app.azim.opensource254.covidkenya.R;

public class AboutTitlesViewHolder extends GroupViewHolder {

    private TextView aboutTitle;

    public AboutTitlesViewHolder(View itemView) {
        super(itemView);
        aboutTitle = itemView.findViewById(R.id.about_title);
    }


    public void setGenreTitle(ExpandableGroup group) {
        aboutTitle.setText(group.getTitle());
    }
}
