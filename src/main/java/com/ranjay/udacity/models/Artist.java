package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
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

    @Override
    public Stream<StoredObject> mapPojoToFileData(Stream<String> dataStream) {
        List<StoredObject> storedObjects = new ArrayList<>();
        Gson converter = new Gson();
        for (String line : dataStream.collect(Collectors.toList())) {
            storedObjects.add(converter.fromJson(line, Artist.class));
        }
        return storedObjects.stream();

    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) {

        List<List<StoredObject>> output = ListUtils.partition(boundList, 100);
        // for (List<BoundStatement> boundedStatement : output) {
        //     BatchStatement microBatches = new BatchStatement();
        //     microBatches.addAll(boundedStatement);
        //     if (session.execute(microBatches) != null)
        //         System.out.println("Successfully Inserted Batch of size: " + microBatches.size());

        // }
        // try {
        // // statement = connecton.prepareStatement(insertTableSQL);
        // statement.setString(1, this.getArtist_id());
        // statement.setString(2, this.getArtist_name());
        // statement.setString(3, this.getArtist_location());
        // statement.setFloat(4, this.getArtist_latitude());
        // statement.setFloat(5, this.getArtist_longitude());
        // statement.addBatch();
        // } catch (SQLException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        return null;
    }

}