package com.mustafaiev.tair.dressin;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.util.Log;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.mustafaiev.tair.dressin.adapter.DressinFragmentStatePagerAdapter;
import com.mustafaiev.tair.dressin.manager.DressinLocationManager;
import com.mustafaiev.tair.dressin.manager.LocationHelper;

public class DressInActivity extends SherlockFragmentActivity {

    private ViewPager mViewPager;

    private FragmentStatePagerAdapter mPagerAdapter;

    private final ActionBar.TabListener tabListener = new ActionBar.TabListener() {

        @Override
        public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction ft) {
            DressInActivity.this.mViewPager.setCurrentItem(tab.getPosition(), true);
        }

        @Override
        public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction ft) {
        }

        @Override
        public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction ft) {
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.mViewPager = (ViewPager) this.findViewById(R.id.dressin_view_pager);
        this.mPagerAdapter = new DressinFragmentStatePagerAdapter(getSupportFragmentManager());
        final ActionBar actionBar = this.buildActionBarTabs();
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(final int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
    }

    /**
     * Builds the ActionBar tabs
     *
     * @return {@link ActionBar} object with added tabs
     */
    private ActionBar buildActionBarTabs() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(0);
        // Specify that a dropdown list should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // create new Statistics tab
        this.addTab(actionBar, R.string.title_me_tab);
        // create new Assignments tab
        this.addTab(actionBar, R.string.title_friends_tab);
        return actionBar;
    }

    /**
     * Adds tab to action bar
     *
     * @param actionBar - {@link ActionBar}
     * @param title     - tab's title
     */
    private void addTab(final ActionBar actionBar, final int title) {
        final ActionBar.Tab statisticsTab = actionBar.newTab().setText(this.getString(title)).setTabListener(this.tabListener);
        actionBar.addTab(statisticsTab);
    }

}
