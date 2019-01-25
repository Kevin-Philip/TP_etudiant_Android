package com.kphilip.tp2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button play_button = findViewById(R.id.button_play);
        final Button stop_button = findViewById(R.id.button_stop);

        String fileName = "/sample.mp3";
        String url = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + fileName;

        final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.setLooping(true); // On loop pour pouvoir vérifier que le bouton stop fonctionne
            mediaPlayer.prepare();
        } catch (IOException e) {
            Log.d("MainActivity", "Bug with datasource URL");
            e.printStackTrace();
        }

        // On disable le bouton stop au départ
        stop_button.setEnabled(false);


        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                stop_button.setEnabled(true);
                play_button.setEnabled(false);
            }
        });

        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                play_button.setEnabled(true);
                stop_button.setEnabled(false);
            }
        });

    }


}
