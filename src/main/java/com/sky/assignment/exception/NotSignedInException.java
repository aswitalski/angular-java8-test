package com.sky.assignment.exception;

public class NotSignedInException extends SkyException {
	
	private static final long serialVersionUID = 1L;

	public NotSignedInException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotSignedInException(String message) {
		super(message);
	}
	
	public NotSignedInException(Throwable cause) {
		super(cause);
	}
	
}
