package app.azim.opensource254.covidkenya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.activities.NewsActivity;
import app.azim.opensource254.covidkenya.api.RetrofitServiceInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private TextView cases, recovered, deaths;
    MaterialButton moreTips;

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        cases = v.findViewById(R.id.txt_cases);
        recovered = v.findViewById(R.id.txt_recovered);
        deaths = v.findViewById(R.id.txt_deaths);
        moreTips = v.findViewById(R.id.btn_talk_more_tips);


        fetchDataForCountry("kenya");

        CardView mnews = v.findViewById(R.id.card_news);
        CardView mheathunits = v.findViewById(R.id.card_health_units);
        //starting News activity
        mnews.setOnClickListener(this::card_btn_news);
        //starting Heath Units activity
        mheathunits.setOnClickListener(this::card_btn_health_units);

        moreTips.setOnClickListener(this::btn_more_tips);
        return v;
    }

    private void fetchDataForCountry(String country) {
        RetrofitServiceInstance.getApiService().getCountryData().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                if(response.isSuccessful()) {

                    try {
                        JSONObject countryData = new JSONObject(new Gson().toJson(response.body()));
                        JSONObject todayRecords = countryData.getJSONObject("4/21/20");
                        JSONObject totals = countryData.getJSONObject("total");

                        String totalReported = totals.getString("reported");
                        String totalRecoverd = totals.getString("recovered");
                        String totalDeaths  = totals.getString("deaths");

                        cases.setText(totalReported);
                        recovered.setText(totalRecoverd);
                        deaths.setText(totalDeaths);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(getActivity(), "Error failed to fetch data", Toast.LENGTH_SHORT).show();
                System.out.println("response  Error  "+ t.getMessage());
            }
        });
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




}
