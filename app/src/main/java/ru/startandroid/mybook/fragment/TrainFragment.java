package ru.startandroid.mybook.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import ru.startandroid.mybook.R;

/**
 * Created by Алексей on 07.10.2015.
 */
public class TrainFragment  extends Fragment implements View.OnClickListener{
    private static final int LAYOUT = R.layout.fragment_train;
    private Button createTrain;
    private int year,month,day;
    final Calendar nowdate = Calendar.getInstance();
    private View view;

    public static TrainFragment getInstance(){
        Bundle args = new Bundle();
        TrainFragment fragment1 = new TrainFragment();
        fragment1.setArguments(args);
        return fragment1;
    }
    public TrainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        createTrain = (Button)view.findViewById(R.id.create_train);
        year = nowdate.get(Calendar.YEAR);
        month = nowdate.get(Calendar.MONTH);
        day = nowdate.get(Calendar.DAY_OF_MONTH);
        createTrain.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch ((v.getId()))
        {
            case R.id.create_train:
            {
                AlertClick();
                break;
            }
        }

    }

    private void AlertClick()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Составленная тренировка!")
                .setMessage("Что-то там, что-то там")
                .setNegativeButton("Закрыть",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }})
                .setCancelable(true).setPositiveButton("Создать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                        /* код добавления тренировки*/
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
