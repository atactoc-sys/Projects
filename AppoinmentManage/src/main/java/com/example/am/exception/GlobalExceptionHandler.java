package com.example.am.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;


public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	// Handles the GlobalException and returns a ResponseEntity
	//with a custom ErrorMessage and HTTP status code.
	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ErrorMessage> handleGlobalException(GlobalException ex, WebRequest request){
		
		// Create an ErrorMessage with the HTTP status code and the exception message
		ErrorMessage errorMessage= new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
		
		// Return a ResponseEntity with the ErrorMessage and the corresponding HTTP status code
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
