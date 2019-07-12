package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TStamps  {
    private long ts;

    @Override
    public String toString() {
        return "{" + " ts='" + getTs() + "'" + "}";
    }

    // @Override
    // public <T extends StoredObject> Stream<> mapPojoToFileData(Stream<String> dataStream) {
    //     List<TStamps> storedObjectList = new ArrayList<>();
    //     Gson converter = new Gson();
    //     for (String line : dataStream.collect(Collectors.toList())) {
    //         storedObjectList.add(converter.fromJson(line, TStamps.class));
    //     }
    //     return (Stream<T>) storedObjectList.stream();
        
    // }

    // @Override
    // public Stream<StoredObject> mapPojoToFileData(Stream<String> dataStream) {
    //     List<StoredObject> storedObjectList = new ArrayList<>();
    //     Gson converter = new Gson();
    //     for (String line : dataStream.collect(Collectors.toList())) {
    //         storedObjectList.add(converter.fromJson(line, TStamps.class));
    //     }
    //     return storedObjectList.stream();   
    // }

    // @Override
    // public PreparedStatement createPreparedStatement(Connection connection) {
    //     return null;
    // }
    
}