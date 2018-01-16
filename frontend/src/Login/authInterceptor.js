app.factory('AuthInterceptor', function ($window, $q, $localStorage) {
    return {
        request: function(config) {
            config.headers = config.headers || {};
            if ($localStorage.getItem('authToken')) {
                config.headers.Authorization = 'Bearer ' + $localStorage.getItem('token');
            }
            return config || $q.when(config);
        },
        response: function(response) {
            if (response.status === 401) {
                // TODO: Redirect user to login page.
            }
            return response || $q.when(response);
        }
    };
});

// Register the previously created AuthInterceptor.
app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
});
