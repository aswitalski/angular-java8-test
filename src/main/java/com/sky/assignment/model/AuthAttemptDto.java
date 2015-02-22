package com.sky.assignment.model;

import java.util.Date;

/**
 * Authentication attempt DTO.
 * 
 * @author switalski
 */
public class AuthAttemptDto extends AbstractDto {
	
	private String username;
	
	private AuthResult result;

	private String ip;
	
	private long timestamp;
	
	/**
	 * Default constructor needed for deserialization.
	 */
	public AuthAttemptDto() {
	}
	
	/**
	 * Constructor with all necessary parameters.
	 * 
	 * @param username
	 *            Username
	 * @param result
	 *            Auth result
	 * @param ip
	 *            User ip
	 * @param date
	 *            Date
	 */
	public AuthAttemptDto(String username, AuthResult result, String ip, Date date) {
		this.username = username;
		this.result = result;
		this.ip = ip;
		this.timestamp = (date != null ? date.getTime() / 1000 : 0);
	}
	
	public AuthAttemptDto(String username, AuthResult result, String ip, long timestamp) {
		this.username = username;
		this.result = result;
		this.ip = ip;
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public AuthResult getResult() {
		return result;
	}
	
	public void setResult(AuthResult result) {
		this.result = result;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	private static final long serialVersionUID = 1L;

}
