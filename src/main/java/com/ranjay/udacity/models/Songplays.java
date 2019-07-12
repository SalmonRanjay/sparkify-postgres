package com.ranjay.udacity.models;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Songplays extends StoredObject {

    private String songplay_id;
    private String userId;
    private String level;
    private int sessionId;
    private String location;
    private String userAgent;

    
}