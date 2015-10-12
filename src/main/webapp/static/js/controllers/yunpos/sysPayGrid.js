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
                         {field: 'payName', displayName: '配置名称', width: 200,  pinnable: false,  sortable: false}, 
                         {field: 'payDes', displayName: '配置说明', enableCellEdit: false,width: 500}, 
                         {field: 'openStr', displayName: '是否启用', enableCellEdit: false,width: 120}, 
                         {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                         // cellTemplate: '<div><a ui-sref="app.table.alipaPayEdit({id:row.getProperty(col.field)})"> <button>配置</button> </a></div>'
         				 cellTemplate:'<a ng-if="row.getProperty(\'mark\')==\'alipay\'" ui-sref="app.table.alipaPayEdit({id:row.getProperty(col.field)})"><button>配置</button> </a>'
         					 +'<a ng-if="row.getProperty(\'mark\')==\'wechat\'" ui-sref="app.table.wechatPayEdit({id:row.getProperty(col.field)})"><button>配置</button></a>'
         					+'<a ng-if="row.getProperty(\'mark\')==\'alipay_bar\'" ui-sref="app.table.alipayBar({id:row.getProperty(col.field)})"><button>配置</button></a><a ng-if="row.getProperty(\'mark\')==\'alipay_bar\'" ui-sref="app.table.alipayBarTest()"><button>测试</button></a>'
         					+'<a ng-if="row.getProperty(\'mark\')==\'alipay_scan\'" ui-sref="app.table.alipayScan({id:row.getProperty(col.field)})"><button>配置</button></a><a ng-if="row.getProperty(\'mark\')==\'alipay_scan\'" ui-sref="app.table.alipayScanTest()"><button>测试</button></a>'
         					+'<a ng-if="row.getProperty(\'mark\')==\'alipay_wap\'" ui-sref="app.table.alipayWap({id:row.getProperty(col.field)})"><button>配置</button></a><a ng-if="row.getProperty(\'mark\')==\'alipay_wap\'" ui-sref="app.table.alipayWapTest()"><button>测试</button></a>'
         					+'<a ng-if="row.getProperty(\'mark\')==\'wechat_bar\'" ui-sref="app.table.wechatBar({id:row.getProperty(col.field)})"><button>配置</button></a><a ng-if="row.getProperty(\'mark\')==\'wechat_bar\'" ui-sref="app.table.wechatBarTest()"><button>测试</button></a>'
         					+'<a ng-if="row.getProperty(\'mark\')==\'wechat_scan\'" ui-sref="app.table.wechatScan({id:row.getProperty(col.field)})"><button>配置</button></a><a ng-if="row.getProperty(\'mark\')==\'wechat_scan\'" ui-sref="app.table.wechatScanTest()"><button>测试</button></a>'
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
	        url     : '/ajax/alipayconfig/'+ 1
	    }).success(function(data) {
	            $scope.pay = data;
	        });
	};
	
	 $scope.saved = {};
     $scope.alipaySave = function(pay) {
    	 $scope.saved = angular.copy(pay);
	     $http({
	        method  : 'put',
	        url     : '/ajax/alipayconfig/'+1,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	};
     
   
});


