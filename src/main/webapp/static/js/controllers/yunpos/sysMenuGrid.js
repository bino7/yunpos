app.controller('SysMenuController', ['$scope', '$http', '$interval','$state', 'uiGridTreeViewConstants', 'uiGridConstants', function ($scope, $http, $interval,$state, uiGridTreeViewConstants, uiGridConstants) {
		//添加新增更新事件

		$scope.gridOptions = {};
		$scope.gridOptions.data = 'myData';
		$scope.gridOptions.enableFiltering = false;
		$scope.gridOptions.showGridFooter = false;
		$scope.gridOptions.enableSorting = false;
		
		$scope.gridOptions.rowIdentity = function(row) {
			    return row.id;
			  };
	    $scope.gridOptions.getRowIdentity = function(row) {
			    return row.id;
			  };
		
		$scope.gridOptions.columnDefs = [{
				name : 'menuNo',
				displayName : '菜单编号',
				enableColumMenu : true,
				enableCellEdit : false,
				enableCellEditOnFocus : false,
				width : '20%'
			}, {
				name : 'menuName',
				displayName : '菜单名',
				enableCellEditOnFocus : false,
				enableCellEdit : true,
				width : '20%'
			}, {
				name : 'menuUrl',
				displayName : '链接',
				enableCellEditOnFocus : false,
				enableCellEdit : true,
				width : '25%'
			}, {
				name : 'id',
				displayName : '操作',
				enableCellEdit : false,
				enableCellEditOnFocus : false,
				width : '28%',
				//cellTemplate : '<span ng-controller="SysOrgEditModalCtrl"> <script type="text/ng-template" id="sys_org_edit"><div ng-include="\'tpl/system/sys_org_edit.html\'"></div></script> <button class="btn btn-success" ng-click="open(lg,\'sys_org_edit\',row)">添加下级</button></span> <button class="btn btn-success" ng-click="deleted({id:row.getProperty(col.field) , id})">删除</button>'
				cellTemplate : '<span ng-controller="SysMenuEditModalCtrl">'
					//		  +'<script type="text/ng-template" id="sys_menu_edit"><div ng-include="\'tpl/system/sys_menu_edit.html\'"></div></script>'
//							  +'<button ng-if="row.entity.isLeaf==0"  ng-click="open(lg,\'sys_menu_edit\',row)">添加下级</button>'
//							  +'<button ng-if="row.entity.id==1"  ng-click="open(lg,\'sys_menu_edit\',row)">添加同级</button>'
							  +'<button ng-if="row.entity.isLeaf==1"  ng-click="grid.appScope.deleted(row)">删除</button>'
							  +'</span>'
			}
		];
		
		
		
		//数据获取
		$http.get('/ajax/menu/select').success(function (data) {
			for(var i=0;i<data.length;i++){
				if(data[i].isLeaf==0 || data[i].menuParentNo==''){
					data[i].$$treeLevel = 0;
				}
			}
			$scope.myData = data;
		});
		
		$scope.add = function(){
			$state.go('app.table.menuedit');
		};
		
		//菜单保存
		$scope.menuSave = function(menu){
			$http({
				method : 'post',
				url : '/ajax/menu',
				params : menu
			}).success(function (data) {
				alert("添加成功！");
				$state.go('app.table.menu');
			}).error(function (data) {
				alert("出错");
			});
		}
		 
		//刷新属性
		$scope.refreshData = function(){
	    $scope.myData = [];
	    $http.get('/ajax/menu/select')
	        .success(function(data) {
	          data.forEach(function(row){
	        	  if(row.isLeaf==0 || row.menuParentNo==''){
	        		  row.$$treeLevel = 0;
					}
	            $scope.myData.push(row);
	          });
	        })
	        .error(function() {
	        });
	  };
		
		
		//焦点数据
		$scope.currentFocused = "";
		$scope.getCurrentFocus = function () {
			var rowCol = $scope.gridApi.cellNav.getFocusedCell();
			if (rowCol !== null) {
				$scope.currentFocused = 'Row Id:' + rowCol.row.entity.id + ' col:' + rowCol.col.colDef.name;
			}
		};

		//删除
		$scope.deleted = function (row) {
			var index = $scope.gridOptions.data.indexOf(row.entity);
			if(row.entity.id!=null && row.entity.id!=""){
				$http({
					method : 'delete',
					url : '/ajax/menu/' + row.entity.id
				}).success(function () {
					//$scope.gridOptions.data.splice(index, 1);
					$scope.myData.splice(index, 1);
					alert("删除成功！");
				}).error(function (data, status, headers, config) {
					alert("删除失败！");
				});
			}
		};

		//删除第一项
		$scope.removeFirstRow = function () {}

		$scope.msg = "";
		$scope.gridOptions.onRegisterApi = function (gridApi) {
			$scope.gridApi = gridApi;
			//注册编辑后事件相应
			gridApi.edit.on.afterCellEdit($scope, function (rowEntity, colDef, newValue, oldValue) {
				$scope.msg = 'edited row id:' + rowEntity.id + ' Column:' + colDef.name + ' newValue:' + newValue + ' oldValue:' + oldValue;
				var colName = colDef.name;
				if (newValue != oldValue) {
					$http({
						method : 'put',
						url : '/ajax/menu/' + rowEntity.id + "?" + colName + "=" + newValue
					}).success(function (data) {
						console.log(data);
						alert("修改成功！");
						//$state.go('app.table.menu');
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
 * 菜单编辑控制器
 */
app.controller('SysMenuEditCtrl' ,['$scope', '$http','$state', function ($scope, $http,$state) {
	//获取下拉列表数据
	$scope.menus={};
	$scope.init = function() {
		$http.get('/ajax/menu/select/levelOne').success(function (data) {    
			$scope.menus= data;
        })
	};
	
	//新增
	$scope.add = function(data){
		
		var menu = {
				menuParentNo:data.parent?data.parent.menuNo:"",
				menuName:data.menuName,
				menuUrl:data.menuUrl
		};
		
		$http({
			method : 'post',
			url : '/ajax/menu',
			params : menu
		}).success(function (data) {
			alert("添加成功！");
			$state.go('app.table.menu');
		}).error(function (data) {
			alert("出错");
		});
	}
	

}]);



	
	
/**
 * 弹出框势实例化控制器
 */

app.controller('SysMenuEditModalInstanceCtrl', ['$scope', '$http', '$state','$location','$modalInstance', 'items', function ($scope, $http,$state,$location, $modalInstance, items) {
			$scope.items = items;
			//$scope.corg = $scope.corg;
			$scope.selected = {
				item : $scope.items[0]
			};
			//弹出框确定按钮触发处理方法
			$scope.ok = function (obj) {
				$http({
					method : 'post',
					url : '/ajax/menu',
					params : obj
				}).success(function (data) {
					alert("添加成功！");
				}).error(function (data) {
					alert("出错");
				});
				$modalInstance.close($scope.selected.item);
			};

			//弹出框取消按钮触发处理方法
			$scope.cancel = function () {
				$modalInstance.dismiss('cancel');
			};
		}
	]);

//模式框
app.controller('SysMenuEditModalCtrl', ['$scope', '$modal', '$log', function ($scope, $modal, $log) {
			$scope.items = ['item1', 'item2', 'item3'];
			$scope.open = function (size, tempUrl, data) {
				$scope.corg = data.entity;
				var modalInstance = $modal.open({
						templateUrl : tempUrl,
						controller : 'SysMenuEditModalInstanceCtrl',
						size : size,
						//scope:$scope,
						resolve : {
							items : function () {
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
		}
	]);
/**/
