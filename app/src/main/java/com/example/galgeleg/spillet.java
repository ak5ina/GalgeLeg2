package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class spillet extends AppCompatActivity {
    private int liv;
    private String ordet;
    private EditText guess;
    private char c_gues;
    private Galgelogik gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet);

        gl = new Galgelogik();


        //ordet bliver hentet fra DR
        ordet = hentOrdFraDr();

        //ord / bogstav man har gættet.
        guess = findViewById(R.id.game_input);

        //knappen
        Button btn = findViewById(R.id.btn_guess);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringGues = guess.toString();
                gl.gætBogstav(stringGues);

                if (stringGues.length() == 1) {
                    c_gues = stringGues.charAt(0);
                } else if (stringGues.length() > 1) {
                }

                if (gl.erSpilletSlut()){
                    TextView tx = findViewById(R.id.textView);
                    tx.setText("du har vundet");
                }
            }
        });

        //forsæt her under
    }

    private String hentOrdFraDr() {

        return "hej";
    }

    private String guessPaaOrd() {

        return "hej";
    }


}
