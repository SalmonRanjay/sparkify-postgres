package com.ranjay.udacity;

import java.io.File;
import java.util.stream.Stream;

import com.ranjay.udacity.models.Artist;
import com.ranjay.udacity.services.FileService;
import com.ranjay.udacity.services.ObjectMapperService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
      
        Stream<String> strings = FileService.directoryToLInes(new File("./data/song_data"));
        Stream<Artist> artists = ObjectMapperService.mapJsonLinesToObjects(strings, Artist.class);

        artists.forEach(System.out::println);
    }
    
}


