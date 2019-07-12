package com.ranjay.udacity.models;

import java.util.Objects;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Song extends StoredObject {
    private String song_id;
    private String title;
    private String artist_id;
    private int year;
    private float duration;

    @Override
    public String toString() {
        return "{" + " song_id='" + getSong_id() + "'" + ", title='" + getTitle() + "'" + ", artist_id='"
                + getArtist_id() + "'" + ", year='" + getYear() + "'" + ", duration='" + getDuration() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Song)) {
            return false;
        }
        Song song = (Song) o;
        return Objects.equals(song_id, song.song_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(song_id);
    }

    
}