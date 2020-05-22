package app.azim.opensource254.covidkenya.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.AboutAdapter;
import app.azim.opensource254.covidkenya.models.About;
import app.azim.opensource254.covidkenya.models.AboutContent;
import app.azim.opensource254.covidkenya.models.HealthUnitModel;

public class AboutFragment extends Fragment {

    private RecyclerView aboutrv;
    private List<About> aboutList;
    private AboutAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        aboutList = new ArrayList<>();
        aboutList = getAboutsList();
        aboutrv  = v.findViewById(R.id.about_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        aboutrv.setLayoutManager(layoutManager);
        adapter = new AboutAdapter(aboutList);
        aboutrv.setAdapter(adapter);
        return v;
    }

    private List<About> getAboutsList() {

        AboutContent aboutContent1 = new AboutContent();
        aboutContent1.setContent("app purpose");
        About about1 = new About("What is the purpose of this app", Arrays.asList(aboutContent1));
        aboutList.add(about1);

        AboutContent aboutContent2 = new AboutContent();
        aboutContent2.setContent("benefits");
        About about2 = new About("How does this app benefit me", Arrays.asList(aboutContent2));
        aboutList.add(about2);

        AboutContent aboutContent3 = new AboutContent();
        aboutContent3.setContent("users");
        About about3 = new About("Who can use Corvid safe", Arrays.asList(aboutContent3));
        aboutList.add(about3);

        return aboutList;




    }


}
