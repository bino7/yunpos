app.controller("SysOrgController", ["$scope","$rootScope", "IntegralUITreeGridService", "$timeout","OrgService", function($scope,$rootScope, $gridService, $timeout,OrgService){
	$scope.gridName = "gridSample";
	$scope.gridLines = 'vertical';
	$scope.rows = [];
	
 	var promise = OrgService.query();
	   promise.then(function(data) {  // 调用承诺API获取数据 .resolve  
		   if($rootScope.orgs){
			   $rootScope.orgs = null;
		   }
		   $gridService.expandColIndex($scope.gridName, 1);
			$gridService.loadData($scope.gridName, data);
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
		{ 
			id: 1, 
			contentAlignment: "right", 
			width: 45, 
			fixedWidth: true
		},
		{ id: 2, headerText: "组织机构编码", width: 400,headerAlignment: "center" },
		{ id: 3, headerText: "组织结构", headerAlignment: "center", contentAlignment: "center", width: 200 },
		{ id: 4, headerText: "创建时间", headerAlignment: "center", contentAlignment: "right", width: 200 }
	];

//	var initTimer = $timeout(function(){
//
//		$gridService.expandColIndex($scope.gridName, 1);
//		$gridService.loadData($scope.gridName, $rootScope.orgs);
//		$timeout.cancel(initTimer);
//	}, 1);
}]);
