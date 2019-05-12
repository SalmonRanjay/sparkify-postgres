package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artist implements StoredObject {
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
    public PreparedStatement createPreparedStatement(Connection connecton) {
        String insertTableSQL = "INSERT INTO artists"
                + "(artist_id, name, location, lattitude, longitude) VALUES" + "(?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connecton.prepareStatement(insertTableSQL);
            statement.setString(1, this.getArtist_id());
            statement.setString(2, this.getArtist_name());
            statement.setString(3, this.getArtist_location());
            statement.setFloat(4, this.getArtist_latitude());
            statement.setFloat(5, this.getArtist_longitude());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return statement;
    }

}