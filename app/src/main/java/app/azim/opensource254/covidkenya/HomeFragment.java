package app.azim.opensource254.covidkenya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        CardView mnews = v.findViewById(R.id.card_news);
        //starting News activity
        mnews.setOnClickListener(this::card_btn_news);
        return v;
    }

    //onclick  for news card
    public void  card_btn_news(View v) {
        startActivity(new Intent(getActivity(), NewsActivity.class));
    }


}
