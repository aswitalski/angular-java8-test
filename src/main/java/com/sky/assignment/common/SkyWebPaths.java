package com.sky.assignment.common;

/**
 * All paths utilized in the controllers.
 * 
 * @author switalski
 */
public interface SkyWebPaths {
	
	/** Paths for authentication controller. */
	interface Auth {
		
		/** Root path for authentication controller */
		String ROOT = "auth";
		
		/** Sign-in path. */
		String SIGN_IN = "sign-in";
		
		/** Sign-out path. */
		String SIGN_OUT = "sign-out";
		
		/** Sign-in check path. */
		String CHECK = "check";
	}

	/** Paths for admin feed controller. */
	interface Feed {
		
		/** Root path for feed controller. */
		String ROOT = "admin/feed";
		
		/** Auth attempts path. */
		String AUTH_ATTEMPTS = "auth-attempts";
	}
}
