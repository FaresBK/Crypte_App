package com.example.fbk_pc.crypte_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tm161.crypte_app.R;

/**
 * Created by TM161 on 28-05-2017.
 */

public class listee extends ArrayAdapter<String> {
    private final Activity context;
    private final String[]text;
    private final Integer[] imgid;
    public listee(Activity context, String[] text,Integer[] imgid) {
        super(context, R.layout.liste_adpte,text);
        this.context = context;
        this.text=text;
        this.imgid=imgid;

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.liste_adpte, null,true);
        TextView textView=(TextView)rowView.findViewById(R.id.txt);
        ImageView imageView=(ImageView)rowView.findViewById(R.id.image);

        textView.setText(text[position]);
        imageView.setImageResource(imgid[position]);


        return rowView;

    }
}
