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

import ru.startandroid.mybook.MainActivity;
import ru.startandroid.mybook.R;
import ru.startandroid.mybook.TrainActivity;
import ru.startandroid.mybook.db.DatabaseHandler;
import ru.startandroid.mybook.db.DbTables.DiaryItem;
import ru.startandroid.mybook.db.DbTables.Item;
import ru.startandroid.mybook.db.DbTables.Type;

/**
 * Created by Алексей on 07.10.2015.
 */
public class TrainFragment  extends Fragment implements View.OnClickListener{
    private static final int LAYOUT = R.layout.fragment_train;
    private Button createTrain;
    private Spinner typeSpinner;
    private  Spinner exSpinner;
    private int year,month,day;
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
        typeSpinner  =(Spinner)view.findViewById(R.id.model_train_spinner);
        year = nowdate.get(Calendar.YEAR);
        month = nowdate.get(Calendar.MONTH);
        day = nowdate.get(Calendar.DAY_OF_MONTH);
        createTrain.setOnClickListener(this);
        return view;
    }


    private String DetermineType(int type)
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
                AlertClick();
                break;
            }
        }

    }

    private void AlertClick()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int type = typeSpinner.getSelectedItemPosition();
        List<Type> types = db.getAllType();
        for( Type tr : types )
        {
            Log.d("TYPE", tr.get_name_tp());
        }
        final Item item =  db.SearchClientTraining(DetermineType(type),exSpinner.getSelectedItem().toString());
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
                            AlertClick();
                        }
                    })
                    .setCancelable(true).setPositiveButton("Создать", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
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




}
