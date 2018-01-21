var app = angular.module('gradeApp', ['ngRoute', 'ngMaterial']);
app.controller('gradingController', function ($scope, $location, gradingService) {
    gradingService.getGradeAssessment(0)
        .then(function (response) {
            $scope.gradeAssessment = response;
            console.log($scope.gradeAssessment)
        });

    $scope.addGradingAssessment = function() {
        angular.forEach($scope.gradeAssessment.students, function(student, key) {
            console.log(student);
            gradingService.addGradingAssessment(0, student.id, 1, 3, student.id, student.description, student.grade)
        });
    }
});