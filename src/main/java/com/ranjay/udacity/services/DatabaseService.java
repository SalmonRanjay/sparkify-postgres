package com.ranjay.udacity.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public final class DatabaseService {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/udacity_demo?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";


    private DatabaseService(){};

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        establishDriver();
        return createDBConnection(dbConnection);
    }

    private static Connection createDBConnection(Connection dbConnection) {
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    private static void establishDriver() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void executePreparedStatement(PreparedStatement statement) {
        try {
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e.getMessage());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}