package com.sky.assignment.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.sky.assignment.exception.MandatoryResourceMissingException;
import com.sky.assignment.model.UserInfoDto;
import com.sky.assignment.model.UserRole;

public class DefaultAuthServiceUnitTest {
	
	private static final String USERS_FILE_PATH = "/data/users.json";

	private DefaultAuthService authService;
	
	@Before
	public void setUp() {
		authService = new DefaultAuthService();
	}
	
	@Test(expected = MandatoryResourceMissingException.class)
	public void shouldThrowMandatoryResourceMissingException() throws Exception {
		authService.loadUserData("/not/a/valid/path");
	}
	
	@Test
	public void shouldLoadUsersFromGivenFile() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		
		// then
		assertEquals(5, authService.getUsers().size());
	}
	
	@Test
	public void shouldAuthenticateUser() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("user", "password");
		
		// then
		assertEquals("user", user.getUsername());
		assertEquals(UserRole.USER, user.getRole());
	}
	
	@Test
	public void shouldAuthenticateDeveloper() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("developer", "password");
		
		// then
		assertEquals("developer", user.getUsername());
		assertEquals(UserRole.USER, user.getRole());
	}
	
	@Test
	public void shouldAuthenticateTester() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("tester", "password");
		
		// then
		assertEquals("tester", user.getUsername());
		assertEquals(UserRole.USER, user.getRole());
	}
	
	@Test
	public void shouldAuthenticateManager() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("manager", "password");
		
		// then
		assertEquals("manager", user.getUsername());
		assertEquals(UserRole.USER, user.getRole());
	}
	
	@Test
	public void shouldAuthenticateAdministrator() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("admin", "password");
		
		// then
		assertEquals("admin", user.getUsername());
		assertEquals(UserRole.ADMIN, user.getRole());
	}

	@Test
	public void shouldAuthenticateUserWithAllCapitalLettersInUsername() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("USER", "password");
		
		// then
		assertEquals("user", user.getUsername());
		assertEquals(UserRole.USER, user.getRole());
	}

	@Test
	public void shouldAuthenticateUserWithSomeCapitalLettersInUsername() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("UsEr", "password");
		
		// then
		assertEquals("user", user.getUsername());
		assertEquals(UserRole.USER, user.getRole());
	}
	
	@Test
	public void shouldNotAuthenticateUserWhenIncorrectCaseInPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("user", "PASSWORD");
		
		// then
		assertNull(user);
	}
	
	@Test
	public void shouldNotAuthenticateTesterWithIncorrectCaseInPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("tester", "Password");
		
		// then
		assertNull(user);
	}
	
	@Test
	public void shouldNotAuthenticateDeveloperWithIncorrectCaseInPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("developer", "paSSword");
		
		// then
		assertNull(user);
	}
	
	@Test
	public void shouldNotAuthenticateManagerWithIncorrectCaseInPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("manager", "PassworD");
		
		// then
		assertNull(user);
	}

	@Test
	public void shouldNotAuthenticateAdminWithIncorrectCaseInPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("admin", "PassWord");
		
		// then
		assertNull(user);
	}

	@Test
	public void shouldNotAuthenticateUserWithIncorrectPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("user", "user");
		
		// then
		assertNull(user);
	}

	@Test
	public void shouldNotAuthenticateManagerWithIncorrectPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("manager", "something");
		
		// then
		assertNull(user);
	}
	
	@Test
	public void shouldNotAuthenticateDeveloperWithIncorrectPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("developer", "gimmeaccess");
		
		// then
		assertNull(user);
	}
	
	@Test
	public void shouldNotAuthenticateAdministratorWithIncorrectPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("admin", "admin");
		
		// then
		assertNull(user);
	}
	
	@Test
	public void shouldNotAuthenticateTesterWithIncorrectPassword() throws Exception {
		
		// when
		authService.loadUserData(USERS_FILE_PATH);
		UserInfoDto user = authService.authenticate("tester", "qwerty");
		
		// then
		assertNull(user);
	}

}
