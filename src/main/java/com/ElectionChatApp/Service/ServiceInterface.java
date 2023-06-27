package com.ElectionChatApp.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ElectionChatApp.Model.ChatMessage;
import com.ElectionChatApp.Model.Signuppojo;

import io.micronaut.http.multipart.CompletedFileUpload;


public interface ServiceInterface {
	
	
	
	String registration(Signuppojo sup) throws IOException,SQLException;
	
	
	boolean login(String username) throws IOException,SQLException;
	
	
	List<Signuppojo>  showallregdetails()  throws IOException,SQLException;
	
	
	
	ChatMessage sendMessage(ChatMessage chatMessage,CompletedFileUpload image) throws SQLException, IOException;
	
	List<ChatMessage> getChatHistory(String sender, String receiver) throws SQLException ;


	ChatMessage getLastMessageByTransactionId(String transaction_id);
	
	// ChatMessage getLastMessageByTransactionId(String transactionId) ;

}
