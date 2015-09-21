app.controller('MainCtrl', ['$scope', '$http', '$interval', 'uiGridTreeViewConstants', function ($scope, $http, $interval, uiGridTreeViewConstants ) {
	
	$scope.gridOptions = { enableCellEditOnFocus: true };
	$scope.msg = {};
	
	$scope.gridOptions = {
//    enableSorting: true,
//    enableCellEditOnFocus:true,
//    enableFiltering: true,
//    showTreeExpandNoChildren: true,
    columnDefs: [
		{ name: 'orgNo',displayName:'组织机构代码', width: '20%' },
		{ name: 'orgName',displayName:'组织机构', width: '30%' },
		{ name: 'createDate',displayName:'创建时间', type:'date',cellFilter: 'date:"yyyy-MM-dd"',width: '20%' },
		{ name: 'id',displayName:'操作', width: '23%',enableCellEdit:false,
		 //cellTemplate:'<div><a ui-sref="app.table.org({id:row.getProperty(col.field)})" id="{{row.getProperty(col.id)}}">修改</a> |  <a ui-sref="app.table.org({id:row.getProperty(col.id)})" id="{{row.getProperty(col.field)}}">添加下级</a>   |   <a ui-sref="app.table.org({id:row.getProperty(col.id)})" id="{{row.getProperty(col.field)}}">删除</a></div>'}
		cellTemplate:'<button class="btn btn-success" ng-click="open()">修改</button> <button class="btn btn-success" ng-click="open(lg)">添加下级</button> <button class="btn btn-success" ng-click="open(lg)">删除</button>'}
		],
    
    onRegisterApi: function( gridApi ) {
      $scope.gridApi = gridApi;
      //编辑触发shijia
      gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
          $scope.msg.lastCellEdited = 'edited row id:' + rowEntity.id + ' Column:' + colDef.name + ' newValue:' + newValue + ' oldValue:' + oldValue ;
          $scope.$apply();
       });
  }};
 
	
    $scope.open = function () {
    	alert("33333333333333");
//        var corg = data.entity;
//          var modalInstance = $modal.open({
//            templateUrl: tempUrl,
//            controller: 'ModalInstanceCtrl',
//            size: size,
//            resolve: {
//              items: function () {
//                return $scope.items;
//              }
//            }
//          });
//
//          modalInstance.result.then(function (selectedItem) {
//            $scope.selected = selectedItem;
//          }, function () {
//            $log.info('Modal dismissed at: ' + new Date());
//          });
        };
	
 $http.get('/ajax/org/select')
 .success(function(data) {
	   data[0].$$treeLevel = 0;
	   data[2].$$treeLevel = 2;
	   data[3].$$treeLevel = 3;

   $scope.gridOptions.data = data;
 });
 
  $scope.expandAll = function(){
    $scope.gridApi.treeBase.expandAllRows();
  };
 
  $scope.toggleRow = function( rowNum ){
    $scope.gridApi.treeBase.toggleRowTreeState($scope.gridApi.grid.renderContainers.body.visibleRowCache[rowNum]);
  };
 
  $scope.toggleExpandNoChildren = function(){
    $scope.gridOptions.showTreeExpandNoChildren = !$scope.gridOptions.showTreeExpandNoChildren;
    $scope.gridApi.grid.refresh();
  };
}]);
/**/