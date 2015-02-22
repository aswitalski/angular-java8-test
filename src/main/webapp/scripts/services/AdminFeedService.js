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

	.factory('skyAdminFeedService', ['skyErrorHandlingService', '$http', function(errorHandler, $http) {

		return {
			getAuthAttempts : function getAuthAttempts(onSuccess, onError) {

				$http({
					url : '/admin/feed/auth-attempts',
					method : 'GET',
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