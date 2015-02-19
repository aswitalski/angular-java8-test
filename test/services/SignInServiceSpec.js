'use strict';

describe('Service: Error Handling Service', function () {

  var service,
      errorHandler,
      $httpBackend;

  beforeEach(function() {

    module('skyCore');
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

  beforeEach(inject(function ($injector, _skySignInService_, _skyErrorHandlingService_) {
    service = _skySignInService_;
    errorHandler = _skyErrorHandlingService_;
    $httpBackend = $injector.get('$httpBackend');
  }));

  afterEach(function() {
    $httpBackend.verifyNoOutstandingExpectation();
    $httpBackend.verifyNoOutstandingRequest();
  });

  it('should expose "isSignedIn" function', function () {
    expect(service.isSignedIn).toEqual(jasmine.any(Function));
  });

  it('should expose "signIn" function', function () {
    expect(service.signIn).toEqual(jasmine.any(Function));
  });

  it('should expose "signOut" function', function () {
    expect(service.signOut).toEqual(jasmine.any(Function));
  });

  describe('Function: Is Signed In', function() {

    it('should call the remote service and after a successful response invoke onSuccess handler with response data', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('POST', '/auth/check').respond(200, '{ "role" : "user" }');

      service.isSignedIn(onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).toHaveBeenCalledWith({ role : 'user' });
      expect(errorHandler.handleHttpError).not.toHaveBeenCalled();
    });

    it('should call the remote service and after 403 error invoke handleHttpError passing onError handler', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('POST', '/auth/check').respond(403, '');

      service.isSignedIn(onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).not.toHaveBeenCalled();
      expect(errorHandler.handleHttpError).toHaveBeenCalledWith(jasmine.objectContaining({ data: '', status: 403 }), onError, 'Sign-in check');
    });
  });

  describe('Function: Sign In', function() {

    it('should call the remote service and after a successful response invoke onSuccess handler with response data', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('POST', '/auth/sign-in').respond(200, '{ "role" : "admin" }');

      service.signIn({
        user : 'user',
        pass : 'pass'
      }, onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).toHaveBeenCalledWith({ role : 'admin' });
      expect(errorHandler.handleHttpError).not.toHaveBeenCalled();
    });

    it('should call the remote service and after 405 error invoke handleHttpError passing onError handler', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('POST', '/auth/sign-in').respond(405, '');

      service.signIn({
        user : 'user',
        pass : ''
      }, onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).not.toHaveBeenCalled();
      expect(errorHandler.handleHttpError).toHaveBeenCalledWith(jasmine.objectContaining({ data: '', status: 405  }), onError, 'Sign in');
    });

    it('should call the remote service and after 500 error invoke handleHttpError passing onError handler', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('POST', '/auth/sign-in').respond(500, '{}');

      service.signIn({}, onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).not.toHaveBeenCalled();
      expect(errorHandler.handleHttpError).toHaveBeenCalledWith(jasmine.objectContaining({ data: {}, status: 500 }), onError, 'Sign in');
    });
  });

  describe('Function: Sign Out', function() {

    it('should call the remote service and after a successful response invoke onSuccess handler with response data', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('POST', '/auth/sign-out').respond(200, '{ "role" : "admin" }');

      service.signOut(onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).toHaveBeenCalledWith({ role : 'admin' });
      expect(errorHandler.handleHttpError).not.toHaveBeenCalled();
    });

    it('should call the remote service and after 404 error invoke handleHttpError passing onError handler', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      $httpBackend.when('POST', '/auth/sign-out').respond(404, '');

      service.signOut(onSuccess, onError);

      $httpBackend.flush();
      expect(onSuccess).not.toHaveBeenCalled();
      expect(errorHandler.handleHttpError).toHaveBeenCalledWith(jasmine.objectContaining({ data: '', status: 404  }), onError, 'Sign out');
    });

  });

});