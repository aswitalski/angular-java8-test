package com.sky.assignment.web.controller;

import static com.sky.assignment.web.controller.AuthController.ATTR_USER_INFO;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.sky.assignment.exception.NotAuthorizedException;
import com.sky.assignment.exception.NotSignedInException;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.model.UserRole;
import com.sky.assignment.service.FeedService;

public class AdminFeedControllerUnitTest {
	
	private AdminFeedController controller;
	
	@Before
	public void setUp() {
		controller = new AdminFeedController();
	}

	@Test
	public void shouldInvokeTheServiceWhenAdminUser() {
		
		// given
		List<AuthAttemptDto> authAttempts = Lists.newArrayList();
		HttpSession sessionMock = mock(HttpSession.class);
		when(sessionMock.getAttribute(ATTR_USER_INFO)).thenReturn(new UserInfoDto("admin", UserRole.ADMIN));
		
		FeedService feedServiceMock = mock(FeedService.class);
		when(feedServiceMock.getAuthAttempts()).thenReturn(authAttempts);
		controller.setFeedService(feedServiceMock);
		
		// when
		List<AuthAttemptDto> result = controller.getAuthAttempts(sessionMock);
		
		// then
		verify(feedServiceMock, only()).getAuthAttempts();
		assertSame(authAttempts, result);
	}
	
	@Test
	public void shouldThrowNotAuthorizedExceptionForStandarUser() {
		
		// given
		List<AuthAttemptDto> authAttempts = Lists.newArrayList();
		HttpSession sessionMock = mock(HttpSession.class);
		when(sessionMock.getAttribute(ATTR_USER_INFO)).thenReturn(new UserInfoDto("user", UserRole.USER));
		
		FeedService feedServiceMock = mock(FeedService.class);
		when(feedServiceMock.getAuthAttempts()).thenReturn(authAttempts);
		controller.setFeedService(feedServiceMock);
		
		// when
		NotAuthorizedException ex = null;
		try {
			controller.getAuthAttempts(sessionMock);
		} catch (NotAuthorizedException e) {
			// not using JUnit's expected attribute to ensure that service is not called
			ex = e;
		}
		
		// then
		verify(feedServiceMock, never()).getAuthAttempts();
		assertNotNull("Not authorized expection should have been thrown", ex);
	}
	
	@Test
	public void shouldThrowNotSignedInException() {
		
		// given
		List<AuthAttemptDto> authAttempts = Lists.newArrayList();
		HttpSession sessionMock = mock(HttpSession.class);
		when(sessionMock.getAttribute(ATTR_USER_INFO)).thenReturn(null);
		
		FeedService feedServiceMock = mock(FeedService.class);
		when(feedServiceMock.getAuthAttempts()).thenReturn(authAttempts);
		controller.setFeedService(feedServiceMock);
		
		// when
		NotSignedInException ex = null;
		try {
			controller.getAuthAttempts(sessionMock);
		} catch (NotSignedInException e) {
			// not using JUnit's expected attribute to ensure that service is not called
			ex = e;
		}
		
		// then
		verify(feedServiceMock, never()).getAuthAttempts();
		assertNotNull("Not authorized expection should have been thrown", ex);
	}

}
