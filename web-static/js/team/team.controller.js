var onboardingApp = angular.module('onboardingApp');

onboardingApp.controller('TeamsController', ['$scope', 'TeamService', function ($scope, TeamService) {
    $scope.tabs.selected = $scope.tabs.list[0];
    $scope.message = 'Hello Teams!';

    TeamService.list().then(function successCallback(response) {
        console.log(response);
    }, function errorCallback(response) {
        console.log(response);
    });
}]);
