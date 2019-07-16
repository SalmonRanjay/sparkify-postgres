package com.ranjay.udacity.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseService {
    private final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_CONNECTION = "jdbc:mysql://localhost:3306/udacity?useSSL=false&serverTimezone=UTC";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "password";
    private final Logger LOGGER = LogManager.getLogger(DatabaseService.class.getName());
    private Connection dbConnection;
    private static DatabaseService instance;

    private DatabaseService() {
        establishDriver();
        createDBConnection();
    };


    private void createDBConnection() {
        try {
            this.dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            this.dbConnection.setAutoCommit(false);// commit trasaction manually
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void establishDriver() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DatabaseService getInstance(){
        if(instance == null){
            instance = new DatabaseService();
        }
        return instance;
    }

     /**
     * Create microbatches of size 100 from a List of BoundStatements lists
     */
    // public static void executeBatchStatment(){ 
    //     List<List<BoundStatement>> output = ListUtils.partition(boundList, 100);
    //     for (List<BoundStatement> boundedStatement : output) {
    //         BatchStatement microBatches = new BatchStatement();
    //         microBatches.addAll(boundedStatement);
    //         if(session.execute(microBatches) != null)
    //             System.out.println("Successfully Inserted Batch of size: " + microBatches.size());
            
    //     }
    // }

}