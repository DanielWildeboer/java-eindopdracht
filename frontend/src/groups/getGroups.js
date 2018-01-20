app.service('getGroups', ['$http', function($scope, $http) {


	$scope.data = [];

    function loadData() {
        $http.get("http://127.0.0.1:8080/api/group")
            .then(function (response) {
                $scope.data = response;
                console.log($scope.data);
            })
            .catch(function(error) {
                $scope.error = error;
            });
    }

    loadData();

}]);