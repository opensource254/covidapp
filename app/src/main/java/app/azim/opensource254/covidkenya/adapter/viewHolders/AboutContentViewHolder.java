package app.azim.opensource254.covidkenya.adapter.viewHolders;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.models.AboutContent;

public class AboutContentViewHolder extends ChildViewHolder {

    private TextView content;

    public AboutContentViewHolder(View itemView) {
        super(itemView);

        content = itemView.findViewById(R.id.about_content);
    }

    public void onBind(AboutContent aboutContent) {
       content.setText(aboutContent.getContent());
    }
}
