package ru.startandroid.mybook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import ru.startandroid.mybook.db.DatabaseHandler;
import ru.startandroid.mybook.fragment.TrainFragment;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner trainType;
    Spinner trainRound;
    Button downloadButton;
    Spinner trainEx;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseHandler(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        InitSpinner();
        downloadButton = (Button)findViewById(R.id.download_button);
        downloadButton.setOnClickListener(this);


    }
    void InitSpinner ()
    {
        trainType = (Spinner)findViewById(R.id.model_train_spinner);
        trainRound = (Spinner)findViewById(R.id.type_round_train_spinner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.download_button:
                downLoadSetting();

                break;
        }
    }

    void downLoadSetting()
    {
        downloadButton.setClickable(false);
        HashMap<String,String> param = new HashMap<String,String>();
        param.put("maxId",db.getMaxTypeAndTrainID().toString());
        param.put("user", "u0123039_admin");
        param.put("pass", "axel1994");
        param.put("db", "u0123039_trains");
        String type = TrainFragment.DetermineType(trainType.getSelectedItemPosition());
        if ( type!="" )
        {
            param.put("type", type);
        }
        if (! trainRound.getSelectedItem().toString().contains("Любая") )
        {
            String  str ;
            if (trainRound.getSelectedItem().toString().contains("Раунды"))
            {
                str = "раундов";
            }
            else{
                str = trainRound.getSelectedItem().toString();
            }
            param.put("rnd", str);
        }

        DownloadAsyncTask splashScreenAsyncTask = new DownloadAsyncTask(this);
        splashScreenAsyncTask.param = param;
        splashScreenAsyncTask.execute();
    }



    private class DownloadAsyncTask extends AsyncTask <Void, Void, Void>{
        public HashMap<String,String> param;
        boolean checkTrain = false;
        DownloadActivity activity;
        ApiConnector connector = new ApiConnector(db);
        public DownloadAsyncTask(DownloadActivity activity) {
            this.activity = activity;
            checkTrain = false;
        }

        @Override
        protected Void doInBackground(Void... params) {
            checkTrain = false;
            connector.getAllTraining(checkTrain,"getNewJsonTraining.php",param);
            return null;
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent myIntent = new Intent(activity, MainActivity.class);
                    activity.startActivity(myIntent);
                    activity.finish();
                }
            }, 100);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent myIntent = new Intent(activity, MainActivity.class);
                    activity.startActivity(myIntent);
                    activity.finish();
                }
            }, 100);
        }
    }


}
