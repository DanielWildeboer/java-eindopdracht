app.service('groupGradingService', function ($http, $q) {
   return ({
       getGroup : getGroup,
       getGradeAssessment : getGradeAssessment
   });

    function getGradeAssessment(groupId, studentId) {

        var request = $http({
            method: "GET",
            url: "http://127.0.0.1:8080/api/grade/assessment/" + groupId + "/" + studentId
        });

        return (request.then(handleSuccess, handleError));
    }

    function getGroup(currentId) {

        var request = $http({
            method: "GET",
            url: "http://127.0.0.1:8080/api/group/" + currentId
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