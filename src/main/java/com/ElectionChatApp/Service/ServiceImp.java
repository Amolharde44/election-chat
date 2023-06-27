package com.ElectionChatApp.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ElectionChatApp.DBConfig.DatabaseConfig;
import com.ElectionChatApp.Model.ChatMessage;
import com.ElectionChatApp.Model.Signuppojo;


import io.micronaut.context.ApplicationContext;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;




public class ServiceImp implements ServiceInterface{
	
	
	

//	private MessagingService messagingService;
//
//    public void ChatServiceImpl(MessagingService messagingService) {
//        this.messagingService = messagingService;
//    }
    
    
	@Inject
	ApplicationContext context1;

	@Override
	public String registration(Signuppojo sup) throws IOException, SQLException {
		System.out.println("Conntec to front end");
		DatabaseConfig config = context1.getBean(DatabaseConfig.class);
		String dataBase = config.getDatabaseName();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(),
					config.getPassword());

			stmt = conn.prepareStatement(ECQueryConstant.INSERT_INTO_SIGNUP_TABLE
					.replace(ECQueryConstant.DATA_BASE_PLACE_HOLDER, dataBase));

			stmt.setString(1, sup.getBoothid());
			stmt.setString(2, sup.getUser_name());
			stmt.setString(3, sup.getAgent_secrete());
			
			
			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return "done";
	}

	@Override
	public boolean login(String username) throws IOException, SQLException {
		// TODO Auto-generated method stub
		boolean response=false;
		DatabaseConfig config = context1.getBean(DatabaseConfig.class);
		String dataBase = config.getDatabaseName();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(),
					config.getPassword());

			stmt = conn.prepareStatement(ECQueryConstant.SELECT_USER_FOR_LOGIN
					.replace(ECQueryConstant.DATA_BASE_PLACE_HOLDER, dataBase));

			stmt.setString(1, username);
			ResultSet rs=stmt.executeQuery();
			String usernamee=null;
			
			
			while(rs.next())
				
			{
				usernamee=rs.getString("user_name");
			}
			if(username.equals(usernamee))
			{
				response=true;
			}
			else {
				response=false;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		
		
		return response;
	}

	@Override
	public List<Signuppojo> showallregdetails() throws IOException, SQLException {
	
		DatabaseConfig config = context1.getBean(DatabaseConfig.class);
		String dataBase = config.getDatabaseName();
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Signuppojo> list=new ArrayList<>();
		try {
			conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(),
					config.getPassword());

			stmt = conn.prepareStatement(ECQueryConstant.SELECT_REGISTRATION_DETAILS
					.replace(ECQueryConstant.DATA_BASE_PLACE_HOLDER, dataBase));

			
			ResultSet rs=stmt.executeQuery();
			
			
			
			while(rs.next())
				
			{
				Signuppojo reg=buildData1(rs);
				list.add(reg);
			}
			

		} catch (SQLException e) {

			e.printStackTrace();
		}

		
		
		return list;
	}


	private Signuppojo buildData1(ResultSet rs) throws SQLException {
		Signuppojo sp=new Signuppojo();
		sp.setId(rs.getString("id"));
		sp.setBoothid(rs.getString("Booth_id"));
		sp.setUser_name(rs.getString("user_name"));
		sp.setAgent_secrete(rs.getString("agent_secrete"));
		return sp;
	}

	@Override
	public ChatMessage  sendMessage(ChatMessage chatMessage,CompletedFileUpload image) throws SQLException, IOException {
		String sender = chatMessage.getSender();
        System.out.println("sender:"+sender);
        String receiver = chatMessage.getReceiver();
        System.out.println("receiver:"+receiver);
        String message = chatMessage.getMessage();
        System.out.println("message:"+message);
        
        

        if (receiver.equals(receiver)) {
            System.out.println("Forwarding message to "+receiver+":" + message);
           
            transferMessage(sender, receiver, message,image);
        } else {
            System.out.println("Message ignored as the recipient is not receiver.");
        }
		return chatMessage;
		
	}

	private void transferMessage(String sender, String receiver, String message, CompletedFileUpload image) throws SQLException, IOException {
		
	   
	   
	        
	        System.out.println("Transferring message from " + sender + " to " + receiver + ": " + message);

	        String fileLocation = saveFile(image.getBytes(), image.getFilename());
	        sendMessage(sender, receiver, message,fileLocation,image);

	       
	       // ChatMessage chatMessage = new ChatMessage(sender, receiver, message);
	        //save(chatMessage);

	        
	    }

