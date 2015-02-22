package com.sky.assignment.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.sky.assignment.dao.AuthAttemptDAO;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.model.AuthResult;

public class DefaultFeedServiceUnitTest {
	
	private DefaultFeedService feedService;
	
	@Before
	public void setUp() {
		feedService = new DefaultFeedService();
	}
	
	@Test
	public void shouldDelegateTheCallToDAO() {
		
		// given
		AuthAttemptDAO daoMock = mock(AuthAttemptDAO.class);
		ArrayList<AuthAttemptDto> result = Lists.newArrayList();
		result.add(new AuthAttemptDto("user", AuthResult.AUTH_SUCCESS, "127.0.0.1", new Date()));
		when(daoMock.getAuthAttempts()).thenReturn(result);
		feedService.setDao(daoMock);
		
		// when
		List<AuthAttemptDto> authAttempts = feedService.getAuthAttempts();
		
		// then
		verify(daoMock, only()).getAuthAttempts();
		assertEquals(result, authAttempts);

	}
	
}
