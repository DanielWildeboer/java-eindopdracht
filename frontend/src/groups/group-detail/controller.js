app.controller("ListController", ['$scope', 'AddGroupService', function ($scope, AddGroupService) {

    $scope.students = [];

    $scope.addNew = function (students) {
        if(students) {
            $scope.students.push({
                'firstName': students.firstName,
                'email': students.email
            });
        }
    };

    $scope.postGroup = function () {
        AddGroupService.postGroup($scope.name, $scope.grade, $scope.subject).then (
            $scope.postGroup =  angular.forEach($scope.students, function (singleStudent) {
                AddGroupService.postStudents(singleStudent);
            })
        )
            .then(
                function (errorMessage) {
                    console.warn(errorMessage);
                }
            )
    };

    $scope.remove = function (students) {
        var index = $scope.students.indexOf(students);
        $scope.students.splice(index, 1);
    };
}]);


app.service('AddGroupService', function ($http, $q) {

    return ({
        postGroup: postGroup,
        postStudents: postStudents
    });




    function postGroup(name, grade, subject, students, userId) {

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

    function postStudents(name, email) {

        var request = $http({
            method: "post",
            url: "http://127.0.0.1:8080/api/student",
            data: {
                name: name,
                email: email
            },
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