package com.sky.assignment.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sky.assignment.exception.InvalidCredentialsException;
import com.sky.assignment.exception.NotAuthorizedException;
import com.sky.assignment.exception.NotSignedInException;

/**
 * Abstract controller, basis for all application controllers.
 * 
 * @author switalski
 */
public class AbstractController {
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public void handleInvalidCredentials(InvalidCredentialsException ex, HttpServletResponse response) {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}
	
	@ExceptionHandler(NotSignedInException.class)
	public void handleNotSignedIn(NotSignedInException ex, HttpServletResponse response) {
		response.setStatus(HttpStatus.FORBIDDEN.value());
	}

	@ExceptionHandler(NotAuthorizedException.class)
	public void handleNotAuthorized(NotAuthorizedException ex, HttpServletResponse response) {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

}
