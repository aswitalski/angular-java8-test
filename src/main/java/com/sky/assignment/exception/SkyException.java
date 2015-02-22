package com.sky.assignment.exception;

public class SkyException extends RuntimeException {
	
	public SkyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SkyException(String message) {
		super(message);
	}
	
	public SkyException(Throwable cause) {
		super(cause);
	}
	
	private static final long serialVersionUID = 1L;
	
}
