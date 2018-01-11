var app = angular.module('mainApp', []);

app.controller('cardController', function($scope) {

	$scope.cards = [
		{title: "LEERLINGEN TOTAAL", content: ["Bjorne Hoeksema", "Lesley van Oostenrijk", "Pim Gelmers", "Daniël Wildeboer", "Rik Hassing", "Ramon Valk"]},
		{title: "GROEPEN TOTAAL", content: ["Team 1", "Team 2", "Team 3", "Team 4", "Team 5", "Team 6", "Team 7", "Team 8", "Team 9", "Team 10", "Team 420"]},
		{title: "LOPENDE RESULTATEN", content: ["Team 2", "Team 3"]},
		{title: "COMPLETE RESULTATEN", content: ["Team 1", "Team 4", "Team 420", "Team 5"]}
	]



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