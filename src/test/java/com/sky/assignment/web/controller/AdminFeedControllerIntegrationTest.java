package com.sky.assignment.web.controller;

import static com.sky.assignment.web.controller.AuthController.ATTR_USER_INFO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.common.collect.Lists;
import com.sky.assignment.model.AuthAttemptDto;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.model.UserRole;
import com.sky.assignment.service.FeedService;

public class AdminFeedControllerIntegrationTest {
	
	private AdminFeedController controller;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		controller = new AdminFeedController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void shouldReturnForbiddenErrorCodeWhenNonSignedUserFetchesAuthAttempts() throws Exception {

		// when
		ResultActions result = mockMvc.perform(get("/admin/feed/auth-attempts"));
		
		// then
		result.andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));
	}
	
	@Test
	public void shouldReturnUnauthorizedErrorCodeWhenStandardUserFetchesAuthAttempts() throws Exception {
		
		// given
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(ATTR_USER_INFO, new UserInfoDto("user", UserRole.USER));
		
		// when
		ResultActions result = mockMvc.perform(get("/admin/feed/auth-attempts").session(session));
		
		// then
		result.andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
	}
	
	@Test
	public void shouldReturnAuthAttemptsForAuthorizedUser() throws Exception {
		
		// given
		List<AuthAttemptDto> authAttempts = Lists.newArrayList();
		FeedService feedServiceMock = mock(FeedService.class);
		when(feedServiceMock.getAuthAttempts()).thenReturn(authAttempts);
		controller.setFeedService(feedServiceMock);

		MockHttpSession session = new MockHttpSession();
		session.setAttribute(ATTR_USER_INFO, new UserInfoDto("admin", UserRole.ADMIN));
		
		// when
		ResultActions result = mockMvc.perform(get("/admin/feed/auth-attempts").session(session));
		
		// then
		result.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
		assertEquals("[]", result.andReturn().getResponse().getContentAsString());
	}

}
