app.controller('MainCtrl', ['$scope', '$http', '$interval', 'uiGridTreeViewConstants', function ($scope, $http, $interval, uiGridTreeViewConstants ) {
	
	$scope.gridOptions = {
    enableSorting: true,
    enableFiltering: true,
    showTreeExpandNoChildren: true,
    columnDefs: [
		{ name: 'orgNo',displayName:'组织机构代码', width: '20%' },
		{ name: 'orgName',displayName:'组织机构', width: '30%' },
		{ name: 'createDate',displayName:'创建时间', type:'date',cellFilter: 'date:"yyyy-MM-dd"',width: '20%' },
		{ name: 'oper',displayName:'操作', width: '30%',}
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
   for ( var i = 0; i < data.length; i++ ){
     data[i].oper = '修改|添加下级|删除';
   }
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