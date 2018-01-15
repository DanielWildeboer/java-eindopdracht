app.controller("ListController", ['$scope', function ($scope) {

      $scope.personalDetails = [];

    $scope.addNew = function (personalDetails) {
        console.log(personalDetails);

        $scope.personalDetails.push({
            'fname': personalDetails.fname,
            'lname': personalDetails.lname,
        });
        $scope.PD = {};
    };

    $scope.remove = function () {
        var newDataList = [];
        $scope.selectedAll = false;
        angular.forEach($scope.personalDetails, function (selected) {
            if (!selected.selected) {
                newDataList.push(selected);
            }
        });
        $scope.personalDetails = newDataList;
    };

}]);