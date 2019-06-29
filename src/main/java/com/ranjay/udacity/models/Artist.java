package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;

import com.ranjay.udacity.interfaces.StoredObject;
import com.ranjay.udacity.services.ObjectMapperService;

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

    @Override
    public <T extends StoredObject> Stream<T> mapPojoToFileData(Stream<String> dataStream) {
        return ObjectMapperService.mapPojoToFileData(dataStream, Artist.class);
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) {
        // try {
        //     // statement = connecton.prepareStatement(insertTableSQL);
        //     statement.setString(1, this.getArtist_id());
        //     statement.setString(2, this.getArtist_name());
        //     statement.setString(3, this.getArtist_location());
        //     statement.setFloat(4, this.getArtist_latitude());
        //     statement.setFloat(5, this.getArtist_longitude());
        //     statement.addBatch();
        // } catch (SQLException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        
        return null;
    }

}