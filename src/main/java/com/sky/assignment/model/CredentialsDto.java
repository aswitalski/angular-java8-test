package com.sky.assignment.model;

/**
 * User credentials DTO.
 * 
 * @author switalski
 */
public class CredentialsDto extends AbstractDto {
	
	private String username;
	
	private String password;
	
	/**
	 * Default constructor. Used in deserialization.
	 */
	public CredentialsDto() {
	}
	
	/**
	 * Constructor accepting username and password (for convenience).
	 * 
	 * @param username
	 *            Username
	 * @param password
	 *            Plain password
	 */
	public CredentialsDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	private static final long serialVersionUID = 1L;

}
