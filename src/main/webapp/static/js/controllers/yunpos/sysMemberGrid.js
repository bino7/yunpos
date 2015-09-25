//商户会员查询
app.controller('SysMemberCtl',  function($scope, $http, $state, $stateParams) {
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
        $scope.sysMemberListData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'sysMemberListData',
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
               {field: 'nickName', displayName: '会员昵称', enableCellEdit: false,  sortable: false, width: 100}, 
               {field: 'tel', displayName: '手机号码', width: 120,  pinnable: false}, 
               {field: 'address', displayName: '所在地区', enableCellEdit: false, sortable: false, width: 220},
               {field: 'balance', displayName: '余额', enableCellEdit: false, sortable: false, width: 80},
               {field: 'score', displayName: '可用积分', enableCellEdit: false, sortable: false, width: 80},
               {field: 'createdAt', displayName: '注册时间', enableCellEdit: false, sortable: false, width: 150},        
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate: '<div>'
                 +	'<a ui-sref="app.table.sysMemberScoreDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"><button>积分明细</button></a>' 
                 +	'<a ui-sref="app.table.sysPayrecordDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"><button>充值记录</button></a>'
                 +	'<a ui-sref="app.table.sysUserecordDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"><button>消费记录</button></a>'              	
                 +	'</div>'
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
                data = $scope.sysMemberData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/member/search').success(function (largeLoad) {
                    $scope.sysMemberData = largeLoad.rows;
                    $scope.setPagingData($scope.sysMemberData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.sysMemberData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.sysMemberData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
	
	 $scope.search = function() {
		  var ft = $scope.filterText;
          var data = $scope.sysMemberData.filter(function(item) {
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
app.controller('SysMemberAddCtrl', function($scope, $http, $state, $stateParams) {
    console.log($stateParams);
    
    $scope.master = {};

	  $scope.add = function(sysMember) {
	    $scope.master = angular.copy(sysMember);
	    $http({
	        method  : 'post',
	        url     : '/ajax/sysMember',
	        params    : sysMember  
	    }).success(function(data) {
	            console.log(data);
	            alert("添加成功！");
	            $state.go('app.table.sysMember');
	    }).error(function(data){
	    	alert("出错");
	    });
	  };

	  $scope.reset = function() {
	    $scope.sysMember = angular.copy($scope.master);
	  };

	  $scope.reset();
});


/**
 * 这里是用户编辑
 * @type {[type]}
 */
app.controller('SysMemberDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/member/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.sysMember = data;
	           // alert(data.id);
	        });
	};
	 $scope.saved = {};
     $scope.save = function(sysMember) {
    	 $scope.saved = angular.copy(sysMember);
	     $http({
	        method  : 'put',
	        url     : '/ajax/member/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});

