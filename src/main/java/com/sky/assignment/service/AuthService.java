package com.sky.assignment.service;

import com.sky.assignment.model.UserInfoDto;

/**
 * Authentication service.
 * 
 * @author switalski
 */
public interface AuthService {
	
	/**
	 * Authenticates user with given username and password.
	 * 
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 * 
	 * @return User info data transfer object if the authentication succeeds, null otherwise
	 */
	UserInfoDto authenticate(String username, String password);

}
