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

	.factory('skyAdminFeedService', ['envConfig', '$http', function(env, $http) {

		// environment-specific domain and root URL not to hardcode it in the service
		var root = env.remoteServiceBaseUrl();

		return {
			getAuthAttempts : function getAuthAttempts(onSuccess, onError) {

				$http({
					// the path could actually be shorter, but just to demonstrate how to potentially handle
					// different incompatible REST service versions and make it self-descriptive
					// also - under "admin/**" which should have a different authorization rule on the server side
					// (users with "admin" role only)
					url : root + '/rest/v1/admin/feeds/auth-attempts.json',
					method : 'GET',
					// params not defined, at the moment all authentication attempts fetched, filtering done on the frontend side
					data : { }
				})
				.then(function getAuthAttemptsSuccess(response) {
					// TODO: handle
				})
				.catch(function getAuthAttemptsError(data, status, headers, config) {
					// TODO: handle
				});
			}
		};
	}]);