package ru.startandroid.mybook.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.startandroid.mybook.R;

public class CalendarFragment extends Fragment  {
    private static final int LAYOUT = R.layout.fragment_calendar;
    static Calendar date;
    private  View view;

    public static CalendarFragment getInstance(){
        Bundle args = new Bundle();
        CalendarFragment fragment1 = new CalendarFragment();
        fragment1.setArguments(args);
        return fragment1;
    }
    public CalendarFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        InitCalendar((CalendarView) view.findViewById(R.id.calendar));
        return view;
    }

    private void InitCalendar(CalendarView calendar)
    {
        calendar.setShowWeekNumber(false);
        calendar.setFirstDayOfWeek(2);
        date = new GregorianCalendar();
        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Calendar date1 = new GregorianCalendar(year,month,day);
                if (date.get(Calendar.DAY_OF_MONTH) != date1.get(Calendar.DAY_OF_MONTH)) {
                    AlertClick(year, month, day);
                }
                date= date1;
            }
        });
    }

    private void AlertClick(int year1,int month1,int day1)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(day1+"."+(month1+1) +"." +year1)
                .setMessage("Тренировки")
                .setNegativeButton("Закрыть",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }})
                .setCancelable(true);
        AlertDialog alert = builder.create();
        alert.show();
    }
}

