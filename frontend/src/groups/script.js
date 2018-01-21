app.controller('groupsController', function($scope, $location, $http) {

    $scope.groups = $http.get('http://127.0.0.1:8080/api/group').then(function(result) {
        $scope.groups = result.data;
        if($scope.groups.status) {
            $scope.gradeStatus = "Afgerond";
        }
        else {
            $scope.gradeStatus = "Open";
        }
    });
    $scope.delete = function(group, index) {

        $scope.groups.splice(index, 1);

        var del = "/" + (group);
        $http.delete("http://127.0.0.1:8080/api/group" + del).then(function(response) {

        });
    };
    $scope.routeToGroup = function(routeID){
        $location.path("grading/"+ routeID);
    };
});