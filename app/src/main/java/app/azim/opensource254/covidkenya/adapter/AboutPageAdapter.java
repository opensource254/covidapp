package app.azim.opensource254.covidkenya.adapter;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import app.azim.opensource254.covidkenya.models.AboutModel;

public class AboutPageAdapter  extends ExpandableGroup<AboutModel> {


    public AboutPageAdapter(String title, List<AboutModel> items) {
        super(title, items);
    }
}
