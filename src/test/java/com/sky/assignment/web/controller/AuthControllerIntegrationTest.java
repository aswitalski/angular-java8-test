package com.sky.assignment.web.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sky.assignment.dao.AuthAttemptDAO;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.model.UserRole;
import com.sky.assignment.service.AuthService;

public class AuthControllerIntegrationTest {
	
	private AuthController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		controller = new AuthController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void shouldReturnUnauthorizedErrorCodeForInvalidCredentials() throws Exception {
		
		AuthService authServiceMock = mock(AuthService.class);
		controller.setAuthService(authServiceMock);
		
		AuthAttemptDAO daoMock = mock(AuthAttemptDAO.class);
		controller.setAuthAttemptsDAO(daoMock);

		// when
		ResultActions result = mockMvc.perform(post("/auth/sign-in").contentType(MediaType.APPLICATION_JSON).content("{}"));
		
		// then
		result.andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
	}
	
	@Test
	public void shouldReturnOKCodeForValidCredentials() throws Exception {
		
		AuthService authServiceMock = mock(AuthService.class);
		when(authServiceMock.authenticate(anyString(), anyString())).thenReturn(new UserInfoDto("user", UserRole.USER));
		controller.setAuthService(authServiceMock);
		
		AuthAttemptDAO daoMock = mock(AuthAttemptDAO.class);
		controller.setAuthAttemptsDAO(daoMock);
		
		// when
		ResultActions result = mockMvc.perform(post("/auth/sign-in").contentType(MediaType.APPLICATION_JSON).content("{}"));
		
		// then
		result.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}
	
	@Test
	public void shouldReturnOKCodeForSignOut() throws Exception {
		
		// when
		ResultActions result = mockMvc.perform(post("/auth/sign-out").contentType(MediaType.APPLICATION_JSON).content("{}"));
		
		// then
		result.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

	@Test
	public void shouldReturnOKCodeForSignInCheck() throws Exception {
		
		// when
		ResultActions result = mockMvc.perform(post("/auth/check").contentType(MediaType.APPLICATION_JSON).content("{}"));
		
		// then
		result.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

}
