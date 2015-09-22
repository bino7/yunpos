app.controller('SysMenuController', ['$scope', '$http', '$interval','$state', 'uiGridTreeViewConstants', 'uiGridConstants', function ($scope, $http, $interval,$state, uiGridTreeViewConstants, uiGridConstants) {
			$scope.gridOptions = {};
			$scope.gridOptions.enableSorting = false;
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
					cellTemplate : '<span ng-controller="SysMenuEditModalCtrl"> <script type="text/ng-template" id="sys_menu_edit"><div ng-include="\'tpl/system/sys_menu_edit.html\'"></div></script> <button class="btn btn-success" ng-click="open(lg,\'sys_menu_edit\',row)">添加下级</button></span><span ng-controller="SysMenuController"> <button class="btn btn-success" ng-click="deleted({id:row.getProperty(col.field) , menu:row})">删除</button></span>'
				}
			];
			
			//数据获取
			$http.get('/ajax/menu/select').success(function (data) {
				data[0].$$treeLevel = 0;
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
			$scope.deleted = function (obj) {
				//$scope.gridOptions.data.splice(1, 1);
				if (obj.menu.entity.id != null && obj.menu.entity.id != "") {
					$http({
						method : 'delete',
						url : '/ajax/menu/' + obj.menu.entity.id
					}).success(function () {
						//$state.go('app.table.menu');
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
					console.log(data);
					alert("添加成功！");
					//$location.path('/menu');
					$state.go('app.table.menu');
					//$scope.$state.go('app.table.menu');
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
