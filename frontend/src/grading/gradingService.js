app.service('gradingService', function ($http) {
    this.getGradeAssessment = function() {
        return $http({
            method: "GET",
            url: "http://127.0.0.1:8080/api/group/0"
        }).then(function(response){
            return response.data;
        });
    };
});

