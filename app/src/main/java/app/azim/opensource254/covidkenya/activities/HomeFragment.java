package app.azim.opensource254.covidkenya.activities;

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
import com.google.android.material.button.MaterialButton;
import app.azim.opensource254.covidkenya.R;

public class HomeFragment extends Fragment {

private    MaterialButton moreTips, mTalkToSpecialist;


    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        moreTips = v.findViewById(R.id.btn_talk_more_tips);
        mTalkToSpecialist = v.findViewById(R.id.btn_talk_to_a_doctor);



        // ImageView icImg1 = v.findViewById(R.id.img_ic_what_is_corona);
       // icImg1.setImageResource(R.drawable.ic_corona);
        CardView mnews = v.findViewById(R.id.card_news);
        CardView mheathunits = v.findViewById(R.id.card_health_units);

        //starting News activity
        mnews.setOnClickListener(this::card_btn_news);
        //starting Heath Units activity
        mheathunits.setOnClickListener(this::card_btn_health_units);

        moreTips.setOnClickListener(this::btn_more_tips);
        mTalkToSpecialist.setOnClickListener(this::btn_talk_to_specialist);
        return v;
    }



    //onclick  for news card
    public void  card_btn_news(View v) {
        startActivity(new Intent(getActivity(), NewsActivity.class));
    }

    //onclick  for health units card
    public void  card_btn_health_units(View v) {
        startActivity(new Intent(getActivity(), HealthUnitsActivity.class));
    }

    public void btn_more_tips(View v){
        startActivity(new Intent(getActivity(), TipsActivity.class));
    }

    public void btn_talk_to_specialist(View v){
        startActivity(new Intent(getActivity(), SymptomActivity.class));
    }





}
