app.controller('TransactionListCtrl',  function($scope, $http, $state, $stateParams) {
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
        $scope.transactionListData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'transactionListData',
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
               {field: 'transNum', displayName: '交易流水号', width: 180,  pinnable: false,  sortable: false}, 
               {field: 'transPrice', displayName: '金额', enableCellEdit: false, width: 100},
               {field: 'info', displayName: '备注', enableCellEdit: false, width: 140}, 
               {field: 'channel',displayName: '交易渠道',enableCellEdit: false, width: 80}, 
               {field: 'transType', displayName: '交易类型', enableCellEdit: false, width: 80 }, 
               {field: 'merchantName', displayName: '商户', enableCellEdit: false, width: 120 }, 
               {field: 'orderId', displayName: '原支付订单号', enableCellEdit: false, width: 120 }, 
               {field: 'user_order_no', displayName: '支付流水号', enableCellEdit: false, width: 120 }, 
               {field: 'transTime', displayName: '支付时间', cellFilter : 'date:"yyyy-MM-dd HH:mm:ss"',enableCellEdit: false, width: 120 }, 
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate: '<div><span ng-controller="TransactionDetailCtrl"> <script type="text/ng-template" id="transaction_detail"><div ng-include="\'tpl/system/sys_transaction_detail.html\'"></div></script> <button class="btn btn-success" ng-click="open(lg,\'transaction_detail\',row)">详情</button></div>'  
                	/*'<div><a ui-sref="app.table.transactionDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"> <button>查看</button> </a></div>'*/
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
                data = $scope.transactionData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/transaction/search').success(function (largeLoad) {
                    $scope.transactionData = largeLoad.rows;
                    $scope.setPagingData($scope.transactionData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.transactionData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.transactionData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);

    $scope.deleted = function(obj) {
	     $http({
	        method  : 'delete',
	        url     : '/ajax/transaction/' + obj.id,
	        params  : {"id":obj.id}
	     }).success(function() {
	    	 alert("删除成功！");
	    	 $scope.transactionData.splice(obj.transaction.rowIndex, 1);
	    	 $scope.setPagingData($scope.transactionData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("删除失败！");
	     });
	};
	
	 $scope.search = function() {
		  var ft = $scope.filterText;
		  var select_zero = document.getElementById("select_zero").value;
		  var select_one = document.getElementById("select_one").value;
		  var select_two = document.getElementById("select_two").value;
		  
          var data = $scope.transactionData.filter(function(item) {
        	  if(JSON.stringify(item.channel).indexOf(select_zero) !=-1 && JSON.stringify(item.transType).indexOf(select_one) != -1){
        		  return item;
        		  }
        	  if(JSON.stringify(item.channel).indexOf(select_zero) !=-1 ){
        			return item;  
        		  }
        	  if(JSON.stringify(item.transType).indexOf(select_one) != -1){
        		  return item;  
        	  }
                if(JSON.stringify(item).toLowerCase().indexOf(ft) != -1){
                	return item;
                }
        			  
        			  /*return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;*/
         });
         $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
    
});


/**
 * 这里是用户编辑
 * @type {[type]}
 */
app.controller('TransactionDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/transaction/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.transaction = data;
	           // alert(data.id);
	        });
	};
	
	
	/**
	 * 弹出框势实例化控制器
	 */
	app.controller('transaction_detail_ctrl', ['$scope','$http', '$modalInstance', 'items', function($scope,$http, $modalInstance,items) {
		$scope.items = items;
		//$scope.corg = $scope.corg;
	  $scope.selected = {
	    item: $scope.items[0]
	  };
	}])
	;
	
	app.controller('TransactionDetailCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
		  $scope.items = ['item1', 'item2', 'item3'];
		  $scope.open = function (size,tempUrl,data) {
		  $scope.transaction = data.entity;
		    var modalInstance = $modal.open({
		      templateUrl: tempUrl,
		      controller: 'transaction_detail_ctrl',
		      size: size,
		      scope:$scope,
		      resolve: {
		        items: function () {
		          return $scope.items;
		        }
		      }
		    });
		  }}]);
	
	
	 $scope.saved = {};
     $scope.save = function(transaction) {
    	 $scope.saved = angular.copy(transaction);
	     $http({
	        method  : 'put',
	        url     : '/ajax/transaction/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});