//	private List<ChatMessage> save(ChatMessage chatMessage) {
//		
//	    	
//	    	DatabaseConfig config = context1.getBean(DatabaseConfig.class);
//			String dataBase = config.getDatabaseName();
//			Connection conn = null;
//			PreparedStatement stmt = null;
//			try {
//				conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(),
//						config.getPassword());
//				 String sql = "INSERT INTO MessagesData (sender, receiver, message) VALUES (?, ?, ?)";
//				stmt  = conn.prepareStatement(sql);
//	        
//	           
//	            {
//	            	stmt.setString(1, chatMessage.getSender());
//	            	stmt.setString(2, chatMessage.getReceiver());
//	            	stmt.setString(3, chatMessage.getMessage());
//	            	stmt.executeUpdate();
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//			return null;
//	    }
	    public List<ChatMessage> getChatHistory(String sender, String receiver) {
	        List<ChatMessage> chatHistory = new ArrayList<>();
	        DatabaseConfig config = context1.getBean(DatabaseConfig.class);
			String dataBase = config.getDatabaseName();
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(),
						config.getPassword());
				
				
	            String sql = "SELECT transaction_id,sender, receiver, message,fileLocation,current_time1 FROM MessagesData WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?)";
	            stmt  = conn.prepareStatement(sql);
	             {
	            	 
	            	 stmt.setString(1, sender);
	            	 stmt.setString(2, receiver);
	            	 stmt.setString(3, receiver);
	            	 stmt.setString(4, sender);
	                try (ResultSet resultSet = stmt.executeQuery()) {
	                    while (resultSet.next()) {
	                    	//String transactioinid=resultSet.getString("transaction_id");
	                        String retrievedSender = resultSet.getString("sender");
	                        //String retrievedReceiver = resultSet.getString("receiver");
	                        String retrievedMessage = resultSet.getString("message");
	                        //String retriveImagenaem=resultSet.getString("fileLocation");
	                       // String retrievedTime=resultSet.getString("current_time1");
	                        
	                        ChatMessage message = new ChatMessage(retrievedSender, retrievedMessage);
	                        chatHistory.add(message);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			
	        return chatHistory;
	    }
	
	

	@SuppressWarnings("resource")
	private void sendMessage(String sender, String receiver, String message, String fileLocation, CompletedFileUpload image) throws SQLException, IOException {
		DatabaseConfig config = context1.getBean(DatabaseConfig.class);
		String dataBase = config.getDatabaseName();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// Save the file to a location (e.g., storage directory)
	    saveFile(fileLocation.getBytes(), image.getFilename());
		try {
		    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		    String currentTime = timeFormat.format(new Date());

		    conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(), config.getPassword());
		    String transactionId = null;
		    if (sender.compareTo(receiver) < 0) {
		        // Sort the sender and receiver names to ensure consistency
		        transactionId = sender + "_" + receiver;
		    } else {
		        transactionId = receiver + "_" + sender;
		    }
		        // Insert the new transaction with the generated ID
		        String insertSql = "INSERT INTO MessagesData (transaction_id,sender, receiver, message,image, fileLocation,current_time1 ) VALUES (?, ?, ?, ?, ?,?,?)";
		        stmt = conn.prepareStatement(insertSql);
		        stmt.setString(1, transactionId);
		        stmt.setString(2, sender);
		        stmt.setString(3, receiver);
		        stmt.setString(4, message);
		        stmt.setBytes(5, fileLocation.getBytes());
		        stmt.setString(6, fileLocation);
		        stmt.setString(7, currentTime);
		       
		        stmt.executeUpdate();
		    
		} finally {
		    // Close the resources in a finally block
		    if (rs != null) {
		        rs.close();
		    }
		    if (stmt != null) {
		        stmt.close();
		    }
		    if (conn != null) {
		        conn.close();
		    }
		}

		

	}

	private String saveFile(byte[] bytes, String filename) throws IOException {
		String folderPath = "D:\\Files\\"; // Specify the folder path where you want to store the files
	    
		// Create the folder if it doesn't exist
	    File folder = new File(folderPath);
	    if (!folder.exists()) {
	        folder.mkdirs();
	    }

	    // Combine the folder path and filename to create the complete file location
	    String fileLocation = folderPath  + filename;

	    // Save the file to the specified location
	    Files.write(Paths.get(fileLocation), bytes);
		return fileLocation;
		
	}

	public ChatMessage getLastMessageByTransactionId(String transactionId) {
	    DatabaseConfig config = context1.getBean(DatabaseConfig.class);
	    String dataBase = config.getDatabaseName();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ChatMessage lastMessage = null;
	    
	    try {
	        conn = DriverManager.getConnection(config.getUrltable() + ";databaseName=" + dataBase, config.getUsername(), config.getPassword());
	        
	        String sql = "SELECT TOP 1 transaction_id, sender, receiver, message, current_time1 FROM MessagesData WHERE transaction_id = ? ORDER BY current_time1 DESC";
	        stmt = conn.prepareStatement(sql);

	        stmt.setString(1, transactionId);
	        
	        try (ResultSet resultSet = stmt.executeQuery()) {
	            if (resultSet.next()) {
	                String retrievedSender = resultSet.getString("sender");
	                String retrievedReceiver = resultSet.getString("receiver");
	                String retrievedMessage = resultSet.getString("message");
	                String retriveImagenaem=resultSet.getString("filename");
	                String retrievedTime = resultSet.getString("current_time1");
	                lastMessage = new ChatMessage(transactionId, retrievedSender, retrievedReceiver, retrievedMessage,retriveImagenaem ,retrievedTime);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close the resources in a finally block
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return lastMessage;
	}

}
