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

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère nos éléments
        final Button play_button = findViewById(R.id.button_play);
        final Button stop_button = findViewById(R.id.button_stop);

        // On disable le bouton stop au départ
        stop_button.setEnabled(false);

        // On récupère le path du son
        String fileName = "/sample.mp3";
        final String url = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + fileName;
        Log.d("MainActivity", "The url is : " + url);

        // On créer notre media player
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(url);
            Log.d("MainActivity", "MP is ready to prepare");
        } catch (IOException e) {
            Log.d("MainActivity", "Bug with datasource URL. The file must be in downloads directory");
            e.printStackTrace();
        }

        // On loop pour pouvoir vérifier que le bouton stop fonctionne
        mediaPlayer.setLooping(true);

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                        stop_button.setEnabled(true);
                        play_button.setEnabled(false);
                        Log.d("MainActivity", "MP is playing");
                    }
                });
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

    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        super.onDestroy();
    }


}
