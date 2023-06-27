package com.ElectionChatApp.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ElectionChatApp.Model.ChatMessage;
import com.ElectionChatApp.Model.Signuppojo;
import com.ElectionChatApp.Service.ServiceInterface;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;

@Controller
public class APIController {
@Inject
ServiceInterface si;

@Post("/Register")

String register(Signuppojo sup) throws IOException, SQLException
{
	
	String r=si.registration(sup);
	return r;
	
}

@Get("/login/{username}")
boolean login(@PathVariable ("username")  String username ) throws IOException, SQLException {
	
	
	boolean response=si.login(username);
	return response;
	
	
}

@Get("/showall")
List<Signuppojo>  showall() throws IOException, SQLException
{
	
	System.out.println("show all");
	return si.showallregdetails();
	
}
@Post(uri="/sendMessage", consumes = MediaType.MULTIPART_FORM_DATA , produces = MediaType.TEXT_PLAIN)
public ChatMessage sendMessage(@Body ChatMessage chatMessage,CompletedFileUpload image) throws SQLException, IOException {
	ChatMessage cm=    si.sendMessage(chatMessage, image);
	return cm;
}

@Get("/receiveMesaage/{sender}/{receiver}")
List<ChatMessage> getChatHistory(@PathVariable   String sender, String receiver) throws SQLException 
{
	List<ChatMessage> l=si.getChatHistory(sender, receiver);
	
	return l;
	
}


@Get("/lastMesaage/{transaction_id}")
ChatMessage lastmdg(@PathVariable   String transaction_id) 
{
	ChatMessage l=si.getLastMessageByTransactionId(transaction_id);
	
	return l;
	
}
}
