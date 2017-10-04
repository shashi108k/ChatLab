package org.lab.dto;

import org.springframework.http.HttpStatus;

public class ResponseMessageDTO {

	HttpStatus status;
	
	String message;
	
	String description;

	public ResponseMessageDTO(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ResponseMessageDTO(HttpStatus status, String message, String description) {
		super();
		this.status = status;
		this.message = message;
		this.description = description;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
