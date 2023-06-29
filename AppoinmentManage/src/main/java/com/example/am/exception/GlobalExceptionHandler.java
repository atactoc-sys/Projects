package com.example.am.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;


public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<ErrorMessage> handleGlobalException(GlobalException ex, WebRequest request){
		
		ErrorMessage errorMessage= new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
