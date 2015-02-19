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

	.controller('MainCtrl', ['$scope', '$window', function ($scope, $window) {
		
		// parent object specified to avoid saving the role to the wrong object because of dynamic scoping
		$scope.auth = {
			role : ''
		};
	}]);