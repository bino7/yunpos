app.controller('MainCtrl', ['$scope', '$http', '$interval', 'uiGridTreeViewConstants', function ($scope, $http, $interval, uiGridTreeViewConstants ) {
	
	$scope.gridOptions = {
    enableSorting: true,
    enableCellEditOnFocus:true,
    enableFiltering: true,
    showTreeExpandNoChildren: true,
    columnDefs: [
		{ name: 'orgNo',displayName:'组织机构代码', width: '20%' },
		{ name: 'orgName',displayName:'组织机构', width: '30%' },
		{ name: 'createDate',displayName:'创建时间', type:'date',cellFilter: 'date:"yyyy-MM-dd"',width: '20%' },
		{ name: 'id',displayName:'操作', width: '23%',enableCellEdit:false,
		 //cellTemplate:'<div><a ui-sref="app.table.org({id:row.getProperty(col.field)})" id="{{row.getProperty(col.id)}}">修改</a> |  <a ui-sref="app.table.org({id:row.getProperty(col.id)})" id="{{row.getProperty(col.field)}}">添加下级</a>   |   <a ui-sref="app.table.org({id:row.getProperty(col.id)})" id="{{row.getProperty(col.field)}}">删除</a></div>'}
		cellTemplate:'<span ng-controller="ModalDemoCtrl"> <script type="text/ng-template" id="myModalContent.html"><div ng-include="\'tpl/modal.form.html\'"></div></script><button class="btn btn-success" ng-click="open(lg)">修改</button> <button class="btn btn-success" ng-click="open(lg)">添加下级</button> <button class="btn btn-success" ng-click="open(lg)">删除</button></span>'}
		],
    onRegisterApi: function( gridApi ) {
      $scope.gridApi = gridApi;
//      $scope.gridApi.treeBase.on.rowExpanded($scope, function(row) {
//        if( row.entity.$$hashKey === $scope.gridOptions.data[50].$$hashKey && !$scope.nodeLoaded ) {
//          $interval(function() {
//            $scope.gridOptions.data.splice(51,0,
//              {name: 'Dynamic 1', gender: 'female', age: 53, company: 'Griddable grids', balance: 38000, $$treeLevel: 1},
//              {name: 'Dynamic 2', gender: 'male', age: 18, company: 'Griddable grids', balance: 29000, $$treeLevel: 1}
//            );
//            $scope.nodeLoaded = true;
//          }, 2000, 1);
//        }
//      });
    }
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