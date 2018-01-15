var app = angular.module('mainApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'src/header/test1.html'
        })
        .when('/students', {
            templateUrl: 'src/groups/group-detail/group-detail.html'
        })
        .when('/groups', {
            templateUrl: 'src/groups/groups.html'
        })
});
