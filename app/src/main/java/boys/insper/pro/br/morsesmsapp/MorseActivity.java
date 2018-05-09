package boys.insper.pro.br.morsesmsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    private Translator translator = new Translator();
    private boolean init = true;
    private boolean bsMode = false;
    private boolean timerbsRunning = false;
    private int startSpan = 0;
    private String[] mPlanetTitles;
    private ListView mDrawerList;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);
        RelativeLayout layout = findViewById(R.id.morseLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;

        final android.support.design.widget.FloatingActionButton buttonTap = findViewById(R.id.button_tap);
        textTap = findViewById(R.id.text_tap);
        progressTap = findViewById(R.id.progress_tap);
        textPreview = findViewById(R.id.text_tap2);


        final List<String> letraAZ = new ArrayList<String>();
        final List<String> morseAZ = new ArrayList<String>();
        final List<String> letraCodes = new ArrayList<String>();
        final List<String> morseCodes = new ArrayList<String>();
        List<DictionaryItem> dictionaryItems = new ArrayList<DictionaryItem>();

        for (int i = 0; i < 20; i++) {
//            letraAZ.add("lAZ");
//            morseAZ.add("mAZ");
//            letraCodes.add("lC");
//            morseCodes.add("mC");
            dictionaryItems.add(new DictionaryItem("lAZ", "mAZ", "lC", "mC"));
        }



        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerList = findViewById(R.id.left_drawer);

        // Set the adapter for the list view
//        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, letraAZ));

        //mDrawerList.setAdapter(new DictAdapter(letraAZ, morseAZ, letraCodes, morseCodes, this));
        mDrawerList.setAdapter(new DictAdapter(dictionaryItems, this));

        // Set the list's click listener
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());



        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if (init) {
                    buttonTap.setX(width/2 - buttonTap.getWidth()/2);

                    textTap.setText("Oh snap fam. Where'd ya found this.");

                    init = false;
                }

            }
        });


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
                    pressOutcome = "space";
                    textPreview.setText(pressOutcome);
                }

            }

        };

        final CountDownTimer timerbs = new CountDownTimer(500, 10) {
            @Override
            public void onTick(long l) {
                progressTap.setProgress((int) l*100 / 500);
            }

            @Override
            public void onFinish() {
                if (startSpan > 0) {
                    startSpan--;
                    addHighlight();
                    start();
                }
            }

        };


        buttonTap.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int longPress2;

                if (width/2 - event.getRawX() > buttonTap.getWidth() / 2 & !bsMode) {
                    bsMode = true;
                    pressOutcome = "";
                    textPreview.setText("");
                    progressTap.setProgress(0);
                }

                if (bsMode) {
                    if (event.getRawX() > width / 2) {
                        buttonTap.setX(width / 2 - buttonTap.getWidth() / 2);
                    } else if (event.getRawX() < (width / 2 - buttonTap.getWidth())) {
                        buttonTap.setX((width / 2 - buttonTap.getWidth()) - buttonTap.getWidth() / 2);
                        buttonTap.setBackgroundTintList(ColorStateList.valueOf(getApplicationContext().getResources().getColor(R.color.colorChange)));
                        if (!timerbsRunning) {
                            addHighlight();
                            timerbs.start();
                            timerbsRunning = true;
                        }
                    } else {
                        buttonTap.setX(event.getRawX() - buttonTap.getWidth() / 2);
                        buttonTap.setBackgroundTintList(ColorStateList.valueOf(getApplicationContext().getResources().getColor(R.color.colorAccent)));
                        timerbs.cancel();
                        timerbsRunning = false;
                        progressTap.setProgress(0);

                    }

                    timer2.cancel();
                }



                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    longPressTime = (int) (long) System.currentTimeMillis();
                    timer.cancel();
                    timer2.start();
                    startSpan = textTap.length();

                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    longPress2 = (int) (long) System.currentTimeMillis();
                    //Log.d("long2-long1", String.valueOf(longPress2 - longPressTime));

                    backspace();
                    timerbs.cancel();
                    timerbsRunning = false;
                    buttonTap.setX(width/2 - buttonTap.getWidth()/2);
                    buttonTap.setBackgroundTintList(ColorStateList.valueOf(getApplicationContext().getResources().getColor(R.color.colorAccent)));
                    timer2.cancel();
                    progressTap.setProgress(0);

                    if (!bsMode) {
                        if (pressOutcome == "space") {
                            textTap.setText(textTap.getText() + " ");
                            pressOutcome = "";
                            textPreview.setText("");

                        } else {
                            if (longPress2 - longPressTime < 140) {
                                pressOutcome += ".";
                            } else {
                                pressOutcome += "-";
                            }

                            textPreview.setText(pressOutcome);
                            timer.start();
                        }
                    }

                    bsMode = false;
                }

                return false;
            }
        });


    }


    private void newWord(String press) {

        try {
            String saveMe = translator.morseToChar(press) + "";

            if (press.length() <= 5 & translator.morseToChar(press) != '_' & translator.morseToChar(press) != ' ') {

                textTap.setText(textTap.getText() + saveMe);
                pressOutcome = "";
                textPreview.setText("");
            }
            else {
                pressOutcome = "";
                textPreview.setText("");
            }
        }
        catch (NullPointerException ex) {
            pressOutcome = "";
            textPreview.setText("");
        }

    }

    private void backspace() {

        if (textTap.length() - startSpan > 0) {
            textTap.setText(textTap.getText().subSequence(0, startSpan));
        }
    }

    private void addHighlight() {
        SpannableString str = new SpannableString(textTap.getText());
        str.setSpan(new BackgroundColorSpan(Color.LTGRAY), startSpan, textTap.length(), 0);
        textTap.setText(str);
    }

}