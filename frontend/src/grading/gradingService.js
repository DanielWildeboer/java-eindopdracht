app.service('gradingService', function ($http) {
    this.getGradeAssessment = function(groupId) {
        return $http({
            method: "GET",
            url: "http://127.0.0.1:8080/api/group/" + groupId
        }).then(function(response){
            return response.data;
        });
    };
});

