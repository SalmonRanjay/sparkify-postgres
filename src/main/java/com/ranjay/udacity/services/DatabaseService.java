package com.ranjay.udacity.services;

import java.io.File;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ranjay.udacity.interfaces.StoredObject;
import com.ranjay.udacity.models.TStamps;
import com.ranjay.udacity.models.Timestamps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DatabaseService {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/udacity?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    private static final Logger LOGGER = LogManager.getLogger(DatabaseService.class.getName());

    private DatabaseService() {
    };

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        establishDriver();
        return createDBConnection(dbConnection);
    }

    private static Connection createDBConnection(Connection dbConnection) {
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            dbConnection.setAutoCommit(false);// commit trasaction manually
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

    public static void executeBatchPreparedStatement(PreparedStatement statement, Connection connection) {
        try {
            statement.executeBatch();
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            LOGGER.error(e.getMessage());
        } catch (BatchUpdateException e) {
            LOGGER.error(e.getLocalizedMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static <T extends StoredObject> void commitThreadTransaction(List<T> storedObjectsList, T storedObj,
            String sqlInsertStatement, String filepath) {

        Connection connection = getDBConnection();
        PreparedStatement statement;
        List<T> intermediateObjectList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sqlInsertStatement);
            for (File file : FileService.getAllFilesInDirectory(filepath)) {
                intermediateObjectList.addAll(FileService.returnStoredObject(file, storedObj));
            }
            Set<T> set = new HashSet<T>(intermediateObjectList);
            storedObjectsList.addAll(set);
            for (T art : storedObjectsList) {
                statement = art.createPreparedStatement(connection, statement);
            }
            executeBatchPreparedStatement(statement, connection);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        }
    }

    public static <T extends StoredObject, P> void commitThreadTransactionTs(List<T> storedObjectsList, P storedObj,
            String sqlInsertStatement, String filepath) {

        Connection connection = getDBConnection();
        PreparedStatement statement;
        List<P> intermediateObjectList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sqlInsertStatement);
            for (File file : FileService.getAllFilesInDirectory(filepath)) {
                intermediateObjectList.addAll(FileService.returnStoredObject(file, storedObj));
            }
            for (P item : intermediateObjectList) {
                TStamps timestamp = (TStamps) item;
                storedObjectsList.add((T) new Timestamps(timestamp.getTs()));
            }

            for (T art : storedObjectsList) {
                statement = art.createPreparedStatement(connection, statement);
            }
            executeBatchPreparedStatement(statement, connection);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        }
    }

}