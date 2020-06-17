package app.azim.opensource254.covidkenya.activities;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.rahman.dialog.Activity.SmartDialog;
import com.rahman.dialog.ListenerCallBack.SmartDialogClickListener;
import com.rahman.dialog.Utilities.SmartDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.adapter.SituationRecyclerAdapter;
import app.azim.opensource254.covidkenya.api.publicdata.ApiServices;
import app.azim.opensource254.covidkenya.api.publicdata.CoronaNinjaInstance;
import app.azim.opensource254.covidkenya.models.SituationModel;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

public class SituationsFragment extends Fragment {
    final static String mSituationsFragment = "SituationsFragment";

    private SituationRecyclerAdapter mrecyclerAdapter;
    private RecyclerView situationRecyclerView;
    private ProgressBar progressBar;
    ApiServices service;
    CompositeDisposable disposable;
    List<SituationModel> situationModelList;
    SituationModel situationModel;
    Object response;
    MaterialButton moreStats,btngeofence,self_test;
    private int yes_count = 0;
    private List<String> answers;

    //overriding oncreate view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_situation, container, false);

        //init the api
        Retrofit mretrofit = CoronaNinjaInstance.getNinjaRetrofitInstance();
        service = mretrofit.create(ApiServices.class);
        disposable = new CompositeDisposable();

        situationModelList = new ArrayList<>();

        //view
        progressBar = v.findViewById(R.id.progress_bar);
        situationRecyclerView = v.findViewById(R.id.recycler_situation);
        moreStats = v.findViewById(R.id.btn_talk_more_situations);
        btngeofence = v.findViewById(R.id.btn_geofence);
        self_test = v.findViewById(R.id.btn_self_test);



        situationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        situationRecyclerView.setHasFixedSize(true);

        moreStats.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), CountiesStatsActivity.class)));
        btngeofence.setOnClickListener(v1 -> startActivity(new Intent(getActivity(), MapsActivity.class)));
        self_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers = new ArrayList<>();
                dialog1();
            }
        });


        try {
            assert this.getArguments() != null;
            response = this.getArguments().getSerializable("response");
        } catch (Exception e) {
            Log.d(mSituationsFragment, "Bundle error: " + e.getMessage());
            response = null;
        }
        situationModel = jsonData(response);

        Log.d(mSituationsFragment, "" + situationModel.cases);
        situationModelList.add(situationModel);

        mrecyclerAdapter = new SituationRecyclerAdapter(situationModelList, getContext());
        situationRecyclerView.setAdapter(mrecyclerAdapter);

        situationRecyclerView.setVisibility(View.VISIBLE);

        return v;
    }

    private SituationModel jsonData(Object response) {
        situationModel = new SituationModel();
        try {
            JSONObject countryData = new JSONObject(new Gson().toJson(response));
            Log.d(mSituationsFragment, "" + countryData);

            String updated = countryData.getString("updated");
            String cases = countryData.getString("cases");
            String todayCases = countryData.getString("todayCases");
            String deaths = countryData.getString("deaths");
            String todayDeaths = countryData.getString("todayDeaths");
            String recovered = countryData.getString("recovered");
            String active = countryData.getString("active");
            String critical = countryData.getString("critical");
            String casesPerOneMillion = countryData.getString("casesPerOneMillion");
            String deathsPerOneMillion = countryData.getString("deathsPerOneMillion");
            String tests = countryData.getString("tests");
            String testsPerOneMillion = countryData.getString("testsPerOneMillion");

            situationModel =
                    new SituationModel(0, cases, todayCases, deaths, todayDeaths,
                            recovered, active, critical, casesPerOneMillion,
                            deathsPerOneMillion, tests, testsPerOneMillion);

        } catch (JSONException e) {
            Log.d(mSituationsFragment, "Json error: " + e.getMessage());
        }
        return situationModel;
    }


    public void addYES(){
        answers.add("Yes");
    }

    public void addNO(){
        answers.add("No");
    }

    public String AnswersCounter(){
        yes_count = Collections.frequency(answers,"Yes");
        String testLevel = riskLevel();
        return testLevel;
    }

    public void resetAnswerCounter(){
        yes_count= 0;
    }

    public String riskLevel(){
        String level= "";
        if (yes_count>=6){
            level =  "High";
        }
        else if (yes_count<=5){
            level =  "Low";
        }
        return level;
    }

    public void whereNext(){
        if (AnswersCounter().equals("High")){
            dialog14();
        }
        else
            dialog15();
    }

    public void dialog1(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have a dry cough?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog2();


                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog2();


            }
        }).build().show();
    }

    public void dialog2(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have a cold?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog3();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog3();


            }
        }).build().show();
    }

    public void dialog3(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have diarrhoea?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog4();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog4();

            }
        }).build().show();
    }

    public void dialog4(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have a sore throat?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addYES();
                smartDialog.dismiss();
                dialog5();

            }
        }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog5();

            }
        }).build().show();
    }

    public void dialog5(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have severe headache?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog6();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog6();


            }
        }).build().show();
    }

    public void dialog6(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have a fever?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog7();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog7();


            }
        }).build().show();
    }

    public void dialog7(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have difficulty in breathing?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog8();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog8();

            }
        }).build().show();
    }

    public void dialog8(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have fatigue?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog9();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog9();

            }
        }).build().show();
    }

    public void dialog9(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Have you travelled recently in the past 14 days?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog10();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog10();

            }
        }).build().show();
    }

    public void dialog10(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have a travel history to a covid 19 infected Area?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog11();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog11();


            }
        }).build().show();
    }

    public void dialog11(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have direct contact with or are you taking care of a covid 19 patient?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog12();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog12();

            }
        }).build().show();
    }

    public void dialog12(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you have any pre-existing condition i.e. (Cancer,HIV,Diabetes e.t.c)?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        dialog13();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                dialog13();

            }
        }).build().show();
    }

    public void dialog13(){
        new SmartDialogBuilder(getContext())
                .setTitle("")
                .setSubTitle("Do you smoke?")
                .setCancalable(false)
                .setPositiveButton("Yes", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        addYES();
                        smartDialog.dismiss();
                        whereNext();

                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                addNO();
                smartDialog.dismiss();
                whereNext();
            }
        }).build().show();
        resetAnswerCounter();
    }

    public void dialog14(){
        new SmartDialogBuilder(getContext())
                .setTitle("Risk Level "+AnswersCounter())
                .setSubTitle("Do you wish to join a virtual quarantine?")
                .setCancalable(false)
                .setNegativeButtonHide(true)
                .setPositiveButton("Join", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        smartDialog.dismiss();
                        startActivity(new Intent(getContext(),QuarantineActivity.class));
                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                smartDialog.dismiss();
            }
        }).build().show();
    }

    public void dialog15(){
        new SmartDialogBuilder(getContext())
                .setTitle("Risk Level "+AnswersCounter())
                .setSubTitle("Your risk level is low, Continue being safe!!!")
                .setCancalable(false)
                .setNegativeButtonHide(true)
                .setPositiveButton("Done", new SmartDialogClickListener() {
                    @Override
                    public void onClick(SmartDialog smartDialog) {
                        smartDialog.dismiss();
                    }
                }).setNegativeButton("No", new SmartDialogClickListener() {
            @Override
            public void onClick(SmartDialog smartDialog) {
                smartDialog.dismiss();
            }
        }).build().show();
    }
}
