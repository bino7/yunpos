app.controller('SysOrgController', ['$scope', '$http', '$interval', 'uiGridTreeViewConstants','uiGridConstants', function ($scope, $http, $interval, uiGridTreeViewConstants,uiGridConstants) {
			$scope.gridOptions = {};
			$scope.gridOptions.enableSorting = true;
			$scope.gridOptions.columnDefs = [{
					name : 'orgNo',
					displayName : '组织机构代码',
					enableColumMenu:true,
					enableCellEdit : true,
					enableCellEditOnFocus : false,
					width : '20%'
				}, {
					name : 'orgName',
					displayName : '组织机构',
					enableCellEditOnFocus : false,
					enableCellEdit : true,
					width : '30%'
				}, {
					name : 'createDate',
					displayName : '创建时间',
					//enableCellEditOnFocus : false,
					//enableCellEdit : true,
					type : 'date',
					cellFilter : 'date:"yyyy-MM-dd"',
					width : '20%'
				}
//				{
//					name : 'id',
//					displayName : '操作',
//					width : '23%',
//					cellTemplate:'<span ng-controller="SysOrgEditModalCtrl"> <script type="text/ng-template" id="sys_org_edit"><div ng-include="\'tpl/system/sys_org_edit.html\'"></div></script> <button class="btn btn-success" ng-click="open(lg,\'sys_org_edit\',row)">添加下级</button></span><button class="btn btn-success" ng-click="grid.appScope.Delete(row)">删除</button>' 
//				}
			];
			//数据获取
			$http.get('/ajax/org/select').success(function (data) {
				data[0].$$treeLevel = 0;
//				data[2].$$treeLevel = 2;
//				data[3].$$treeLevel = 3;
				$scope.gridOptions.data = data;
			});
			//焦点数据
			$scope.currentFocused = "";
			$scope.getCurrentFocus = function () {
				var rowCol = $scope.gridApi.cellNav.getFocusedCell();
				if (rowCol !== null) {
					$scope.currentFocused = 'Row Id:' + rowCol.row.entity.id + ' col:' + rowCol.col.colDef.name;
				}
			};
			

			
			//删除
			$scope.Delete = function(row) {
				var index = $scope.gridOptions.data.indexOf(row.entity);
				if(row.entity.id!=null && row.entity.id!=""){
					$http({
				        method  : 'delete',
				        url     : '/ajax/org/' + row.entity.id
				     }).success(function() {
				    	$scope.gridOptions.data.splice(index, 1);
				    	alert("删除成功！");
				     }).error(function(data,status,headers,config){
				      	alert("删除失败！");
				     });
				}
			};
			
			//删除第一项
			$scope.removeFirstRow = function(){
				
			}
			

			$scope.msg = "";
			$scope.gridOptions.onRegisterApi = function (gridApi) {
				$scope.gridApi = gridApi;
				//注册编辑后事件相应
				gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
					$scope.msg = 'edited row id:' + rowEntity.id + ' Column:' + colDef.name + ' newValue:' + newValue + ' oldValue:' + oldValue;
					var colName = colDef.name ;
					if(newValue!=oldValue){
						$http({
							method : 'put',
							url : '/ajax/org/'+rowEntity.id+"?"+colName+"="+newValue
						}).success(function (data) {
							console.log(data);
							alert("修改成功！");
							$state.go('app.table.org');
						}).error(function (data) {
							alert("修改出错");
						});
					}

					$scope.$apply();
				});
			};
		}
	]);






/**
 * 弹出框势实例化控制器
 */
app.controller('SysOrgEditModalInstanceCtrl', ['$scope','$http', '$modalInstance', 'items', function($scope,$http, $modalInstance,items) {
	$scope.items = items;
	//$scope.corg = $scope.corg;
  $scope.selected = {
    item: $scope.items[0]
  };
  
  //弹出框确定按钮触发处理方法
  $scope.ok = function (obj) {
	  $http({
			method : 'post',
			url : '/ajax/org',
			params : obj
		}).success(function (data) {
			console.log(data);
			alert("添加成功！");
			$state.go('app.table.user');
		}).error(function (data) {
			alert("出错");
		});

    $modalInstance.close($scope.selected.item);
  };

//弹出框取消按钮触发处理方法
  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
}])
; 


//模式框
app.controller('SysOrgEditModalCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
  $scope.items = ['item1', 'item2', 'item3'];
  $scope.open = function (size,tempUrl,data) {
  $scope.corg = data.entity;
    var modalInstance = $modal.open({
      templateUrl: tempUrl,
      controller: 'SysOrgEditModalInstanceCtrl',
      size: size,
      //scope:$scope,
      resolve: {
        items: function () {
          return $scope.items;
        }
      }
    });

    modalInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };
}])
; 
/**/