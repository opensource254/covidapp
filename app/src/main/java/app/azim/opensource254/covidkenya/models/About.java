package app.azim.opensource254.covidkenya.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class About extends ExpandableGroup<AboutContent> {

    public About(String title, List<AboutContent> items) {
        super(title, items);
    }
}
