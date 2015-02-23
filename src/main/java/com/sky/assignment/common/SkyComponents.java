package com.sky.assignment.common;

/**
 * Defines names of all own utilized beans - just for clarity.
 * 
 * @author switalski
 **/
public interface SkyComponents {
	
	/** Defines names of all utilized services. */
	interface Services {
		String AUTH_SERVICE = "authService";
		String FEED_SERVICE = "feedService";
	}
	
	/** Defines names of all utilized controllers. */
	interface Controllers {
		String AUTH_CONTROLLER = "authController";
		String FEED_CONTROLLER = "feedController";
	}
	
	/** Defines names of all utilized DAOs. */
	interface DAOs {
		String AUTH_ATTEMPT_DAO = "authAttemptDAO";
	}
	
}
