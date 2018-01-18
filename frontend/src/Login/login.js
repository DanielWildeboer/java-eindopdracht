var app = angular.module('Login', ['ngMaterial', 'ngStorage']);

app.controller('LoginController', ['$scope', 'LoginService', '$localStorage', '$http', function ($scope, LoginService, $localStorage, $http) {
    $scope.login = function () {

        LoginService.login($scope.email, $scope.password)
            .then(
                function (errorMessage) {
                    console.warn(errorMessage);
                }
            )
        // new LoginService({email: $scope.email, password: $scope.password},
        //
        // function (data, headers) {
        //         $localStorage.user = data.user;
        //         $localStorage.authToken = headers['x-auth-token'];
        //         $http.defaults.headers.common['x-auth-token'] = headers['x-auth-token'];
        //     }, function (error) {
        //         console.log(error);
        //     });
    };
}]);

app.service('LoginService', function ($http, $q) {

<<<<<<< HEAD
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
=======
    return ({
        login: login
    });

    function login(email, password) {
        var request = $http({
            method: "post",
            url: "http://127.0.0.1:8080/api/login",
            data: {
                email: email,
                password: password,
            }
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
>>>>>>> b9e035cd15c18116d065a365a6dff468cc7078c9
});