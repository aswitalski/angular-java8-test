'use strict';

/**
 * @ngdoc function
 * @name skyCore.service:skyErrorHandlingService
 * @description
 *
 * NOTE: Not absolutely necessary for the given task, but provided for the sake of the example -
 * how to integrate components with one another (also how to mock and test them in isolation).
 *
 * Error handling service also providing the translation of various errors to domain-specific error objects, allowing to handle them in a predictable manner
 * (by having all the necessary information about the appearance).
 * On-error handlers passed from arbitrary places from the application (services, controllers, directives)
 * being functions expecting domain-specific error as a sole parameter.
 *
 * # skyErrorHandlingService
 * 		Error Handling Service of the skyCore
 */
angular.module('skyCore')

	.factory('skyErrorHandlingService', [function() {

		// I'm not a huge fan of the underscore prefix notation, but used for clarity

		var _createErrorObject = function createErrorObject(type, code, operation) {
			return {
				type : type, // one of ['http', 'execution']
				code : code || 0, // a dictionary to be created in documentation, HTTP status could be passed as-is, application errors e.g. in <0, 99>
				operation : operation || '( unspecified )', // optional operation name to simplify code inspection/debugging
				// possibly some other useful information
			};
		};

		var _convertHttpError = function convertHttpError(error, operation) {
			return _createErrorObject('http', error && error.status || 500, operation);
		};

		var _convertException = function convertException(ex, operation) {
			return _createErrorObject('execution', 0, operation);
		};

		var _handle = function handle(error, converter, onErrorHandler, operation) {
			if (angular.isFunction(onErrorHandler)) {
				onErrorHandler(converter(error, operation));
			} else if (onErrorHandler !== undefined) {
				throw new Error('Invalid error handler function specified: ' + onErrorHandler);
			}
		}

		// all above kept private but tested indirectly for all cases, when the public functions are fully unit-tested (for all possible scenarios)

		return {
			handleHttpError : function handleHttpError(error, onErrorHandler, /* optional */ operation) {
				_handle(error, _convertHttpError, onErrorHandler, operation);
			},
			handleException : function handleException(exception, onErrorHandler, /* optional */ operation) {
				_handle(exception, _convertException, onErrorHandler, operation);
			}
		};
	}]);