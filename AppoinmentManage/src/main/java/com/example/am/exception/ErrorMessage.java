package com.example.am.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage 
{
	// The HTTP status code of the error
	private HttpStatus status;
	
	// The error message
	private String message;
	
}
