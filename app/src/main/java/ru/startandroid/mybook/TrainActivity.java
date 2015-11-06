package ru.startandroid.mybook;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ru.startandroid.mybook.R;

public class TrainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    String nameTrain;
    Integer id_tr;
    Integer id_tp_tr;
    String descr;
    TextView trainName;
    TextView trainDescr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        nameTrain =  getIntent().getExtras().getString("trainName");
        id_tr =  getIntent().getExtras().getInt("id_tr");
        id_tp_tr =  getIntent().getExtras().getInt("id_tp_tr");
        descr =  getIntent().getExtras().getString("descr");
        trainName = (TextView)findViewById(R.id.trainName);
        trainDescr = (TextView)findViewById(R.id.trainDescr);
        trainName.setText(nameTrain);
        trainDescr.setText(descr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_train, menu);
        return true;
    }

}
