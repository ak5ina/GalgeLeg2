package com.example.galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        Button btn_help = findViewById(R.id.btn_help);

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

        //help knappen:
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        checkSharedPreferences();


    }

    public void checkSharedPreferences(){

        //finder elementerne
        String errors = mpreferences.getString("1", "fejl");
        String forste = mpreferences.getString("2", "fejl");
        String anden = mpreferences.getString("3", "fejl");
        String tredje = mpreferences.getString("4", "fejl");

        //Finder textview objekterne
        TextView tv = findViewById(R.id.text_won_errors);
        TextView tv2 = findViewById(R.id.test2);
        TextView tv3 = findViewById(R.id.test3);
        TextView tv4 = findViewById(R.id.test4);

        //Indsætter værdierne i textview objekterne
        tv.setText(errors);
        tv2.setText(forste);
        tv3.setText(anden);
        tv4.setText(tredje);


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        checkSharedPreferences();

    }

    public void openDialog() {
        final AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_box, null);
        alert.setPositiveButton("OK", null);  //This is my Solution to this question(adding OK button)
        alert.setCancelable(false);
        alert.setInverseBackgroundForced(true);
        alert.setView(dialogView);
        alert.show();
        //alert.setInverseBackgroundForced(true);
    }


}
