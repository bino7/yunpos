'use strict';

/**
 * 用户登录控制器
 */

//{'get':    {method:'GET'},  
// 'save':   {method:'POST'},  
// 'query':  {method:'GET', isArray:true},  
// 'remove': {method:'DELETE'},  
// 'delete': {method:'DELETE'}  
//};
//app.factory("Login", function ($resource) {
//    return $resource(URLS.hotels, {id: "@id"}, {
//        update: {
//            method: 'PUT'
//        }
//    });
//});
//
//
//app.controller("LoginController", function ($scope, Login, $state) {
//    $scope.login = function () {
//        var login = new Login($scope.username,$scope.password);
//        login.$save({}, function() {
//            $state.transitionTo("app.home");
//        });
//    };
//
//});

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