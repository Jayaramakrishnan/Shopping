var App = angular.module('Registration', ['ngSanitize']); 

App.factory('helloService', function($http, $q)
{

	var getData = function()
	{
		var loadInternalData = function(data){
			console.log(data.id);
			console.log(data.title);
		};
		var deferred = $q.defer();
		$http({
			method : 'GET',
			url : 'https://jsonplaceholder.typicode.com/posts/3',
			dataType : 'json',
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(result)
		{
			deferred.resolve(result);
		});
		return {
			promise: deferred.promise,
			load: loadInternalData
		}
	};
	return {
		getData : getData
	};
});


App.controller('Form', ['$scope', '$http', '$sce', 'helloService', function($scope, $http, $sce, helloService){
	$scope.statusObj = {};
	$http.get('https://jsonplaceholder.typicode.com/posts/2').success(function(data, status){
		$scope.statusObj = data;
		$scope.statusObj.title = $sce.trustAsHtml ($scope.statusObj.title + '<b onClick = "alert(2);"> Hello </b>');
		$scope.statusObj.asTitle = $scope.statusObj.title;
		$scope.val = helloService.getData();
		var check = $scope.val.promise.then(
				function(data)
				{
					$scope.val.load(data);
				});
	});
}]);