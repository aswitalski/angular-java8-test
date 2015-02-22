package com.sky.assignment.service.impl;

import static com.sky.assignment.common.SkyComponents.Services.FEED_SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.assignment.dao.AuthAttemptDAO;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.service.FeedService;

/**
 * Default feed service, just a simple delegate for this case. It could use caching mechanism in real-world scenario.
 * 
 * @author switalski
 */
@Service(FEED_SERVICE)
public class DefaultFeedService implements FeedService {
	
	@Autowired
	private AuthAttemptDAO dao;
	
	/**
	 * Returns all user authentication attempts.
	 * 
	 * @return List of authentication attempt data transfer objects
	 */
	@Override
	public List<AuthAttemptDto> getAuthAttempts() {
		return dao.getAuthAttempts();
	}
	
	protected void setDao(AuthAttemptDAO dao) {
		this.dao = dao;
	}

}
