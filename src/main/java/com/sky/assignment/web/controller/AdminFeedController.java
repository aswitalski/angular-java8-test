package com.sky.assignment.web.controller;

import static com.sky.assignment.web.controller.AuthController.ATTR_USER_INFO;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.assignment.common.SkyWebPaths.Feed;
import com.sky.assignment.exception.NotAuthorizedException;
import com.sky.assignment.exception.NotSignedInException;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.model.UserRole;
import com.sky.assignment.service.FeedService;

@Controller
@RequestMapping(Feed.ROOT)
public class AdminFeedController extends AbstractController {
	
	@Autowired
	private FeedService feedService;

	/**
	 * Returns a list of all authentication attempts.
	 */
	@RequestMapping(value = Feed.AUTH_ATTEMPTS, method = RequestMethod.GET)
	@ResponseBody
	public List<AuthAttemptDto> getAuthAttempts(HttpSession session) {
		UserInfoDto userInfo = (UserInfoDto) session.getAttribute(ATTR_USER_INFO);
		
		// all the signed-in user checks should really be moved to a security aspect, but present here just to have more than a delegate
		// and be able to write some "interesting" unit test

		if (userInfo != null && userInfo.getRole() == UserRole.ADMIN) {
			return feedService.getAuthAttempts();
		} else if (userInfo == null) {
			throw new NotSignedInException("User is not signed in!");
		} else {
			throw new NotAuthorizedException("User " + userInfo.getUsername() + " is not authorized to access authentication attempts feed!");
		}
	}
	
	protected void setFeedService(FeedService feedService) {
		this.feedService = feedService;
	}
}
