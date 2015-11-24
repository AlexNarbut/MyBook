package ru.startandroid.mybook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import ru.startandroid.mybook.fragment.CalendarFragment;
import ru.startandroid.mybook.fragment.StatictikFragment;
import ru.startandroid.mybook.fragment.TrainFragment;


public class TabsPageFragmentAdapter extends FragmentPagerAdapter {
    private  String[] tabs;

    public TabsPageFragmentAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
        tabs = new String[]{"Тренировка","Календарь", "Статистика","Изометрика","Рекорды" };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override

    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return TrainFragment.getInstance();
            case 1:
                return CalendarFragment.getInstance();
            case 2:
                return StatictikFragment.getInstance();
            case 3:
                return StatictikFragment.getInstance();
            case 4:
                return StatictikFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}

