var time_left;
app.controller('TransactionListCtrl',  function($scope, $http, $state, $stateParams) {
    $scope.filterOptions = {
        filterText: "",
        transaction_time_one:"",
        transaction_time_two:"",
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
               {field: 'totalPrice', displayName: '金额', enableCellEdit: false, width: 100},
               {field: 'info', displayName: '备注', enableCellEdit: false, width: 140}, 
               {field: 'channelStr',displayName: '交易渠道',enableCellEdit: false, width: 80}, 
               {field: 'transTypeStr', displayName: '交易类型', enableCellEdit: false, width: 80 }, 
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
    
    
    
    $scope.getPagedDataAsync = function (pageSize, page, searchText,json_channel,transType,transaction_time_one,transaction_time_two) {
        setTimeout(function () {
        	var select_channel = document.getElementById("select_zero").value;
       	 var select_transType = document.getElementById("select_one").value;
       	 var filter_text = document.getElementById("filter_text").value;
            var data;
            if ( searchText || json_channel || transType || transaction_time_one || transaction_time_two) {
                data = $scope.transactionData.filter(function(item) {
                	 var date1 = new Date(item.transTime.replace(/-/g , '/'));
                	 var checkDate2 = true;
               	  var checkDate3 = true;
               	  if(transaction_time_one != null && date1 < transaction_time_one) {
               		  checkDate2 = false;
               	  }
               	  if(transaction_time_two != null && date1 > transaction_time_two) {
               		  checkDate3 = false;
               	  }
               	  
                	 if(JSON.stringify(item.channelStr).indexOf(select_channel) !=-1 && JSON.stringify(item).toLowerCase().indexOf(searchText) != -1
                			 && JSON.stringify(item.transTypeStr).indexOf(select_transType)!=-1 && checkDate2 && checkDate3){
               		  return item;
                	 }
                    
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/transaction/search').success(function (largeLoad) {
                    $scope.transactionData = largeLoad.rows;
                    $scope.setPagingData($scope.transactionData,page,pageSize);
                });
            }
        }, 0.1);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.transactionData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
        	 var select_channel = document.getElementById("select_zero").value;
        	 var select_transType = document.getElementById("select_one").value;
        	 var filter_text = document.getElementById("filter_text").value;
        	
        	 /*var ft = searchText.toLowerCase();*/
        	 /*var ft = $scope.filterText;*/
        	 var transaction_time_lef =  $scope.transaction_time_one;
        	 var transaction_tion_righ = $scope.transaction_time_one;
        	 var date2 ;
       	     var date3 ;
	       	 var transTimeOne = document.getElementById("transaction_time_left").value ;
			 var transTimeTwo = document.getElementById("transaction_time_right").value ;
			if(transTimeOne != null && transTimeOne != ''){
			  transTimeOne  = transTimeOne +  " 00:00:00";
			  date2 = new Date(transTimeOne.replace(/-/g , '/'))
		  }
		  if(transTimeTwo != null && transTimeTwo != ''){
			  transTimeTwo  =  transTimeTwo + " 23:59:59";
			  date3 = new Date(transTimeTwo.replace(/-/g , '/'));
		  }
            
        	
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, filter_text, select_channel, select_transType, date2, date3);
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
		  if (typeof(ft) == "undefined") {
			   ft=null;
			}   
    	  var date2 ;
    	  var date3 ;
    	  
		  var transTimeOne = document.getElementById("transaction_time_left").value ;
		  var transTimeTwo = document.getElementById("transaction_time_right").value ;
		  
		  if(transTimeOne != null && transTimeOne != ''){
			  transTimeOne  = transTimeOne +  " 00:00:00";
			  date2 = new Date(transTimeOne.replace(/-/g , '/'))
		  }
		  if(transTimeTwo != null && transTimeTwo != ''){
			  transTimeTwo  =  transTimeTwo + " 23:59:59";
			  date3 = new Date(transTimeTwo.replace(/-/g , '/'));
		  }
			 
		   
		  var select_channel = document.getElementById("select_zero").value;
		  var select_transType = document.getElementById("select_one").value;
		 
	
          var data = $scope.transactionData.filter(function(item) {
        	  var date1 = new Date(item.transTime.replace(/-/g , '/'));

        	  var json_channel = JSON.stringify(item.channelStr).indexOf(select_channel);
        	  var json_transType = JSON.stringify(item.transTypeStr).indexOf(select_transType);
        	  var json_item = JSON.stringify(item).toLowerCase().indexOf(ft);
//        	  var date4 = date2 ==null &&  date3 ==null;
        	  var checkDate2 = true;
        	  var checkDate3 = true;
        	  if(date2 != null && date1 < date2) {
        		  checkDate2 = false;
        	  }
        	  if(date3 != null && date1 > date3) {
        		  checkDate3 = false;
        	  }
        	  
        	  if(json_channel !=-1 && json_transType != -1 && json_item != -1 && checkDate2 && checkDate3 ){
        		  return item;
        	  }
        	  /*var json_date1 = JSON.stringify(item.transTime).indexOf(transTimeOne);*/
//        	  if(date4==false){
//        		  if(json_channel !=-1 && json_transType != -1 && json_item != -1 )
//				  /*&& date1 >= date2 && date1 <= date3*/ {
//    		       return item;
//        	  }else{
//        	 if(json_channel !=-1 && json_transType != -1 && json_item != -1 && date1 >= date2  &&  date1 <= date3)
//				  /*&& date1 >= date2 && date1 <= date3*/ {
//    		       return item;
//        	  }
//        	  }
//        	  }
        	  
        	  
        		 /* if(date4 == "flase"){
        			 
        			  if(json_channel !=-1 && json_transType != -1 && json_item != -1 && date1 >= date2 && date1 <= date3 ){
                		  return item;
                	  }
        		  }
        		  if(date4 == "true"){
        			  
        			  if(json_channel !=-1 && json_transType != -1 && json_item != -1 && date4  && date1 >= date2 && date1 <= date3 ){
                		  return item;
                	  }
        		  }
        	  */
        	/*  if(date1 >= date2 && date1 <= date3 ){
        		  return item;
        	  }*/
        	  
        	  
        	
        	 
         }); 
         $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
    
});


/**
 * 这里是用户编辑
 * @type {[type]}
 */
//app.controller('TransactionDetailCtrl', function($scope, $http, $state, $stateParams) {
//    $scope.processForm = function() {
//	    $http({
//	        method  : 'get',
//	        url     : '/ajax/transaction/'+ $stateParams.id
//	    }).success(function(data) {
//	           // console.log(data);
//	            $scope.transaction = data;
//	           // alert(data.id);
//	        });
//	};
//	
	
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
	
	//app.controller('TransactionDetailCtrl', function($scope, $http, $state, $stateParams) {
	app.controller('TransactionDetailCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log , $stateParams) {
		 $scope.processForm = function() {
			    $http({
			        method  : 'get',
			        url     : '/ajax/transaction/'+ $stateParams.id
			    }).success(function(data) {
			           // console.log(data);
			            $scope.transaction = data;
			           // alert(data.id);
			        });
		 }
		
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
	
	
//	 $scope.saved = {};
//     $scope.save = function(transaction) {
//    	 $scope.saved = angular.copy(transaction);
//	     $http({
//	        method  : 'put',
//	        url     : '/ajax/transaction/' + $scope.saved.id,
//	        params  : $scope.saved
//	     }).success(function(data) {
//	    	 alert("保存成功！");
//	     }).error(function(data,status,headers,config){
//	      	alert("保存失败！");
//	     });
//	}
//});
//
	/**
	 * 日期时间控件
	 */
	app.controller('DatepickerDemoCtrl', ['$scope', function($scope) {
		
		
	    $scope.today = function() {
	      $scope.dt = new Date();
	    };
	    $scope.today();
	    $scope.clear = function () {
	      $scope.dt = null;
	    };

	    // Disable weekend selection
	    $scope.disabled = function(date, mode) {
	      return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	    };

	    $scope.toggleMin = function() {
	      $scope.minDate = $scope.minDate ? null : new Date();
	    };
	    $scope.toggleMin();

	    $scope.open = function($event) {
	      $event.preventDefault();
	      $event.stopPropagation();

	      $scope.opened = true;
	    };

	    $scope.dateOptions = {
	      formatYear: 'yy',
	      startingDay: 1,
	      class: 'datepicker'
	    };

	    $scope.initDate = new Date('2016-15-20');
	    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	    $scope.format = $scope.formats[0];
	  }])
	  ; 
	  app.controller('TimepickerDemoCtrl', ['$scope', function($scope) {
	    $scope.mytime = new Date();

	    $scope.hstep = 1;
	    $scope.mstep = 15;

	    $scope.options = {
	      hstep: [1, 2, 3],
	      mstep: [1, 5, 10, 15, 25, 30]
	    };

	    $scope.ismeridian = true;
	    $scope.toggleMode = function() {
	      $scope.ismeridian = ! $scope.ismeridian;
	    };

	    $scope.update = function() {
	      var d = new Date();
	      d.setHours( 14 );
	      d.setMinutes( 0 );
	      $scope.mytime = d;
	    };

	    $scope.changed = function () {
	      //console.log('Time changed to: ' + $scope.mytime);
	    };

	    $scope.clear = function() {
	      $scope.mytime = null;
	    };
	  }]);