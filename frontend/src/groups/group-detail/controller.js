app.controller("ListController", ['$scope', 'AddGroupService', function ($scope, AddGroupService) {

    $scope.students = [];

    $scope.addNew = function (students) {
        if (students) {
            $scope.students.push({
                'email': students.email,
                'firstName': students.firstName,
                'lastName': students.lastName
            });
        }
    };

    $scope.postGroup = function () {
        AddGroupService.postGroup($scope.name, $scope.grade, $scope.subject).then(function (response) {
            $scope.groupID = response;
            angular.forEach($scope.students, function (singleStudent) {
                AddGroupService.postStudents(singleStudent.email, singleStudent.firstName, singleStudent.lastName).then(function (response) {
                    $scope.studentID = response;
                    AddGroupService.addStudents($scope.groupID, $scope.studentID);
                    AddGroupService.postToken($scope.groupID, $scope.studentID)
                        .then(function (response) {
                            $scope.tokenID = response;
                            AddGroupService.sentMail(singleStudent.email, "Rob@Stenden.com", $scope.tokenID)
                        })
                })
            })
        })

    };

    $scope.remove = function (students) {
        var index = $scope.students.indexOf(students);
        $scope.students.splice(index, 1);
    };
}]);


app.service('AddGroupService', function ($http, $q) {

    return ({
        postGroup: postGroup,
        postStudents: postStudents,
        addStudents: addStudents,
        postToken: postToken,
        sentMail: sentMail
    });

    function sentMail(toEmail, fromEmail, tokenId) {

        var request = $http({
            method: "POST",
            url: "http://127.0.0.1:8080/sentMail/" + toEmail + "/" + fromEmail + "/" + tokenId
        });

        return (request.then(handleSuccess, handleError));
    }

    function postToken(groupId, studentId) {

        var request = $http({
            method: "POST",
            url: "http://127.0.0.1:8080/api/token/" + groupId + "/" + studentId
        });

        return (request.then(handleSuccess, handleError));
    }

    function postGroup(name, grade, subject, userId) {

        var request = $http({
            method: "post",
            url: "http://127.0.0.1:8080/api/group",
            data: {
                name: name,
                grade: grade,
                subject: subject,
                userId: 1
            },
            header: {
                'Content-Type': 'application/json'
            }
        });

        return (request.then(handleSuccess, handleError));
    }

    function postStudents(email, firstName, lastName) {

        var request = $http({
            method: "post",
            url: "http://127.0.0.1:8080/api/student",
            data: {
                email: email,
                firstName: firstName,
                lastName: lastName
            },
            header: {
                'Content-Type': 'application/json'
            }
        });

        return (request.then(handleSuccess, handleError));
    }

    function addStudents(groupId, studentId) {

        var request = $http({
            method: "post",
            url: 'http://127.0.0.1:8080/api/group/' + groupId + '/student/' + studentId,
            header: {
                'Content-Type': 'application/json'
            }
        });

        return (request.then(handleSuccess, handleError));
    }

    function handleError(response) {
        if (
            !angular.isObject(response.data) ||
            !response.data.message
        ) {
            return ( $q.reject("An unknown error occurred.") );
        }
        return ( $q.reject(response.data.message) );
    }

    function handleSuccess(response) {
        return ( response.data );
    }

});