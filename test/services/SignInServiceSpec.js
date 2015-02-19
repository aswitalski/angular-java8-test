'use strict';

describe('Service: Error Handling Service', function () {

  // load the controller's module
  beforeEach(function() {
    module('skyCore');
    module(function($provide) {
      $provide
      .service('skyEnvConfig', function() {
        this.remoteServiceBaseUrl = function() {
          return '';
        };
      })
      /*.factory('skyErrorHandlingService', function() {
        return {
        };
      });*/
    });
  });

  var service;

  beforeEach(inject(function (_skySignInService_) {
    service = _skySignInService_;
  }));

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

    it('should call the REST service', function() {

      // given
      var onSuccess = jasmine.createSpy('onSuccess');
      var onError = jasmine.createSpy('onError');

      service.signIn({
        user : 'user',
        pass : 'pass'
      }, onSuccess, onError);
      // when
    });

  });

  describe('Function: Sign In', function() {

  });

  describe('Function: Sign Out', function() {

  });

});