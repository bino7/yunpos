'use strict';


app.controller('LoginController', ['$rootScope','$scope','$window', '$http', '$state','AUTH_EVENTS', 'AuthService','Session', function($rootScope,$scope,$window, $http, $state,AUTH_EVENTS,AuthService,Session) {
	//封装登录输入信息
	$rootScope.isLogined = false;
	$scope.authError = null;
    //登录
    $scope.login = function() {
    	var credentials = {
    			  username: $scope.username,
    			  password: $scope.password
    	}
    	AuthService.login(credentials).then(function (user) {
//			$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
			$scope.setCurrentUser(user);
			$state.go('app.home');
		}, function () {
			$scope.authError = '用户名或密码不正确';
			$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
		});
    };
    
    //登出
    $scope.loginOut = function() {
    	$window.sessionStorage["userInfo"]=null;
    	$scope.setCurrentUser(null);
    	Session.destroy();
		$state.go('login');
		
    }
    
  }]);
