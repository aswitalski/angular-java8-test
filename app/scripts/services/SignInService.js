'use strict';

/**
 * @ngdoc function
 * @name skyCore.service:skySignInService
 * @description
 * Service allowing to perform sign-in and sign-out operations and also to check the current sign-in status
 *
 * # skySignInService
 * 		Sign-In Service of the skyCore
 */
angular.module('skyCore')

	.factory('skySignInService', ['skyEnvConfig', /*'skyErrorHandlingService',*/ '$http', function(env, /*errorHandler,*/ $http) {

		// environment-specific domain and root URL not to hardcode it in the service
		var root = env.remoteServiceBaseUrl();

		return {
			isSignedIn : function isSignedIn(onSuccess, onError) {
				
			},
			signIn : function signIn(credentials, onSuccess, onError) {
				$http({
					url : root + '/auth/signIn',
					method : 'POST',
					data : credentials
				})
				.then(function signInSuccess(response) {
					// TODO: handle
				})
				.catch(function signInError(data, status, headers, config) {
					//errorHandler.handleHttpError(data, onError, 'Sign-in');
				});
			},
			signOut : function signOut(onSuccess, onError) {

			}
		};
	}]);