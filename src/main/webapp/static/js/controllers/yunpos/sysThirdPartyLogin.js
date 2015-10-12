app.controller('ThirdPartyLoginCtrl',  function($scope, $http, $state, $stateParams) {
    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    }; 
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [10, 20, 50],
        pageSize: 10,
        currentPage: 1
    };  
    
	/**
	 * 登录收单包服务窗
	 */
	$scope.loginfwc = function() {
		var url = "http://y.o2o520.com/index.php?g=Api&m=Login&a=login";
		var username = "09600041039";
		var key = "yunpos_xn_20151012";
		//获取Md5加密后的Token信息	
		$http({
			method: 'get',
			url: '/ajax/md5sign/username',
			params: {
				"username": username,
				"key": key
			}
		}).success(function(data) {
			url = url + "&token=" + data.token;
			url = url + "&username=" + username;
			url = url + "&t=" + data.t;
			url = url + "&p=" + data.p;
			window.open(url,"newwindow");		
		}).error(function(data) {
			alert("MD5加密失败,请重新再试！");
		});   
	};
    
});



