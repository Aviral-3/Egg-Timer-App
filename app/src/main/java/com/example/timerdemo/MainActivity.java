package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

public class MainActivity extends AppCompatActivity {
TextView timertv;
SeekBar arcSeekBar;
Boolean counterIsActive=false;
Button goButton;
CountDownTimer countDownTimer;
MediaPlayer mplayer;
public void resetTimer(){
    timertv.setText("0:30");
    arcSeekBar.setProgress(30);
    arcSeekBar.setEnabled(true);
    countDownTimer.cancel();
    goButton.setText("GO");
    counterIsActive=false;
}


    public void buttonClicked(View view) {
        if (counterIsActive) {
            resetTimer();
         }
          else {
            counterIsActive = true;
            arcSeekBar.setEnabled(false);
            goButton.setText("STOP!");


            countDownTimer = new CountDownTimer(arcSeekBar.getProgress() *1000 + 100, 1000) {
                @Override
                public void onTick(long l)
                {
                    UpdateTimer((int) l/1000);
                }


                public void onFinish() {
                     mplayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void UpdateTimer(int secondsLeft){
        int minutes=secondsLeft/60;
        int seconds=secondsLeft-(minutes*60);
         String secondString=Integer.toString(seconds);
        if(seconds<=9){
          secondString="0"+ secondString;
        }
        timertv.setText(Integer.toString(minutes)+":"+ secondString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         arcSeekBar=findViewById(R.id.seekBar);
        timertv=findViewById(R.id.textView);
        goButton=findViewById(R.id.button);
        arcSeekBar.setMax(600);
        arcSeekBar.setProgress(30);
        arcSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
            UpdateTimer(i);
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        /*new CountDownTimer(10000, 1000) {
            public void onTick(long millisecondIntoilDone) {

                Log.i("seconds left", String.valueOf(millisecondIntoilDone / 1000));

            }

            public void onFinish() {
                Log.i("we r done", "no more countdown");

            }


        }.start();*/
    }
}


/*
final Handler handler =new Handler();
        Runnable run=new Runnable() {
@Override
public void run() {
        Log.i("Hey its me aviral", "A second paased after saying that 😅 ");
        handler.postDelayed(this,1000);
        handler.post(run);
        }}
 */