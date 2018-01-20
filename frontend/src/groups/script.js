app.controller('groupsController', function($scope, $location, $http) {

    $scope.groups = $http.get('http://127.0.0.1:8080/api/group').then(function(result) {
        console.log( result.data);
    });

});