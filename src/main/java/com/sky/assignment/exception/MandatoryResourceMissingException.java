package com.sky.assignment.exception;

public class MandatoryResourceMissingException extends SkyException {
	
	public MandatoryResourceMissingException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MandatoryResourceMissingException(String message) {
		super(message);
	}
	
	public MandatoryResourceMissingException(Throwable cause) {
		super(cause);
	}
	
	private static final long serialVersionUID = 1L;

}
