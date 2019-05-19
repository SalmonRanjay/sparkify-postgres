package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Songplays implements StoredObject {

    private String songplay_id;
    private String userId;
    private String level;
    private int sessionId;
    private String location;
    private String userAgent;

    @Override
    public PreparedStatement createPreparedStatement(Connection connection, PreparedStatement statement) {

        String selectSQL = ""; //
        /*
        SELECT a.artist_id, s.song_id, u.user_id FROM users
                            join artist on  WHERE USER_ID = ?";
        */
      
        return null;
    }
    
}