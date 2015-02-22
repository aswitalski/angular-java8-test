'use strict';

/**
 * @ngdoc function
 * @name skyApp.controller:AdminCtrl
 * @description
 *
 * # AdminCtrl
 * Admin Controller of the skyApp
 */
angular.module('skyApp')

	.controller('AdminCtrl', ['$scope', '$window', 'skyAdminFeedService', function ($scope, $window, feedService) {

		$scope.fetchLog = function fetchLog() {
			var self = this;
			feedService.getAuthAttempts(function onSuccess(data) {
				self.attempts = data;
			}, function onError(error) {
				self.displayError(error);
			});
		};


	}]);