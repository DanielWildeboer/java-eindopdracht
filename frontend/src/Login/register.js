var app = angular.module('Register', ['ngMaterial']);

app.controller('RegisterController', ['$scope', 'RegisterService', function ($scope, RegisterService) {
    $scope.register = function () {

        RegisterService.register($scope.firstName, $scope.lastName, $scope.email, $scope.password, $scope.passwordConfirm)
            .then(
                function (errorMessage) {
                    console.warn(errorMessage);
                }
            )

    }
}]);

app.service('RegisterService', function ($http, $q) {

    return ({
        register: register
    });

    function register(firstName, lastName, email, password, passwordConfirm) {
        var request = $http({
            method: "post",
            url: "http://127.0.0.1/api/registration",
            params: {
                action: "add"
            },
            data: {
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password,
                passwordConfirm: passwordConfirm
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

});