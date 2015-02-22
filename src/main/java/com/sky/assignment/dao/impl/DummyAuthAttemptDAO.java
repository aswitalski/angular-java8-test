package com.sky.assignment.dao.impl;

import static com.sky.assignment.common.SkyComponents.DAOs.AUTH_ATTEMPT_DAO;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sky.assignment.dao.AuthAttemptDAO;
import com.sky.assignment.model.AuthAttemptDto;

/**
 * Dummy (in-memory) implementation of the Data Access Object.
 * 
 * @author switalski
 */
@Repository(AUTH_ATTEMPT_DAO)
public class DummyAuthAttemptDAO implements AuthAttemptDAO {
	
	/** List of authentication attempts. */
	private List<AuthAttemptDto> authAttempts = Collections.synchronizedList(Lists.newArrayList());
	
	/**
	 * Adds new auth attempt at the end of the list.
	 */
	@Override
	public void insert(AuthAttemptDto authAttempt) {
		authAttempts.add(authAttempt);
	}
	
	/**
	 * Returns a defensive copy of the authentication attempts list.
	 */
	@Override
	public List<AuthAttemptDto> getAuthAttempts() {
		return Lists.newArrayList(authAttempts);
	}

}
