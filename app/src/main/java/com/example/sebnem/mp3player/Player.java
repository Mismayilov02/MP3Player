package com.example.sebnem.mp3player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class Player extends AppCompatActivity {

    SeekBar sekbar;
    ImageButton playpause ,exit;
    Runnable runnable;
    Handler handler;
    int id;
    String musicName;
    TextView name;
    boolean play = false;
    Intent intent;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        intent = new Intent(this , MainActivity.class);

        exit = findViewById(R.id.dinleme_exit);
        playpause = findViewById(R.id.Playpause);
        name  = findViewById(R.id.music_name);
        sekbar = findViewById(R.id.seekBar);
        musicName = getIntent().getStringExtra("musicName");
        id = getIntent().getIntExtra("id", 12);

        mediaPlayer = MediaPlayer.create(this, id);
        name.setText(musicName);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                startActivity(intent);
            }
        });

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!play){
                    mediaPlayer.start();
                    play= true;
                    sekbar.setMax(mediaPlayer.getDuration());
                    mediaPlayer.setVolume(100,100);
                    playpause.setImageResource(R.drawable.pause);
                }
                else {
                    mediaPlayer.pause();
                    play=false;
                    playpause.setImageResource(R.drawable.play_button);
                }
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                sekbar.setProgress((int) mediaPlayer.getCurrentPosition());
                handler.postDelayed(this , 500);
            }
        };
        handler.post(runnable);



        sekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(i);
                        mediaPlayer.start();
                    }else {mediaPlayer.seekTo(i);}
                }
            }@Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }@Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}