package ru.startandroid.mybook.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ru.startandroid.mybook.R;
import ru.startandroid.mybook.TrainActivity;
import ru.startandroid.mybook.db.DatabaseHandler;
import ru.startandroid.mybook.db.DbTables.DiaryItem;
import ru.startandroid.mybook.db.DbTables.Item;
import ru.startandroid.mybook.db.DbTables.Type;
import android.support.v4.app.DialogFragment;
public class TrainFragment  extends Fragment implements View.OnClickListener{
    private static final int LAYOUT = R.layout.fragment_train;
    private Button createTrain;
    private Button setDate;
    private Spinner typeSpinner;
    private TextView dateTextView;
    private  Spinner exSpinner;
    private int mYear,mMonth,mDay;
    static final int DATE_DIALOG_ID = 0;
    final Calendar nowdate = Calendar.getInstance();
    private View view;
    DatabaseHandler db;
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
        db = new DatabaseHandler(getContext());
        exSpinner  =(Spinner)view.findViewById(R.id.main_ex_train_spinner);
        createTrain = (Button)view.findViewById(R.id.create_train);
        setDate = (Button)view.findViewById(R.id.select_date_button);
        typeSpinner  =(Spinner)view.findViewById(R.id.model_train_spinner);
        dateTextView =  (TextView)view.findViewById(R.id.train_date_textview);
        mYear = nowdate.get(Calendar.YEAR);
        mMonth = nowdate.get(Calendar.MONTH);
        mDay = nowdate.get(Calendar.DAY_OF_MONTH);
        createTrain.setOnClickListener(this);
        setDate.setOnClickListener(this);
        Calendar date = Calendar.getInstance();
        mYear = date.get(Calendar.YEAR);
        mMonth = date.get(Calendar.MONTH);
        mDay = date.get(Calendar.DAY_OF_MONTH);
        dateTextView.setTextColor(getResources().getColor(R.color.accent));
        dateTextView.setText(mDay+ "/" + mMonth+ "/" + mYear );
        return view;
    }


    public static String DetermineType(int type)
    {
        switch (type)
        {
            case 0: return "";
            case 1: return "W";
            case 2: return "G";
            case 3: return "M";
            case 4: return "WG";
            case 5: return "WM";
            case 6: return "GM";
            case 7: return "WGM";
            default : return "";
        }
    }
    @Override
    public void onClick(View v) {

        switch ((v.getId()))
        {
            case R.id.create_train:
            {
                alertClick();
                break;
            }
            case R.id.select_date_button:
            {
                showDatePicker();
                break;
            }
        }

    }

    private void alertClick()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final int type = typeSpinner.getSelectedItemPosition();
        final Item item =  db.SearchClientTraining(DetermineType(typeSpinner.getSelectedItemPosition()),exSpinner.getSelectedItem().toString());
        if(item !=null) {
            builder.setTitle("Название:" + item.getTrainName())
                    .setMessage("Упражнения\n" + item.getDescr())
                    .setNegativeButton("Закрыть",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                    .setNeutralButton("Обновить", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertClick();
                        }
                    })
                    .setCancelable(true).setPositiveButton("Создать", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    Date date =  new Date(mYear,mMonth,mDay);
                    db.addDiary(new DiaryItem(item.getId_tp_tr(),new Date(mYear,mMonth,mDay), 0));
                    Intent intent = new Intent(getActivity(), TrainActivity.class);
                    intent.putExtra("id_tp_tr", item.getId_tp_tr());
                    intent.putExtra("id_tr", item.getId_tr());
                    intent.putExtra("trainName", item.getTrainName());
                    intent.putExtra("descr", item.getDescr());
                    getActivity().startActivity(intent);
                }
            });
        }
        else {builder.setTitle("Тренеровок нет! " )
                .setMessage("Подключитесь к интерернету и нажмите кнопку Обновить в меню")
                .setNegativeButton("Закрыть",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        }
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();

        // set date of today
        date.setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Holo_Light_Dialog);
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(ondate);
        date.show(getChildFragmentManager(), "Date Picker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            dateTextView.setText(mDay+ "/" + mMonth+ "/" + mYear );
        }
    };

/* Класс для DatePicker Fragment*/

    public class DatePickerFragment extends DialogFragment {
        private static final String TAG = "DatePickerFragment";
        DatePickerDialog.OnDateSetListener ondateSet;

        public int year;
        public int month;
        public int day;

        public DatePickerFragment() {
        }

        public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
            ondateSet = ondate;
        }

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            year = args.getInt("year");
            month = args.getInt("month");
            day = args.getInt("day");
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
        }
    }

}
