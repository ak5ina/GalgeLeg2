package com.example.galgeleg;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class spillet extends AppCompatActivity {

    //forsøg med sharedpref
    private SharedPreferences mpreferences;
    private SharedPreferences.Editor mEditor;
    //
    private EditText guess;
    private Galgelogik gl;
    private TextView text_intro;
    private TextView text_outputt;
    private TextView text_ordet;
    int antal;
    LocalTime startTime;
    LocalTime endTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet);
        antal = 0;
        startTime = LocalTime.now();

        //sharedpref
        mpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mpreferences.edit();


        gl = new Galgelogik();

        try {
            gl.hentOrdFraDr();
        } catch (Exception e) {
            e.printStackTrace();
        }


        text_intro = findViewById(R.id.textView);
        text_ordet = findViewById(R.id.text_ordet);
        text_outputt = findViewById(R.id.text_output);
        text_ordet.setText(gl.getSynligtOrd());


        //ord / bogstav man har gættet.
        guess = findViewById(R.id.game_input);



        //knappen
        Button btn = findViewById(R.id.btn_guess);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringGues = guess.getText().toString();


                if (stringGues.length() == 1) {
                    antal++;
                    gl.gætBogstav(stringGues);
                    text_ordet.setText(gl.getSynligtOrd());
                    //Rigtigt gæt
                    if (gl.erSidsteBogstavKorrekt()) {
                        text_outputt.setText(text_outputt.getText().toString() + stringGues + ", ");
                        guess.setText("");
                    }
                    //Forkert gæt
                    else {
                        text_outputt.setText(text_outputt.getText().toString() + stringGues + ", ");
                        opdaterBilled(gl.getAntalForkerteBogstaver());
                        guess.setText("");
                    }

                    if (gl.erSpilletSlut()){

                        //man vinder
                        if (gl.erSpilletVundet()){
                            Intent in2 = new Intent(spillet.this, gameWon.class);
                            in2.putExtra("forkert", Integer.toString(gl.getAntalForkerteBogstaver()));
                            in2.putExtra("antal", Integer.toString(antal));

                            //converter tid
                            endTime = LocalTime.now();

                            long time = Duration.between(startTime,endTime).toMillis();
                            String timeString = Long.toString(time/1000);

                            //sharedpref
                            String saveableString = timeString + " " + gl.getAntalForkerteBogstaver();
                            mEditor.putString("new", saveableString);
                            mEditor.commit();

                            //---

                            in2.putExtra("tid",timeString);

                            startActivity(in2);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }

                        //man taber
                        else if (gl.erSpilletTabt()){
                            
                            Intent in_tabt = new Intent(spillet.this, gameLost.class);
                            in_tabt.putExtra("ord", gl.getOrdet());
                            startActivity(in_tabt);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        }

                        //fejl i spillet (Den lukker og går tilbage til MainActivity)
                        else {
                            text_intro.setText("fejl");
                            try {
                                Thread.sleep(2500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            finish();
                        }
                    }

                }
                else if (stringGues.length() < 1 || stringGues.length() > 1 || stringGues.length() == 0){
                    text_intro.setText("Gæt kun et bogstav af gangen");
                }

            }
        });

        //forsæt her under
    }

    public void opdaterBilled(int liv){

        //ku ha været int Array
        ImageView pic = findViewById(R.id.image_galge);
        switch (liv){
            case 1:
                pic.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                pic.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                pic.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                pic.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                pic.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                pic.setImageResource(R.drawable.forkert6);
                break;
            default:
                pic.setImageResource(R.drawable.galge);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
