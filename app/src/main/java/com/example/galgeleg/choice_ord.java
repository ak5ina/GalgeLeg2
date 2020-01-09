package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class choice_ord extends AppCompatActivity {

    TextView detNyeOrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_ord);

        Button btn_comp_start = findViewById(R.id.btn_comp_start);

        detNyeOrd = findViewById(R.id.privatord);


        btn_comp_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detNyeOrd.getText().toString().length() > 2) {
                    Intent in = new Intent(getApplicationContext(), spillet.class);
                    in.putExtra("difficult", 0);
                    in.putExtra("detNyeOrd", detNyeOrd.getText().toString());
                    startActivity(in);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }

            }
        });
    }
}
