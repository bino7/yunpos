'use strict';

/**
 * 用户登录控制器
 */
app.controller('LoginController', ['$scope', '$http', '$state', function($scope, $http, $state) {
    $scope.user = {};
    $scope.authError = null;
    //登录点击事件
    $scope.login = function() {
      $scope.authError = null;
      $http.post('api/login', {name: $scope.user.name, password: $scope.user.password})
      .then(function(response) {
        if ( !response.data.user ) {
          $scope.authError = '邮箱或密码不正确';
        }else{
          $state.go('app.home');
        }
      }, function(x) {
        $scope.authError = '系统服务异常';
      });
    };
    
  }])
;