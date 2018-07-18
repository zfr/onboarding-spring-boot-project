var onboardingApp = angular.module('onboardingApp');

onboardingApp.factory('TeamService', ['$http', function ($http) {
    return {
        list: function () {
            return $http({
                method: "GET",
                url: "/teams"
            });
        }
        /*
        ,
        save: function (params) {
            var data = {
                teamIds: params.teamIds,
                public: params.public,
                name: params.name,
                description: params.description,
                alertSearchQuery: params.alertSearchQuery
            };
            return $http(
                {
                    method: "POST",
                    url: "/alert/savedSearch/save?_=" + (new Date()).getTime(),
                    data: data
                }
            );
        },
        delete: function (savedSearchId) {
            return $http(
                {
                    method: "DELETE",
                    url: "/alert/savedSearch/delete",
                    params: {
                        id: savedSearchId,
                        _: (new Date()).getTime()
                    }
                }
            );
        },
        makeDefault: function (savedSearchId) {
            return $http(
                {
                    method: "GET",
                    url: "/alert/savedSearch/default",
                    params: {
                        id: savedSearchId,
                        _: (new Date()).getTime()
                    }
                }
            );
        }
        */
    };
}]);