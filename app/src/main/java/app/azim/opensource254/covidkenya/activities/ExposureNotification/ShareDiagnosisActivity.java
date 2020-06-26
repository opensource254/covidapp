package app.azim.opensource254.covidkenya.activities.ExposureNotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import app.azim.opensource254.covidkenya.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class ShareDiagnosisActivity extends AppCompatActivity {
    static final String SAVED_INSTANCE_STATE_FRAGMENT_KEY =
            "ShareExposureActivity.SAVED_INSTANCE_STATE_FRAGMENT_KEY";
    static final String SHARE_EXPOSURE_FRAGMENT_TAG =
            "ShareExposureActivity.POSITIVE_TEST_FRAGMENT_TAG";

    private static final String EXTRA_VIEW_POSITIVE_DIAGNOSIS_ID =
            "ShareExposureActivity.EXTRA_VIEW_POSITIVE_DIAGNOSIS_ID";

    /** Creates an intent for adding a positive diagnosis flow. */
    public static Intent newIntentForAddFlow(Context context) {
        return new Intent(context, ShareDiagnosisActivity.class);
    }

    /**
     * Creates an intent for viewing a positive diagnosis flow.
     *
     * @param entity the {@link PositiveDiagnosisEntity} to view
     */
    public static Intent newIntentForViewFlow(Context context, PositiveDiagnosisEntity entity) {
        Intent intent = new Intent(context, ShareDiagnosisActivity.class);
        intent.putExtra(EXTRA_VIEW_POSITIVE_DIAGNOSIS_ID, entity.getId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_diagnosis);

        if (savedInstanceState != null) {
            // If this is a configuration change such as rotation, restore the fragment that was
            // previously saved in onSaveInstanceState
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(
                    R.id.share_exposure_fragment,
                    Objects.requireNonNull(
                            getSupportFragmentManager()
                                    .getFragment(savedInstanceState, SAVED_INSTANCE_STATE_FRAGMENT_KEY)),
                    SHARE_EXPOSURE_FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {
            // This is a fresh launch.
            if (getIntent().hasExtra(EXTRA_VIEW_POSITIVE_DIAGNOSIS_ID)) {
                // Has extra so start view flow.
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(
                        R.id.share_exposure_fragment,
                        ShareDiagnosisViewFragment.newInstance(
                                getIntent().getLongExtra(EXTRA_VIEW_POSITIVE_DIAGNOSIS_ID, -1)),
                        SHARE_EXPOSURE_FRAGMENT_TAG);
                fragmentTransaction.commit();
            } else {
                // Start the add flow.
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(
                        R.id.share_exposure_fragment,
                        new ShareDiagnosisBeginFragment(),
                        SHARE_EXPOSURE_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }
        }
    }
}