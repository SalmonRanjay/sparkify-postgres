package com.ranjay.udacity;

import java.io.BufferedReader;
import java.io.File;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.ranjay.udacity.interfaces.StoredObject;
import com.ranjay.udacity.models.Artist;
import com.ranjay.udacity.models.Song;
import com.ranjay.udacity.models.TStamps;
import com.ranjay.udacity.models.Timestamps;
import com.ranjay.udacity.models.User;
import com.ranjay.udacity.services.DatabaseService;
import com.ranjay.udacity.services.SparkifyService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        String usertStatement = "INSERT INTO users" + "(user_id, first_name, last_name, gender, level) VALUES"
                + "(?,?,?,?,?)";
        String timeStampStatement = "INSERT INTO logtimes"
                + "(start_time, hour, day, week, month, year, weekday) VALUES" + "(?,?,?,?,?,?,?)";
        String songStatement = "INSERT INTO songs" + "(song_id, title, artist_id, year, duration) VALUES"
                + "(?,?,?,?,?)";
        String artistStatement = "INSERT INTO artists" + "(artist_id, name, location, lattitude, longitude) VALUES"
                + "(?,?,?,?,?)";

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
        // app.createArtisteAndSongs(new File("./data/log_data"), userConvert, userRecord, usertStatement);
        app.createArtisteAndSongs(new File("./data/song_data"), artistConverter, artistRecored, artistStatement);
        app.createArtisteAndSongs(new File("./data/song_data"), songConverter, songsRecord, songStatement);
        app.storeTimeStamps(new File("./data/log_data"), timeStampConverter, tstampsRecord,timeStampStatement);

    }

    public void createArtisteAndSongs(File node, Gson converter, StoredObject record, String sqlString) {
        BufferedReader br = null;
        Connection connection = DatabaseService.getDBConnection();
        PreparedStatement uStatement = null;

        if (node.isDirectory()) {
            String[] subDirs = node.list();
            for (String filename : subDirs) {
                createArtisteAndSongs(new File(node, filename), converter, record, sqlString);
            }
        } else {
            uStatement = initialPreparentStatement(sqlString, connection, uStatement);
            storeInDB(node, converter, record, br, connection, uStatement);
        }
    }

    private PreparedStatement initialPreparentStatement(String sqlString, Connection connection, PreparedStatement uStatement) {
        try {
            uStatement = connection.prepareStatement(sqlString);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uStatement;
    }

    public void storeTimeStamps(File node, Gson converter, StoredObject record, String sqlString) {
        BufferedReader br = null;
        Connection connection = DatabaseService.getDBConnection();
        PreparedStatement uStatement = null;
        if (node.isDirectory()) {
            String[] subDirs = node.list();
            for (String filename : subDirs) {
                storeTimeStamps(new File(node, filename), converter, record, sqlString);
            }
        } else {
            uStatement = initialPreparentStatement(sqlString, connection, uStatement);
            storeTSInDB(node, converter, record, br, connection, uStatement);

        }
    }

    private void storeTSInDB(File node, Gson converter, StoredObject record, BufferedReader br, Connection connection,
            PreparedStatement uStatement) {
        List<String> jsonstrings = new ArrayList<>();
        List<StoredObject> timestampObjects = new ArrayList<>();
        jsonstrings = SparkifyService.extractJsonStringList(node, br);

        for (StoredObject objtype : SparkifyService.extractStoredObjectFromList(jsonstrings, converter, record)) {
            timestampObjects.add(new Timestamps(((TStamps) objtype).getTs()));
        }

        for (StoredObject ts : timestampObjects) {
            uStatement = ts.createPreparedStatement(connection, uStatement);
            
        }
        DatabaseService.executePreparedStatement(uStatement, connection);
    }

    private void storeInDB(File node, Gson converter, StoredObject record, BufferedReader br, Connection connection,
            PreparedStatement uStatement) {
        List<String> jsonstrings = new ArrayList<>();
        jsonstrings = SparkifyService.extractJsonStringList(node, br);

        for (StoredObject objtype : SparkifyService.extractStoredObjectFromList(jsonstrings, converter, record)) {
            // System.out.println(objtype.toString());
            uStatement = objtype.createPreparedStatement(connection, uStatement);
        }
        DatabaseService.executePreparedStatement(uStatement, connection);

    }

}
