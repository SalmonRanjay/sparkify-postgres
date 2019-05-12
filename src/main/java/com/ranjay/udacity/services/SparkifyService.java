package com.ranjay.udacity.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.ranjay.udacity.interfaces.StoredObject;

public final class SparkifyService {

    private SparkifyService(){};

    public static StoredObject createDatabaseRecord(File node, Gson converter, StoredObject record) {

        try {
            record = converter.fromJson(new FileReader(node.getAbsolutePath()), record.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }

    public static List<String> extractJsonStringList(File node, BufferedReader br)  {
        List<String> userList = new ArrayList<>();
        try {
            br = Files.newBufferedReader(node.toPath());
            System.out.println(node.getAbsolutePath());
            String line;  
            while ((line = br.readLine()) != null) {
                userList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static List<StoredObject> extractStoredObjectFromList(List<String> jsonStrings, Gson converter, StoredObject record){
        List<StoredObject> objectList = new ArrayList<>();
        for(String jstring : jsonStrings){
            objectList.add(converter.fromJson(jstring, record.getClass()));
        }
        return objectList;
    }
   
}