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

        AddGroupService.postGroup($scope.name, $scope.grade, $scope.subject, $scope.students)
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
        postGroup: postGroup
    });

    function postGroup(name, grade, subject, students) {

        var request = $http({
            method: "post",
            url: "http://127.0.0.1:8080/api/group",
            data: {
                name: name,
                grade: grade,
                subject: subject,
                students: students
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