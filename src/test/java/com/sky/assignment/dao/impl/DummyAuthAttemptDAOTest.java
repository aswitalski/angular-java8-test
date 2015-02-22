package com.sky.assignment.dao.impl;

import static com.sky.assignment.model.AuthResult.AUTH_ERROR;
import static com.sky.assignment.model.AuthResult.AUTH_SUCCESS;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.sky.assignment.model.AuthAttemptDto;

public class DummyAuthAttemptDAOTest {
	
	private DummyAuthAttemptDAO dao;
	
	@Before
	public void setUp() {
		dao = new DummyAuthAttemptDAO();
	}
	
	@Test
	public void shouldStoreAndRetrieveSingleAttempt() {
		
		// given
		AuthAttemptDto authAttempt = new AuthAttemptDto("admin", AUTH_SUCCESS, "127.0.0.1", new Date());
		
		// when
		dao.insert(authAttempt);
		
		// then
		assertEquals(1, dao.getAuthAttempts().size());
		assertEquals(authAttempt, dao.getAuthAttempts().get(0));
	}
	
	@Test
	public void shouldStoreAndRetrieveMultipleAttemptsInOrder() {
		
		// given
		AuthAttemptDto authAttempt1 = new AuthAttemptDto("admin", AUTH_SUCCESS, "127.0.0.1", new Date());
		AuthAttemptDto authAttempt2 = new AuthAttemptDto("manager", AUTH_ERROR, "10.0.0.1", new Date());
		AuthAttemptDto authAttempt3 = new AuthAttemptDto("user", AUTH_SUCCESS, "234.152.235.111", new Date());
		
		// when
		dao.insert(authAttempt1);
		dao.insert(authAttempt2);
		dao.insert(authAttempt3);
		
		// then
		assertEquals(3, dao.getAuthAttempts().size());
		assertEquals(authAttempt1, dao.getAuthAttempts().get(0));
		assertEquals(authAttempt2, dao.getAuthAttempts().get(1));
		assertEquals(authAttempt3, dao.getAuthAttempts().get(2));
	}

}
