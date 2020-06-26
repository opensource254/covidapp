package app.azim.opensource254.covidkenya.activities.ExposureNotification;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.activities.ExposureNotification.Storage.ExposureNotificationSharedPref;
import app.azim.opensource254.covidkenya.activities.MainActivity;

public class ExposureHomeFragment extends Fragment {
    private ExposureEntityAdapter adapter;
    ExposureNotificationSharedPref exposureNotificationSharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        exposureNotificationSharedPref = new ExposureNotificationSharedPref(getContext());
        return inflater.inflate(R.layout.fragment_exposure_home, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView exposureNotificationStatus, infoStatus;

        exposureNotificationStatus = view.findViewById(R.id.exposure_notifications_status);
        infoStatus = view.findViewById(R.id.info_status);

        if (exposureNotificationSharedPref.isEnabled()) {
            exposureNotificationStatus.setText(R.string.on);
            infoStatus.setText(R.string.notifications_enabled_info);
        } else {
            exposureNotificationStatus.setText(R.string.off);
            infoStatus.setText(R.string.notifications_disabled_info);
        }

        view.findViewById(R.id.exposure_notification_btn).setOnClickListener(v -> {
            exposureNotificationSharedPref.setEnable(false);
            if (!exposureNotificationSharedPref.isEnabled()) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.exposure_about_button).setOnClickListener(v -> launchAboutAction());

        view.findViewById(R.id.create_test_exposure_btn).setOnClickListener(v -> {
            List<ExposureEntity> exposureEntities = new ArrayList<>();

            long[] millies = {System.currentTimeMillis(), System.currentTimeMillis()};

            for (long milly : millies) {
                ExposureEntity exposureEntity = new ExposureEntity(
                        milly, millies[0]);

                exposureEntities.add(exposureEntity);
            }
            refreshUiForExposureEntities(exposureEntities);
        });

        RecyclerView exposuresList = view.findViewById(R.id.exposures_list);
        adapter =
                new ExposureEntityAdapter(
                        exposureEntity -> {
                            ExposureBottomSheetFragment sheet =
                                    ExposureBottomSheetFragment.newInstance(exposureEntity);
                            sheet.show(ExposureHomeFragment.this.getChildFragmentManager(), ExposureBottomSheetFragment.TAG);
                        });
        exposuresList.setItemAnimator(null);
        exposuresList.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        exposuresList.setAdapter(adapter);
    }

    /** Open the Exposure Notifications about screen. */
    private void launchAboutAction() {
        startActivity(new Intent(requireContext(), ExposureAboutActivity.class));
    }

    /**
     * Display new exposure information
     *
     * @param exposureEntities List of potential exposures
     */
    private void refreshUiForExposureEntities(@Nullable List<ExposureEntity> exposureEntities) {
        View rootView = getView();
        if (rootView == null) {
            return;
        }

        if (adapter != null) {
            adapter.submitList(exposureEntities);
        }

        ViewAnimator switcher = rootView.findViewById(R.id.exposures_list_empty_switcher);
        switcher.setDisplayedChild(exposureEntities == null || exposureEntities.isEmpty() ? 0 : 1);
    }
}