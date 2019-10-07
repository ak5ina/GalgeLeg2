package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class spillet extends AppCompatActivity {

    private EditText guess;
    private Galgelogik gl;
    private TextView text_intro;
    private TextView text_outputt;
    private TextView text_ordet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet);


        gl = new Galgelogik();
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
                    gl.gætBogstav(stringGues);
                    text_ordet.setText(gl.getSynligtOrd());
                    //Rigtigt gæt
                    if (gl.erSidsteBogstavKorrekt()) {
                        text_outputt.setText("Bogstavet er i ordet! Du gættede: " + stringGues);
                    }
                    //Forkert gæt
                    else {
                        text_outputt.setText("Bogstavet er ikke i ordet, du gættede: " + stringGues);
                        opdaterBilled(gl.getAntalForkerteBogstaver());
                    }

                    if (gl.erSpilletSlut()){
                        if (gl.erSpilletVundet()){
                            text_intro.setText("vundet");
                        }
                        else if (gl.erSpilletTabt()){
                            text_intro.setText("tabt");
                        }
                        else {
                            text_intro.setText("fejl");
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

}
