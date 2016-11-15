shopApp.controller('appController', ['$scope', function($scope){

	$scope.showLogin = function()
	{
		$('#loginModal').modal('show');
	};
	
}]);