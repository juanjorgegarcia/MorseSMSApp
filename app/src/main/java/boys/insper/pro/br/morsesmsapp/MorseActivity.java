package boys.insper.pro.br.morsesmsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MorseActivity extends AppCompatActivity {

    private int longPressTime = 0;
    private CountDownTimer timer;
    private String pressOutcome = "";
    private TextView textTap;
    private ProgressBar progressTap;
    private TextView textPreview;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);


        android.support.design.widget.FloatingActionButton buttonTap = findViewById(R.id.button_tap);
        textTap = findViewById(R.id.text_tap);
        progressTap = findViewById(R.id.progress_tap);
        textPreview = findViewById(R.id.text_tap2);


        timer = new CountDownTimer(500, 10) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                newWord(pressOutcome);
            }

        };

        final CountDownTimer timer2 = new CountDownTimer(500, 10) {
            @Override
            public void onTick(long l) {
                progressTap.setProgress( 105 - ((int) l*100 / 500));
            }

            @Override
            public void onFinish() {
                if (pressOutcome == "") {
                    pressOutcome = "0";
                    textPreview.setText(pressOutcome);
                }

            }

        };


        buttonTap.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int longPress2;

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    longPressTime = (int) (long) System.currentTimeMillis();
                    timer.cancel();
                    timer2.start();
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    longPress2 = (int) (long) System.currentTimeMillis();
                    Log.d("long2-long1", String.valueOf(longPress2 - longPressTime));

                    timer2.cancel();

                    if (pressOutcome == "0") {
                        newWord(pressOutcome);
                        progressTap.setProgress(0);

                    }
                    else {
                        progressTap.setProgress(0);


                        if (longPress2 - longPressTime < 140) {
                            pressOutcome += ".";
                        } else {
                            pressOutcome += "-";
                        }

                        textPreview.setText(pressOutcome);
                        timer.start();
                    }

                }

                return false;
            }
        });


    }


    private void newWord(String press) {
        textTap.setText(textTap.getText() + " " + press);
        pressOutcome = "";
        textPreview.setText("");
    }

}
