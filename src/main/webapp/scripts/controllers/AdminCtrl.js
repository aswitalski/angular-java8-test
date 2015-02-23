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

		$scope.assignAttempts = function assignAttempts(attempts) {
			$scope.attempts = attempts;
		};

		$scope.fetchLog = function fetchLog() {
			feedService.getAuthAttempts($scope.assignAttempts, $scope.displayError);
		};
	}]);