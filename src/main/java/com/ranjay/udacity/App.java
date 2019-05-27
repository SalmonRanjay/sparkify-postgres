package com.ranjay.udacity;

import java.util.ArrayList;
import java.util.List;

import com.ranjay.udacity.interfaces.StoredObject;
import com.ranjay.udacity.models.Artist;
import com.ranjay.udacity.models.Song;
import com.ranjay.udacity.models.TStamps;
import com.ranjay.udacity.models.User;
import com.ranjay.udacity.services.DatabaseService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */

class ArtistThread extends Thread {

    @Override
    public void run() {
        String artistStatement = "INSERT INTO artists" + "(artist_id, name, location, lattitude, longitude) VALUES"
                + "(?,?,?,?,?)";
        List<Artist> artists = new ArrayList<>();
        Artist at = new Artist();
        DatabaseService.commitThreadTransaction(artists, at, artistStatement,"./data/song_data");

    }
}

public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread th, Throwable ex) {
                System.out.println("Uncaught exception: " + ex);
            }
        };
        ArtistThread aThread = new ArtistThread();
        aThread.setName("Artiste Thread");
        aThread.setUncaughtExceptionHandler(h);
        aThread.start();

         
        SongThread sThread = new SongThread();
        sThread.setName("Song Thread");
        sThread.setUncaughtExceptionHandler(h);
        sThread.start();

        UserThread uThread = new UserThread();
        uThread.setName("User Thread");
        uThread.setUncaughtExceptionHandler(h);
        uThread.start();

        TimestampThread tsThread = new TimestampThread();
        tsThread.setName("Timestamp Thread");
        tsThread.setUncaughtExceptionHandler(h);
        tsThread.start();
      
    }
}


class SongThread extends Thread {

    @Override
    public void run() {
        String sqlInsert = "INSERT INTO songs" + "(song_id, title, artist_id,year, duration) VALUES"
                + "(?,?,?,?,?)";
        List<StoredObject> songs = new ArrayList<>();
        StoredObject st = new Song();
        DatabaseService.commitThreadTransaction(songs, st, sqlInsert,"./data/song_data");
    }
}

class UserThread extends Thread {
    @Override
    public void run() {
        String usertStatement = "INSERT INTO users" + "(user_id, first_name," + "last_name, gender, level) VALUES"
                + " (?,?,?,?,?)";
        List<StoredObject> users = new ArrayList<>();
        StoredObject st = new User();
        DatabaseService.commitThreadTransaction(users, st, usertStatement,"./data/log_data");
    }
}

class TimestampThread extends Thread {
    @Override
    public void run() {
        String timeStampStatement = "INSERT INTO time"
                + "(start_time, hour, day, week, month, year, weekday) VALUES" + "(?,?,?,?,?,?,?)";
                List<StoredObject> timstamps = new ArrayList<>();
                StoredObject ts = new TStamps();
                DatabaseService.commitThreadTransactionTs(timstamps, ts, timeStampStatement,"./data/log_data");
    }
}
