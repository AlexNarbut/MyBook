package ru.startandroid.mybook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.mybook.R;
import ru.startandroid.mybook.db.DatabaseHandler;
import ru.startandroid.mybook.db.DbTables.DiaryItem;
import ru.startandroid.mybook.db.DbTables.Item;
import ru.startandroid.mybook.db.DbTables.Training;
import ru.startandroid.mybook.db.DbTables.Type;
import ru.startandroid.mybook.db.DbTables.TypeAndTrain;

public class StatictikFragment extends Fragment implements View.OnClickListener{
    private static final int LAYOUT = R.layout.fragment_statictiks;
    private View view;
    ArrayList<String> trainList;
    public ArrayAdapter<String> adapter;
    DatabaseHandler db;
    TextView itemText;
    public static StatictikFragment getInstance(){
        Bundle args = new Bundle();
        StatictikFragment fragment1 = new StatictikFragment();
        fragment1.setArguments(args);
        return fragment1;
    }
    public StatictikFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        db = new DatabaseHandler(getContext());

        ListView listView = (ListView)view.findViewById(R.id.listTrain);
        trainList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,trainList);
        listView.setAdapter(adapter);
        itemText = (TextView)view.findViewById(R.id.itemText);
        Button lkbut = (Button)view.findViewById(R.id.lkBut);
        lkbut.setOnClickListener(this);
        Button delbut = (Button)view.findViewById(R.id.delBut);
        delbut.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.lkBut:
            {
                ShowList();
                break;
            }
            case R.id.delBut:
            {
                DelList();
                break;
            }
        }
    }

    private void DelList() {
        db.deleteAll();
        trainList.clear();
        adapter.notifyDataSetChanged();
    }

    void ShowList() {

        trainList.clear();
        Log.d("myLog", "TABLES --------------------");
        Log.d("myLog", "Inserting Types");
        db.addType(new Type("Empty 1 "));
        db.addType(new Type("Empty as1dasd "));
        List<Type> types = db.getAllType();
        for (Type cn : types) {
            String log = "Id: " + cn.get_id_tp() + " ,Name: " + cn.get_name_tp();
            Log.d("myLog", log);
        }
        Log.d("myLog", "------------------");
        Log.d("myLog", "Inserting Trainings");
        db.addTraining(new Training("say-say", "ds111afd", "sa222dasd", "2222"));
        db.addTraining(new Training("say-say", "ds111afd", "sa222dasd", "asdasd"));
        db.addTraining(new Training("say-say", "ds111afd", "sa222dasd", "asd2eqwa"));
        List<Training> trains = db.getAllTraining();
        for (Training cn : trains) {
            String log = "Id: " + cn.get_id() + " ,Name: " + cn.get_name() +",Descr: " + cn.get_tr_descr();
            Log.d("myLog", log);
        }
        Log.d("myLog", "------------------");
        Log.d("myLog", "Inserting Type_Train");
        db.addTypeandTran(new TypeAndTrain(1, 1));
        db.addTypeandTran(new TypeAndTrain(2,2));
        List<TypeAndTrain> tp = db.getAllTypeAndTrain();
        for (TypeAndTrain cn : tp) {
            String log = "Id: " + cn.get_id_tp_tr() + " ,id_tp: " + cn.get_id_tp() + ",id_tr: " + cn.get_id_tr();
            Log.d("myLog", log);
            trainList.add(log);
            adapter.notifyDataSetChanged();
        }

       Log.d("myLog", "------------------");
        Log.d("myLog", "Inserting Diary");
        db.addDiary(new DiaryItem(1,(byte)1));
        db.addDiary(new DiaryItem(2, (byte) 2));
        List<DiaryItem> diary = db.getAllDiary();
        for (DiaryItem item : diary) {
            String log = "Id: " + item.get_id_dr() + " ,id_tr_tp: " + item.get_id_tp_tr() + ",date: " + item.getDay()+ " state: " + item.getState();
            Log.d("myLog", log);
        }
        Log.d("myLog", "------------------");
        Item item =  db.SearchClientTraining();
        itemText.setText("id:" +item.getId_tp_tr() + "id_tr:" + item.getId_tr() + "descr: " +item.getDescr());

    }



}

