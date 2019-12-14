package com.assignment.backbase.exceptions;

import com.assignment.backbase.enums.Status;

public class IllegalGameStateException extends RuntimeException {
	 private final Status gameStatus;
	 

	public IllegalGameStateException(String message, Status gameStatus) {
		super(message);
		this.gameStatus = gameStatus;
	}


	public Status getStatus() {
        return gameStatus;
    }
}
