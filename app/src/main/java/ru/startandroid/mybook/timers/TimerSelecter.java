package ru.startandroid.mybook.timers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import ru.startandroid.mybook.R;

public class TimerSelecter extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_selecter);
        initToolbar();
        initButtons();
    }
    void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void initButtons()
    {
        Button usTimerButton = (Button)findViewById(R.id.butt_usual_timer);
        usTimerButton.setOnClickListener(this);
        Button tabataTimerButton = (Button)findViewById(R.id.butt_tabato_timer);
        tabataTimerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.butt_usual_timer:
            {
                Intent intent = new Intent(TimerSelecter.this,UsualTimer.class);
                startActivity(intent);
                break;
            }
            case R.id.butt_tabato_timer:
            {
                Intent intent = new Intent(TimerSelecter.this,CountDownTimer.class);
                startActivity(intent);
                break;
            }
        }
    }
}
