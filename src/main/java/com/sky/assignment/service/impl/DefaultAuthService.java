package com.sky.assignment.service.impl;

import static com.sky.assignment.common.SkyComponents.Services.AUTH_SERVICE;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sky.assignment.model.UserDto;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.model.UserRole;
import com.sky.assignment.service.AuthService;

/**
 * Default authentication service, verifying the user credentials against the predefined JSON file.
 * 
 * @author switalski
 */
@Service(AUTH_SERVICE)
public class DefaultAuthService implements AuthService {
	
	/** List of all users. */
	private List<UserDto> users = Lists.newArrayList();

	/** Loads users data. */
	@PostConstruct
	protected void loadUserData() {
		// TODO: read users
	}
	
	/**
	 * Performs authentication for given username (case insensitive) and password (case sensitive).
	 * 
	 * @return User role if authenticated successfully, null otherwise.
	 */
	@Override
	public UserInfoDto authenticate(String username, String password) {
		// TODO: implement
		return new UserInfoDto(username, UserRole.USER);
	}
	
}
