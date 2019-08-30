package com.bawei.yuchenlei20190731.adatper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 于晨雷
 * 2019-7-31 09:31:30
 * fragment适配器
 */
public class FragmentAdatper extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;
    ArrayList<String> strings;
    public FragmentAdatper(FragmentManager supportFragmentManager, ArrayList<Fragment> fragments, ArrayList<String> strings) {
        super(supportFragmentManager);
        this.fragments=fragments;
        this.strings=strings;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
