package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements StoredObject {

    private String userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String level;


    @Override
    public PreparedStatement createPreparedStatement(Connection connection) {
        PreparedStatement statement = null;
        String insertStatement = "INSERT INTO users" + "(user_id, first_name, last_name, gender, level) VALUES"
                + "(?,?,?,?,?)";   
        try {
            statement = connection.prepareStatement(insertStatement);
            statement.setString(1, this.getUserId());
            statement.setString(2, this.getFirstName());
            statement.setString(3, this.getLastName());
            statement.setString(4,this.getGender());
            statement.setString(5, this.getLevel());
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        return statement;
    }



    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", gender='" + getGender() + "'" +
            ", length='" + getLevel() + "'" +
            "}";
    }


}