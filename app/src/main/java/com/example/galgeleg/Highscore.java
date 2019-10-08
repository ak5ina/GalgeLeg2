package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Highscore extends AppCompatActivity {

    private SharedPreferences mpreferences;
    private SharedPreferences.Editor mEditor;

    String header[];
    String time[];
    String errors[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        mpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mpreferences.edit();

        int icons[] = {R.drawable.numberone, R.drawable.number2, R.drawable.number3, R.drawable.number4, R.drawable.number5};
        header = new String[5];
        time = new String[5];
        errors = new String[5];

        checkSharedPreferences();

        ListView lv = (ListView) findViewById(R.id.list_try);

        lv.setAdapter(new CustomAdapter(Highscore.this, icons, header, time, errors));


        //Finder alt den data vi smider i high scoren:



    }


    public void checkSharedPreferences(){
        ArrayList<String> bigList = new ArrayList<>();
//        String forste = mpreferences.getString("1", "fejl");
//        String anden = mpreferences.getString("2", "fejl");
//        String tredje = mpreferences.getString("3", "fejl");
//        String fjere = mpreferences.getString("4", "fejl");
//        String femte = mpreferences.getString("5", "fejl");

        bigList.add(mpreferences.getString("1","fejl"));
        bigList.add(mpreferences.getString("2","fejl"));
        bigList.add(mpreferences.getString("3","fejl"));
        bigList.add(mpreferences.getString("4","fejl"));
        bigList.add(mpreferences.getString("5","fejl"));


        //Smider værdierne over i time og errors (og måske header senere)
        int i = 0;
        for (String a : bigList) {
            if (!a.equals("fejl")) {
                String[] splitterList = a.split(" ", 2);
                if (splitterList.length > 1) {

                    time[i] = "Tid: " + splitterList[0] + " Sekunder.";
                    errors[i] = "Fejl: " + splitterList[1];
                    header[i] = "Header: " + (i+1);

                }
            }
            i++;
        }
    }
}
