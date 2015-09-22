'use strict';

/**
 * 通用控制器
 */


//  /**
//   * 弹出框势实例化控制器
//   */
//  app.controller('ModalInstanceCtrl', ['$scope', '$modalInstance', 'items', function($scope, $modalInstance,items) {
//	$scope.items = items;
//	$scope.corg = $scope.corg;
//    $scope.selected = {
//      item: $scope.items[0]
//    };
//    
//    //弹出框确定按钮触发处理方法
//    $scope.ok = function () {
//      $modalInstance.close($scope.selected.item);
//    };
//
//  //弹出框取消按钮触发处理方法
//    $scope.cancel = function () {
//      $modalInstance.dismiss('cancel');
//    };
//  }])
//  ; 
//  
//
//  //模式框
//  app.controller('ModalDemoCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
//    $scope.items = ['item1', 'item2', 'item3'];
//    $scope.open = function (size,tempUrl,data) {
//    $scope.corg = data.entity;
//      var modalInstance = $modal.open({
//        templateUrl: tempUrl,
//        controller: 'ModalInstanceCtrl',
//        size: size,
//        scope:$scope,
//        resolve: {
//          items: function () {
//            return $scope.items;
//          }
//        }
//      });
//
//      modalInstance.result.then(function (selectedItem) {
//        $scope.selected = selectedItem;
//      }, function () {
//        $log.info('Modal dismissed at: ' + new Date());
//      });
//    };
//  }])
//  ; 
 