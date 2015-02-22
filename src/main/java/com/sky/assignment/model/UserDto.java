package com.sky.assignment.model;

/**
 * User data DTO.
 * 
 * @author switalski
 */
public class UserDto extends AbstractDto {
	
	private CredentialsDto credentials;
	
	private UserRole role;
	
	public void setCredentials(CredentialsDto credentials) {
		this.credentials = credentials;
	}
	
	public CredentialsDto getCredentials() {
		return credentials;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}

	private static final long serialVersionUID = 1L;

}