app.controller('SysAlipayTestCtl', function($scope, $http, $state, $stateParams) {
		//条码支付测试
	   $scope.bardata = {};
	   $scope.alipayBarTest = function(bardata) {
	    	 $scope.bardata = angular.copy(bardata);
		     $http({
		        method  : 'put',
		        url     : '/ajax/alipay/bar/test',
		        params  : $scope.bardata
		     }).success(function(data) {
		    	 if(data.result_code=='SUCCESS'){
		    		 alert("支付成功");
		    	 }else{
		    		 alert("支付结果="+data.result_code+" 错误代码="+data.err_code +" 错误说明="+data.result_msg);
		    	 }
		     }).error(function(data,status,headers,config){
		      	alert("系统错误！");
		     });
		};
		
		//查询
	   $scope.barquerydata = {};
	   $scope.alipayBarQueryTest = function(alipaybarquerydata) {
	    	 $scope.alipaybarquerydata = angular.copy(alipaybarquerydata);
		     $http({
		        method  : 'post',
		        url     : '/ajax/alipay/barQuery/test',
		        params  : $scope.alipaybarquerydata
		     }).success(function(data) {
		    	 if(data.result_code=='SUCCESS'){
		    		 alert("查询成功");
		    	 }else{
		    		 alert(data.result_code+"|"+data.err_code +"|"+data.result_msg);
		    	 }
		     }).error(function(data,status,headers,config){
		      	alert("系统错误！");
		     });
		};
		
		//扫码支付退款测试
	    $scope.alipayscanrecanceldata = {};
	    $scope.alipayscanrecanceldata = function(alipayscanrecanceldata) {
	   	 $scope.alipayscanrefunddata = angular.copy(alipayscanrecanceldata);
		     $http({
		        method  : 'post',
		        url     : '/ajax/alipay/scanCancel/test',
		        params  : $scope.alipayscanrefunddata
		     }).success(function(data) {
		    	 if(data.result_code=='SUCCESS'){
		    		 alert("撤单成功")
		    	 }else{
		    		 alert("|"+data.result_code+"|"+data.err_code +"|"+data.result_msg);
		    	 }
		     }).error(function(data,status,headers,config){
		      	alert("系统错误！");
		     });
		};

		
		//扫码支付退款测试
	    $scope.alipaybarrefunddata = {};
	    $scope.alipayScanRefundTest = function(alipaybarrefunddata) {
	   	 $scope.alipaybarrefunddata = angular.copy(alipaybarrefunddata);
		     $http({
		        method  : 'post',
		        url     : '/ajax/alipay/scanRefund/test',
		        params  : $scope.alipaybarrefunddata
		     }).success(function(data) {
		    	 if(data.result_code=='SUCCESS'){
		    		 alert("退款成功")
		    	 }else{
		    		 alert("|"+data.result_code+"|"+data.err_code +"|"+data.result_msg);
		    	 }
		     }).error(function(data,status,headers,config){
		      	alert("系统错误！");
		     });
		};

	     
		//扫码支付测试
	     $scope.scandata = {};
	     $scope.alipayScanTest = function(scandata) {
	    	 $scope.scandata = angular.copy(scandata);
		     $http({
		        method  : 'post',
		        url     : '/ajax/alipay/scan/test',
		        params  : $scope.scandata
		     }).success(function(data) {
		    	 if(data.result_code=='SUCCESS'){
		    		 document.getElementById("aaa").src=data.lists.small_pic_url;
		    		 document.getElementById("imgs").style.display="";
		    		 //alert(data.lists.small_pic_url);
		    	 }else{
		    		 alert("支付结果="+data.result_code+" 错误代码="+data.err_code +" 错误说明="+data.result_msg);
		    	 }
		     }).error(function(data,status,headers,config){
		      	alert("系统错误！");
		     });
		};
	     
	     $scope.wapdata = {};
	     $scope.alipayWapTest = function(scandata) {
	    	 document.getElementById("imgs").style.display="";
	    	 $scope.wapdata = angular.copy(wapdata);
		     $http({
		        method  : 'put',
		        url     : '/ajax/alipay/wap/test',
		        params  : $scope.saved
		     }).success(function(data) {
		    	 alert("测试成功！");
		     }).error(function(data,status,headers,config){
		      	alert("测试失败！");
		     });
		};
	     
		//获取订单号
	     $scope.getOrderNo = function(bardata) {
	    	 $http({
			        method  : 'get',
			        url     : '/ajax/getOrderNo'
			     }).success(function(data) {
			    	 bardata.user_order_no=data.orderNo;
			     }).error(function(data,status,headers,config){
			      	alert("测试失败！");
			     });
	    	 
		};
});


app.controller('SysWechatTestCtl', function($scope, $http, $state, $stateParams) {
	//条码支付测试
   $scope.bardata = {};
   $scope.wechatBarTest = function(bardata) {
    	 $scope.bardata = angular.copy(bardata);
	     $http({
	        method  : 'post',
	        url     : '/ajax/wechat/bar/test',
	        params  : $scope.bardata
	     }).success(function(data) {
	    	 if(data.result_code=='SUCCESS'){
	    		 alert("支付成功");
	    	 }else{
	    		 alert(data.result_code+"|"+data.err_code +"|"+data.result_msg);
	    	 }
	     }).error(function(data,status,headers,config){
	      	alert("系统错误！");
	     });
	};
	
	//查询
   $scope.barquerydata = {};
   $scope.wechatBarQueryTest = function(barquerydata) {
    	 $scope.bardata = angular.copy(barquerydata);
	     $http({
	        method  : 'post',
	        url     : '/ajax/wechat/bar/test',
	        params  : $scope.barquerydata
	     }).success(function(data) {
	    	 if(data.result_code=='SUCCESS'){
	    		 alert("支付成功");
	    	 }else{
	    		 alert(data.result_code+"|"+data.err_code +"|"+data.result_msg);
	    	 }
	     }).error(function(data,status,headers,config){
	      	alert("系统错误！");
	     });
	};
	
	//扫码支付退款测试
    $scope.alipayscanrefunddata = {};
    $scope.wechatScanRefundTest = function(alipayscanrefunddata) {
   	 $scope.alipayscanrefunddata = angular.copy(alipayscanrefunddata);
	     $http({
	        method  : 'post',
	        url     : '/ajax/wechat/scanRefund/test',
	        params  : $scope.alipayscanrefunddata
	     }).success(function(data) {
	    	 if(data.result_code=='SUCCESS'){
	    		 alert("退款成功")
	    	 }else{
	    		 alert("|"+data.result_code+"|"+data.err_code +"|"+data.result_msg);
	    	 }
	     }).error(function(data,status,headers,config){
	      	alert("系统错误！");
	     });
	};

     
	//扫码支付测试
     $scope.scandata = {};
     $scope.wechatScanTest = function(scandata) {
    	 $scope.scandata = angular.copy(scandata);
	     $http({
	        method  : 'post',
	        url     : '/ajax/wechat/scan/test',
	        params  : $scope.scandata
	     }).success(function(data) {
	    	 if(data.result_code=='SUCCESS'){
	    		 $('#qrcode').qrcode(data.lists.code_url);
	    	 }else{
	    		 alert("|"+data.result_code+"|"+data.err_code +"|"+data.result_msg);
	    	 }
	     }).error(function(data,status,headers,config){
	      	alert("系统错误！");
	     });
	};
	
	   //获取订单号
    $scope.getOrderNo = function(bardata) {
   	 $http({
		        method  : 'get',
		        url     : '/ajax/getOrderNo'
		     }).success(function(data) {
		    	 bardata.user_order_no=data.orderNo;
		     }).error(function(data,status,headers,config){
		      	alert("测试失败！");
		     });
   	 
	};
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





