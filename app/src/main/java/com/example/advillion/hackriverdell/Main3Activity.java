package com.example.advillion.hackriverdell;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class Main3Activity extends MainActivity {

    private static TextView countdownText;
    private static Button countdownButton;
    public static String current = "Start";


    private static CountDownTimer countDownTimer;
    private static long timeLeftInMilliseconds = 600000; //seconds * 1000, this will be a variable
    private static boolean timerRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        countdownText = (TextView)findViewById(R.id.countdown_text);
        countdownButton = (Button)findViewById(R.id.countdown_button);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main3Activity.this, MainActivity.class);
                startActivity(i);
            }
        });



        countdownButton.setText(current);
        countdownButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                startStop();
        }
    });
                updateTimer();
}



    public static void startStop () {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public static void startTimer () {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {

                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        current = "Pause";
        countdownButton.setText(current);

        timerRunning = true;
    }

    public static void stopTimer () {
        countDownTimer.cancel();
        current = "Start";
        countdownButton.setText(current);

        timerRunning = false;
    }

    public static void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10)
            timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }


}