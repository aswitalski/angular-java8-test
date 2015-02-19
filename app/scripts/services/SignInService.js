'use strict';

/**
 * @ngdoc function
 * @name skyCore.service:skySignInService
 * @description
 * Service allowing to perform sign-in and sign-out operations and also to check the current sign-in status.
 * Adapted to 
 *
 * # skySignInService
 * 		Sign-In Service of the skyCore
 */
angular.module('skyCore')

	.factory('skySignInService', ['skyEnvConfig', 'skyErrorHandlingService', '$http', function(env, errorHandler, $http) {

		// environment-specific domain and root URL not to hardcode it in the service
		var _root = env.remoteServiceBaseUrl();

		var _handlePostRequest = function handleHttpRequest(path, payload, onSuccess, onError, operation) {
			$http({
				url : _root + path,
				method : 'POST',
				data : payload
			})
			.then(function restOnSuccess(response) {
				onSuccess(response.data);
			})
			.catch(function restOnError(data, status, headers, config) {
				errorHandler.handleHttpError(data, onError, operation);
			});
		};

		// This implementation is tailored for the created Java backend (awareness of session on the backend).
		// An alternative could be based on tokens

		return {
			isSignedIn : function isSignedIn(onSuccess, onError) {
				_handlePostRequest('/auth/is-signed-in', '', onSuccess, onError, 'Sign-in check');
			},
			signIn : function signIn(credentials, onSuccess, onError) {
				_handlePostRequest('/auth/sign-in', credentials, onSuccess, onError, 'Sign in');
			},
			signOut : function signOut(onSuccess, onError) {
				_handlePostRequest('/auth/sign-out', '', onSuccess, onError, 'Sign out');
			}
		};
	}]);