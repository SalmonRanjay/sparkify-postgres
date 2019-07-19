package com.ranjay.udacity.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

import com.ranjay.udacity.interfaces.StoredObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseService {
    private final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_CONNECTION = "jdbc:mysql://localhost:3306/udacity?useSSL=false&serverTimezone=UTC";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "password";
    private final Logger LOGGER = LogManager.getLogger(DatabaseService.class.getName());
    private Connection dbConnection;
    private static DatabaseService instance;

    private DatabaseService() {
        establishDriver();
        createDBConnection();
    };


    private void createDBConnection() {
        try {
            this.dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            // this.dbConnection.setAutoCommit(false);// commit trasaction manually
            this.initDB();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void establishDriver() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DatabaseService getInstance(){
        if(instance == null){
            instance = new DatabaseService();
        }
        return instance;
    }


    /**
     * 
     * @param objects Stream of StoredObjects to store to DB
     * @param sqlString SQL String for insert statement
     */
    public <T extends StoredObject> void storeObjectsToDatabase(Stream<T> objects, String sqlString){
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sqlString);
            objects.forEach(item -> {
                try {
                    LOGGER.info(item.toString());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute a DB query given a SQL string
     * @param sqlQuery
     */
    public void executeDBStatement(String sqlQuery){
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sqlQuery);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /***
     * *******************************************************
     * **************** DATABASE INITIALIZATION **************
     * *******************************************************
     */
    public void dropTables(){
        String dropSongPlayTable = "DROP TABLE IF EXISTS songplays";
        String dropUserTable = "DROP TABLE IF EXISTS users";
        String dropSongTable ="DROP TABLE IF EXISTS songs";
        String dropArtistTable = "DROP TABLE IF EXISTS artists";
        String dropTimeTable = "DROP TABLE IF EXISTS time";
         
        try {
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.execute(dropSongPlayTable);
            stmt.execute(dropUserTable);
            stmt.execute(dropSongTable);
            stmt.execute(dropArtistTable);
            stmt.execute(dropTimeTable);
            stmt.execute("CREATE DATABASE IF NOT EXISTS udacity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void createUsersTable(){
        StringBuilder songPlayTable = new StringBuilder();
       songPlayTable.append("CREATE TABLE IF NOT EXISTS users( ")
       .append("user_id VARCHAR(90) NOT NULL UNIQUE, ")
       .append("first_name TEXT, ")
       .append("last_name TEXT, ")
       .append("gender TEXT, ")
       .append("level TEXT, ")
       .append("PRIMARY KEY (user_id) );");
        
        executeStringStatement(songPlayTable);
    }

   

    public void createSongsTable(){
        StringBuilder songTable = new StringBuilder();
        songTable.append("CREATE TABLE IF NOT EXISTS songs( ")
        .append(" song_id VARCHAR(90) NOT NULL UNIQUE, ")
        .append("title TEXT, ")
        .append("artist_id TEXT, ")
        .append(" year int, ")
        .append("duration float(10),")
        .append("PRIMARY KEY (song_id) );");
        executeStringStatement(songTable);

    }

    public void createArtistTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS artists( ")
            .append("artist_id VARCHAR(90) NOT NULL UNIQUE , ")
            .append("name TEXT, ")
            .append("location TEXT, ")
            .append("lattitude FLOAT(10), ")
            .append("longitude FLOAT(10), ")
            .append("PRIMARY KEY (artist_id) ); ");
            executeStringStatement(builder);
    }

    public void createTimeTable(){
        StringBuilder builder = new StringBuilder();
        builder.append(" CREATE TABLE IF NOT EXISTS time( ")
            .append("start_time TEXT, ")
            .append("hour int, ")
            .append(" day int,")
            .append(" week int,")
            .append(" month int,")
            .append(" year int,")
            .append(" weekday TEXT,")
            .append(" id int NOT NULL AUTO_INCREMENT,")
            .append(" PRIMARY KEY (id));");
            executeStringStatement(builder);
    };

    public void createSongPlayTable(){
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS songplays( ")
            .append("  songplay_id VARCHAR(90) NOT NULL UNIQUE,")
            .append(" start_time TEXT,")
            .append(" user_id VARCHAR(90),")
            .append(" level TEXT,")
            .append("  song_id VARCHAR(90),")
            .append(" session_id int,")
            .append(" location TEXT,")
            .append("  user_agent TEXT,")
            .append("  artist_id VARCHAR(90),")
            .append(" PRIMARY KEY (songplay_id),")
            .append(" FOREIGN KEY (user_id) REFERENCES users(user_id),")
            .append("  FOREIGN KEY (song_id) REFERENCES songs(song_id),")
            .append("    FOREIGN KEY (artist_id) REFERENCES artists(artist_id) );");
            executeStringStatement(builder);
            
    }

    public void initDB(){
        dropTables();
        createSongsTable();
        createArtistTable();
        createTimeTable();
        createUsersTable();
        createSongPlayTable();
    }

    private void executeStringStatement(StringBuilder statementBuilder) {
        try {
            Statement stmt = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.execute(statementBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     /**
     * Create microbatches of size 100 from a List of BoundStatements lists
     */
    // public static void executeBatchStatment(){ 
    //     List<List<BoundStatement>> output = ListUtils.partition(boundList, 100);
    //     for (List<BoundStatement> boundedStatement : output) {
    //         BatchStatement microBatches = new BatchStatement();
    //         microBatches.addAll(boundedStatement);
    //         if(session.execute(microBatches) != null)
    //             System.out.println("Successfully Inserted Batch of size: " + microBatches.size());
            
    //     }
    // }

}