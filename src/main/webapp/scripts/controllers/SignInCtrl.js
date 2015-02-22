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

		$scope.invalidCredentials = false;

		var clearSignInError = function clearSignInError(newValue, oldValue) {
			if (!angular.equals(newValue, oldValue)) {
				$scope.signInError = '';
			}
		};

		$scope.$watch('credentials.username', clearSignInError);
		$scope.$watch('credentials.password', clearSignInError);

		$scope.signIn = function signIn() {
			var self = this;
			signInService.signIn({
				username : this.credentials.username,
				password : this.credentials.password
			}, function signInSuccess(data) {
				self.credentials.username = '';
				self.credentials.password = '';
				self.credentials.invalid = false;
				self.auth.role = data.role;
				self.auth.username = data.username;
			}, function signInError(error) {
				if (error.code == 401) {
					self.credentials.invalid = true;
					$timeout(function() {
						self.credentials.invalid = false;
					}, 1000);
				} else {
					self.signInError = 'Error signing in to application (code = ' + error.code + ')';
				}
			});
		};

		window.signInScope = $scope;
	}]);