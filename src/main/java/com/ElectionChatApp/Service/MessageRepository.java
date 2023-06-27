package com.ElectionChatApp.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ElectionChatApp.DBConfig.DatabaseConfig;
import com.ElectionChatApp.Model.ChatMessage;

import io.micronaut.context.ApplicationContext;
import jakarta.inject.Inject;

public class MessageRepository {
//	@Inject
//	ApplicationContext context1;
//    public void save(ChatMessage chatMessage) {
//    	
//    	DatabaseConfig config = context1.getBean(DatabaseConfig.class);
//		String dataBase = config.getDatabaseName();
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		try {
//			conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(),
//					config.getPassword());
//			 String sql = "INSERT INTO MessagesData (sender, receiver, message) VALUES (?, ?, ?)";
//			stmt  = conn.prepareStatement(sql);
//        
//           
//            {
//            	stmt.setString(1, chatMessage.getSender());
//            	stmt.setString(2, chatMessage.getReceiver());
//            	stmt.setString(3, chatMessage.getMessage());
//            	stmt.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public List<ChatMessage> getChatHistory(String sender, String receiver) {
//        List<ChatMessage> chatHistory = new ArrayList<>();
//        DatabaseConfig config = context1.getBean(DatabaseConfig.class);
//		String dataBase = config.getDatabaseName();
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		try {
//			conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(),
//					config.getPassword());
//			
//			
//            String sql = "SELECT sender, receiver, message FROM MessagesData WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?)";
//            stmt  = conn.prepareStatement(sql);
//             {
//            	 
//            	 stmt.setString(1, sender);
//            	 stmt.setString(2, receiver);
//            	 stmt.setString(3, receiver);
//            	 stmt.setString(4, sender);
//                try (ResultSet resultSet = stmt.executeQuery()) {
//                    while (resultSet.next()) {
//                        String retrievedSender = resultSet.getString("sender");
//                        String retrievedReceiver = resultSet.getString("receiver");
//                        String retrievedMessage = resultSet.getString("message");
//                        ChatMessage message = new ChatMessage(retrievedSender, retrievedReceiver, retrievedMessage);
//                        chatHistory.add(message);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return chatHistory;
  //  }

	
}

