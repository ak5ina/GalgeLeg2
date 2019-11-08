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
    private Galgelogik gl;
    private TextView text_ordet;
    int antal;
    LocalTime startTime;
    LocalTime endTime;
    Button btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ, btnÆ, btnØ, btnÅ;
    View.OnClickListener myClickListner;


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

        text_ordet = findViewById(R.id.text_ordet);
        text_ordet.setText(gl.getSynligtOrd());

        //metoden
        View.OnClickListener myClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setEnabled(false);

                String tag = (String) v.getTag();
                antal++;
                gl.gætBogstav(tag);
                text_ordet.setText(gl.getSynligtOrd());

                if (!gl.erSidsteBogstavKorrekt()) {
                    opdaterBilled(gl.getAntalForkerteBogstaver());
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
                        text_ordet.setText("fejl");
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }


            }
        };
        //forsæt her under

        //alle knapperne
        btnA = findViewById(R.id.buttonA);
        btnA.setTag("a");
        btnA.setOnClickListener(myClickListner);
        btnB = findViewById(R.id.buttonB);
        btnB.setTag("b");
        btnB.setOnClickListener(myClickListner);
        btnC = findViewById(R.id.buttonC);
        btnC.setTag("c");
        btnC.setOnClickListener(myClickListner);
        btnD = findViewById(R.id.buttonD);
        btnD.setTag("d");
        btnD.setOnClickListener(myClickListner);
        btnE = findViewById(R.id.buttonE);
        btnE.setTag("e");
        btnE.setOnClickListener(myClickListner);
        btnF = findViewById(R.id.buttonF);
        btnF.setTag("f");
        btnF.setOnClickListener(myClickListner);
        btnG = findViewById(R.id.buttonG);
        btnG.setTag("g");
        btnG.setOnClickListener(myClickListner);
        btnH = findViewById(R.id.buttonH);
        btnH.setTag("h");
        btnH.setOnClickListener(myClickListner);
        btnI = findViewById(R.id.buttonI);
        btnI.setTag("i");
        btnI.setOnClickListener(myClickListner);
        btnJ = findViewById(R.id.buttonJ);
        btnJ.setTag("j");
        btnJ.setOnClickListener(myClickListner);
        btnK = findViewById(R.id.buttonK);
        btnK.setTag("k");
        btnK.setOnClickListener(myClickListner);
        btnL = findViewById(R.id.buttonL);
        btnL.setTag("l");
        btnL.setOnClickListener(myClickListner);
        btnM = findViewById(R.id.buttonM);
        btnM.setTag("m");
        btnM.setOnClickListener(myClickListner);
        btnN = findViewById(R.id.buttonN);
        btnN.setTag("n");
        btnN.setOnClickListener(myClickListner);
        btnO = findViewById(R.id.buttonO);
        btnO.setTag("o");
        btnO.setOnClickListener(myClickListner);
        btnP = findViewById(R.id.buttonP);
        btnP.setTag("p");
        btnP.setOnClickListener(myClickListner);
        btnQ = findViewById(R.id.buttonQ);
        btnQ.setTag("q");
        btnQ.setOnClickListener(myClickListner);
        btnR = findViewById(R.id.buttonR);
        btnR.setTag("r");
        btnR.setOnClickListener(myClickListner);
        btnS = findViewById(R.id.buttonS);
        btnS.setTag("s");
        btnS.setOnClickListener(myClickListner);
        btnT = findViewById(R.id.buttonT);
        btnT.setTag("t");
        btnT.setOnClickListener(myClickListner);
        btnU = findViewById(R.id.buttonU);
        btnU.setTag("u");
        btnU.setOnClickListener(myClickListner);
        btnV = findViewById(R.id.buttonV);
        btnV.setTag("v");
        btnV.setOnClickListener(myClickListner);
        btnW = findViewById(R.id.buttonW);
        btnW.setTag("w");
        btnW.setOnClickListener(myClickListner);
        btnX = findViewById(R.id.buttonX);
        btnX.setTag("x");
        btnX.setOnClickListener(myClickListner);
        btnY = findViewById(R.id.buttonY);
        btnY.setTag("y");
        btnY.setOnClickListener(myClickListner);
        btnZ = findViewById(R.id.buttonZ);
        btnZ.setTag("z");
        btnZ.setOnClickListener(myClickListner);
        btnÆ = findViewById(R.id.buttonÆ);
        btnÆ.setTag("æ");
        btnÆ.setOnClickListener(myClickListner);
        btnØ = findViewById(R.id.buttonØ);
        btnØ.setTag("ø");
        btnØ.setOnClickListener(myClickListner);
        btnÅ = findViewById(R.id.buttonÅ);
        btnÅ.setTag("å");
        btnÅ.setOnClickListener(myClickListner);


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
