package ru.startandroid.mybook.adapter;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import ru.startandroid.mybook.fragment.ExFragment;


public class TabsPageFragmentAdapter extends FragmentPagerAdapter {
    private  String[] tabs;

    public TabsPageFragmentAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
        tabs = new String[]{"Calendar", "Train","WoD"};
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override

    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ExFragment.getInstance();
            case 1:
                return ExFragment.getInstance();
            case 2:
                return ExFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}

