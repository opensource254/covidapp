package app.azim.opensource254.covidkenya.activities.ExposureNotification;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.android.material.snackbar.Snackbar;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.activities.ExposureNotification.Storage.ExposureNotificationSharedPref;
import app.azim.opensource254.covidkenya.activities.MainActivity;

public class ExposureNotifyFragment extends Fragment {
    ExposureNotificationSharedPref exposureNotificationSharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        exposureNotificationSharedPref = new ExposureNotificationSharedPref(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exposure_notify, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button exposureNotificationBtn = view.findViewById(R.id.exposure_notification_btn);
        exposureNotificationBtn.setOnClickListener(
                v -> {
                    exposureNotificationSharedPref.setEnable(false);
                    if (!exposureNotificationSharedPref.isEnabled()) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                });

        Button shareButton = view.findViewById(R.id.fragment_notify_share_button);
        shareButton.setOnClickListener(
                v -> ExposureNotifyFragment.this.startActivity(ShareDiagnosisActivity
                        .newIntentForAddFlow(ExposureNotifyFragment.this.requireContext())));

        PositiveDiagnosisEntityAdapter notifyViewAdapter =
                new PositiveDiagnosisEntityAdapter(
                        positiveDiagnosisEntity -> ExposureNotifyFragment.this.startActivity(
                                ShareDiagnosisActivity.newIntentForViewFlow(
                                        ExposureNotifyFragment.this.requireContext(), positiveDiagnosisEntity)));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.notify_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(notifyViewAdapter);

        final ViewSwitcher switcher =
                requireView().findViewById(R.id.fragment_notify_diagnosis_switcher);

//        notifyHomeViewModel
//                .getAllPositiveDiagnosisEntityLiveData()
//                .observe(getViewLifecycleOwner(), l -> {
//                    switcher.setDisplayedChild(l.isEmpty() ? 0 : 1);
//                    notifyViewAdapter.setPositiveDiagnosisEntities(l);
//                });

        TextView notifyDescription = view.findViewById(R.id.fragment_notify_description);
        appendLearnMoreLink(
                notifyDescription, new Intent(requireContext(), NotifyLearnMoreActivity.class));
    }

    /** Appends a clickable learn more link to the end of the text view specified. */
    public static void appendLearnMoreLink(TextView textView, Intent intent) {
        ClickableSpan clickableSpan =
                new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        textView.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                };
        String learnMoreText = textView.getContext().getString(R.string.learn_more);
        SpannableString learnMoreSpannable = new SpannableString(learnMoreText);
        learnMoreSpannable.setSpan(
                clickableSpan, 0, learnMoreText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(TextUtils.concat(textView.getText(), " ", learnMoreSpannable));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}