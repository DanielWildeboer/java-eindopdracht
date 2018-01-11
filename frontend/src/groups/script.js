var app = angular.module('mainApp', []);

app.controller('studentsController', function($scope) {

	$scope.groups = [
		{name: "Team 1", status: "Compleet" },
		{name: "Team 2", status: "Open" },
		{name: "Team 3", status: "Open" },
		{name: "Team 420", status: "Dank" },
		{name: "Team 5", status: "Compleet" },
		{name: "Team 6", status: "Compleet" },
		{name: "Team 7", status: "Compleet" },
	]
});