package com.ndroid.musicplayerap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ndroid.musicplayerapp.R;

import java.util.ArrayList;

public class SongsAdapter extends ArrayAdapter<Song> {
    private int resource;

    public SongsAdapter(@NonNull Context context, ArrayList<Song> item_song) {
        super(context, 0, item_song);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, null, false);
        TextView songname = convertView.findViewById(R.id.song_name);
        TextView artist = convertView.findViewById(R.id.artist_name);
        Song song = getItem(position);
        songname.setText(song.getmSongName() + "");
        artist.setText(song.getmArtist() + "");
        return convertView;
    }
}
