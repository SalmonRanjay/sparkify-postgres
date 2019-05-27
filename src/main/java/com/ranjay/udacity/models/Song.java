package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Song implements StoredObject {
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
        return Objects.equals(song_id, song.song_id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(song_id);
    }


    @Override
    public PreparedStatement createPreparedStatement(Connection connection, PreparedStatement statement) {

        try {
            // statement = connection.prepareStatement(insertStatement);
            statement.setString(1, this.getSong_id());
            statement.setString(2, this.getTitle());
            statement.setString(3, this.getArtist_id());
            statement.setInt(4, this.getYear());
            statement.setFloat(5, this.getDuration());
            statement.addBatch();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return statement;
    }

}