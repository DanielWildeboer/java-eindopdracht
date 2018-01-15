var app = angular.module('Login', ['ngMaterial']);

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

    return ({
        login: login
    });

    function login(email, password) {
        var request = $http({
            method: "post",
            url: "http://localhost:8080/api/login",
            headers: {
                Authorization: "Basic " + btoa(email + ":" + password)
            },
            // data: {
            //     email: email,
            //     password: password
            // }
        });

        return (request.then(handleSuccess, handleError));
    }

    function handleError(response) {
        if (
            !angular.isObject(response.data) ||
            !response.data.message
        ) {
            return ( $q.reject("An unknown error occurred.") );
        }
        return ( $q.reject(response.data.message) );
    }

    function handleSuccess(response) {
        return ( response.data );
    }

});