app.controller('gradingController', function ($scope, $route, $routeParams, $http, groupGradingService) {
    $scope.group = {
        name: "Team Rood", subject: "Java Minor", groupGrade: 7.4, status: "Gesloten", members: [
            {name: "Bjorne Hoeksema", grade: 2.0, feedback: [
                {name: "Bjorne Hoeksema", grade: 2.1, comment: "1Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Pim Gelmers", grade: 7.2, comment: "2Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Rik Hassing", grade: 7.3, comment: "3Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Ramon Valk", grade: 7.4, comment: "4Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Lesley van Oostenrijk", grade: 7.5, comment: "5Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Daniel Wildeboer", grade: 7.6, comment: "6Feedback bla bla bla redenen bla bla bla Stenden."}
            ]
            },
            {name: "Pim Gelmers", grade: 7.4, feedback: [
                {name: "Bjorne Hoeksema", grade: 7.4, comment: "7Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Pim Gelmers", grade: 7.4, comment: "8Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Rik Hassing", grade: 7.4, comment: "9Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Ramon Valk", grade: 7.4, comment: "10Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Lesley van Oostenrijk", grade: 7.4, comment: "11Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Daniel Wildeboer", grade: 7.4, comment: "12Feedback bla bla bla redenen bla bla bla Stenden."}
            ]
            },
            {name: "Rik Hassing", grade: 7.4, feedback: [
                {name: "Bjorne Hoeksema", grade: 7.4, comment: "13Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Pim Gelmers", grade: 7.4, comment: "14Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Rik Hassing", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Ramon Valk", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Lesley van Oostenrijk", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Daniel Wildeboer", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."}
            ]
            },
            {name: "Ramon Valk", grade: 7.4, feedback: [
                {name: "Bjorne Hoeksema", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Pim Gelmers", grade: 1.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Rik Hassing", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Ramon Valk", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Lesley van Oostenrijk", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Daniel Wildeboer", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."}
            ]
            },
            {name: "Lesley van Oostenrijk", grade: 8.0, feedback: [
                {name: "Bjorne Hoeksema", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Pim Gelmers", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Rik Hassing", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Ramon Valk", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Lesley van Oostenrijk", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Daniel Wildeboer", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."}
            ]
            },
            {name: "Daniel Wildeboer", grade: 7.4, feedback: [
                {name: "Bjorne Hoeksema", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Pim Gelmers", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Rik Hassing", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Ramon Valk", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Lesley van Oostenrijk", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."},
                {name: "Daniel Wildeboer", grade: 7.4, comment: "Feedback bla bla bla redenen bla bla bla Stenden."}
            ]
            }
        ]
    };

    var currentId = $routeParams.id;
    groupGradingService.getGroup(currentId)
        .then(function (response) {
            $scope.singleGroup = response;
        });

    $scope.getAssessment = function(studentId) {
        groupGradingService.getGradeAssessment($scope.singleGroup.id, studentId)
    };

    $scope.calc = function (member) {
            var gradeArray = new Array();
            angular.forEach(member.feedback, function (value, key) {
                gradeArray.push(value.grade);
            });
            var sum = 0;
            angular.forEach(gradeArray, function (value) {
                sum += parseFloat(value);
            });
            return (Math.round((sum / gradeArray.length) * 10) / 10).toFixed(1);
    };
});

