package com.sky.assignment.service;

import java.util.List;

import com.sky.assignment.model.AuthAttemptDto;

/**
 * Returns data feeds.
 * 
 * @author switalski
 */
public interface FeedService {
	
	/**
	 * Returns the list of all authentication attempts.
	 * 
	 * @return List of auth attempt data transfer objects
	 */
	List<AuthAttemptDto> getAuthAttempts();
}
