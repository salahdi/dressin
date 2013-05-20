package com.mustafaiev.tair.dressin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.mustafaiev.tair.dressin.PropositionCardsFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Project dressin
 *
 * @autor tair_mustafaiev
 * Date: 5/16/13
 * Time: 5:29 PM
 */
public class DressinFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private static final int FRAGMENTS_COUNT = 2;

    public DressinFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return getFragment(i);
    }

    @Override
    public int getCount() {
        return FRAGMENTS_COUNT;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return "";
    }

    /**
     * Returns needed fragment
     *
     * @param position - position
     * @return {@link Fragment}
     */
    private Fragment getFragment(final int position) {
        final List<Fragment> fragments = new LinkedList<Fragment>();
        fragments.add(new PropositionCardsFragment());
        fragments.add(new Fragment());
        return fragments.get(position);
    }
}
