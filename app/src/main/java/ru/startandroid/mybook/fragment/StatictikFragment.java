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
                Show();
                break;
            }
            case R.id.delBut:
            {
                db.deleteAll();
                break;
            }
        }
    }

    public void Show()
    {
        List<Training> traings = db.getAllTraining();

        /*List<TypeAndTrain> ty = db.getAllTypeAndTrain();
        for( TypeAndTrain tr : ty )
        {
            trainList.add( "id tp_tr: " + tr.get_id_tp_tr() + "id_tp: " + tr.get_id_tp() + " id_tr: " + tr.get_id_tr());
            adapter.notifyDataSetChanged();
        }*/

        List<DiaryItem> diery = db.getAllDiary();

        /*for(DiaryItem tr: diery)
        {
            db.deleteDiaryItem(tr);
        }*/
    }



}

