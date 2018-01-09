var app = angular.module('mainApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'test1.html'
        })
        .when('/students', {
            templateUrl: 'test2.html'
        })
        .when('/groups', {
            templateUrl: 'test1.html'
        })
});

