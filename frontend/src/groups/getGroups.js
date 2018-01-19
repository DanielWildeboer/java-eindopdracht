app.service('getGroups', function($http) {
    this.get = function (id) {
    	var url = "/api/group/" + id;
        $http.get(url)
        			.then(function (response){
						return response.data;
						console.log("Got the data");
					}).catch(function(response) {
					  console.error('Error occurred:', response.status, response.data);
					});
    }
});