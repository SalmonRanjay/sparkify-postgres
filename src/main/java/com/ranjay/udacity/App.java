package com.ranjay.udacity;

import java.io.File;

import com.ranjay.udacity.models.Artist;
import com.ranjay.udacity.services.SPFileService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
      

        Artist artistsManager = new Artist();
        artistsManager.mapPojoToFileData(SPFileService.processFileToStreamOFStrings(new File("./data")))
        .forEach(item -> {
            System.out.println(item.toString());
        });
     
      
    }
}


