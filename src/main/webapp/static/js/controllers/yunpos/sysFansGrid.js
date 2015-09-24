app.controller('SysFansCtrl',  function($scope, $http, $state, $stateParams) {
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
        $scope.SysFansData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'SysFansData',
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
               {field: 'serialNo', displayName: '粉丝编号', width: 120,  pinnable: false,  sortable: false}, 
               {field: 'nickName', displayName: '昵称', enableCellEdit: false,width:150}, 
               {field: 'phone', displayName: '手机号码', enableCellEdit: false, width: 200},
               {field: 'prov', displayName: '所在地区', enableCellEdit: false, width: 200}, 
               {field: 'subscribeTime',displayName: '关注时间',enableCellEdit: false, type : 'date',cellFilter : 'date:"yyyy-MM-dd HH:mm:ss"',width: 200}, 
                
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate:'<div><span ng-controller="FansEditCtrl"> <script type="text/ng-template" id="fans_edit"><div ng-include="\'tpl/system/fans_edit.html\'"></div></script> <button class="btn btn-success" ng-click="open(lg,\'fans_edit\',row)">详情</button></div>' 
                	
//                	'<div><a ui-sref="app.table.bookdetail({bookId:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}">详情</a></div>'
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
                $http.get('/ajax/Fans/search').success(function (largeLoad) {
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
	        url     : '/ajax/Fans/' + obj.id,
	        params  : {"id":obj.id}
	     }).success(function() {
	    	 alert("删除成功！");
	    	 $scope.userData.splice(obj.user.rowIndex, 1);
	    	 $scope.setPagingData($scope.userData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("删除失败！");
	     });
	};
	
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
/*app.controller('UserAddCtrl', function($scope, $http, $state, $stateParams) {
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
});*/


/**
 * 弹出框势实例化控制器
 */
app.controller('FansEditInstanceCtrl', ['$scope','$http', '$modalInstance', 'items', function($scope,$http, $modalInstance,items) {
	$scope.items = items;
	//$scope.corg = $scope.corg;
  $scope.selected = {
    item: $scope.items[0]
  };
}])
;

/**
 * 这里是用户编辑
 * @type {[type]}
 */

	app.controller('FansEditCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
		  $scope.items = ['item1', 'item2', 'item3'];
		  $scope.open = function (size,tempUrl,data) {
		  $scope.fans = data.entity;
		    var modalInstance = $modal.open({
		      templateUrl: tempUrl,
		      controller: 'FansEditInstanceCtrl',
		      size: size,
		      scope:$scope,
		      
//		      fans.sex=1?男:女,

		      resolve: {
		        items: function () {
		          return $scope.items;
		        }
		      }
		    });
	
	
	
	
	
   
	
		  }}]);

