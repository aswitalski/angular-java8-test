package com.sky.assignment.dao;

import java.util.List;

import com.sky.assignment.model.AuthAttemptDto;

/**
 * Simple interface for managing persistence of authentication attempts.
 * 
 * @author switalski
 */
public interface AuthAttemptDAO {
	
	/**
	 * Inserts new authentication attempt.
	 * 
	 * @param authAttempt
	 *            Auth attempt data transfer object
	 */
	void insert(AuthAttemptDto authAttempt);
	
	/**
	 * Returns all authentication attempts.
	 * 
	 * @return List of all authentication attemp data transfer objects
	 */
	List<AuthAttemptDto> getAuthAttempts();
	
}
