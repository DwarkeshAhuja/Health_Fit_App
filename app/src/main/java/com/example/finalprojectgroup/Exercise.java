package com.example.finalprojectgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.PrecomputedText;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class Exercise extends AppCompatActivity {

    ImageView iv, excerciseImage, plank_pushup;
    public int counter;
    Button start, reset;
    TextView tv;
    Boolean status = false;
    int millisLeft;
    private boolean mTimerRunning;
    private CountDownTimer mCountDownTimer;
    private static final long START_TIME_IN_MILLIS = 600000;
    private long mTimeLeftInMillis;
    String exerciseType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        iv = findViewById(R.id.imageView);
        excerciseImage = findViewById(R.id.imageView2);
        plank_pushup = findViewById(R.id.imageView3);
        Intent intent = getIntent();
        exerciseType = intent.getStringExtra("Exercise_Type");
        plank_pushup.setVisibility(View.INVISIBLE);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (exerciseType.equals("Beginner")) {
            mTimeLeftInMillis = preferences.getLong("TimeLeftBegin", START_TIME_IN_MILLIS);
        } else if (exerciseType.equals("Intermediate")) {
            mTimeLeftInMillis = preferences.getLong("TimeLeftInter", START_TIME_IN_MILLIS);
        } else {
            mTimeLeftInMillis = preferences.getLong("TimeLeftAdvance", START_TIME_IN_MILLIS);
        }

        start = findViewById(R.id.Start);
        reset = findViewById(R.id.reset);
        tv = findViewById(R.id.time);
        updateCountDownTextForBeginner();
        if (exerciseType.equals("Beginner")) {
            iv.setImageResource(R.drawable.beginner);
        } else if (exerciseType.equals("Intermediate")) {
            iv.setImageResource(R.drawable.inter);
        } else {
            iv.setImageResource(R.drawable.advance);
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                if (exerciseType.equals("Beginner")) {
                    updateCountDownTextForBeginner();
                } else if (exerciseType.equals("Intermediate")) {
                    updateCountDownTextForIntermediate();
                } else {
                    updateCountDownTextForAdvance();
                }

            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                tv.setText("Start");
                start.setVisibility(View.INVISIBLE);
                reset.setText("Reset");
                reset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        start.setText("pause");
        reset.setText("Skip");
        //reset.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.VISIBLE);
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        if((String)start.getText() == "pause"){
            reset.setText("Reset");
        }else{
            reset.setText("Skip");
        }
        start.setText("Start");
        //reset.setText("Reset");
        reset.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {

        if((String) reset.getText()=="Reset"){
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            reset.setText("Skip");
        }else{
            reset.setText("Reset");
            mTimeLeftInMillis = (mTimeLeftInMillis / 1000) / 60;
            mTimeLeftInMillis = (mTimeLeftInMillis * 60) * 1000;
        }
        pauseTimer();

        updateCountDownTextForBeginner();
        reset.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
    }

    private void updateCountDownTextForBeginner() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        excerciseImage.setVisibility(View.VISIBLE);
        plank_pushup.setVisibility(View.INVISIBLE);
        if (minutes == 9 || minutes == 4) {
            if (seconds % 2 == 0 && seconds >= 40) {
                excerciseImage.setImageResource(R.drawable.squat_sit);
            } else if (seconds % 2 != 0 && seconds >= 40) {
                excerciseImage.setImageResource(R.drawable.squat_stand);
            } else {
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 8 || minutes == 3) {
            if (seconds % 2 == 0 && seconds >= 40) {
                excerciseImage.setVisibility(View.INVISIBLE);
                plank_pushup.setVisibility(View.VISIBLE);
                plank_pushup.setImageResource(R.drawable.pushup_down);
            } else if (seconds % 2 != 0 && seconds >= 40) {
                excerciseImage.setVisibility(View.INVISIBLE);
                plank_pushup.setVisibility(View.VISIBLE);
                plank_pushup.setImageResource(R.drawable.pushup_up);
            } else {
                excerciseImage.setVisibility(View.VISIBLE);
                plank_pushup.setVisibility(View.INVISIBLE);
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 7 || minutes == 2) {
            if (seconds % 2 == 0 && seconds >= 40) {
                excerciseImage.setImageResource(R.drawable.lunges_sit);
            } else if (seconds % 2 != 0 && seconds >= 40) {
                excerciseImage.setImageResource(R.drawable.lunges_stand);
            } else {
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 6 || minutes == 1) {
            excerciseImage.setVisibility(View.INVISIBLE);
            plank_pushup.setVisibility(View.VISIBLE);
            plank_pushup.setImageResource(R.drawable.plank);
        } else {
            excerciseImage.setVisibility(View.VISIBLE);
            plank_pushup.setVisibility(View.INVISIBLE);
            excerciseImage.setImageResource(R.drawable.rest);
        }
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tv.setText(timeLeftFormatted);
    }

    private void updateCountDownTextForIntermediate() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        excerciseImage.setVisibility(View.VISIBLE);
        plank_pushup.setVisibility(View.INVISIBLE);
        if (minutes == 9 || minutes == 4) {
            if (seconds % 2 == 0 && seconds >= 20) {
                excerciseImage.setImageResource(R.drawable.lunges_sit);
            } else if (seconds % 2 != 0 && seconds >= 20) {
                excerciseImage.setImageResource(R.drawable.lunges_stand);
            } else {
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 8 || minutes == 3) {
            if (seconds % 2 == 0 && seconds >= 20) {
                excerciseImage.setImageResource(R.drawable.squat_sit);
            } else if (seconds % 2 != 0 && seconds >= 20) {
                excerciseImage.setImageResource(R.drawable.squat_stand);
            } else {
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 7 || minutes == 2) {
            if (seconds % 2 == 0 && seconds >= 20) {
                excerciseImage.setVisibility(View.INVISIBLE);
                plank_pushup.setVisibility(View.VISIBLE);
                plank_pushup.setImageResource(R.drawable.pushup_down);
            } else if (seconds % 2 != 0 && seconds >= 20) {
                excerciseImage.setVisibility(View.INVISIBLE);
                plank_pushup.setVisibility(View.VISIBLE);
                plank_pushup.setImageResource(R.drawable.pushup_up);
            } else {
                excerciseImage.setVisibility(View.VISIBLE);
                plank_pushup.setVisibility(View.INVISIBLE);
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 6 || minutes == 1) {
            excerciseImage.setVisibility(View.INVISIBLE);
            plank_pushup.setVisibility(View.VISIBLE);
            plank_pushup.setImageResource(R.drawable.plank);
        } else {
            excerciseImage.setVisibility(View.VISIBLE);
            plank_pushup.setVisibility(View.INVISIBLE);
            excerciseImage.setImageResource(R.drawable.rest);
        }
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tv.setText(timeLeftFormatted);
    }

    private void updateCountDownTextForAdvance() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        excerciseImage.setVisibility(View.VISIBLE);
        plank_pushup.setVisibility(View.INVISIBLE);
        if (minutes == 9 || minutes == 4) {
            if (seconds % 2 == 0 && seconds >= 0) {
                excerciseImage.setVisibility(View.INVISIBLE);
                plank_pushup.setVisibility(View.VISIBLE);
                plank_pushup.setImageResource(R.drawable.pushup_down);
            } else if (seconds % 2 != 0 && seconds >= 0) {
                excerciseImage.setVisibility(View.INVISIBLE);
                plank_pushup.setVisibility(View.VISIBLE);
                plank_pushup.setImageResource(R.drawable.pushup_up);
            } else {
                excerciseImage.setVisibility(View.VISIBLE);
                plank_pushup.setVisibility(View.INVISIBLE);
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 8 || minutes == 3) {
            if (seconds % 2 == 0 && seconds >= 0) {
                excerciseImage.setImageResource(R.drawable.squat_sit);
            } else if (seconds % 2 != 0 && seconds >= 0) {
                excerciseImage.setImageResource(R.drawable.squat_stand);
            } else {
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 7 || minutes == 2) {
            if (seconds % 2 == 0 && seconds >= 0) {
                excerciseImage.setImageResource(R.drawable.lunges_sit);
            } else if (seconds % 2 != 0 && seconds >= 0) {
                excerciseImage.setImageResource(R.drawable.lunges_stand);
            } else {
                excerciseImage.setImageResource(R.drawable.rest);
            }
        } else if (minutes == 6 || minutes == 1) {
            excerciseImage.setVisibility(View.INVISIBLE);
            plank_pushup.setVisibility(View.VISIBLE);
            plank_pushup.setImageResource(R.drawable.plank);
        } else {
            excerciseImage.setVisibility(View.VISIBLE);
            plank_pushup.setVisibility(View.INVISIBLE);
            excerciseImage.setImageResource(R.drawable.rest);
        }
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tv.setText(timeLeftFormatted);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        if (exerciseType.equals("Beginner")) {
            editor.putLong("TimeLeftBegin", mTimeLeftInMillis);
        } else if (exerciseType.equals("Intermediate")) {
            editor.putLong("TimeLeftInter", mTimeLeftInMillis);
        } else {
            editor.putLong("TimeLeftAdvance", mTimeLeftInMillis);
        }
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}