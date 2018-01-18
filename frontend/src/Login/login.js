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

    var payload =  new FormData();


    return function (email, password, success, error) {
        payload.append('password', email.password);
        payload.append('email', email.email);

       $http({
            method: 'POST',
            url: 'http://localhost:8080/api/login',
            headers: {
                Authorization: "Basic " + btoa(email + ":" + password)
            },
            data: payload
        }).then(function (resp) {
            success(resp.data, resp.headers())
        }, error);
    };
});