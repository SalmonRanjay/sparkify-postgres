package com.ranjay.udacity.models;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ranjay.udacity.interfaces.StoredObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TStamps implements StoredObject{
    private long ts;


    @Override
    public String toString() {
        return "{" +
            " ts='" + getTs() + "'" +
            "}";
    }
    

	@Override
	public PreparedStatement createPreparedStatement(Connection connection) {
		return null;
	}
}