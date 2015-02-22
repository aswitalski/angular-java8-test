package com.sky.assignment.web.controller;

import static com.sky.assignment.web.controller.AuthController.ATTR_USER_INFO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.sky.assignment.dao.AuthAttemptDAO;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.model.AuthResult;
import com.sky.assignment.model.CredentialsDto;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.model.UserRole;
import com.sky.assignment.service.AuthService;


public class AuthControllerUnitTest {
	
	private AuthController controller;
	
	@Before
	public void setUp() {
		controller = new AuthController();
	}
	
	@Test
	public void shouldSetAttributeInSessionForSuccessfullSignInAttempt() {
		
		// given
		HttpSession sessionMock = mock(HttpSession.class);
		ServletRequest requestMock = mock(ServletRequest.class);
		when(requestMock.getRemoteAddr()).thenReturn("127.0.0.1");
		
		AuthAttemptDAO daoMock = mock(AuthAttemptDAO.class);
		List<AuthAttemptDto> params = Lists.newArrayList();
		doAnswer(invocation -> {
			return params.add((AuthAttemptDto) invocation.getArguments()[0]);
		}).when(daoMock).insert(anyObject());

		AuthService authServiceMock = mock(AuthService.class);
		UserInfoDto userInfo = new UserInfoDto("admin", UserRole.ADMIN);
		when(authServiceMock.authenticate(anyString(), anyString())).thenReturn(userInfo);
		controller.setAuthService(authServiceMock);
		controller.setAuthAttemptsDAO(daoMock);
		
		// when
		controller.signIn(new CredentialsDto("admin", "admin"), sessionMock, requestMock);
		
		// then
		verify(sessionMock, only()).setAttribute(ATTR_USER_INFO, userInfo);
		verify(authServiceMock, only()).authenticate("admin", "admin");
		verify(daoMock).insert(anyObject());

		assertEquals(1, params.size());
		assertEquals("admin", params.get(0).getUsername());
		assertEquals("127.0.0.1", params.get(0).getIp());
		assertEquals(AuthResult.AUTH_SUCCESS, params.get(0).getResult());
	}

	@Test
	public void shouldNotSetAttributeInSessionForFailedSignInAttempt() {
		
		// given
		HttpSession sessionMock = mock(HttpSession.class);
		ServletRequest requestMock = mock(ServletRequest.class);
		when(requestMock.getRemoteAddr()).thenReturn("127.0.0.1");

		AuthAttemptDAO daoMock = mock(AuthAttemptDAO.class);
		List<AuthAttemptDto> params = Lists.newArrayList();
		doAnswer(invocation -> {
			return params.add((AuthAttemptDto) invocation.getArguments()[0]);
		}).when(daoMock).insert(anyObject());

		AuthService authServiceMock = mock(AuthService.class);
		when(authServiceMock.authenticate(anyString(), anyString())).thenReturn(null);
		controller.setAuthService(authServiceMock);
		controller.setAuthAttemptsDAO(daoMock);
		
		// when
		controller.signIn(new CredentialsDto("admin", "admin"), sessionMock, requestMock);
		
		// then
		verify(sessionMock, never()).setAttribute(eq(ATTR_USER_INFO), any());
		verify(authServiceMock, only()).authenticate("admin", "admin");
		
		assertEquals(1, params.size());
		assertEquals("admin", params.get(0).getUsername());
		assertEquals("127.0.0.1", params.get(0).getIp());
		assertEquals(AuthResult.AUTH_ERROR, params.get(0).getResult());
	}
	
	@Test
	public void shouldInvalidateTheSessionForSignOut() {
		
		// given
		HttpSession sessionMock = mock(HttpSession.class);
		
		// when
		controller.signOut(sessionMock);
		
		// then
		verify(sessionMock, times(1)).invalidate();
	}
	
	@Test
	public void shouldReturnSignedInUserInfo() {
		
		// given
		UserInfoDto userInfo = new UserInfoDto("user", UserRole.ADMIN);
		HttpSession sessionMock = mock(HttpSession.class);
		when(sessionMock.getAttribute(ATTR_USER_INFO)).thenReturn(userInfo);
		
		// when
		UserInfoDto result = controller.isSignedIn(sessionMock);
		
		// then
		verify(sessionMock, times(1)).getAttribute(ATTR_USER_INFO);
		assertSame(userInfo, result);
	}
	
	@Test
	public void shouldReturnNoSignedInUserInfo() {
		
		// given
		HttpSession sessionMock = mock(HttpSession.class);
		when(sessionMock.getAttribute(ATTR_USER_INFO)).thenReturn(null);
		
		// when
		UserInfoDto result = controller.isSignedIn(sessionMock);
		
		// then
		verify(sessionMock, times(1)).getAttribute(ATTR_USER_INFO);
		assertNull(result);
	}

}
