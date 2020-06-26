package app.azim.opensource254.covidkenya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.skyfishjy.library.RippleBackground;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.activities.ExposureNotification.ExposureNotificationActivity;
import app.azim.opensource254.covidkenya.activities.ExposureNotification.Storage.ExposureNotificationSharedPref;

public class HomeFragment extends Fragment {

    private MaterialButton moreTips, mTalkToSpecialist;
    MaterialTextView contactTracingMTV;
    Button exposureNotificationBtn;
    RippleBackground rippleBackground;

    ExposureNotificationSharedPref exposureNotificationSharedPref;

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        moreTips = v.findViewById(R.id.btn_talk_more_tips);
        mTalkToSpecialist = v.findViewById(R.id.btn_talk_to_a_doctor);
        contactTracingMTV = v.findViewById(R.id.contact_tracing_material_tv);

        //DISABLE talk to a specialist button from home fragment
        mTalkToSpecialist.setEnabled(false);

        rippleBackground = v.findViewById(R.id.blu_content);
        ImageView imageView = v.findViewById(R.id.btn_bluesafe);
        exposureNotificationBtn = v.findViewById(R.id.exposure_notification_btn);

        CardView mnews = v.findViewById(R.id.card_news);
        CardView mheathunits = v.findViewById(R.id.card_health_units);

        exposureNotificationSharedPref = new ExposureNotificationSharedPref(getContext());

        //starting News activity
        mnews.setOnClickListener(this::card_btn_news);
        //starting Heath Units activity
        mheathunits.setOnClickListener(this::card_btn_health_units);

        moreTips.setOnClickListener(this::btn_more_tips);
        mTalkToSpecialist.setOnClickListener(this::btn_talk_to_specialist);

        // Show status of exposure or contact tracing
        exposureNotificationStatus();

      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(exposureNotificationSharedPref.isEnabled()){
                  // If exposure notification is enabled, disable everything
                  rippleBackground.stopRippleAnimation();
                  exposureNotificationBtn.setVisibility(View.GONE);
                  exposureNotificationSharedPref.setEnable(false);
                  contactTracingMTV.setText("To enable contact tracing mode please tap the blue icon");
              }else{
                  // If exposure notification is disabled, enable everything
                  rippleBackground.startRippleAnimation();
                  exposureNotificationBtn.setVisibility(View.VISIBLE);
                  exposureNotificationSharedPref.setEnable(true);
                  contactTracingMTV.setText("Tap the blue icon to disable");
              }
          }
      });

      exposureNotificationBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getActivity(), ExposureNotificationActivity.class);
              startActivity(intent);
          }
      });

        return v;
    }

    public void exposureNotificationStatus(){
        if(exposureNotificationSharedPref.isEnabled()){
            rippleBackground.startRippleAnimation();
            exposureNotificationBtn.setVisibility(View.VISIBLE);
            contactTracingMTV.setText("Tap the blue icon to disable");
        }else{
            rippleBackground.stopRippleAnimation();
            exposureNotificationBtn.setVisibility(View.GONE);
            contactTracingMTV.setText("To enable contact tracing mode please tap the blue icon");
        }
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
