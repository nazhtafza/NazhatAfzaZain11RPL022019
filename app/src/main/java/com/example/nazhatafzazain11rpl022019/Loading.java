package com.example.nazhatafzazain11rpl022019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class Loading extends AppCompatActivity {
    ProgressBar progressBar;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar=findViewById(R.id.pbLoading);
        final Timer time= new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressBar.setProgress(counter);
                if (counter==100){
                    time.cancel();
                }
            }
        };
        time.schedule(timerTask,0,100);
    }
}
