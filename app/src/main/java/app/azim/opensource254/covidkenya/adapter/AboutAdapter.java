package app.azim.opensource254.covidkenya.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.viewHolders.AboutContentViewHolder;
import app.azim.opensource254.covidkenya.adapter.viewHolders.AboutTitlesViewHolder;
import app.azim.opensource254.covidkenya.models.About;
import app.azim.opensource254.covidkenya.models.AboutContent;

public class AboutAdapter extends ExpandableRecyclerViewAdapter<AboutTitlesViewHolder, AboutContentViewHolder> {


    public AboutAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public AboutTitlesViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.about_titles_row, parent, false);
        return new AboutTitlesViewHolder(view);
    }

    @Override
    public AboutContentViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.about_content_row, parent, false);
        return new AboutContentViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(AboutContentViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        final AboutContent aboutContent = (AboutContent) (group.getItems()).get(childIndex);
        holder.onBind(aboutContent);

    }

    @Override
    public void onBindGroupViewHolder(AboutTitlesViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setAboutTitle(group);

    }
}
