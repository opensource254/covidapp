package app.azim.opensource254.covidkenya.activities.ExposureNotification;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import app.azim.opensource254.covidkenya.R;

import static app.azim.opensource254.covidkenya.activities.ExposureNotification.ExposureNotificationActivity.EXPOSURE_FRAGMENT_TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExposureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExposureFragment extends Fragment {

    private static final String KEY_START_TAB = "KEY_START_TAB";

    // Constants so the tabs are settable by name and not just index.
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TAB_EXPOSURES, TAB_NOTIFY, TAB_DEBUG})
    @interface TabName {}

    static final int TAB_EXPOSURES = 0;
    static final int TAB_NOTIFY = 1;
    static final int TAB_DEBUG = 2;

    static final int TAB_DEFAULT = TAB_EXPOSURES;

    private ExposureFragmentPagerAdapter fragmentPagerAdapter;

    public ExposureFragment() {
        // Required empty public constructor
    }

    public static ExposureFragment newInstance(int tab) {
        ExposureFragment exposureFragment = new ExposureFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_START_TAB, tab);
        exposureFragment.setArguments(args);
        return exposureFragment;
    }

    /** Creates a {@link ExposureFragment} instance with a default start tab {@value #TAB_DEFAULT}. */
    public static ExposureFragment newInstance() {
        return new ExposureFragment();
    }

    private @TabName int getStartTab() {
        if (getArguments() != null) {
            return getArguments().getInt(KEY_START_TAB, TAB_DEFAULT);
        } else {
            return TAB_DEFAULT;
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        fragmentPagerAdapter = new ExposureFragmentPagerAdapter(getFragmentManager());

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(getStartTab());

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(TAB_EXPOSURES).setIcon(R.drawable.ic_bell);
        tabLayout.getTabAt(TAB_EXPOSURES).setText(R.string.home_tab_exposures_text);
        tabLayout.getTabAt(TAB_NOTIFY).setIcon(R.drawable.ic_flag);
        tabLayout.getTabAt(TAB_NOTIFY).setText(R.string.home_tab_notify_text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exposure, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentPagerAdapter.getCurrentFragment().onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Helper to transition from one fragment to {@link ExposureFragment}
     *
     * @param fragment The fragment to transit from
     */
    public static void transitionToExposureFragment(Fragment fragment) {
        // Remove previous fragment from the stack if it is there.
        fragment.getFragmentManager()
                .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction fragmentTransaction =
                fragment.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_fragment, ExposureFragment.newInstance(), EXPOSURE_FRAGMENT_TAG);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}