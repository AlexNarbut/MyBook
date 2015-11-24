package ru.startandroid.mybook.timers;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ru.startandroid.mybook.R;

public class UsualTimer extends AppCompatActivity implements View.OnClickListener {

    TextView digitalClock;
    Button firstButton;
    ArrayList<String> timeList;
    public ArrayAdapter<String> adapter;
    ListView timeView;
    boolean chechPause = false;
    Button secondButton;
    Timer timer;
    int mCurrentPeriod = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usual_timer);
        initToolbar();
        initTimerSet();
    }

    void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    void initTimerSet()
    {
        firstButton = (Button)findViewById(R.id.butt_first_us_timer);
        firstButton.setText("Старт");
        firstButton.setBackgroundColor(Color.GREEN);
        firstButton.setOnClickListener(this);
        secondButton = (Button)findViewById(R.id.butt_second_us_timer);
        secondButton.setText("Сброс");
        secondButton.setOnClickListener(this);
        timeView = (ListView)findViewById(R.id.listTimer);
        timeList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,timeList);
        timeView.setAdapter(adapter);
        digitalClock =  (TextView)findViewById(R.id.time_view);
        digitalClock.setText("00:00:00");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.butt_first_us_timer:
            {
                if (chechPause == false)
                {
                    firstButton.setBackgroundColor(getResources().getColor(R.color.accent));
                    firstButton.setText("Пауза");
                    secondButton.setText("Круг");
                    chechPause = true;
                        onStartButtonClick();
                        break;
                }
                else {
                    onPauseButtonClick();

                    break;
                }


            }
            case R.id.butt_second_us_timer:
            {
                if (chechPause == false)
                {
                    onResetButtonClick();
                    break;
                }else {
                    int size = timeList.size()+1;
                    timeList.add("Круг: " + size + "\t\t\t\tВремя: " + digitalClock.getText().toString());
                    adapter.notifyDataSetChanged();
                    break;
                }
            }
        }
    }

    public void onStartButtonClick(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        }, 0, 10);
    }

    void  TimerMethod()
    {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        @Override
        public void run() {
            mCurrentPeriod++;
            Date date =  new Date(mCurrentPeriod*10);
            String temp = (new SimpleDateFormat("mm:ss.SS")).format(date);
            digitalClock.setText(temp);
        }
    };

    public void onPauseButtonClick() {
        if(timer!= null) {
            timer.cancel();
            chechPause = false;
            firstButton.setText("Старт");
            firstButton.setBackgroundColor(Color.GREEN);
            secondButton.setText("Сброс");
        }
    }

    public void onResetButtonClick() {
        mCurrentPeriod = 0;
        if (timer != null) {
            chechPause= false;
            digitalClock.setText("00:00:00");
            timeList.clear();
            adapter.notifyDataSetChanged();
        }
    }



}
