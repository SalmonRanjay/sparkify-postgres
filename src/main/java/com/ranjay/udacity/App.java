package com.ranjay.udacity;

import java.io.BufferedReader;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.ranjay.udacity.interfaces.StoredObject;
import com.ranjay.udacity.models.Artist;
import com.ranjay.udacity.models.Song;
import com.ranjay.udacity.models.TStamps;
import com.ranjay.udacity.models.Timestamps;
import com.ranjay.udacity.models.User;
import com.ranjay.udacity.services.SparkifyService;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Gson userConvert = new Gson();
        Gson artistConverter = new Gson();
        Gson songConverter = new Gson();
        Gson timeStampConverter = new Gson();
        StoredObject userRecord = new User();
        StoredObject artistRecored = new Artist();
        StoredObject songsRecord = new Song();
        StoredObject timestampRecord = new Timestamps();
        StoredObject tstampsRecord = new TStamps();

        App app = new App();
        // app.createArtisteAndSongs(new File("./data/log_data"), userConvert,
        // userRecord);
        // app.createArtisteAndSongs(new File("./data/song_data"), artistConverter,
        // artistRecored);
        // app.createArtisteAndSongs(new File("./data/song_data"), songConverter,
        // songsRecord);
        // app.createArtisteAndSongs(new File("./data/log_data"), timeStampConverter,
        app.storeTimeStamps(new File("./data/log_data"), timeStampConverter, tstampsRecord);

    }

    public void createArtisteAndSongs(File node, Gson converter, StoredObject record) {
        BufferedReader br = null;
        if (node.isDirectory()) {
            String[] subDirs = node.list();
            for (String filename : subDirs) {
                createArtisteAndSongs(new File(node, filename), converter, record);
            }
        } else {
            List<String> jsonstrings = new ArrayList<>();
            jsonstrings = SparkifyService.extractJsonStringList(node, br);

            for (StoredObject objtype : SparkifyService.extractStoredObjectFromList(jsonstrings, converter, record)) {
                System.out.println(objtype.toString());
            }

        }
    }

    public void storeTimeStamps(File node, Gson converter, StoredObject record) {
        BufferedReader br = null;
        if (node.isDirectory()) {
            String[] subDirs = node.list();
            for (String filename : subDirs) {
                storeTimeStamps(new File(node, filename), converter, record);
            }
        } else {
            List<String> jsonstrings = new ArrayList<>();
            List<StoredObject> timestampObjects = new ArrayList<>();
            jsonstrings = SparkifyService.extractJsonStringList(node, br);

            for (StoredObject objtype : SparkifyService.extractStoredObjectFromList(jsonstrings, converter, record)) {
                timestampObjects.add(new Timestamps(((TStamps) objtype).getTs()));
            }

            for (StoredObject ts : timestampObjects) {
                System.out.println(ts.toString());
            }

        }
    }

}
