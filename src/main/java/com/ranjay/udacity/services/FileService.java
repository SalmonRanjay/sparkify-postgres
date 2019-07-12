package com.ranjay.udacity.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

public final class FileService{
    
    private FileService(){};

    public static Stream<String> directoryToLInes(File directory){
        return FileUtils.listFiles(directory, null, true)
                .stream()
                .map(FileService::readFileAndCreateListOfStrings)
                .flatMap(list -> list.stream());
    }

    public static void readFileLinesAdnAddToList(File file, List<String> fileContents) throws IOException{
        for (String line : FileUtils.readLines(file, "UTF-8")){
            fileContents.add(line);
        }
    }

    private static List<String> readFileAndCreateListOfStrings(File file){
        List<String> fileContents = new ArrayList<>();
        try {
            readFileLinesAdnAddToList(file, fileContents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileContents;
    }
}