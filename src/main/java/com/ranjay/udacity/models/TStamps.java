package com.ranjay.udacity.models;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TStamps extends StoredObject  {
    private long ts;

    @Override
    public String toString() {
        return "{" + " ts='" + getTs() + "'" + "}";
    }
    
}