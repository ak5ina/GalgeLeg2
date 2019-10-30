package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        String forkerte, antal, tid;
        forkerte = "FEJL";
        antal = "FEJL";
        tid = "FEJL";


        if (extras != null){
            forkerte = extras.getString("forkert");
            antal = extras.getString("antal");
            tid = extras.getString("tid");
        }

        TextView tv = findViewById(R.id.text_won_turns);
        TextView tv2 = findViewById(R.id.text_won_errors);
        TextView tv3 = findViewById(R.id.text_won_time);

        tv.setText("Du gjorde det på: " + antal + " ture!");
        tv2.setText("Du havde: " + forkerte + " forkert(e) gæt i løbet af spillet.");
        tv3.setText("Du blev færdig på: " + tid + " sekunder!");

        updateSharedPreferences();

        Button btn = findViewById(R.id.btn_start_page);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //Lang algoritme som rangere forsøgende, og gemme de 5 bedste som 1-5 og den dårligeste som new, og new bliver overskrevet ved næste win.
    public void updateSharedPreferences(){
        ArrayList<String> bigList = new ArrayList<>();
        ArrayList<Score> scoreList = new ArrayList<>();

        bigList.add(mpreferences.getString("new", "fejl"));
        bigList.add(mpreferences.getString("5","fejl"));
        bigList.add(mpreferences.getString("4","fejl"));
        bigList.add(mpreferences.getString("3","fejl"));
        bigList.add(mpreferences.getString("2","fejl"));
        bigList.add(mpreferences.getString("1","fejl"));

        //Smider værdierne over i en scoreliste som jeg rangere     senere
        for (String a : bigList) {
            if (!a.equals("fejl")) {
                String[] splitterList = a.split(" ", 2);
                if (splitterList.length > 1) {
                    Score score = new Score(splitterList[0], splitterList[1]);
                    scoreList.add(score);
                }
            }
        }

        //rangere min scoreliste
            //her rangere den efter antal fejl
        Collections.sort(scoreList, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return Integer.parseInt(o1.getFejl()) - Integer.parseInt(o2.getFejl());
            }
        });

            //Her rangere den efter tid
        Collections.sort(scoreList, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if (Integer.parseInt(o1.getFejl()) == Integer.parseInt(o2.getFejl())) {
                    return Integer.parseInt(o1.getTid()) - Integer.parseInt(o2.getTid());
                }
                return 0;
            }
        });
            //Gemmer dem som strings i sharedpref
        for (int i = 0; i < scoreList.size(); i++) {
            String objektString;
            objektString = scoreList.get(i).getTid() + " " + scoreList.get(i).getFejl();

            switch (i) {
                case 0:
                    mEditor.putString("1",objektString);
                    break;
                case 1:
                    mEditor.putString("2",objektString);
                    break;
                case 2:
                    mEditor.putString("3",objektString);
                    break;
                case 3:
                    mEditor.putString("4",objektString);
                    break;
                case 4:
                    mEditor.putString("5",objektString);
                    break;
                case 5:
                    mEditor.putString("new",objektString);
                    break;
                default:
                    mEditor.putString("FEJL", objektString);
                    break;
            }

            mEditor.commit();
        }



    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
