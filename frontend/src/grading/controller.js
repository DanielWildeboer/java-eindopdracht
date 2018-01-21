<<<<<<< HEAD:frontend/src/grading/controller.js
var app = angular.module('gradeApp', ['ngRoute']);
app.controller('gradingController', function($scope, $location) {
	$scope.grades = {grade: "7.0", names: [ "Bjorne Hoeksema", "Lesley van Oostenrijk", "Pim Gelmers", "Daniel Wildeboer", "Rik Hassing", "Ramon Valk"]};

	
=======
var app = angular.module('gradeApp', ['ngRoute', 'ngMaterial']);
app.controller('gradingController', function ($scope, $location, gradingService) {
    $scope.grades = {
        grade: "7.0",
        names: ["Bjorne Hoeksema", "Lesley van Oostenrijk", "Pim Gelmers", "Daniel Wildeboer", "Rik Hassing", "Ramon Valk"]
    };
    console.log(gradingService.getGradeAssessment())
>>>>>>> master:frontend/src/grading/gradingController.js
});