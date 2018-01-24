var app = angular.module('Login', ['ngMaterial', 'ngStorage']);

app.controller('LoginController', ['$scope', 'LoginService', '$localStorage', '$http', '$location', function ($scope, LoginService, $localStorage, $http, $location) {
    $scope.login = function () {

        LoginService.login($scope.email, $scope.password)

    };
}]);

app.service('LoginService', function ($http, $q) {


    return ({
        login: login
    });

    function login(email, password) {

        var request = $http({
            method: "post",
            url: "http://127.0.0.1:8080/api/login",
            data: {
                email:email,
                password: password
            },
            headers: {
                'Content-Type': 'application/json'
            }

        }).then(function successCallback() {
            window.location = "http://localhost/JavaEindOpdracht/java-eindopdracht/frontend/#/";


        }, function errorCallback() {
            console.log("Verkeerde gebruikersnaam / Wachtwoord")

        });
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