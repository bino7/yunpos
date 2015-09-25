app.controller('SysPayCtl',  function($scope, $http, $state, $stateParams) {
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
        $scope.orderListData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'orderListData',
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
                         {field: 'payName', displayName: '配置名称', width: 120,  pinnable: false,  sortable: false}, 
                         {field: 'payDes', displayName: '配置说明', enableCellEdit: false,width: 500}, 
                         {field: 'openStr', displayName: '是否启用', enableCellEdit: false,width: 120}, 
                         {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                         // cellTemplate: '<div><a ui-sref="app.table.alipaPayEdit({id:row.getProperty(col.field)})"> <button>配置</button> </a></div>'
         				 cellTemplate:'<a ng-if="row.getProperty(\'mark\')==\'alipay\'" ui-sref="app.table.alipaPayEdit({id:row.getProperty(col.field)})"><button>配置</button> </a>'
         					 +'<a ng-if="row.getProperty(\'mark\')==\'wechat\'" ui-sref="app.table.wechatPayEdit({id:row.getProperty(col.field)})"><button>配置</button></a>'
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
                data = $scope.orderData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/pay/search').success(function (largeLoad) {
                    $scope.orderData = largeLoad.rows;
                    $scope.setPagingData($scope.orderData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.orderData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.orderData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    
    
	$scope.Config = function(row) {
		var mark = row.entity.mark
		if(mark=='alipay'){
			$state.go('app.table.alipaPayEdit');
		}else if(mark=='wechat'){
			$state.go('app.table.wechatPayEdit',{'tag1':'yang1','tag2':'yang2','tag3':'yang3'});
			//$location.path("/pay/wechat?tag=yang")
		}
	};

    $scope.deleted = function(obj) {
	     $http({
	        method  : 'delete',
	        url     : '/ajax/pay/' + obj.id,
	        params  : {"id":obj.id}
	     }).success(function() {
	    	 alert("删除成功！");
	    	 $scope.orderData.splice(obj.order.rowIndex, 1);
	    	 $scope.setPagingData($scope.orderData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("删除失败！");
	     });
	};
	

	
	 $scope.search = function() {
		  var ft = $scope.filterText;
          var data = $scope.orderData.filter(function(item) {
                  return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
         });
         $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
	
	
	
    
});


/**
 * 这里是用户编辑
 * @type {[type]}
 */
app.controller('SysAlipayEditCtl', function($scope, $http, $state, $stateParams) {
    $scope.alipayInit = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/alipayconfig/'+ $stateParams.id
	    }).success(function(data) {
	            $scope.pay = data;
	        });
	};
	 $scope.saved = {};
     $scope.alipaySave = function(pay) {
    	 $scope.saved = angular.copy(pay);
	     $http({
	        method  : 'put',
	        url     : '/ajax/alipayconfig/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});


app.controller('SysWechatpayEditCtl', function($scope, $http, $state, $stateParams) {
    $scope.wechatInit = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/wechatconfig/'+ 1
	    }).success(function(data) {
	            $scope.wechat = data;
	        });
	};
	 $scope.saved = {};
     $scope.wechatSave = function(wechat) {
    	 $scope.saved = angular.copy(wechat);
	     $http({
	        method  : 'put',
	        url     : '/ajax/wechatconfig/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});





