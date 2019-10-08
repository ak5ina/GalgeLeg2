package com.example.galgeleg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent in = new Intent(getApplicationContext(), Highscore.class);
                startActivity(in);
            }
        });

        //help knappen:
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

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
