package com.ranjay.udacity.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface StoredObject {
    public PreparedStatement createPreparedStatement(Connection connection);
}