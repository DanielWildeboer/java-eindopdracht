app.service('cardService', ['$http', function($scope, $http) {

    return ({
        getGradeAssessment : getGradeAssessment,
        addGradeAssessment : addGradeAssessment
    });

    function getGradeAssessment(groupId) {
        var request = $http({
            method: "get",
            url: "http://127.0.0.1:8080/api/group/" + groupId
        });
        return (request.then(handleSuccess, handleError));
    }

    function addGradeAssessment() {
        var request = $http({
            method: "get",
            url: "http://127.0.0.1:8080/api/student"
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

}]);

