package ru.startandroid.mybook;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ru.startandroid.mybook.db.DatabaseHandler;
import ru.startandroid.mybook.db.DbTables.DiaryItem;

public class CustomTrainingFragment extends DialogFragment implements
    DialogInterface.OnClickListener {
        public int year,month,day;
        private View form=null;
        ArrayList<String> trainList;
        public ArrayAdapter<String> adapter;
        DatabaseHandler db;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            db = new DatabaseHandler(getContext());
            form= getActivity().getLayoutInflater()
                    .inflate(R.layout.fragment_calendar_list_train, null);
            ListView list  = (ListView)form.findViewById(R.id.calendar_train_list);
            trainList = new ArrayList<>();
            adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,trainList);
            list.setAdapter(adapter);
            List<DiaryItem> dieryList = db.SearchDieryList(new GregorianCalendar(year,month,day));
            for (DiaryItem item: dieryList)
            {
                trainList.add( "id tp_tr: " + item.get_id_tp_tr() + "Date: " + item.getDay().toString() + " state: " + item.getState());
                adapter.notifyDataSetChanged();
            }
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            db.close();

            return(builder.setTitle("Список тренировок ("+day+"/"+month+"/"+year +")").setView(form)
                    .setPositiveButton(android.R.string.ok, this).create());
        }
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
        @Override
        public void onDismiss(DialogInterface unused) {
            super.onDismiss(unused);
        }
        @Override
        public void onCancel(DialogInterface unused) {
            super.onCancel(unused);
        }
}

