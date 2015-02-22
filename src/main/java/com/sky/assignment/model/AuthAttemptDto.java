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
	
	private Date timestamp;
	
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
	 * @param dateTime
	 *            Timestamp
	 */
	public AuthAttemptDto(String username, AuthResult result, String ip, Date timestamp) {
		this.username = username;
		this.result = result;
		this.ip = ip;
		this.timestamp = (timestamp != null ? new Date(timestamp.getTime()) : null);
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
	
	public Date getTimestamp() {
		return timestamp != null ? new Date(timestamp.getTime()) : null;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = (timestamp != null ? new Date(timestamp.getTime()) : null);
	}

	private static final long serialVersionUID = 1L;

}
