var app = angular.module('mainApp', []);

app.controller('cardController', function($scope) {
	$scope.content = ['Team Rood', 'Team Geel', 'Team Blauw Geel', 'Team Groen', 'Team Paars']
	$scope.acronym = function(name) {

		var matches = name.match(/\b(\w)/g);
		var acronym = matches.join('');

		return acronym
	}

	$scope.color = function() {
		var colorArray = ['red', 'pink', 'purple', 'deep-purple', 'indigo', 'blue', 'light-blue', 'cyan', 'teal', 'green', 'light-green', 'lime', 'yellow', 'amber', 'orange', 'deep-orange'];
		var rand = colorArray[Math.floor(Math.random() * colorArray.length)];

		return rand
	}
});