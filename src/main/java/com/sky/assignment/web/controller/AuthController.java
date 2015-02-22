package com.sky.assignment.web.controller;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.assignment.common.SkyWebPaths.Auth;
import com.sky.assignment.dao.AuthAttemptDAO;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.model.AuthResult;
import com.sky.assignment.model.CredentialsDto;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.service.AuthService;

@Controller
@RequestMapping(Auth.ROOT)
public class AuthController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	/** Session attribute name. */
	public static final String ATTR_USER_INFO = "user-info";

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthAttemptDAO authAttemptsDAO;

	/**
	 * Attempts user sign in operation. After a successful attempt stores the user info object in the session.
	 * 
	 * @param credentials
	 *            Credentials data transfer object
	 * @param session
	 *            HTTP session
	 * 
	 * @return User info data transfer object
	 */
	@RequestMapping(value = Auth.SIGN_IN, method = RequestMethod.POST)
	@ResponseBody
	public UserInfoDto signIn(@RequestBody CredentialsDto credentials, HttpSession session, ServletRequest request) {
		LOG.info("Sign in: " + credentials);
		UserInfoDto userInfo = authService.authenticate(credentials.getUsername(), credentials.getPassword());
		AuthResult result;
		if (userInfo != null) {
			result = AuthResult.AUTH_SUCCESS;
			session.setAttribute(ATTR_USER_INFO, userInfo);
		} else {
			result = AuthResult.AUTH_ERROR;
		}

		// TODO: should really be moved to an interceptor
		authAttemptsDAO.insert(new AuthAttemptDto(credentials.getUsername(), result, request.getRemoteAddr(), new Date()));

		return userInfo;
	}
	
	/**
	 * Signs the current user out by invalidating their session.
	 * 
	 * @param session
	 *            HTTP session
	 */
	@RequestMapping(value = Auth.SIGN_OUT, method = RequestMethod.POST)
	@ResponseBody
	public Object signOut(HttpSession session) {
		LOG.info("Sign out");
		session.invalidate();
		return new Object();
	}
	
	/**
	 * Check the sign in of the current user by getting the user info object from the session.
	 * 
	 * @param session
	 *            HTTP session
	 * 
	 * @return User info data transfer object
	 */
	@RequestMapping(value = Auth.CHECK, method = RequestMethod.POST)
	@ResponseBody
	public UserInfoDto isSignedIn(HttpSession session) {
		LOG.info("Sign in check");
		return (UserInfoDto) session.getAttribute(ATTR_USER_INFO);
	}
	
	protected void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	protected void setAuthAttemptsDAO(AuthAttemptDAO authAttemptsDAO) {
		this.authAttemptsDAO = authAttemptsDAO;
	}
}
