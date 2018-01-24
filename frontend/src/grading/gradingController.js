var app = angular.module('gradeApp', ['ngRoute', 'ngMaterial']);
app.controller('gradingController', function ($scope, $location, gradingService, $window) {
    $scope.userId = 1;
    $scope.groupId = getParameterByName('groupId');
    $scope.studentId = getParameterByName('studentId');
    $scope.token = getParameterByName('token');

    $scope.init = function () {
        gradingService.getToken($scope.groupId, $scope.studentId)
            .then(function (response) {
                $scope.tokenOnServer = response.randomString;
                if($scope.tokenOnServer !== $scope.token) {
                    $window.location.href = 'http://www.google.com';
                }
            });
    };

    gradingService.getGradeAssessment($scope.groupId)
        .then(function (response) {
            $scope.gradeAssessment = response;
        });

    $scope.addGradingAssessment = function () {
        angular.forEach($scope.gradeAssessment.students, function (student, key) {
            gradingService.addGradingAssessment($scope.groupId, student.id, $scope.userId, $scope.studentId, student.id, student.description, student.grade)
        });
    };

    // Because it actually is a separate application and so $routeParams won't work
    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }
});