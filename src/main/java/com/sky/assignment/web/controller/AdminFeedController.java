package com.sky.assignment.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.assignment.common.SkyWebPaths.Feed;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.service.FeedService;

@Controller(Feed.ROOT)
public class AdminFeedController {
	
	@Autowired
	private FeedService feedService;

	/**
	 * Returns a list of all authentication attempts.
	 */
	@RequestMapping(value = Feed.AUTH_ATTEMPTS, method = RequestMethod.GET)
	@ResponseBody
	public List<AuthAttemptDto> getAuthAttempts() {
		return feedService.getAuthAttempts();
	}
}
