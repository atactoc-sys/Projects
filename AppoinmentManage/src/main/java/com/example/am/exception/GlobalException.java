package com.example.am.exception;

public class GlobalException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	//Constructs a new GlobalException with the specified error message.
	public GlobalException(String msg) {
		super(msg);
		
	}
}
