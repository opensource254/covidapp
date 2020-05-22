package app.azim.opensource254.covidkenya.adapter.viewHolders;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.models.AboutModel;

public class AboutContentViewHolder extends ChildViewHolder {

    private TextView aboutContent;

    public AboutContentViewHolder(View itemView) {
        super(itemView);

        aboutContent = itemView.findViewById(R.id.about_content);
    }

    public void onBind(AboutModel aboutModel) {
       aboutContent.setText(aboutModel.getContent());
    }
}
