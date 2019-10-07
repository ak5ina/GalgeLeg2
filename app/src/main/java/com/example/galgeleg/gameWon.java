package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

public class gameWon extends AppCompatActivity {

    private SharedPreferences mpreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        mpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mpreferences.edit();



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

        updateSharedPreferences();
    }

    public void updateSharedPreferences(){
        ArrayList<String> bigList = new ArrayList<>();
        ArrayList<Score> scoreList = new ArrayList<>();

        bigList.add(mpreferences.getString("new", "fejl"));
        bigList.add(mpreferences.getString("5","fejl"));
        bigList.add(mpreferences.getString("4","fejl"));
        bigList.add(mpreferences.getString("3","fejl"));
        bigList.add(mpreferences.getString("2","fejl"));
        bigList.add(mpreferences.getString("1","fejl"));

        //Smider værdierne over i en scoreliste som jeg rangere senere
        for (String a : bigList) {
            if (!a.equals("fejl")) {
                String[] splitterList = a.split("|", 2);
                if (splitterList.length > 1) {
                    Score score = new Score(splitterList[0], splitterList[1]);
                    scoreList.add(score);
                }
            }
        }

        //rangere min scoreliste
        Collections.sort(scoreList, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return Integer.parseInt(o1.getFejl()) - Integer.parseInt(o2.getFejl());
            }
        });

        Collections.sort(scoreList, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if (Integer.parseInt(o1.getFejl()) == Integer.parseInt(o2.getFejl())) {
                    return Integer.parseInt(o1.getTid()) - Integer.parseInt(o2.getTid());
                }
                return 0;
            }
        });

        for (int i = 0; i < scoreList.size(); i++) {

        }



    }
}
