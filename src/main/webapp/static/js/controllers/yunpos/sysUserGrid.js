app.controller('UserListCtrl',  function($scope, $http, $state, $stateParams) {
    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    }; 
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [10, 20, 50],
        pageSize: 10,
        currentPage: 1
    };  
    $scope.setPagingData = function(data, page, pageSize){  
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.userListData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'userListData',
            rowTemplate: '<div style="height: 100%"><div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell ">' +
                '<div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }"> </div>' +
                '<div ng-cell></div>' +
                '</div></div>',
            multiSelect: false,
            enableCellSelection: true,
            enableRowSelection: false,
            enableCellEdit: true,
            enablePinning: true,
            columnDefs: [{
                field: 'userName',
                displayName: '用户名',
                width: 120,
                pinnable: false,
                sortable: false
            }, {
                field: 'nickname',
                displayName: '昵称',
                enableCellEdit: true
            }, {
                field: 'fullname',
                displayName: '联系人',
                enableCellEdit: true,
                width: 220
            }, {
                field: 'role',
                displayName: '用户角色',
                enableCellEdit: true,
                width: 120
            }, {
                field: 'createdBy',
                displayName: '添加人',
                enableCellEdit: true,
                width: 120
            }, {
                field: 'status',
                displayName: '状态',
                enableCellEdit: true,
                width: 60
            }, {
                field: 'id',
                displayName: '操作',
                enableCellEdit: false,
                sortable: false,
                pinnable: false,
                cellTemplate: '<div><a ui-sref="app.table.userDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}">查看编辑</a>      <button class="btn btn-success col-offset-6" ng-click="deleted({id:row.getProperty(col.field) , user:row})">删除</button></div>'
            }],
            enablePaging: true,
            showFooter: true,        
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            filterOptions: $scope.filterOptions
        };
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                $http.get('js/controllers/largeLoad.json').success(function (largeLoad) {    
                    data = largeLoad.filter(function(item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data,page,pageSize);
                });            
            } else {
                $http.get('/ajax/user/search').success(function (largeLoad) {
                    $scope.setPagingData(largeLoad.rows,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);

    $scope.deleted = function(obj) {
	     $http({
	        method  : 'delete',
	        url     : '/ajax/user/' + obj.id,
	        params  : {"id":obj.id}
	     }).success(function() {
	    	 alert("删除成功！");
	    	 $scope.userListData.splice(obj.user.rowIndex, 1);
	    	 $scope.setPagingData($scope.userListData, 1, 10);
	     }).error(function(data,status,headers,config){
	      	alert("删除失败！");
	     });
	};
	
	 $scope.search = function() {
		  var ft = $scope.filterText;
          var data = $scope.userListData.filter(function(item) {
                  return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
         });
         $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
    
});



/**
 * 这里是用户 新增 模块
 * 
 * @type {[type]}
 */
app.controller('UserAddCtrl', function($scope, $http, $state, $stateParams) {
    console.log($stateParams);
    
    $scope.master = {};

	  $scope.add = function(user) {
	    $scope.master = angular.copy(user);
	    $http({
	        method  : 'post',
	        url     : '/ajax/user',
	        params    : user  
	    }).success(function(data) {
	            console.log(data);
	            alert("添加成功！");
	            $state.go('app.table.user');
	    }).error(function(data){
	    	alert("出错");
	    });
	  };

	  $scope.reset = function() {
	    $scope.user = angular.copy($scope.master);
	  };

	  $scope.reset();
});


/**
 * 这里是用户编辑
 * @type {[type]}
 */
app.controller('UserDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/user/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.user = data;
	           // alert(data.id);
	        });
	};
	 $scope.saved = {};
     $scope.save = function(user) {
    	 $scope.saved = angular.copy(user);
	     $http({
	        method  : 'put',
	        url     : '/ajax/user/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});

