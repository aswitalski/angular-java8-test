describe('Admin Controller', function() {
  
  beforeEach(module('skyApp'));

  var AdminCtrl,
      $scope,
      feedService;

  beforeEach(inject(function ($controller, $rootScope) {
  	$rootScope.displayError = jasmine.createSpy('displayError');
    $scope = $rootScope.$new();
    feedService = {};
    MainCtrl = $controller('AdminCtrl', {
      $scope: $scope,
      skyAdminFeedService : feedService
    });
  }));


  describe('$scope.assignAttempts', function() {
    it('should assign list of attempts to the scope', function() {
      var attempts = [{ username : 'admin', ip : '127.0.0.1' }]
      $scope.assignAttempts(attempts)
      expect($scope.attempts).toEqual(attempts);
    });
  });

  describe('$scope.getAuthAttempts', function() {
    it('should call the getAuthAttempts function of the feed service ', function() {
      var getAuthAttemptsSpy = jasmine.createSpy('getAuthAttempts');
      feedService.getAuthAttempts = getAuthAttemptsSpy;
      $scope.fetchLog();
      expect(feedService.getAuthAttempts).toHaveBeenCalledWith($scope.assignAttempts, $scope.displayError);
    });
  });

});