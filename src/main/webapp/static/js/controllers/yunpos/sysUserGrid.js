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
          //  enablePinning: true,
            columnDefs: [
               {field: 'userName', displayName: '用户名', width: 120,  pinnable: false,  sortable: false}, 
               {field: 'nickname', displayName: '昵称', enableCellEdit: false}, 
               {field: 'fullname', displayName: '联系人', enableCellEdit: false, width: 220},
               {field: 'role', displayName: '用户角色', enableCellEdit: false, width: 120}, 
               {field: 'createdBy',displayName: '添加人',enableCellEdit: false, width: 120}, 
               {field: 'statusStr', displayName: '状态', enableCellEdit: false, width: 80, 
               cellTemplate: '<div class="ngCellText ng-scope ngCellElement col3 colt3">'
          	        +'<span ng-cell-text ng-if="row.getProperty(\'status\')==1" >停用</span>'
          	        +'<span ng-cell-text ng-if="row.getProperty(\'status\')==0" >启用</span>'
          			+'</div>'},
          			
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate: '<div><a ui-sref="app.table.userDetail({id:row.getProperty(col.field)})" ' 
                	+'id="{{row.getProperty(col.field)}}"> <button>查看编辑</button> </a> '     
                	 +'<button ng-click="deleted({id:row.getProperty(col.field) , user:row})">删除</button> '
//                	<button ng-if="row.getProperty(\'status\')==0" ng-click="saveStatus({id:row.getProperty(col.field),user:row})">启用</button> <button ng-if="row.getProperty(\'status\')==1" ng-click="saveStatus({id:row.getProperty(col.field),user:row})">停用</button> </div>'
        	        +'<button ng-if="row.getProperty(\'status\')==0" ng-click="saveStatus({id:row.getProperty(col.field),user:row,status:1})">停用</button> '
        	        +'<button ng-if="row.getProperty(\'status\')==1" ng-click="saveStatus({id:row.getProperty(col.field),user:row,status:0})">启用</button> '
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
                data = $scope.userData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/user/search').success(function (largeLoad) {
                    $scope.userData = largeLoad.rows;
                    $scope.setPagingData($scope.userData,page,pageSize);
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
	        url     : '/ajax/user/' + obj.id,
	        params  : {"id":obj.id}    //deleted({id:row.getProperty(col.field) , user:row})
	     }).success(function() {
	    	 alert("删除成功！");
	    	 $scope.userData.splice(obj.user.rowIndex, 1);
	    	 $scope.setPagingData($scope.userData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("删除失败！");
	     });
	};
	

		
//	 $scope.saved_st = {};
     $scope.saveStatus = function(user) {
    	 user.user.entity.status = user.status;
//    	 $scope.saved_st = angular.copy(user.user.entity);
    	 if (user.status == 0){
 	   		user.status= 1;
 	   		user.statusStr = "停用";
 	   	 }else{
 	   		 user.status= 0;
 	   		 user.statusStr ="启用";
 	   	 }
	     $http({
	        method  : 'put',
	        url     : '/ajax/user/' + user.id,
	        params  : user.user.entity
//	     }).success(function(data) {
//	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
  /*  $scope.delete_st = {} ;
    $scope.deleted = function(obj) {
    	 $scope.delete_st = angular.copy(obj);
	     $http({
	        method  : 'put',
	        url     : '/ajax/user/' + obj.id,
	        params  : $scope.delete_st    //deleted({id:row.getProperty(col.field) , user:row})
	     }).success(function(data) {
	    	 alert("删除成功！");
	    	 $scope.userData.splice(obj.user.rowIndex, 0);
	    	 $scope.setPagingData($scope.userData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("删除失败！");
	     });
	};
  	*/

	
	
	 $scope.search = function() {
		  var ft = $scope.filterText;
          var data = $scope.userData.filter(function(item) {
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

/**
 * 这里是支付密码修改
 * @type {[type]}
 */
app.controller('PayPasswordCtrl', function($scope, $http, $state, $stateParams){
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
	
	$scope.save = function() {
		var o = {};
		
		o.old_pwd = $scope.old_pwd;
		o.new_pwd = $scope.new_pwd;
		o.verify_pwd = $scope.verify_pwd;
		
		if(o.old_pwd.length == '') {
			alert('请输入旧的支付密码');
			return ;
		}
		if(o.new_pwd.length < 6) {
			alert('新的支付密码不能为空且至少6位');
			return ;
		}
		if(o.new_pwd != o.verify_pwd) {
			alert('确认密码不匹配');
			return ;
		}
		
		$scope.user.payPassword = new_pwd;
		$scope.user.oldPayPassword = old_pwd;
		
		$http({
			mothod : 'put',
			url : '/ajax/user/updatePwd/'+$scope.user.id,
			params : $scope.user 
		}).success(function(data){
			alert('保存成功！');
		}).error(function(data){
			alert('保存失败');
		});
	}
});

