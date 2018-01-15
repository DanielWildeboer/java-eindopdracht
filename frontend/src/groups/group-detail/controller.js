app.controller("ListController", ['$scope', function ($scope) {

      $scope.personalDetails = [];

    $scope.addNew = function (personalDetails) {
        if(personalDetails) {
            $scope.personalDetails.push({
                'fname': personalDetails.fname,
                'lname': personalDetails.lname,
            });
        }

        $scope.PD = {};
    };

    $scope.remove = function () {
      // TODO: FIX REMOVE ITEM FROM ARRAY
    };

}]);