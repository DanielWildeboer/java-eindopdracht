var app = angular.module('mainApp', ['ngRoute']);

app.controller('headerController', function($scope, $route) {
    $scope.$route = $route;

    $scope.date = new Date();
    $scope.dashName = 'Rob Smit';

    $scope.acronym = function(name) {

        var matches = name.match(/\b(\w)/g);
        var acronym = matches.join('');

        return acronym
    };
    $scope.logout = function() {
        window.location = "http://localhost/java-eindopdracht/frontend/src/Login/login.html";
    }
});


app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'src/card/card.html',
            activeTab: 'dashboard'
        })
        .when('/groups', {
            templateUrl: 'src/groups/groups.html',
            activeTab: 'groups'
        })
        .when('/groups', {
            templateUrl: 'src/groups/groups.html',
            activeTab: 'groups'
        })
        .when('/addGroup', {
            templateUrl: 'src/groups/group-detail/group-detail.html',
            activeTab: 'groups'
        })
        .when('/grading/:id', {
            templateUrl: 'src/groups/group-grading/group-grading.html',
            activeTab: 'groups'
        })
        .when('/assessment', {
            templateUrl: 'src/grading/grade.html',
            activeTab: 'groups'
        })
});

