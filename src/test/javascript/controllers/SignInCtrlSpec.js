'use strict';

describe('Sign In Controller', function() {
  
  beforeEach(module('skyApp'));

  var SignInCtrl,
      $scope,
      signInService;

  beforeEach(inject(function ($controller, $rootScope) {
  	$rootScope.displayError = jasmine.createSpy('displayError');
    $scope = $rootScope.$new();
    signInService = {};
    SignInCtrl = $controller('SignInCtrl', {
      $scope: $scope,
      skySignInService : signInService
    });
  }));

  it('should be set $scope.credentials to an empty object', function() {
    expect($scope.credentials).toEqual({});
  });

  describe('$scope.onSignIn', function() {
    it('should clear credentials and set new auth object', function() {
      $scope.credentials = {
        username : 'user',
        password : 'pass'
      };
      $scope.auth = {
        username : 'user',
        role : 'USER'
      };
      $scope.onSignIn({ username : 'admin', role : 'ADMIN' });
      expect($scope.credentials.username).toEqual('');
      expect($scope.credentials.password).toEqual('');
      expect($scope.credentials.invalid).toEqual(false);
      expect($scope.auth.username).toEqual('admin');
      expect($scope.auth.role).toEqual('ADMIN');
    });
  });

  describe('$scope.markCredentialsAsInvalid', function() {
    it('should set the invalid flag on credentials to true', function() {
      $scope.credentials.invalid = false;
      $scope.markCredentialsAsInvalid();
      expect($scope.credentials.invalid).toBe(true);
    });
  });

  describe('$scope.markCredentialsAsValid', function() {
    it('should set the invalid flag on credentials to false', function() {
      $scope.credentials.invalid = true;
      $scope.markCredentialsAsValid();
      expect($scope.credentials.invalid).toBe(false);
    });
  });

  describe('$scope.customErrorHandler', function() {
  
    it('should mark the credentials as invalid for error code 401', function() {
      $scope.credentials.invalid = false;
      spyOn($scope, 'markCredentialsAsInvalid');
      $scope.customErrorHandler({ code : 401 });
      expect($scope.markCredentialsAsInvalid).toHaveBeenCalled();
    });

    it('should call the default displayError handler for error code different from 401', function() {
      var error = { code : 404 };
      $scope.customErrorHandler(error);
      expect($scope.displayError).toHaveBeenCalledWith(error);
    });

  });

  describe('$scope.signIn', function() {
    it('should call the signIn function of the sign-in service ', function() {
      var signInSpy = jasmine.createSpy('signIn');
      signInService.signIn = signInSpy;
      $scope.credentials.username = 'user';
      $scope.credentials.password = 'pass';
      $scope.signIn();
      expect(signInService.signIn).toHaveBeenCalledWith({
          username : 'user',
          password : 'pass'
      }, $scope.onSignIn, $scope.customErrorHandler);
    });
  });

});