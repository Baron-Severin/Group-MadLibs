package com.genassembly.dotdashdot.listexample;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class IngestMadlib extends AppCompatActivity {

    TextView textView;
    EditText editMadlib;
    Button buttonAddText;
    EditText editGenre;
    Button buttonComplete;
    Spinner spinner;
    Button buttonSpinner;
    Button resetAll;
    TextToSpeech textToSpeech;
    String currentDisplayText;
    ArrayList<String> currentTextChunks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingest_madlib);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        currentDisplayText = "";
        currentTextChunks = new ArrayList<String>();

        textView = (TextView) findViewById(R.id.constructorTextView);
        editMadlib = (EditText) findViewById(R.id.constructorEditTextMadLib);
        buttonAddText = (Button) findViewById(R.id.constructorButtonAddText);
        editGenre = (EditText) findViewById(R.id.constructorEditGenreName);
        buttonComplete = (Button) findViewById(R.id.constructorButtonMadLibComplete);
        spinner = (Spinner) findViewById(R.id.constructorSpinnerSpace);
        buttonSpinner = (Button) findViewById(R.id.constructorButtonForSpinner);
        resetAll = (Button) findViewById(R.id.constructorButtonResetInput);

    }

    public void addMLText(View view) {

        if (validateMLText()) {
            String newChunk = editMadlib.getText().toString();
            currentDisplayText += " " + newChunk;
            currentTextChunks.add(newChunk);
            textView.setText(currentDisplayText);
        } else {
            String message = "Please enter text into the field";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            textToSpeech.setSpeechRate(.9f);
            if (!textToSpeech.isSpeaking()) {
                textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }

    public boolean validateMLText() {
        if (editMadlib.getText() == null || editMadlib.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
