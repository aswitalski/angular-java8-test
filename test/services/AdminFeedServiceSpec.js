'use strict';

describe('Service: Admin Feed Service', function () {

  var service,
      errorHandler,
      $httpBackend;

  beforeEach(function() {

    module('skyAdmin');
    module(function($provide) {
      $provide.service('skyEnvConfig', function() {
        this.remoteServiceBaseUrl = function() {
          return '';
        };
      })
      $provide.value('skyErrorHandlingService', {
        handleHttpError : jasmine.createSpy('handleHttpError'),
        handleException : jasmine.createSpy('handleException')
      });
    });
  });

  beforeEach(inject(function ($injector, _skyAdminFeedService_, _skyErrorHandlingService_) {
    service = _skyAdminFeedService_;
    errorHandler = _skyErrorHandlingService_;
    $httpBackend = $injector.get('$httpBackend');
  }));

  afterEach(function() {
    $httpBackend.verifyNoOutstandingExpectation();
    $httpBackend.verifyNoOutstandingRequest();
  });

  it('should expose "getAuthAttempts" function', function () {
    expect(service.getAuthAttempts).toEqual(jasmine.any(Function));
  });

  describe('Function: Get Authentication Attempts', function() {

    it('should call the remote service and after a successful response invoke onSuccess handler with response data', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('GET', '/admin/feeds/auth-attempts.json').respond(200, '[]');

      service.getAuthAttempts(onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).toHaveBeenCalledWith([]);
      expect(errorHandler.handleHttpError).not.toHaveBeenCalled();
    });

    it('should call the remote service and after 403 error invoke handleHttpError passing onError handler', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('GET', '/admin/feeds/auth-attempts.json').respond(403, '');

      service.getAuthAttempts(onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).not.toHaveBeenCalled();
      expect(errorHandler.handleHttpError).toHaveBeenCalledWith(jasmine.objectContaining({ data: '', status: 403 }), onError, 'Get authentication attempts feed');
    });
  });

});