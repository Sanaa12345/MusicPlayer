package com.ndroid.musicplayerap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ndroid.musicplayerapp.R;

import java.io.IOException;

public class MusicPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    // views declartion
    TextView tvTime, tvDuration,tvtitle,tvArtist;
    SeekBar seekBarTime, seekBarVolume;
    Button btnPlay;
    Boolean isPlaying = false ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide the actionbar
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        Song song = (Song) getIntent().getSerializableExtra("song");
        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        btnPlay = findViewById(R.id.btnPlay);
        tvtitle=findViewById(R.id.tv_title);
        tvArtist =findViewById(R.id.tv_artist);
        tvtitle.setText(song.getmSongName());
        tvArtist.setText(song.getmArtist());

        btnPlay.setOnClickListener(this);

    } // end main


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnPlay) {
            if(isPlaying) {
                isPlaying = false;
                btnPlay.setBackgroundResource(R.drawable.ic_play);
            } else {
                isPlaying = true;
                btnPlay.setBackgroundResource(R.drawable.ic_pause);
            }
        }

    }
}
