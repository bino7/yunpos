app.controller('IngetralSettingCtrl', function($scope, $http, $state, $stateParams){
	$scope.processForm = function() {
		$http({
			method : 'get',
			url    : '/ajax/member/ingetral_setting/' + $stateParams.id
		}).success(function(data) {
			$scope.sysMemberIngetral = data;
		});
	};
	
	$scope.saved = {};
	$scope.save = function(sysMemberIngetral) {
		$scope.saved = angular.copy(sysMemberIngetral);
		
		$http({
			method : 'put',
			url	   : '/ajax/member/integral_setting/'+$scope.saved.id,
			params : $scope.saved
		}).success(function(data) {
			alert('操作成功');
		}).error(function(data,status,headers,config) {
			alert('操作失败');
		});
	}
});