var app = angular.module('mainApp', []);

app.controller('studentsController', function($scope) {

	$scope.groups = [
		{name: "Team 1", subject: "Een kanker taart bakken", status: "Compleet" },
		{name: "Team 2", subject: "CEH (alweer)", status: "Open" },
		{name: "Team 3", subject: "Java Minor", status: "Open" },
		{name: "Team 420", subject: "JWZ", status: "Dank" },
		{name: "Team 5", subject: "Java Minor", status: "Compleet" },
		{name: "Team 6", subject: "Java Minor", status: "Compleet" },
		{name: "Team 7", subject: "Java Minor", status: "Compleet" },
	]
});