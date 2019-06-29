package com.ranjay.udacity.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.ranjay.udacity.interfaces.StoredObject;

public final class ObjectMapperService {

    private ObjectMapperService() {
    };

    public static <T extends StoredObject> Stream<T> mapPojoToFileData(Stream<String> fileContents, Class<?> klass) {
        List<T> storedObjectList = new ArrayList<>();
        Gson converter = new Gson();
        for (String line : fileContents.collect(Collectors.toList())) {
            // clazz.cast(getNameMap(clazz).get(name)
            // convert unchecked class to generic
            storedObjectList.add(converter.fromJson(line, (Class<T>) klass.cast(klass.getName())));
        }
        return storedObjectList.stream();
    }

    // Version 2 using types and reflection for reference 
    public static <T extends StoredObject> Stream<T> mapPojoToFileDataType(Stream<String> fileContents,
            Class<T> klass) {
        List<T> storedObjectList = new ArrayList<>();
        Gson converter = new Gson();
        for (String line : fileContents.collect(Collectors.toList())) {
            // clazz.cast(getNameMap(clazz).get(name)
            // convert unchecked class to generic
            storedObjectList.add(converter.fromJson(line, (Type) klass.cast(klass.getName())));
        }
        return storedObjectList.stream();
    }
}