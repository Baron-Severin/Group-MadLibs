package com.genassembly.dotdashdot.listexample;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;


public class EnterMadLibInfo extends AppCompatActivity {

    EditAdapter adapty;
    ListView listy;
    ArrayList<String> libs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_mad_lib_info);



//        strings=madLib.madLibs is the array list of string for the madlib, alternating between test block and blank

        Intent intent = getIntent();



        ArrayList<String> string = intent.getStringArrayListExtra("MAD_LIB");

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
}
