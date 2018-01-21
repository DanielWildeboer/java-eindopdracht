var app = angular.module('gradeApp', ['ngRoute', 'ngMaterial']);
app.controller('gradingController', function ($scope, $location, gradingService) {
    $scope.grades = {
        grade: "7.0",
        names: ["Bjorne Hoeksema", "Lesley van Oostenrijk", "Pim Gelmers", "Daniel Wildeboer", "Rik Hassing", "Ramon Valk"]
    };
    console.log(gradingService.getGradeAssessment())
});