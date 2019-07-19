package com.ranjay.udacity;

import java.io.File;
import java.util.stream.Stream;

import com.ranjay.udacity.models.Artist;
import com.ranjay.udacity.models.Song;
import com.ranjay.udacity.models.TStamps;
import com.ranjay.udacity.models.Timestamps;
import com.ranjay.udacity.models.User;
import com.ranjay.udacity.services.DatabaseService;
import com.ranjay.udacity.services.FileService;
import com.ranjay.udacity.services.ObjectMapperService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {

        DatabaseService databaseService = DatabaseService.getInstance();

    
        Thread artistThread = new Thread(()->{
            LOGGER.info("artist Thread");
            String artistStatement = "INSERT INTO artists" + "(artist_id, name, location, lattitude, longitude) VALUES"
            + "(?,?,?,?,?)";
            Stream<String> strings = FileService.directoryToLInes(new File("./data/song_data"));
            Stream<Artist> artists = ObjectMapperService.mapJsonLinesToObjects(strings, Artist.class);
            // artists.forEach(System.out::println);
            databaseService.storeObjectsToDatabase(artists, artistStatement);
        });

        Thread songThread = new Thread(()->{
            LOGGER.info("Song Thread");
            String sqlInsert = "INSERT INTO songs" + "(song_id, title, artist_id,year, duration) VALUES"
                + "(?,?,?,?,?)";
            Stream<String> strings = FileService.directoryToLInes(new File("./data/song_data"));
            Stream<Artist> songs = ObjectMapperService.mapJsonLinesToObjects(strings, Artist.class);
            // songs.forEach(System.out::println);
            databaseService.storeObjectsToDatabase(songs, sqlInsert);
        });
        Thread userThread = new Thread(()->{
            LOGGER.info("User Thread");
            String usertStatement = "INSERT INTO users" + "(user_id, first_name," + "last_name, gender, level) VALUES"
            + " (?,?,?,?,?)";
            Stream<String> strings = FileService.directoryToLInes(new File("./data/log_data"));
            Stream<User> user = ObjectMapperService.mapJsonLinesToObjects(strings, User.class);
            // user.forEach(System.out::println);
            databaseService.storeObjectsToDatabase(user, usertStatement);
        });
        Thread timeStampThread = new Thread(()->{
            LOGGER.info("TimeStamp Thread");
            String timeStampStatement = "INSERT INTO time"
            + "(start_time, hour, day, week, month, year, weekday) VALUES" + "(?,?,?,?,?,?,?)";
            Stream<String> strings = FileService.directoryToLInes(new File("./data/log_data"));
            Stream<TStamps> timestamps = ObjectMapperService.mapJsonLinesToObjects(strings, TStamps.class);
            timestamps.map(time -> new Timestamps(time));
            // .forEach(System.out::println);
            databaseService.storeObjectsToDatabase(timestamps, timeStampStatement);
           
        });
        
        artistThread.start();
        // songThread.start();
        // userThread.start();
        // timeStampThread.start();
       
    }
    
}


