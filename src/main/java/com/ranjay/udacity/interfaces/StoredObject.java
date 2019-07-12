package com.ranjay.udacity.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.stream.Stream;


public abstract class StoredObject {

    public void storePojoToDB(StoredObject item){
        System.out.println("Stored object here");
    }
    // public abstract<T extends StoredObject> Stream<T> mapPojoToFileData(Stream<String> dataStream);
    public abstract Stream<StoredObject> mapPojoToFileData(Stream<String> dataStream);
    public abstract PreparedStatement createPreparedStatement(Connection connection);
}