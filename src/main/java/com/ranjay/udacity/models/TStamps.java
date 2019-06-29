package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.stream.Stream;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TStamps extends StoredObject {
    private long ts;

    @Override
    public String toString() {
        return "{" + " ts='" + getTs() + "'" + "}";
    }

    @Override
    public <T extends StoredObject> Stream<T> mapPojoToFileData(Stream<String> dataStream) {
        return null;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) {
        return null;
    }
    
}