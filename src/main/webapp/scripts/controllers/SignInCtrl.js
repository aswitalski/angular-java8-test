'use strict';

/**
 * @ngdoc function
 * @name skyApp.controller:SignInCtrl
 * @description
 *
 * # SignInCtrl
 * Sign In Controller of the skyApp
 */
angular.module('skyApp')

	.controller('SignInCtrl', ['$scope', '$timeout', 'skySignInService', function ($scope, $timeout, signInService) {

		$scope.credentials = {};

		$scope.onSignIn = function onSignIn(data) {
			$scope.credentials.username = '';
			$scope.credentials.password = '';
			$scope.credentials.invalid = false;
			$scope.auth.role = data.role;
			$scope.auth.username = data.username;
		};

		$scope.markCredentialsAsInvalid = function markCredentialsAsInvalid() {
			$scope.credentials.invalid = true;
		};

		$scope.markCredentialsAsValid = function markCredentialsAsValid() {
			$scope.credentials.invalid = false;
		};

		$scope.customErrorHandler = function customErrorHandler(error) {
			if (error.code == 401) {
				// forces "shake" animation
				$scope.markCredentialsAsInvalid();
				$timeout($scope.markCredentialsAsValid, 1000);
			} else {
				$scope.displayError(error);
			}
		}

		$scope.signIn = function signIn() {
			signInService.signIn({
				username : $scope.credentials.username,
				password : $scope.credentials.password
			}, $scope.onSignIn, $scope.customErrorHandler);
		};
	}]);