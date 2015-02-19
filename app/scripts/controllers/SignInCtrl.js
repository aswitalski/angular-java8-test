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

	.controller('SignInCtrl', ['$scope', 'skySignInService', function ($scope, signInService) {

		$scope.credentials = {};

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
				self.auth.role = data.role;
			}, function signInError(error) {
				self.signInError = 'Error signing in to application (code = ' + error.code + ')';
			});
		};
	}]);