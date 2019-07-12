// package com.ranjay.udacity.services;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// import com.google.gson.Gson;

// public final class FileService {
//     private FileService() {
//     };

    
//     public static java.util.List<File> getAllFilesInDirectory(String dirPath) {
//         List<File> allFiles = new ArrayList<>();
//         traverseDirectory(new File(dirPath), allFiles);
//         return allFiles;
//     }

//     private static void traverseDirectory(File node, List<File> files) {

//         if (node.isDirectory()) {
//             String[] subDirs = node.list();
//             for (String filename : subDirs) {
//                 traverseDirectory(new File(node, filename), files);
//             }
//         } else {
//             // System.out.println(node.getAbsoluteFile());
//             files.add(node.getAbsoluteFile());
//         }
//     }


//     public static <T, P > List<T> returnStoredObject(File node, P objType) {
       
//         List<T> storedObjectList = new ArrayList<>();
//         Gson converter = new Gson();
       
//         try (FileReader fr = new FileReader(node.getAbsolutePath());
//             BufferedReader br = new BufferedReader(fr)){
            
//             String line;
//             while ((line = br.readLine()) != null) {
//                 storedObjectList.add(converter.fromJson(line, (Class<T>)objType.getClass()));
//             }

//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         } 
//         return storedObjectList;
//     }
// }