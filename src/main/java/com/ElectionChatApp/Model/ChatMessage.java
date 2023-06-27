package com.ElectionChatApp.Model;

import java.sql.Time;

public class ChatMessage {

	private String transactioinid;
	private String sender;
    private String receiver;
    private String message;
    private String filename;
    private String current_time1;
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatMessage(String transactioinid, String sender, String receiver, String message, String filename,
			String current_time1) {
		super();
		this.transactioinid = transactioinid;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.filename = filename;
		this.current_time1 = current_time1;
	}
	
		
		
		public ChatMessage(String retrievedSender, String retrievedMessage) {
		this.sender=retrievedSender;
		this.message=retrievedMessage;
	}
		public String getTransactioinid() {
		return transactioinid;
	}
	public void setTransactioinid(String transactioinid) {
		this.transactioinid = transactioinid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCurrent_time1() {
		return current_time1;
	}
	public void setCurrent_time1(String current_time1) {
		this.current_time1 = current_time1;
	}
	
	
    
    
}
