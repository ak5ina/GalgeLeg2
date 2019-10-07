package com.example.galgeleg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mpreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mpreferences.edit();

        Button btn_start = findViewById(R.id.btn_Start);
        Button btn_highscore = findViewById(R.id.btn_highscore);

        //Start spillet knappen
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), spillet.class);
                startActivity(in);
            }
        });



        //Highscore knappen
        btn_highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        checkSharedPreferences();


    }

    public void checkSharedPreferences(){
        String errors = mpreferences.getString(getString(R.string.key_error), "");
        String time = mpreferences.getString(getString(R.string.key_time), "");
    }


}
