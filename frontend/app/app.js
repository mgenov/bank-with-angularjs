var app = angular.module('bankApp', ['ngRoute']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'view/index_page.view.html'
    })
    .when('/history', {
        templateUrl: 'view/transaction_history_page.view.html'
    })
    .otherwise({
        redirectTo: '/'
    });

    $locationProvider.html5Mode(true);
});

app.controller('TransactionsHistoryCtrl', function($scope, $http) {
    $http.get("/history")
        .then(function(response) {
          $scope.transactions = response.data;
    });
});
