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
        .when('/addGroup', {
            templateUrl: 'src/groups/group-detail/group-detail.html',
            activeTab: 'groups'
        })

});

