package com.ranjay.udacity.services;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DatabaseService {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/udacity?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    private static final Logger LOGGER = LogManager.getLogger(DatabaseService.class.getName());


    private DatabaseService(){};

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        establishDriver();
        return createDBConnection(dbConnection);
    }

    private static Connection createDBConnection(Connection dbConnection) {
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            dbConnection.setAutoCommit(false);//commit trasaction manually
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

    public static void executePreparedStatement(PreparedStatement statement, Connection connection) {
        try {
            statement.executeBatch();
            connection.commit();
            // statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            LOGGER.error(e.getMessage());
           
        } catch(BatchUpdateException e){
            LOGGER.error(e.getMessage());
        }
        catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }

}