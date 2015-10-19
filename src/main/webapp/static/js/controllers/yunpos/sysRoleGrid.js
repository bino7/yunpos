app.controller('roleControllerCtrl',  function($scope, $http, $state, $stateParams) {
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
        $scope.roleData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'roleData',
            rowTemplate: '<div style="height: 100%"><div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell ">' +
                '<div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }"> </div>' +
                '<div ng-cell></div>' +
                '</div></div>',
            multiSelect: false,
            enableCellSelection: true,
            enableRowSelection: false,
            enableCellEdit: true,
          //  enablePinning: true,
            columnDefs: [
               {field: 'roleName', displayName: '角色名', width: 200, enableCellEdit: false, pinnable: false,  sortable: false}, 
               {field: 'createDate', displayName: '添加时间', enableCellEdit: false,width: 300}, 
               {field: 'modifyDate', displayName: '最后修改时间', enableCellEdit: false, width: 300},
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate: '<div><a ui-sref="app.table.roleDetail({id:row.getProperty(col.field)})" ' 
                	+'id="{{row.getProperty(col.field)}}"> <button>查看编辑</button> </a> '  
                	+'<a ui-sref="app.table.roleAuthoritySetting({id:row.getProperty(col.field)})" '
                	+'id="{{row.getProperty(col.field)}}"> <button>权限设置</button> </a> '
                	 +'<button ng-click="deleted({id:row.getProperty(col.field) , role:row})">删除</button> '
        			+'</div>'
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
                data = $scope.roleData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/role/search').success(function (largeLoad) {
                    $scope.roleData = largeLoad.rows;
                    $scope.setPagingData($scope.roleData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.userData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.userData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);

    $scope.deleted = function(obj) {
	     $http({
	        method  : 'delete',
	        url     : '/ajax/role/' + obj.id,
	        params  : {"id":obj.id}    //deleted({id:row.getProperty(col.field) , user:row})
	     }).success(function() {
	    	 alert("删除成功！");
	    	 $scope.roleData.splice(obj.role.rowIndex, 1);
	    	 $scope.setPagingData($scope.roleData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("删除失败！");
	     });
	};
	 $scope.search = function() {
		  var ft = $scope.filterText;
          var data = $scope.roleData.filter(function(item) {
                  return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
         });
         $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
    
});



/**
 * 这里是角色 新增 模块
 * 
 * @type {[type]}
 */
app.controller('roleAddCtrl', function($scope, $http, $state, $stateParams) {
    console.log($stateParams);
    $scope.master = {};
	  $scope.add = function(role) {
	    $scope.master = angular.copy(role);
	    $http({
	        method  : 'post',
	        url     : '/ajax/role',
	        params    : role
	    }).success(function(data) {
	            console.log(data);
	            alert("添加成功！");
	            $state.go('app.table.role');
	    }).error(function(data){
	    	alert("出错");
	    });
	  };

	  $scope.reset = function() {
	    $scope.role = angular.copy($scope.master);
	  };

	  $scope.reset();
});

/**
 * 这里是用户编辑
 * @type {[type]}
 */
app.controller('roleDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/role/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.role = data;
	           // alert(data.id);
	        });
	};
	 $scope.saved = {};
     $scope.save = function(role) {
    	 $scope.saved = angular.copy(role);
	     $http({
	        method  : 'put',
	        url     : '/ajax/role/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 $state.go('app.table.role');
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});

/**
 * 这里是角色权限设置
 * @type {[type]}
 */
app.controller('authoritySettingCtrl', function($scope, $http, $state, $stateParams) {
    $scope.setting = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/role/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.role = data;
	           // alert(data.id);
	        });
	};
	 $scope.saved = {};
     $scope.save = function(role) {
    	 $scope.saved = angular.copy(role);
	     $http({
	        method  : 'put',
	        url     : '/ajax/role/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 $state.go('app.table.role');
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});
/**
 * 这里是角色资源配置
 * @type {[type]}
 */
app.controller('resourceSettingCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/role/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.role = data;
	           // alert(data.id);
	        });
	};
	 $scope.saved = {};
     $scope.save = function(role) {
    	 $scope.saved = angular.copy(role);
	     $http({
	        method  : 'put',
	        url     : '/ajax/role/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 $state.go('app.table.role');
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});