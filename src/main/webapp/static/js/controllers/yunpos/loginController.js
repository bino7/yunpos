'use strict';


app.controller('LoginController', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.user = {};
    $scope.authError = null;
    //登录点击事件
    $scope.login = function() {
      $scope.authError = null;
      $http.post("/ajax/login?username="+$scope.username+"&password="+$scope.password, {})
      .then(function(response) {
        if ( !response.data.isSuccess ) {
          $scope.authError = '用户名或密码不正确';
        }else{
          $state.go('app.home');
        }
      }, function(x) {
        $scope.authError = '系统服务异常';
      });
    };
    
  }]);