package com.ndroid.musicplayerap;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.ndroid.musicplayerapp.R;

import java.util.ArrayList;

public class ListMusicActivity extends AppCompatActivity {
    private static final int REQUESTCODE = 19;
    ArrayList<Song> songArrayList;
    ListView lvlist;
    SongsAdapter songsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);
        lvlist = findViewById(R.id.lv_list);
        songArrayList = new ArrayList<>();
        songsAdapter = new SongsAdapter(this, songArrayList);
        lvlist.setAdapter(songsAdapter);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUESTCODE);
            return;
        }
        //you can read the extrenal storage
        else {
            getsongs();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUESTCODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getsongs();
            }

        }
    }

    private void getsongs() {
        // read songs from phone
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int idex_song_name = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idex_artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int idex_DAT = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                String path = cursor.getString(idex_DAT);
                String mSongName = cursor.getString(idex_song_name);
                String mArtist = cursor.getString(idex_artist);
                if (mArtist.toLowerCase().equals("<unknown>")) mArtist = "No Artist";
                Song song = new Song(path, mSongName, mArtist);
                songArrayList.add(song);
            } while (cursor.moveToNext());
        }
        songsAdapter.notifyDataSetChanged();

        addListClickListener();
    }

    private void addListClickListener() {
        lvlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent OPenplayerMusic = new Intent(ListMusicActivity.this, MusicPlayerActivity.class);


                Log.e("TAG", "onItemClick: ");
                Song song = songArrayList.get(position);
                OPenplayerMusic.putExtra("song", song);
                startActivity(OPenplayerMusic);
            }
        });
    }
}

