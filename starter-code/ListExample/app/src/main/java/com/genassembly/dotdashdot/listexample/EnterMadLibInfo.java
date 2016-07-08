package com.genassembly.dotdashdot.listexample;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;


public class EnterMadLibInfo extends AppCompatActivity {

    EditAdapter adapty;
    ListView listy;
    ArrayList<MadLibs> libs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_mad_lib_info);

        libs = new ArrayList<>();
        libs.add(new MadLibs("Horror", 100, 3));

        listy = (ListView) findViewById(R.id.madList);

        adapty = new EditAdapter(this, libs);

        if (listy != null) {
            listy.setAdapter(adapty);
        }
    }
}
