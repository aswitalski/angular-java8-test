package com.sky.assignment.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.assignment.common.SkyWebPaths.Auth;
import com.sky.assignment.model.CredentialsDto;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.service.AuthService;

@Controller
@RequestMapping(Auth.ROOT)
public class AuthController {
	
	/** Session attribute name. */
	private static final String ATTR_USER_INFO = "user-info";

	@Autowired
	private AuthService authService;
	
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
	public UserInfoDto signIn(@RequestBody CredentialsDto credentials, HttpSession session) {
		UserInfoDto userInfo = authService.authenticate(credentials.getUsername(), credentials.getPassword());
		if (userInfo != null) {
			session.setAttribute(ATTR_USER_INFO, userInfo);
		}
		return userInfo;
	}
	
	/**
	 * Signs the current user out by invalidating their session.
	 * 
	 * @param session
	 *            HTTP session
	 */
	@RequestMapping(value = Auth.SIGN_OUT, method = RequestMethod.POST)
	public void signOut(HttpSession session) {
		session.invalidate();
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
		return (UserInfoDto) session.getAttribute(ATTR_USER_INFO);
	}
}
