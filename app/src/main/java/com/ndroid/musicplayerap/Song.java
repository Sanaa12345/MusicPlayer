package com.ndroid.musicplayerap;

import java.io.Serializable;

public class Song implements Serializable {
    private String path;
    private String mSongName;
    private String mArtist;

    public Song(String path, String mSongName, String mArtist) {
        this.path = path;
        this.mSongName = mSongName;
        this.mArtist = mArtist;

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getmSongName() {
        return mSongName;
    }

    public void setmSongName(String mSongName) {
        this.mSongName = mSongName;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }
}

