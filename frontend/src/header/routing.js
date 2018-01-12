var app = angular.module('mainApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'src/header/test1.html'
        })
        .when('/students', {
            templateUrl: 'src/card/card.html'
        })
        .when('/groups', {
            templateUrl: 'src/header/test1.html'
        })
});
