app.controller('OrderListCtrl',  function($scope, $http, $state, $stateParams) {
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
               {field: 'orderId', displayName: '订单号', width: 200,  pinnable: false,  sortable: false}, 
               {field: 'sysMerchantId', displayName: '商品名', enableCellEdit: false, width: 80}, 
               {field: 'productPrice', displayName: '订单金额', enableCellEdit: false, width: 80},
               {field: 'trueName', displayName: '联系人', enableCellEdit: false, width: 100}, 
               {field: 'tel',displayName: '联系电话',enableCellEdit: false, width: 100}, 
               {field: 'payStatus', displayName: '交易状态', enableCellEdit: false, width: 80 }, 
               {field: 'createdAt', displayName: '下单时间', enableCellEdit: false, width: 140 }, 
               {field: 'status', displayName: '来源类型', enableCellEdit: false, width: 120 }, 
               {field: 'openId', displayName: '支付流水号', enableCellEdit: false, width: 120 }, 
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate:'<div><span ng-controller="OrderDetailCtrl"> <script type="text/ng-template" id="order_detail"><div ng-include="\'tpl/system/sys_order_detail.html\'"></div></script> <button class="btn btn-success" ng-click="open(lg,\'order_detail\',row)">详情</button></div>'  
                	
                	/*'<div><a ui-sref="app.table.orderDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"> <button>查看</button> </a></div>'*/
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
                $http.get('/ajax/order/search').success(function (largeLoad) {
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

    $scope.deleted = function(obj) {
	     $http({
	        method  : 'delete',
	        url     : '/ajax/order/' + obj.id,
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
		  var gt = $scope.option_zero;
		  var select_zero = document.getElementById("select_zero").value;
		  var select_one = document.getElementById("select_one").value;
		  var select_two = document.getElementById("select_two").value;
		  
		  
          var data = $scope.orderData.filter(function(item) {
        	/*  alert(item.payStatus);*/
        	  
        	  if(JSON.stringify(item.payStatus).indexOf(select_zero) !=-1  && JSON.stringify(item).toLowerCase().indexOf(ft) != -1
        		/*&& SON.stringify(item.orgName).indexOf(select_one) == null && SON.stringify(item.info).indexOf(select_two) == null*/){
        		  return item ;}
        		  
        	/*  }else if(JSON.stringify(item.payStatus).indexOf(select_zero) !=-1  && JSON.stringify(item).toLowerCase().indexOf(ft) != -1){
        		  return item ;
        		  
        	  }else if(JSON.stringify(item.payStatus).indexOf(select_zero) !=-1  && JSON.stringify(item).toLowerCase().indexOf(ft) != -1
              		&& SON.stringify(item.orgName).indexOf(select_one) == null){
        		  return item;
        		  
        	  }else{
        		  return item;
        		 
        	  }*/
        		 /* return JSON.stringify(item).toLowerCase().indexOf(document.getElementById("select_zero").value) != -1;*/
             /* return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;*/
         });
   
         $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
    
});


/**
 * 这里是用户编辑
 * @type {[type]}
 */
app.controller('OrderDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/order/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.order = data;
	           // alert(data.id);
	        });
	};
	
	/**
	 * 弹出框势实例化控制器
	 */
	app.controller('order_detail_ctrl', ['$scope','$http', '$modalInstance', 'items', function($scope,$http, $modalInstance,items) {
		$scope.items = items;
		//$scope.corg = $scope.corg;
	  $scope.selected = {
	    item: $scope.items[0]
	  };
	}])
	;
	
	app.controller('OrderDetailCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
		  $scope.items = ['item1', 'item2', 'item3'];
		  $scope.open = function (size,tempUrl,data) {
		  $scope.order = data.entity;
		    var modalInstance = $modal.open({
		      templateUrl: tempUrl,
		      controller: 'order_detail_ctrl',
		      size: size,
		      scope:$scope,
		      resolve: {
		        items: function () {
		          return $scope.items;
		        }
		      }
		    });
		  }}]);
	
//	option选项

/*	$scope.getOptionList = function () {
        return $http.get("/ajax/order/search").success(function (response) {
            $scope.typemodel = response.data;
            $scope.typemodel.unshift({id:null,typeName:'全部'});
        }).error(function (response) {
            $log.debug("请求超时或网络故障!获得列表失败!")
        });
    };
    */
    
    
    
    $http.get('/ajax/order/search').success(function (largeLoad) {
        $scope.orderData = largeLoad.rows;
        $scope.setPagingData($scope.orderData,1,10);
    });
	
	
	
	
	 $scope.saved = {};
     $scope.save = function(order) {
    	 $scope.saved = angular.copy(order);
	     $http({
	        method  : 'put',
	        url     : '/ajax/order/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});

