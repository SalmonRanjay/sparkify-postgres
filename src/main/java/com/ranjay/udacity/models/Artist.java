package com.ranjay.udacity.models;

import java.util.Objects;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artist extends StoredObject {
    private String artist_id;
    private String artist_name;
    private String artist_location;
    private float artist_latitude;
    private float artist_longitude;

    @Override
    public String toString() {
        return "{" + " artist_id='" + getArtist_id() + "'" + ", artist_name='" + getArtist_name() + "'"
                + ", artist_location='" + getArtist_location() + "'" + ", artist_latitude='" + getArtist_latitude()
                + "'" + ", artist_longitude='" + getArtist_longitude() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Artist)) {
            return false;
        }
        Artist artist = (Artist) o;
        return Objects.equals(artist_id, artist.artist_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist_id);
    }
  

}