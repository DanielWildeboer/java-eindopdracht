var app = angular.module('mainApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'src/card/card.html'
        })
        .when('/groups', {
            templateUrl: 'src/groups/groups.html'
        })
        .when('/addGroup', {
            templateUrl: 'src/groups/group-detail/group-detail.html'
        })
});

