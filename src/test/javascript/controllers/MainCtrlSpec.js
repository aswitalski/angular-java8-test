'use strict';

describe('Main Controller', function() {
  
  beforeEach(module('skyApp'));

  var MainCtrl,
      $scope,
      signInService;

  beforeEach(inject(function ($controller, $rootScope) {
    $scope = $rootScope.$new();
    signInService = {};
    MainCtrl = $controller('MainCtrl', {
      $scope: $scope,
      skySignInService : signInService
    });
  }));

  it('should set the $scope.auth to an empty object', function() {
    expect($scope.auth).toEqual({});
  });

  describe('$scope.displayError', function() {
    it('should assign given error to the scope', function() {
      var error = { code : 404, operation : 'Some operation'};
      $scope.displayError(error)
      expect($scope.error).toEqual(error);
    });
  });

  describe('$scope.clearAuth', function() {
    it('should clear the information in auth object', function() {
      $scope.auth.username = 'admin';
      $scope.auth.role = 'ADMIN';
      $scope.clearAuth();
      expect($scope.auth.username).toEqual('');
      expect($scope.auth.role).toEqual('');
    });
  });

  describe('$scope.signOut', function() {
    it('should call the signOut function of the sign-in service ', function() {
      var signOutSpy = jasmine.createSpy('signOut');
      signInService.signOut = signOutSpy;
      $scope.signOut();
      expect(signInService.signOut).toHaveBeenCalledWith($scope.clearAuth, $scope.displayError);
    });
  });

});