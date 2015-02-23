'use strict';

/**
 * @ngdoc function
 * @name skyApp.controller:MainCtrl
 * @description
 *
 * # MainCtrl
 * Main Controller of the skyApp
 */
angular.module('skyApp')

	.controller('MainCtrl', ['$scope', '$window', 'skySignInService', function ($scope, $window, signInService) {

		$scope.loading = true;

		// parent object specified to avoid saving the role to the wrong object because of dynamic scoping
		$scope.auth = {};

		// function accepting normalized (domain-specific) error object and allowing to handle it in unified way
		$scope.displayError = function displayError(error) {
			$scope.error = error;
		};

		$scope.clearAuth = function clearAuth() {
			$scope.auth.role = '';
			$scope.auth.username ='';
		};

		$scope.signOut = function signOut() {
			signInService.signOut($scope.clearAuth, $scope.displayError);
		};

	}]);