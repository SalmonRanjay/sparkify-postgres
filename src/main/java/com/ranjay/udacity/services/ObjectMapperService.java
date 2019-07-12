package com.ranjay.udacity.services;

import java.util.stream.Stream;

import com.google.gson.Gson;
import com.ranjay.udacity.interfaces.StoredObject;

public final class ObjectMapperService {

    private ObjectMapperService() {
    };

    public static <T extends StoredObject>  Stream<T> mapJsonLinesToObjects(Stream<String> jsonLines, Class<T> klass) {
     Gson converter = new Gson();
     return jsonLines.map(json -> converter.fromJson(json, klass));
    }
}