package com.assignment.backbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assignment.backbase.exceptions.ApplicationException;
import com.assignment.backbase.exceptions.IllegalGameStateException;
import com.assignment.backbase.exceptions.ResourceNotFoundException;
import com.assignment.backbase.model.ExceptionResponse;

@RestControllerAdvice
public class KalahGameControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(Exception ex) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
		return ResponseEntity.ok().body(response);

	}

	@ExceptionHandler(IllegalGameStateException.class)
	public ResponseEntity<ExceptionResponse> handleConflict(IllegalGameStateException ex) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT, ex.getStatus());
		return ResponseEntity.ok().body(response);
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ExceptionResponse> handleOtherExceptions(ApplicationException ex) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok().body(response);
	}
}
