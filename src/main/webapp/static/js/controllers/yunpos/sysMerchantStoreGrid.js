app
		.controller(
				'MerchantStoreListCtrl',
				function($scope, $http, $state, $stateParams) {
					$scope.filterOptions = {
						filterText : "",
						useExternalFilter : true
					};
					$scope.totalServerItems = 0;
					$scope.pagingOptions = {
						pageSizes : [ 10, 20, 50 ],
						pageSize : 10,
						currentPage : 1
					};
					$scope.setPagingData = function(data, page, pageSize) {
						var pagedData = data.slice((page - 1) * pageSize, page
								* pageSize);
						$scope.storeListData = pagedData;
						$scope.totalServerItems = data.length;
						if (!$scope.$$phase) {
							$scope.$apply();
						}
					};
					$scope.gridOptions = {
						data : 'storeListData',
						rowTemplate : '<div style="height: 100%"><div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell ">'
								+ '<div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }"> </div>'
								+ '<div ng-cell></div>' + '</div></div>',
						multiSelect : false,
						enableCellSelection : true,
						enableRowSelection : false,
						enableCellEdit : true,
						// enablePinning: true,
						columnDefs : [
								{
									field : 'storeName',
									displayName : '门店名称',
									width : 320,
									enableCellEdit : false
								},
								{
									field : 'nickname',
									displayName : '昵称',
									width : 120,
									enableCellEdit : false
								},
								{
									field : 'address',
									displayName : '地址',
									width : 300,
									enableCellEdit : false
								},
								{
									field : 'createdAt',
									displayName : '添加时间',
									width : 140,
									pinnable : false,
									sortable : false
								},
								{
									field : 'status',
									displayName : '状态',
									width : 120,
									enableCellEdit : false,
									sortable : false,
									pinnable : false,
									cellTemplate : '<div class="ngCellText ng-scope ngCellElement col3 colt3">'
											+ '<span ng-cell-text ng-if="row.getProperty(\'status\')==1" >启用</span>'
											+ '<span ng-cell-text ng-if="row.getProperty(\'status\')==0" >停用</span>'
											+ '</div>'
								},
								{
									field : 'apprStatus',
									displayName : '审核状态',
									width : 120,
									enableCellEdit : false,
									sortable : false,
									pinnable : false,
									cellTemplate : '<div class="ngCellText ng-scope ngCellElement col3 colt3">'
											+ '<span ng-if="row.getProperty(\'apprStatus\')==1" >审核中</span>'
											+ '<span ng-if="row.getProperty(\'apprStatus\')==2" >审核通过</span>'
											+ '<span ng-if="row.getProperty(\'apprStatus\')==3" >驳回</span>'
											+ '<span ng-if="row.getProperty(\'apprStatus\')==4" >回退</span>'
											+ '</div>'
								},
								{
									field : 'id',
									displayName : '操作',
									enableCellEdit : false,
									sortable : false,
									pinnable : false,
									cellTemplate : '<div ><a ui-sref="app.table.merchantStoreDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"> <button>查看编辑</button> </a> '
											+ '<button ng-if="row.getProperty(\'status\')==0" ng-click="saveStatus({id:row.getProperty(col.field)},{store:row})">启用</button>'
											+ '<button ng-if="row.getProperty(\'status\')==1" ng-click="saveStatus({id:row.getProperty(col.field)},{store:row})">停用</button>'
											+ '</div>'
								} ],
						enablePaging : true,
						showFooter : true,
						totalServerItems : 'totalServerItems',
						pagingOptions : $scope.pagingOptions,
						filterOptions : $scope.filterOptions
					};
					$scope.getPagedDataAsync = function(pageSize, page,
							searchText) {
						setTimeout(function() {
							var data;
							if (searchText) {
								var ft = searchText.toLowerCase();
								data = $scope.storeData.filter(function(item) {
									return JSON.stringify(item).toLowerCase()
											.indexOf(ft) != -1;
								});
								$scope.setPagingData(data, page, pageSize);
							} else {
								$http.get('/ajax/store/search').success(
										function(largeLoad) {
											$scope.storeData = largeLoad.rows;
											$scope.setPagingData(
													$scope.storeData, page,
													pageSize);
										});
							}
						}, 100);
					};

					$scope.getPagedDataAsync($scope.pagingOptions.pageSize,
							$scope.pagingOptions.currentPage);

					$scope.$watch('pagingOptions', function(newVal, oldVal) {
						if (newVal !== oldVal) {
							// $scope.setPagingData($scope.storeData,
							// $scope.pagingOptions.currentPage,
							// $scope.pagingOptions.pageSize);
							$scope.getPagedDataAsync(
									$scope.pagingOptions.pageSize,
									$scope.pagingOptions.currentPage,
									$scope.filterText);
						}
					}, true);
					$scope.$watch('filterOptions', function(newVal, oldVal) {
						if (newVal !== oldVal) {
							// $scope.setPagingData($scope.storeData,
							// $scope.pagingOptions.currentPage,
							// $scope.pagingOptions.pageSize);
							$scope.getPagedDataAsync(
									$scope.pagingOptions.pageSize,
									$scope.pagingOptions.currentPage,
									$scope.filterText);
						}
					}, true);

					$scope.deleted = function(obj) {
						$http({
							method : 'delete',
							url : '/ajax/store/' + obj.id,
							params : {
								"id" : obj.id
							}
						}).success(
								function() {
									alert("删除成功！");
									$scope.storeData.splice(obj.store.rowIndex,
											1);
									$scope.setPagingData($scope.storeData,
											$scope.pagingOptions.currentPage,
											$scope.pagingOptions.pageSize);
								}).error(
								function(data, status, headers, config) {
									alert("删除失败！");
								});
					};

					$scope.saveStatus = function(id, store) {
						$scope.saved = {};
						$scope.saved = angular.copy(store.store.entity);
						if ($scope.saved.status == '0') {
							$scope.saved.status = '1';
						} else {
							$scope.saved.status = '0';
						}
						$http({
							method : 'put',
							url : '/ajax/store/' + $scope.saved.id,
							params : $scope.saved
						})
								.success(
										function(data) {
											// alert("保存成功！");
											// $state.go('app.table.store');
											$scope.storeData[store.store.rowIndex] = $scope.saved;
											$scope
													.setPagingData(
															$scope.storeData,
															$scope.pagingOptions.currentPage,
															$scope.pagingOptions.pageSize);
										})
								.error(function(data, status, headers, config) {
									alert("保存失败！");
								});
					}

					$scope.search = function() {
						var ft = $scope.filterText;
						var data = $scope.storeData.filter(function(item) {
							return JSON.stringify(item).toLowerCase().indexOf(
									ft) != -1;
						});
						$scope.setPagingData(data,
								$scope.pagingOptions.currentPage,
								$scope.pagingOptions.pageSize);
					};

				});

