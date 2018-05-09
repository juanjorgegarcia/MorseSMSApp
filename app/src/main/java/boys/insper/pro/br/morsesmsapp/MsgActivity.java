package boys.insper.pro.br.morsesmsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Debug;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MsgActivity extends AppCompatActivity {

    public String SelectedMsg;
    private int posCounter = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        final List<String> mensagens = new ArrayList<String>();

        mensagens.add("Estou em uma emergência! Venha rápido");
        mensagens.add("Estou com fome! Me alimente por favor!");
        mensagens.add("Preciso ir ao banheiro!");

        final ListView listaMensagens = (ListView) findViewById(R.id.list_msg);
        Button buttonNext = (Button) findViewById(R.id.button_next);
        Button buttonUp = (Button) findViewById(R.id.button_up);
        Button buttonDown = (Button) findViewById(R.id.button_down);
        Button buttonBack = (Button) findViewById(R.id.button_back);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, mensagens);

        listaMensagens.setAdapter(adapter);


        listaMensagens.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listaMensagens.setItemChecked(posCounter, true);
        listaMensagens.setDrawSelectorOnTop(true);


        buttonUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    vibrate();
                    if (posCounter > 0) {
                        posCounter -= 1;
                        listaMensagens.setItemChecked(posCounter, true);
                    }
                }
                return false;
            }
        });


        buttonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    vibrate();
                    if (posCounter < mensagens.size()-1) {
                        posCounter += 1;
                        listaMensagens.setItemChecked(posCounter, true);
                    }
                }
                return false;
            }
        });


        buttonNext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    vibrate();
                    SelectedMsg = listaMensagens.getItemAtPosition(posCounter).toString();
                    openSendActivity();
                }
                return false;
            }
        });


        buttonBack.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    vibrate();
                    openMorseActivity();
                }
                return false;
            }
        });


        listaMensagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posCounter = i;
            }
        });

    }

    private void openSendActivity() {
        Intent intent = new Intent(this, SendActivity.class);
        intent.putExtra("SelectedMessage", SelectedMsg);
        intent.putExtra("BackInfo", "msg");
        startActivity(intent);

        finish();
    }

    private void openMorseActivity() {
        Intent intent = new Intent(this, MorseActivity.class);
        startActivity(intent);

        finish();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50);
    }
}
