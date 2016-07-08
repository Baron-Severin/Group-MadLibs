package com.genassembly.dotdashdot.listexample;


import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class EnterMadLibInfo extends AppCompatActivity {

    EditAdapter adapty;
    ListView listy;
    ArrayList<String> libs;
    ArrayList<String> string;
    Context context;
    TextToSpeech textToSpeech;
    Button magicMaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_mad_lib_info);
        context = getApplicationContext();
        magicMaker = (Button) findViewById(R.id.makeMagic);

        magicMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initButton();
            }
        });
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });



//        strings=madLib.madLibs is the array list of string for the madlib, alternating between test block and blank

        Intent intent = getIntent();



        string = intent.getStringArrayListExtra("MAD_LIB");

        libs = new ArrayList<>();

        for (int i=1;i<(string.size());i+=2) {
            libs.add(string.get(i));
        }

        listy = (ListView) findViewById(R.id.madList);

        adapty = new EditAdapter(this, libs);

        if (listy != null) {
            listy.setAdapter(adapty);
        }

    }

    private void initButton() {
        boolean isContinue = true;
        for (int i =0; i < listy.getChildCount(); i++) {
            View v = listy.getChildAt(i);
            EditText newEddy = (EditText) v.findViewById(R.id.FIELD10);

            if (!newEddy.getText().toString().isEmpty()) {
                string.set((2*i+1), newEddy.getText().toString());
            } else {
                isContinue = false;
            }
        }

        if (isContinue){
            for (int i = 0; i < string.size(); i++) {
                Log.i("OUR ARRAYLIST<STRING> ", string.get(i));
            }

            Intent newIntent = new Intent(context, ResultActivity.class);
            newIntent.putExtra("MAD_OUT", string);
        } else {
            String message = "Please fill out all fields";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            textToSpeech.setSpeechRate(.9f);
            if (!textToSpeech.isSpeaking()) {
                textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            }
        }

    }
}
