package com.ElectionChatApp.DBConfig;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import io.micronaut.context.ApplicationContext;
import jakarta.inject.Inject;

import java.sql.SQLException;

public class DatabaseInitializer {
    
    @Inject
    ApplicationContext context;
    

   

    public void initialize() throws SQLException {
    	 DatabaseConfig config = context.getBean(DatabaseConfig.class);
        System.out.println("Database URL: " + config.getUrl());

        // check if database exists
        try (Connection conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword())) {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getCatalogs();
            boolean databaseExists = false;
            while (rs.next()) {
                String dbName = rs.getString("TABLE_CAT");
               // System.out.println("dbName:"+dbName);
                if (config.getDatabaseName().equalsIgnoreCase(dbName)) {
                    databaseExists = true;
                    break;
                }
            }
            rs.close();
            if (databaseExists) {
                System.out.println("Database already exists, skipping creation.");

                createTable();

            } else {
                System.out.println("Creating database...");
                Statement stmt = conn.createStatement();
                String sql = "CREATE DATABASE " + config.getDatabaseName();
                System.out.println("sql:"+sql);
                stmt.executeUpdate(sql);
                // add table creation or any other initialization steps here
             // create table
                createTable();
                
            }
        }
    

        
    }

    private void createTable() throws SQLException {
        DatabaseConfig config = context.getBean(DatabaseConfig.class);
        try (Connection conn = DriverManager.getConnection(config.getUrltable(), config.getUsername(), config.getPassword())) {
            DatabaseMetaData metaData = conn.getMetaData();
            List<String> tables = Arrays.asList("Signup","MessagesData");
            for (String tableName : tables) {
                ResultSet rs = metaData.getTables(config.getDatabaseName(), null, tableName, null);
                if (rs.next()) {
                    System.out.println("Table " + tableName + " already exists, skipping creation.");
                } else {
                    System.out.println("Creating table " + tableName + "...");
                    String sql = "";
                    switch (tableName) {
                        case "Signup":
                            sql = "CREATE TABLE ecchat.dbo.Signup("
                                + "id int IDENTITY,"
                                + "Booth_id VARCHAR(50),"
                                + "user_name VARCHAR(50),"
                                + "agent_secrete VARCHAR(50))";
                               
                                
                            break;
                            
                        case "MessagesData":
                        	sql = "CREATE TABLE ecchat.dbo.MessagesData ("
                        		      + "id INT PRIMARY KEY IDENTITY,"
                        			  +"transaction_id varchar(255),"
                        		      + "sender VARCHAR(255) NOT NULL,"
                        		      + "receiver VARCHAR(255) NOT NULL,"
                        		      + "message TEXT,"
                        		      +"image VARBINARY(MAX),"
                        		      + "fileLocation VARCHAR(255),"
                        		      + "current_time1 VARCHAR(10))";
                        	          

                                
                            

                            break;
                           
                           
                            
                               
      
                       
                    }
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    System.out.println("Table " + tableName + " created successfully.");
                }
            }
        }
    }

}
