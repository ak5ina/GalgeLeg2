package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.UUID;

public class gameWon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);


        Bundle extras = getIntent().getExtras();
        String forkerte, antal;
        forkerte = "420";
        antal = "420";


        if (extras != null){
            forkerte = extras.getString("forkert");
            antal = extras.getString("antal");
        }


        TextView tv = findViewById(R.id.test23);
        TextView tv2 = findViewById(R.id.test);
        tv.setText("Du gjorde det på: " + antal + " ture!");
        tv2.setText("Du havde: " + forkerte + " forkert(e) gæt i løbet af spillet.");
    }
}
