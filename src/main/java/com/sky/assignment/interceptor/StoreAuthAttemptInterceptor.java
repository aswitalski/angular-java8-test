package com.sky.assignment.interceptor;

import static com.sky.assignment.common.SkyComponents.Interceptors.AUTH_ATTEMPT_INTERCEPTOR;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component(AUTH_ATTEMPT_INTERCEPTOR)
public class StoreAuthAttemptInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// Date timestamp = new Date();
		// UserInfoDto userInfo = authService.authenticate(username, password);
		// if (userInfo != null) {
		// // username taken from the user info object, because the normalized value
		// // can differ from the provided one (e.g. when auth service mechanism is case insensitive)
		// AuthAttemptDto authAttempt = new AuthAttemptDto(userInfo.getUsername(), AUTH_SUCCESS, ip, timestamp);
		// persistenceService.storeAuthAttempt(authAttempt);
		// return userInfo;
		// } else {
	}
}
