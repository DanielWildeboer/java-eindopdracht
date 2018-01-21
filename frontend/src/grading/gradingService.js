app.service('gradingService', function ($http, $q) {
    this.getGradeAssessment = function (groupId) {
        return $http({
            method: "GET",
            url: "http://127.0.0.1:8080/api/group/" + groupId
        }).then(function (response) {
            return response.data;
        });
    };

    this.addGradingAssessment = function (groupId, studentId, userId, sender_student, receiver_student, description, grade) {
        var request = $http({
            method: "POST",
            url: 'http://127.0.0.1:8080/api/grade/assessment',
            data: {
                groupId: groupId,
                studentId: studentId,
                userId: userId,
                senderStudent: sender_student,
                receiverStudent: receiver_student,
                description: description,
                grade: grade
            },
            header: {
                'Content-Type': 'application/json'
            }
        });

        return (request.then(handleSuccess, handleError));
    };

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

