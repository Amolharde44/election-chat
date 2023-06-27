package com.ElectionChatApp.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ElectionChatApp.DBConfig.DatabaseConfig;

import io.micronaut.context.ApplicationContext;
import jakarta.inject.Inject;

public class MessagingProvider {
	
	@Inject
	ApplicationContext context1;
    
    public void sendMessage(String sender, String receiver, String message) {
    	
    	DatabaseConfig config = context1.getBean(DatabaseConfig.class);
		String dataBase = config.getDatabaseName();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(),
					config.getPassword());
			String sql = "INSERT INTO MessagesData (sender, receiver, message) VALUES (?, ?, ?)";
			stmt  = conn.prepareStatement(sql);
       
            
             {
            	 stmt.setString(1, sender);
            	 stmt.setString(2, receiver);
            	 stmt.setString(3, message);
            	 stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

