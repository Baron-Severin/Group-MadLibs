package com.genassembly.dotdashdot.listexample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final ArrayList<MadLibs> libs;
    private int sortBy;
    private final Context context;

    public SimpleAdapter(Context context, ArrayList<MadLibs> libs) {
        //super();
        inflater = LayoutInflater.from(context);
        this.libs = libs;
        this.sortBy = R.string.SORT_BY_WORDS;
        this.context = context;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public int getCount() {
        return libs.size();
    }

    @Override
    public Object getItem(int position) {
        return libs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View child, ViewGroup parent) {

        Log.d("Postion: " , "" + position);

        View v = child;
        TextView words, genre, spaces;

        if (v == null) {
            v = inflater.inflate(R.layout.list_item, parent, false);
        }
        if (this.sortBy == R.string.SORT_BY_WORDS) {
            words = (TextView) v.findViewById(R.id.FIELD1);
            genre = (TextView) v.findViewById(R.id.FIELD2);
            spaces = (TextView) v.findViewById(R.id.FIELD3);
        } else if (this.sortBy == R.string.SORT_BY_GENRE) {
            genre = (TextView) v.findViewById(R.id.FIELD1);
            spaces = (TextView) v.findViewById(R.id.FIELD2);
            words = (TextView) v.findViewById(R.id.FIELD3);
        } else if (this.sortBy == R.string.SORT_BY_SPACES) {
            spaces = (TextView) v.findViewById(R.id.FIELD1);
            words = (TextView) v.findViewById(R.id.FIELD2);
            genre = (TextView) v.findViewById(R.id.FIELD3);
        } else {
            Log.d("MAIN", "SORT_BY conditional not met, using default");
            words = (TextView) v.findViewById(R.id.FIELD1);
            genre = (TextView) v.findViewById(R.id.FIELD2);
            spaces = (TextView) v.findViewById(R.id.FIELD3);
        }

        words.setText(String.valueOf(libs.get(position).getWords()));
        genre.setText(String.valueOf(libs.get(position).getGenre()));
        spaces.setText(String.valueOf(libs.get(position).getSpaces()));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Adapter", "Clicked " + position);
                Intent intent = new Intent(context.getApplicationContext(), EnterMadLibInfo.class);
                intent.putExtra("MAD_LIB", libs.get(position).madLibs);
                context.startActivity(intent);
            }
        });


        return v;
    }
}
