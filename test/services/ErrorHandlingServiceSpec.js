'use strict';

describe('Service: Error Handling Service', function () {

  // load the controller's module
  beforeEach(module('skyCore'));

  var service;

  beforeEach(inject(function (_skyErrorHandlingService_) {
    service = _skyErrorHandlingService_;
  }));

  it('should expose "handleHttpError" function', function () {
    expect(service.handleHttpError).toEqual(jasmine.any(Function));
  });

  it('should expose "handleException" function', function () {
    expect(service.handleException).toEqual(jasmine.any(Function));
  });

  describe('Function: Handle HTTP Error', function() {

    it('should call onError handler with valid error objects', function () {

      // given
      var onError = jasmine.createSpy('onError');
      var restOperation = 'Some Important REST Request';

      // when
      service.handleHttpError({ status : 404 }, onError, restOperation);

      //then
      expect(onError).toHaveBeenCalledWith({
        type : 'http',
        code : 404,
        operation : restOperation
      });

      // when
      service.handleHttpError({ status : 500 }, onError);

      //then
      expect(onError).toHaveBeenCalledWith({
        type : 'http',
        code : 500,
        operation : '( unspecified )'
      });
    });

    it('should not throw an exception when no onError handler is specified', function () {
      expect(service.handleHttpError).not.toThrow();
    });

    it('should throw an exception when invalid onError handler is specified', function () {

      // given
      var invalidCall = function invalidCall() {
        service.handleHttpError(null, 'Invalid handler');
      };
      var anotherInvalidCall = function anotherInvalidCall() {
        service.handleHttpError(new Error('error'), 17);
      };

      // then
      expect(invalidCall).toThrow();
      expect(anotherInvalidCall).toThrow();
    });

  });

  describe('Function: Handle Exception', function() {

    it('should call onError handler with valid error objects for "handleException"', function () {

      // given
      var onError = jasmine.createSpy('onError');
      var operation = 'Some Important Operation';

      // when
      service.handleException(new Error('some error'), onError, operation);

      // then
      expect(onError).toHaveBeenCalledWith({
        type : 'execution',
        code : 0,
        operation : operation
      });

      // when
      service.handleException(null, onError);

      // then
      expect(onError).toHaveBeenCalledWith({
        type : 'execution',
        code : 0,
        operation : '( unspecified )'
      });
    });

    it('should not throw an exception when no onError handler is specified for "handleException"', function () {
      expect(service.handleException).not.toThrow();
    });

    it('should throw an exception when invalid onError handler is specified', function () {

      // given
      var invalidCall = function invalidCall() {
        service.handleException(new Error(), NaN);
      };
      var anotherInvalidCall = function anotherInvalidCall() {
        service.handleException('Error', null);
      };

      // then
      expect(invalidCall).toThrow();
      expect(anotherInvalidCall).toThrow();
    });

  });

});
