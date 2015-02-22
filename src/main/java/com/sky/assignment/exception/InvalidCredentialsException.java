package com.sky.assignment.exception;

public class InvalidCredentialsException extends SkyException {
	
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidCredentialsException(String message) {
		super(message);
	}
	
	public InvalidCredentialsException(Throwable cause) {
		super(cause);
	}
	
}
