package boys.insper.pro.br.morsesmsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SendActivity extends AppCompatActivity {

    private int posCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);


        Bundle b = getIntent().getExtras();
        final String message = b.getString("SelectedMessage");


        final List<String> numeros = new ArrayList<String>();
        final List<String> contatos = new ArrayList<String>();

        numeros.add("+5517997812170");
        numeros.add("+5511994438280");
        numeros.add("+5511983752928");
        numeros.add("+5511991836878");
        numeros.add("+5517991076213");

        contatos.add("Tarraf");
        contatos.add("Terabyte");
        contatos.add("Folginha");
        contatos.add("Gui");
        contatos.add("Juan");

        final ListView listaContatos = (ListView) findViewById(R.id.list_msg);
        Button buttonSend = (Button) findViewById(R.id.button_send);
        Button buttonUp = (Button) findViewById(R.id.button_up);
        Button buttonDown = (Button) findViewById(R.id.button_down);
        Button buttonBack = (Button) findViewById(R.id.button_back);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, contatos);

        listaContatos.setAdapter(adapter);


        listaContatos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listaContatos.setItemChecked(posCounter, true);
        listaContatos.setDrawSelectorOnTop(true);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posCounter > 0) {
                    posCounter -= 1;
                    listaContatos.setItemChecked(posCounter, true);
                }
            }
        });

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posCounter < numeros.size()-1) {
                    posCounter += 1;
                    listaContatos.setItemChecked(posCounter, true);
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMsgActivity();
            }
        });

        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posCounter = i;
            }
        });


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String numero = numeros.get(posCounter);

                SmsManager manager = SmsManager.getDefault();

                manager.sendTextMessage(numero, null, message, null, null);

                Utils.showToast(getApplicationContext(), "Sua mensagem foi enviada!");

                Log.d("oi", contatos.get(posCounter)+ " " + numero + " : " + message);
            }
        });


    }

    private void openMsgActivity() {
        Intent intent = new Intent(this, MsgActivity.class);
        startActivity(intent);

        finish();
    }
}
