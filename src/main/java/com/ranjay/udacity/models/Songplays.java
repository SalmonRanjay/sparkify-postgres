package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Songplays extends StoredObject {

    private String songplay_id;
    private String userId;
    private String level;
    private int sessionId;
    private String location;
    private String userAgent;

    

    // @Override
    // public <T extends StoredObject> Stream<T> mapPojoToFileData(Stream<String> dataStream) {
    //     List<Songplays> storedObjectList = new ArrayList<>();
    //     Gson converter = new Gson();
    //     for (String line : dataStream.collect(Collectors.toList())) {
    //         storedObjectList.add(converter.fromJson(line, Songplays.class));
    //     }
    //     return (Stream<T>) storedObjectList.stream();
        
    // }
    @Override
    public  Stream<StoredObject> mapPojoToFileData(Stream<String> dataStream) {
        List<StoredObject> storedObjectList = new ArrayList<>();
        Gson converter = new Gson();
        for (String line : dataStream.collect(Collectors.toList())) {
            storedObjectList.add(converter.fromJson(line, Songplays.class));
        }
        return storedObjectList.stream();
        
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) {
        return null;
    }
    
}