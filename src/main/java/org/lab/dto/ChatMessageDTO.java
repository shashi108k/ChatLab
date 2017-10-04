package org.lab.dto;

import java.util.Date;

public class ChatMessageDTO {

	private String from;
	private String text;
	private String message;
	private String topic;
	private String time;// = new Date();
	
	public ChatMessageDTO() {
	}
	
	public ChatMessageDTO(String from, String message, String time) {
		super();
		this.from = from;
		this.message = message;
		this.time = time;
	}
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
