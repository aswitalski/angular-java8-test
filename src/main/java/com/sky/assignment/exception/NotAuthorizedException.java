package com.sky.assignment.exception;

public class NotAuthorizedException extends SkyException {
	
	private static final long serialVersionUID = 1L;

	public NotAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotAuthorizedException(String message) {
		super(message);
	}
	
	public NotAuthorizedException(Throwable cause) {
		super(cause);
	}
	
}
