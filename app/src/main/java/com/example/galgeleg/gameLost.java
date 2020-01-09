package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameLost extends AppCompatActivity {

    private SharedPreferences mpreferences;
    private SharedPreferences.Editor mEditor;
    private SoundPool soundPool;
    private int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lost);

        TextView tv = findViewById(R.id.text_lost_ord);

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


        sound = soundPool.load(this,R.raw.boooo, 1);



        Bundle extras = getIntent().getExtras();
        String ord;
        ord = "FEJL";


        if (extras != null){
            ord = extras.getString("ord");
        }

        tv.setText("Ordet var: " + ord);

        Button btn = findViewById(R.id.btn_start_page_2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void PlaySound(){
        soundPool.play(sound, 1f, 1f, 0, 0, 1f);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
