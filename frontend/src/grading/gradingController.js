var app = angular.module('gradeApp', ['ngRoute', 'ngMaterial']);
app.controller('gradingController', function ($scope, $location, gradingService) {
    gradingService.getGradeAssessment(0)
        .then(function (response) {
            $scope.gradeAssessment = response;
        });
});