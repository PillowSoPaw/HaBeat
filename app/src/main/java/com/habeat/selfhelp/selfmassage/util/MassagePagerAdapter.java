package com.habeat.selfhelp.selfmassage.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.habeat.R;
import com.habeat.selfhelp.selfmassage.Massage;

import java.util.ArrayList;
import java.util.List;

public class MassagePagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragments;
    private MassageFragment currentFragment;

    public MassagePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

        // TODO - refactor this if there is still time to do so; this method is not elegant.
        fragments = new ArrayList<>();
        fragments.add(MassageFragment.newInstance("Massage 1", new Massage(context.getResources()
                .getString(R.string.massage_desc_1), R.raw.massage_vid_1)));
        fragments.add(MassageFragment.newInstance("Massage 2", new Massage(context.getResources()
                .getString(R.string.massage_desc_2), R.raw.massage_vid_2)));
        fragments.add(MassageFragment.newInstance("Massage 3", new Massage(context.getResources()
                .getString(R.string.massage_desc_3), R.raw.massage_vid_3)));
        fragments.add(MassageFragment.newInstance("Massage 4", new Massage(context.getResources()
                .getString(R.string.massage_desc_4), R.raw.massage_vid_4)));
        fragments.add(MassageFragment.newInstance("Massage 5", new Massage(context.getResources()
                .getString(R.string.massage_desc_5), R.raw.massage_vid_5)));
        fragments.add(MassageFragment.newInstance("Massage 6", new Massage(context.getResources()
                .getString(R.string.massage_desc_6), R.raw.massage_vid_6)));
        fragments.add(MassageFragment.newInstance("Massage 7", new Massage(context.getResources()
                .getString(R.string.massage_desc_7), R.raw.massage_vid_7)));
    }

    @Override
    public Fragment getItem(int position) {
        if (currentFragment != null) {
        //    currentFragment.pauseVideo();
        }
        currentFragment = (MassageFragment) fragments.get(position);
        return currentFragment;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Massage " + (position+1);
    }
}
