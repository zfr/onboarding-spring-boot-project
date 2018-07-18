var onboardingApp = angular.module('onboardingApp', ['ui.bootstrap', 'ngRoute']);

onboardingApp.config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'pages/dashboard.html',
            controller: 'DashboardController'
        })
        .when('/teams', {
            templateUrl: 'pages/teams.html',
            controller: 'TeamsController'
        })
        .when('/users', {
            templateUrl: 'pages/users.html',
            controller: 'UsersController'
        })
        .when('/trainings', {
            templateUrl: 'pages/trainings.html',
            controller: 'TrainingsController'
        })
        .otherwise({
            redirectTo: "/"
        });

    $httpProvider.interceptors.push(apiInterceptor);
}]);

var API_URL = 'http://localhost:8086';

function apiInterceptor($q) {
    return {
        request: function (config) {
            var url = config.url;

            // ignore template requests
            if (url.substr(url.length - 5) === '.html') {
                return config || $q.when(config);
            }

            config.url = API_URL + config.url;
            return config || $q.when(config);
        }
    }
}

onboardingApp.controller('OnboardingAppController', ['$scope', '$location', function ($scope, $location) {
    $scope.tabs = {
        list: [
            {
                'href': 'teams',
                'title': 'Teams'
            },
            {
                'href': 'users',
                'title': 'Users'
            },
            {
                'href': 'trainings',
                'title': 'Trainings'
            }
        ],
        selected: undefined
    };

    $scope.onTabSelected = function (tab) {
        $scope.tabs.selected = tab;
        $location.path(tab.href);
    };
}]);

onboardingApp.controller('DashboardController', ['$scope', function ($scope) {
    $scope.tabs.selected = undefined;
    $scope.message = 'Hello Dashboard!';
}]);

onboardingApp.controller('UsersController', ['$scope', function ($scope) {
    $scope.tabs.selected = $scope.tabs.list[1];
    $scope.message = 'Hello Users!';
}]);

onboardingApp.controller('TrainingsController', ['$scope', function ($scope) {
    $scope.tabs.selected = $scope.tabs.list[2];
    $scope.message = 'Hello Trainings!';
}]);
