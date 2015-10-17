app.controller("SysOrgController", ["$scope","$rootScope", "IntegralUITreeGridService", "$timeout","OrgService", function($scope,$rootScope, $gridService, $timeout,OrgService){
	$scope.gridName = "gridSample";
	$scope.gridLines = 'vertical';
	$scope.rows = [];
	


	// Custom field names used to match data fields from our data source to the ones used by the Tree Grid
	$scope.customFields = {
//		column : {headerText : 'columnTitle',headerAlignment : 'columnAlignment',contentAlignment : 'cellAlignment',width : 'size'},
//		row : {id : 'rowID',pid : 'parentID',text : 'rowText'},
//		cell : {text : 'cellText'}
		column : {fixedWidth:true,headerText : 'headerText',headerAlignment : 'headerAlignment',contentAlignment : 'cellAlignment',width : 'width'},
		row : {id : 'orgNo',pid : 'orgParentNo',text : 'text'},
		cell : {text : 'text',cid:'cid'}
	}
	
 	var promise = OrgService.query();
	   promise.then(function(data) {  // 调用承诺API获取数据 .resolve  
		   if($rootScope.orgs){
			   $rootScope.orgs = null;
		   }
		   $gridService.expandColIndex($scope.gridName, 1);
		   //$gridService.loadData($scope.gridName, data);
		   $gridService.loadData($scope.gridName, data,null,$scope.customFields);
			//$gridService.loadData($scope.gridName, $scope.flatData, null, $scope.customFields, true);
	    }, function(data) {  // 处理错误 .reject  
	    	alert("数据获取失败");
	    })
	

	$scope.ctrlStyle = {
		row: {
			general: {
				hovered: 'row-hovered',
				selected: 'row-selected'
			},
			cell: {
				hovered: 'row-cell-hovered'
			}
		}
	}
	
	

	$scope.columns = [
		{ id: 1, contentAlignment: "right", width: 45, fixedWidth: true},
		{ id: 2, headerText: "组织机构编码", width: 400,headerAlignment: "center" },
		{ id: 3, headerText: "组织结构", headerAlignment: "center", contentAlignment: "center", width: 200 },
		{ id: 4, headerText: "创建时间", headerAlignment: "center", contentAlignment: "right", width: 220 }
	];

//	var initTimer = $timeout(function(){
//
//		$gridService.expandColIndex($scope.gridName, 1);
//		$gridService.loadData($scope.gridName, $rootScope.orgs);
//		$timeout.cancel(initTimer);
//	}, 1);
}]);
