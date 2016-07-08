package com.genassembly.dotdashdot.listexample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class EditAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final ArrayList<MadLibs> libs;
    private final Context context;

    public EditAdapter(Context context, ArrayList<MadLibs> libs) {
        inflater = LayoutInflater.from(context);
        this.libs = libs;
        this.context = context;
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

        EditText v =(EditText) child;

        if (v == null) {
            v = inflater.inflate(R.layout.edit_item, parent, false);
        }

        words.setText(String.valueOf(libs.get(position).getWords()));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Adapter", "Clicked " + position);
                eddy=(EditText) v.findViewById(R.id.FIELD10);
            }
        });

        return v;
    }
}
