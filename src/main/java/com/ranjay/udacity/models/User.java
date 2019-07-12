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
public class User extends StoredObject {

    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String level;

    @Override
    public String toString() {
        return "{" + " userId='" + getUserId() + "'" + ", firstName='" + getFirstName() + "'" + ", lastName='"
                + getLastName() + "'" + ", gender='" + getGender() + "'" + ", length='" + getLevel() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    // @Override
    // @SuppressWarnings({"unchecked"})
    // public <T extends StoredObject> Stream<T> mapPojoToFileData(Stream<String> dataStream) {
    //     List<User> storedObjectList = new ArrayList<>();
    //     Gson converter = new Gson();
    //     for (String line : dataStream.collect(Collectors.toList())) {
    //         storedObjectList.add(converter.fromJson(line, User.class));
    //     }
    //     return (Stream<T>) storedObjectList.stream();
       
    // }

    @Override
    public Stream<StoredObject> mapPojoToFileData(Stream<String> dataStream) {
        List<StoredObject> storedObjectList = new ArrayList<>();
        Gson converter = new Gson();
        for (String line : dataStream.collect(Collectors.toList())) {
            storedObjectList.add(converter.fromJson(line, User.class));
        }
        return storedObjectList.stream();
       
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) {
        // try {
        //     // statement = connection.prepareStatement(insertStatement);
        //     statement.setString(1, this.getUserId());
        //     statement.setString(2, this.getFirstName());
        //     statement.setString(3, this.getLastName());
        //     statement.setString(4,this.getGender());
        //     statement.setString(5, this.getLevel());
        //     statement.addBatch();
        // }
        // catch (SQLException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        return null;
    }


}