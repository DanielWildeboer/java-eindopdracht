app.service('gradingService', function ($http) {

    return ({
        getGradeAssessment: getGradeAssessment,
    });

    function getGradeAssessment() {
        console.log("getGradeAssessment");
        $http({
            method: "GET",
            url: "http://127.0.0.1:8080/api/group/0"
        }).then(function(response){
            console.log(response.data);
            return response.data;
        });
    }
});

