var app = angular.module('Login', ['ngMaterial', 'ngStorage']);

app.controller('LoginController', ['$scope', 'LoginService', '$localStorage', '$http', function ($scope, LoginService, $localStorage, $http) {
    $scope.login = function () {

        new LoginService({email: $scope.email, password: $scope.password},
            function (data, headers) {
                $localStorage.user = data.user;
                $localStorage.authToken = headers['x-auth-token'];
                $http.defaults.headers.common['x-auth-token'] = headers['x-auth-token'];
            }, function (error) {
                console.log(error);
            });
    };
}]);

app.service('LoginService', function ($http, $q) {

    return function (email, password, success, error) {
        $http({
            method: 'POST',
            url: 'http://127.0.0.1/api/login',
            headers: {
                Authorization: "Basic " + btoa(email + ":" + password)
            }
        }).then(function (resp) {
            success(resp.data, resp.headers())
        }, error);
    };
});