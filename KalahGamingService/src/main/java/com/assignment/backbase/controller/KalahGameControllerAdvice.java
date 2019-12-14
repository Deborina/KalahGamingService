package com.assignment.backbase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assignment.backbase.exceptions.ApplicationException;
import com.assignment.backbase.exceptions.IllegalGameStateException;
import com.assignment.backbase.exceptions.ResourceNotFoundException;
import com.assignment.backbase.model.ExceptionResponse;


@RestControllerAdvice
public class KalahGameControllerAdvice {

	 @ExceptionHandler(ResourceNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ExceptionResponse handleResourceNotFoundException (Exception ex) {
	        return new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(IllegalGameStateException.class)
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public ExceptionResponse handleConflict(IllegalGameStateException ex) {
	        return new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT, ex.getStatus());
	    }
	    
	    @ExceptionHandler(ApplicationException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ExceptionResponse handleOtherExceptions(ApplicationException ex) {
	        return new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	    }
}
