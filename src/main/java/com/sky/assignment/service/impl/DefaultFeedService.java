package com.sky.assignment.service.impl;

import static com.sky.assignment.common.SkyComponents.Services.FEED_SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.assignment.dao.AuthAttemptDAO;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.service.FeedService;

@Service(FEED_SERVICE)
public class DefaultFeedService implements FeedService {
	
	@Autowired
	private AuthAttemptDAO dao;
	
	@Override
	public List<AuthAttemptDto> getAuthAttempts() {
		return dao.getAuthAttempts();
	}
	
}
