package com.genassembly.dotdashdot.listexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class IngestMadlib extends AppCompatActivity {

    TextView textView;
    EditText editMadlib;
    Button buttonAddText;
    EditText editGenre;
    Button buttonComplete;
    Spinner spinner;
    Button buttonSpinner;
    Button resetAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingest_madlib);

        textView = (TextView) findViewById(R.id.constructorTextView);
        editMadlib = (EditText) findViewById(R.id.constructorEditTextMadLib);
        buttonAddText = (Button) findViewById(R.id.constructorButtonAddText);
        editGenre = (EditText) findViewById(R.id.constructorEditGenreName);
        buttonComplete = (Button) findViewById(R.id.constructorButtonMadLibComplete);
        spinner = (Spinner) findViewById(R.id.constructorSpinnerSpace);
        buttonSpinner = (Button) findViewById(R.id.constructorButtonForSpinner);
        resetAll = (Button) findViewById(R.id.constructorButtonResetInput);



    }

}
