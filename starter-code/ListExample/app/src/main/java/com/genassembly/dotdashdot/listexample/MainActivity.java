package com.genassembly.dotdashdot.listexample;

import android.content.Intent;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    SimpleAdapter adapty;
    ListView listy;
    Button head1;
    Button head2;
    Button head3;
    ArrayList<MadLibs> libs;
    View.OnClickListener sortWords;
    View.OnClickListener sortGenre;
    View.OnClickListener sortSpaces;
    public static ArrayList<MadLibs> madLibsHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        head1 = (Button) findViewById(R.id.header1);
        head2 = (Button) findViewById(R.id.header2);
        head3 = (Button) findViewById(R.id.header3);

        libs = new ArrayList<>();
        libs.add(new MadLibs("Horror", 100, 20));

        ArrayList<String> arrayarray=new ArrayList<>();
        arrayarray.add("Blah Blah");
        arrayarray.add("*");
        arrayarray.add("Blah Blah");
        arrayarray.add("*");
        arrayarray.add("Blah Blah");
        arrayarray.add("*");
        arrayarray.add("Blah Blah");
        arrayarray.add("*");
        arrayarray.add("Blah Blah");


        libs.add(new MadLibs("Fantasy", 12, 123));
        libs.get(0).madLibs=arrayarray;
        libs.add(new MadLibs("Comedy", 10001243, 35));
        libs.add(new MadLibs("Animation", 2134, 212));
        libs.add(new MadLibs("Fantasy", 22, 43));
        libs.add(new MadLibs("Fantasy", 22, 43));

        listy = (ListView) findViewById(R.id.mainList);
        adapty = new SimpleAdapter(this, libs);
        if (listy != null) {
            listy.setAdapter(adapty);
        }

        sortWords = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByWords(view);
            }
        };
        sortGenre = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByGenre(view);
            }
        };
        sortSpaces = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortBySpaces(view);
            }
        };

        madLibsHolder = new ArrayList<MadLibs>();

    }

    public void sortByWords(View v) {
        Collections.sort(libs, new Comparator<MadLibs>() {
            @Override public int compare(MadLibs p1, MadLibs p2) {
                return p1.getWords() - p2.getWords(); // Ascending
            }
        });
        adapty.notifyDataSetChanged();
    }
    public void sortByGenre(View v) {
        Collections.sort(libs, new Comparator<MadLibs>() {
            @Override public int compare(MadLibs p1, MadLibs p2) {
                return p1.getGenre().compareTo(p2.getGenre()); // Ascending
            }
        });
        adapty.notifyDataSetChanged();
    }
    public void sortBySpaces(View v) {
        Collections.sort(libs, new Comparator<MadLibs>() {
            @Override public int compare(MadLibs p1, MadLibs p2) {
                return p1.getSpaces() -  p2.getSpaces(); // Ascending
            }
        });
        adapty.notifyDataSetChanged();
    }

    public void openIngestMadLib(View view) {
        Intent intent = new Intent(this.getApplicationContext(), IngestMadlib.class);
        this.startActivity(intent);
    }
}
