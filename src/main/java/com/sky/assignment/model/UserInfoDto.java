package com.sky.assignment.model;

/**
 * User information DTO, username and the role.
 * 
 * @author switalski
 */
public class UserInfoDto extends AbstractDto {
	
	private String username;
	
	private UserRole role;
	
	public UserInfoDto() {
	}
	
	public UserInfoDto(String username, UserRole role) {
		this.username = username;
		this.role = role;
	}

	public UserInfoDto(UserDto user) {
		this.username = user.getCredentials().getUsername();
		this.role = user.getRole();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}

	private static final long serialVersionUID = 1L;

}
