package com.ranjay.udacity.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

public final class SPFileService {

    private SPFileService() {
    };

    public static Stream<String> processFileToStreamOFStrings(File file){
        return FileUtils.listFiles(file, null, true)
                .stream()
                .map(fileItem -> readFileAndCreateListOfStrings.apply(fileItem) )
                .flatMap(list -> list.stream());
    }

    private static void readFileLinesAndAddToList(File file, List<String> fileContents) throws IOException {
        for (String line : FileUtils.readLines(file, "UTF-8")) {
            fileContents.add(line);
        }
    };

    private static Function<File, List<String>> readFileAndCreateListOfStrings = (file) -> {
        List<String> fileContents = new ArrayList<>();
        try {
            readFileLinesAndAddToList(file, fileContents);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContents;
    };

   
}