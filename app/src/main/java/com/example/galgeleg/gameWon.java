package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jinatonic.confetti.CommonConfetti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.transform.Result;

public class gameWon extends AppCompatActivity {

    private SharedPreferences mpreferences;
    private SharedPreferences.Editor mEditor;
    private String newtime;
    private Score newScore;
    private SoundPool soundPool;
    private int sound;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        mpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mpreferences.edit();


        //HENTET FRA YOUTUBE https://www.youtube.com/watch?v=fIWPSni7kUk
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().
                    setUsage(AudioAttributes.USAGE_GAME).
                    setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                PlaySound();
            }
        });


        sound = soundPool.load(this,R.raw.flag_won, 1);

        //CONFETTI
        handler.post(new Runnable() {
            @Override
            public void run() {
                CommonConfetti.rainingConfetti(
                        (ViewGroup) ((ViewGroup) gameWon.this.findViewById(android.R.id.content)).getChildAt(0)
                        ,
                        new int[]{Color.RED, Color.YELLOW })
                        .infinite();
            }
        });





        Bundle extras = getIntent().getExtras();
        String forkerte, antal, tid;
        int dificult;
        forkerte = "FEJL";
        antal = "FEJL";
        tid = "FEJL";
        dificult = 999;


        if (extras != null){
            forkerte = extras.getString("forkert");
            antal = extras.getString("antal");
            tid = extras.getString("tid");
            dificult = extras.getInt("difficult");
        }

        TextView tv = findViewById(R.id.text_won_turns);
        TextView tv2 = findViewById(R.id.text_won_errors);
        TextView tv3 = findViewById(R.id.text_won_time);
        final EditText ed1 = findViewById(R.id.text_won_navn);

        tv.setText("Du gjorde det på: " + antal + " ture!");
        tv2.setText("Du havde: " + forkerte + " forkert(e) gæt i løbet af spillet.");
        tv3.setText("Du blev færdig på: " + tid + " sekunder!");

        Button btn = findViewById(R.id.btn_start_page);
        final String finalTid = tid;
        final String finalForkerte = forkerte;
        final int finalDificult = dificult;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newScore = new Score(finalTid, finalForkerte, ed1.getText().toString(), Integer.toString(finalDificult));
                updateSharedPreferences();
                finish();
            }
        });
    }


    //Lang algoritme som rangere forsøgende, og gemme de 5 bedste som 1-5 og den dårligeste som new, og new bliver overskrevet ved næste win.
    public void updateSharedPreferences(){
        ArrayList<String> bigList = new ArrayList<>();
        ArrayList<Score> scoreList = new ArrayList<>();

        bigList.add(mpreferences.getString("5","fejl"));
        bigList.add(mpreferences.getString("4","fejl"));
        bigList.add(mpreferences.getString("3","fejl"));
        bigList.add(mpreferences.getString("2","fejl"));
        bigList.add(mpreferences.getString("1","fejl"));

        scoreList.add(newScore);

        //Smider værdierne over i en scoreliste som jeg rangere     senere
        for (String a : bigList) {
            if (!a.equals("fejl")) {
                String[] splitterList = a.split(" ", 4);
                if (splitterList.length > 1) {
                    Score score = new Score(splitterList[0], splitterList[1], splitterList[2], splitterList[3]);
                    scoreList.add(score);
                }
            }
        }

        //rangere min scoreliste

        //her rangere den efter sværhed
        Collections.sort(scoreList, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return Integer.parseInt(o2.getDifficult()) - Integer.parseInt(o1.getDifficult());
            }
        });


            //her rangere den efter antal fejl
        Collections.sort(scoreList, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if (Integer.parseInt(o1.getDifficult()) == Integer.parseInt(o2.getDifficult())) {
                    return Integer.parseInt(o1.getFejl()) - Integer.parseInt(o2.getFejl());
                }
                return 0;
            }
        });

            //Her rangere den efter tid
        Collections.sort(scoreList, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if (Integer.parseInt(o1.getFejl()) == Integer.parseInt(o2.getFejl()) && Integer.parseInt(o1.getDifficult()) == Integer.parseInt(o2.getDifficult())) {
                    return Integer.parseInt(o1.getTid()) - Integer.parseInt(o2.getTid());
                }
                return 0;
            }
        });
            //Gemmer dem som strings i sharedpref
        for (int i = 0; i < scoreList.size(); i++) {
            String objektString;
            objektString = scoreList.get(i).getTid() + " " + scoreList.get(i).getFejl() + " " + scoreList.get(i).getNavn() + " " + scoreList.get(i).getDifficult();

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

    public void PlaySound(){
        soundPool.play(sound, 1f, 1f, 0, 0, 1f);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
