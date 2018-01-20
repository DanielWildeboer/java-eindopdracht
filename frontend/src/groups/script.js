app.controller('groupsController', function($scope, $location, $http) {

    $scope.groups = $http.get('http://127.0.0.1:8080/api/group').then(function(result) {
        $scope.groups = result.data;
    });

    $scope.delete = function(group, index) {

        $scope.groups.splice(index, 1);

        var del = "/" + (group);
        $http.delete("http://127.0.0.1:8080/api/group" + del).then(function(response) {
            console.log($scope.groups);
        });
    };
});