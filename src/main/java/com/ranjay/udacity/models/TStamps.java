package com.ranjay.udacity.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TStamps  {
    private long ts;

    @Override
    public String toString() {
        return "{" + " ts='" + getTs() + "'" + "}";
    }
    
}