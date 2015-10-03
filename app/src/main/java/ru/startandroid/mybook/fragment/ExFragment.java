package ru.startandroid.mybook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.startandroid.mybook.R;

public class ExFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_layout;
    private  View view;

    public static ExFragment getInstance(){
        Bundle args = new Bundle();
        ExFragment fragment1 = new ExFragment();
        fragment1.setArguments(args);
        return fragment1;
    }
    public ExFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        return view;
    }
}

