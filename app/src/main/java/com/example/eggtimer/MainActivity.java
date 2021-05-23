package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerseekbar;
    TextView timertextview;
    Button  controllerbutton;
    Boolean counterisactive=false;
    CountDownTimer countDownTimer;
    public  void resettime()
    {
        timertextview.setText("0:30");
        timerseekbar.setProgress(30);               ///30 sec
        countDownTimer.cancel();
        controllerbutton.setText("GO..!");
        timerseekbar.setEnabled(true); /////re enable seek bar
        counterisactive=false;
    }
    public void updateTimer(int secondsleft)
    {
        int minutes=(int)secondsleft/60;
        int seconds=secondsleft-minutes*60;
        String secondstring=Integer.toString(seconds);
       if(seconds<=9)
        {
            secondstring="0"+secondstring;
        }
        timertextview.setText(Integer.toString(minutes)+":"+ secondstring);
    }
    public  void controlTimer(View view)
    {
        if(counterisactive==false) {


            counterisactive = true;
            timerseekbar.setEnabled(false);         //seek bar cannot be adjusted when go is pressed
            controllerbutton.setText("STOP");
           countDownTimer= new CountDownTimer(timerseekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);


                }

                @Override
                public void onFinish() {
                    Log.i("finished", "timer done ");
                   resettime();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);              ////this not working coz we are inside the function
                    mplayer.start();
                }
            }.start();
        }
        else
        {
           resettime();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       timerseekbar=(SeekBar)findViewById(R.id.timerseekBar);
       timertextview=(TextView)findViewById((R.id.timetextview));
       controllerbutton=(Button)findViewById(R.id.contollerbutton);
        timerseekbar.setMax(600);
        timerseekbar.setProgress(30);
        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progess, boolean b) {
                updateTimer(progess);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}