package com.assignment.backbase.model;

import org.springframework.http.HttpStatus;

import com.assignment.backbase.enums.Status;

public class ExceptionResponse {

	
	private String message;

    private HttpStatus httpStatus;

    private Status gameStatus;
    
    
    
	public ExceptionResponse() {
	}

	
	
	public ExceptionResponse(String message, HttpStatus httpStatus, Status gameStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
		this.gameStatus = gameStatus;
	}



	public ExceptionResponse(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Status getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(Status gameStatus) {
		this.gameStatus = gameStatus;
	}

	
	
}
