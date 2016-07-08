package com.genassembly.dotdashdot.listexample;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
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
    String[] spinnerContents;
    boolean lastAddedWasString;

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
//        spinnerInts = new int[]{R.string.ADJECTIVE, R.string.ANIMAL, R.string.GAME, R.string.NAME, R.string.NOUN, R.string.VERB};
        spinnerContents = new String[] {"*ADJECTIVE*", "*ANIMAL*", "*GAME*", "*NAME*", "*NOUN*", "*VERB*"};
        ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerContents);
        stringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringAdapter);
        lastAddedWasString = false;
    }

    public void addMLText(View view) {

        if (lastAddedWasString) {
            String message = "Please enter a space before adding more text";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            textToSpeech.setSpeechRate(.9f);
            if (!textToSpeech.isSpeaking()) {
                textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            }
        } else if (validateMLText()) {
            // add text
            String newChunk = editMadlib.getText().toString();
            currentDisplayText += " " + newChunk;
            currentTextChunks.add(newChunk);
            textView.setText(currentDisplayText);
            editMadlib.setText("");
            lastAddedWasString = true;
        } else {
            String message = "Please enter text into the field";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            textToSpeech.setSpeechRate(.9f);
            if (!textToSpeech.isSpeaking()) {
                textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }

    public void addSpace (View view) {

        if (!lastAddedWasString){
            String message = "Please enter text before adding another space";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            textToSpeech.setSpeechRate(.9f);
            if (!textToSpeech.isSpeaking()) {
                textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            }
        } else {
            String newChunk = spinner.getSelectedItem().toString();
            currentDisplayText += " " + newChunk;
            currentTextChunks.add(newChunk);
            textView.setText(currentDisplayText);
            lastAddedWasString = false;
        }
    }

    public boolean validateMLText() {
        if (editMadlib.getText() == null || editMadlib.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void resetAllText(View view) {
        currentTextChunks = new ArrayList<String>();
        currentDisplayText = "";
        editMadlib.setText("");
        lastAddedWasString = false;
        editGenre.setText("");
        textView.setText(currentDisplayText);
    }

    public void finishML(View view) {
        if (editGenre.getText().length() != 0) {
            String genre = editGenre.getText().toString();
            MadLibs madLib = new MadLibs(genre, currentTextChunks);
            MainActivity.madLibsHolder.add(madLib);
            resetAllText(view);
        } else {
            String message = "Please enter a genre before finishing your MadLib";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            textToSpeech.setSpeechRate(.9f);
            if (!textToSpeech.isSpeaking()) {
                textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }
}
