package com.example.galgeleg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    static LayoutInflater inflater = null;
    int icons[];
    String header[];
    String time[];
    String errors[];
    String difis[];
    String difstring, difstring2;

    public CustomAdapter(Context context, int icons[], String header[], String time[], String errors[], String difis[]){

        this.context = context;
        this.icons = icons;
        this.header = header;
        this.time = time;
        this.errors = errors;
        this.difis = difis;

    }


    @Override
    public int getCount() {
        return header.length;
    }

    @Override
    public Object getItem(int position) {
        return getItemId(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if(row == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflater.inflate(R.layout.highscore_item , null);
        }

        ImageView img = (ImageView) row.findViewById(R.id.item_imageView);
        TextView head = (TextView) row.findViewById(R.id.item_header);
        TextView fail = (TextView) row.findViewById(R.id.item_errors);
        TextView tid = (TextView) row.findViewById(R.id.item_time);
        TextView difi = (TextView) row.findViewById(R.id.item_dificult);

        img.setImageResource(icons[position]);
        head.setText(header[position]);
        fail.setText(errors[position]);
        tid.setText(time[position]);
        difi.setText(difis[position]);


        return row;
    }
}
