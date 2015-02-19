'use strict';

/**
 * @ngdoc function
 * @name skyAdmin.service:skyAdminFeedService
 * @description
 * Service returning feeds for administators backed by the REST service
 *
 * # skyAdminFeedService
 * 		Admin Feed Service of the skyAdmin module
 */
angular.module('skyAdmin')

	// pretty much the duplication of logic in sign-in service (in real-life conditions extracted to a separate one)

	.factory('skyAdminFeedService', ['skyEnvConfig', 'skyErrorHandlingService', '$http', function(env, errorHandler, $http) {

		// environment-specific domain and root URL not to hardcode it in the service
		var root = env.remoteServiceBaseUrl();

		return {
			getAuthAttempts : function getAuthAttempts(onSuccess, onError) {

				$http({
					// the path could actually be shorter, but just to demonstrate how to make it self-descriptive
					// also - under "admin/**" which should have a different authorization rule on the server side
					// (users with "admin" role only)
					url : root + '/admin/feeds/auth-attempts.json',
					method : 'GET',
					// params not defined, all authentication attempts fetched, filtering done on the frontend side
					data : { }
				})
				.then(function getAuthAttemptsSuccess(response) {
					onSuccess(response.data);
				})
				.catch(function getAuthAttemptsError(data, status, headers, config) {
					errorHandler.handleHttpError(data, onError, 'Get authentication attempts feed');
				});
			}
		};
	}]);