/**
 * 这里是用户 新增 模块
 * 
 * @type {[type]}
 */
app.controller('MerchantStoreAddCtrl', function($scope, $http, $state,
		$stateParams) {

	$scope.check = function(name) {
		$http({
			method : 'get',
			url : '/ajax/user/exist/name/' + name,
			params : name
		}).success(function(data) {
			if(data){
				alert("用户已经存在！");
			}else{
				alert("用户名可以使用！");
			}
		}).error(function(data, status, headers, config) {
			alert("用户已经存在！");
		});
	}
	
	$scope.master = {};

	$scope.add = function(store) {
		$scope.master = angular.copy(store);
		$scope.master.prov=store.prov.code;
		$scope.master.city=store.city.code;
		$scope.master.area=store.area.code;
		$scope.master.address=store.prov.name+' '+store.city.name+' '+store.area.name+' '+store.address;
		$http({
			method : 'post',
			url : '/ajax/store',
			params : $scope.master
		}).success(function(data) {
			console.log(data);
			alert("添加成功！");
			$state.go('app.table.merchantStore');
		}).error(function(data) {
			alert("出错");
		});
	};

	$scope.reset = function() {
		$scope.store = angular.copy($scope.master);
	};

	$scope.reset();
	$scope.save = function(store) {
		$scope.saved = angular.copy(store);
		$http({
			method : 'put',
			url : '/ajax/store/' + $scope.saved.id,
			params : $scope.saved
		}).success(function(data) {
			alert("保存成功！");
			$state.go('app.table.merchantStore');
		}).error(function(data, status, headers, config) {
			alert("保存失败！");
		});
	}
	$scope.cancel = function() {
		$state.go('app.table.merchantStore');
	}
	$scope.addresses={};
	$scope.getAddress = function() {
		$http.get('data/address-1.json').success(function (address) {    
			$scope.addresses= address;
        }); 
	}
});

/**
 * 这里是门店
 * 
 * @type {[type]}
 */
app.controller('MerchantStoreDetailCtrl', function($scope, $http, $state,
		$stateParams) {
	$scope.processForm = function() {
		$http({
			method : 'get',
			url : '/ajax/store/' + $stateParams.id
		}).success(function(data) {
			// console.log(data);
			$scope.store = data;
			// alert(data.id);
		});
	};
	$scope.saved = {};
	$scope.save = function(store) {
		$scope.saved = angular.copy(store);
		$http({
			method : 'put',
			url : '/ajax/store/' + $scope.saved.id,
			params : $scope.saved
		}).success(function(data) {
			alert("保存成功！");
			$state.go('app.table.merchantStore');
		}).error(function(data, status, headers, config) {
			alert("保存失败！");
		});
	}
	$scope.cancel = function() {
		$state.go('app.table.merchantStore');
	}
});
