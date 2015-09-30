app.controller('MerchantListCtrl',  function($scope, $http, $state, $stateParams) {
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
        $scope.merchantListData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'merchantListData',
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
               {field: 'companyName', displayName: '公司名称', enableCellEdit: false, width: 220},
               {field: 'industryType', displayName: '行业类型', enableCellEdit: false, width: 80}, 
               {field: 'createdBy',displayName: '添加人',enableCellEdit: false, width: 120}, 
               {field: 'createdAt',displayName: '添加时间',enableCellEdit: false, width: 140}, 
               {field: 'status', displayName: '状态', enableCellEdit: false, width: 60 }, 
               {field: 'auditStatus', displayName: '审核状态', enableCellEdit: false, width: 100 }, 
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate: '<div><a ui-sref="app.table.merchantDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"> <button>查看编辑</button> </a>     <button ng-click="deleted({id:row.getProperty(col.field) , merchant:row})">删除</button></div>'
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
                data = $scope.merchantData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/merchant/search').success(function (largeLoad) {
                    $scope.merchantData = largeLoad.rows;
                    $scope.setPagingData($scope.merchantData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.merchantData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.merchantData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);

    $scope.deleted = function(obj) {
	     $http({
	        method  : 'delete',
	        url     : '/ajax/merchant/' + obj.id,
	        params  : {"id":obj.id}
	     }).success(function() {
	    	 alert("删除成功！");
	    	 $scope.merchantData.splice(obj.merchant.rowIndex, 1);
	    	 $scope.setPagingData($scope.merchantData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("删除失败！");
	     });
	};
	
	 $scope.search = function() {
		  var ft = $scope.filterText;
          var data = $scope.merchantData.filter(function(item) {
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
app.controller('MerchantAddCtrl', function($scope, $http, $state, $stateParams) {
    console.log($stateParams);
    $scope.tags = [
                   { id:1,  name:'银联' ,  	  checked: false},
                   { id:2,  name:'支付宝',   checked: false },
                   { id:3,  name:'微信支付',  checked: false }] ;
    $scope.master = {};

	  $scope.add = function(merchant) {
		merchant.endTime = formatDateTime(merchant.endTime);
		 merchant.terminals  = "";
   	  	$scope.tags.filter(function(item) {
   		 if(item.checked = true){
   			 merchant.terminals  += item.id + ",";
   		 }
	    });
	    $scope.master = angular.copy(merchant);
	    $http({
	        method  : 'post',
	        url     : '/ajax/merchant',
	        params    : merchant  
	    }).success(function(data) {
	            console.log(data);
	            alert("添加成功！");
	            $state.go('app.table.merchant');
	    }).error(function(data){
	    	alert("出错");
	    });
	  };

	  $scope.reset = function() {
	    $scope.merchant = angular.copy($scope.master);
	  };

	  $scope.reset();
});


/**
 * 这里是用户编辑
 * @type {[type]}
 */
app.controller('MerchantDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/merchant/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.merchant = data;
	            var terminals = data.terminals;
	            var flg1 = false;
	            var flg2 = false;
	            var flg3 = false;
	            if(terminals != null && terminals != ''){
	            	if(terminals.indexOf('1') != -1){ flg1 = true ;}
	            	if(terminals.indexOf('2') != -1){ flg2 = true ;}
	            	if(terminals.indexOf('3') != -1){ flg3 = true ;}
	            }
	            $scope.tags = [
	                    { id:1,  name:'银联' ,  	  checked: flg1},
	                    { id:2,  name:'支付宝',   checked: flg2 },
	                    { id:3,  name:'微信支付',  checked: flg3 }]

	        });
	};
	$scope.saved = {};
    $scope.save = function(merchant) {
    	 merchant.endTime = formatDateTime(new Date(merchant.endTime));
    	 merchant.terminals  = "";
    	  $scope.tags.filter(function(item) {
    		 if(item.checked){
    			 merchant.terminals  += item.id + ",";
    		 }
	    });
    	 $scope.saved = angular.copy(merchant);
	     $http({
	        method  : 'put',
	        url     : '/ajax/merchant/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 $state.go('app.table.merchant');
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});



/**
 * 这里是用户编辑
 * @type {[type]}
 */
app.controller('MerchantInfoCtrl', function($scope, $http, $state, $stateParams) {
	$scope.addresses={};
	$scope.getAddress = function() {
		$http.get('data/address-1.json').success(function (address) {    
			$scope.addresses= address;
        }); 
	};
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/merchant/' + "1"  //+ $stateParams.id
	    }).success(function(data) {
	         $scope.merchant = data;
	         //日期格式
	         $scope.merchant.endTime = parseDateTime(new Date(data.endTime),"YYYY-MM-DD");
	         $scope.merchant.identityBt = parseDateTime(new Date(data.identityBt),"YYYY-MM-DD");
	         $scope.merchant.identityEt = parseDateTime(new Date(data.identityEt),"YYYY-MM-DD");
	         $scope.merchant.organizeBarCodeBt = parseDateTime(new Date(data.organizeBarCodeBt),"YYYY-MM-DD");
	         $scope.merchant.organizeBarCodeEt = parseDateTime(new Date(data.organizeBarCodeEt),"YYYY-MM-DD");
	         $scope.merchant.taxBarCodeBt = parseDateTime(new Date(data.taxBarCodeBt),"YYYY-MM-DD");
	         $scope.merchant.taxBarCodeEt = parseDateTime(new Date(data.taxBarCodeEt),"YYYY-MM-DD");
	         //设置注册地址的默认省、市、区  
	         for (provindex in $scope.addresses) {
	        	 var prov = $scope.addresses[provindex];
	        	 if (prov.code == data.prov) {//设置省份
	        		 $scope.merchant.prov = prov;
	        		 for (cityindex in prov.city) {
	        			 var city = prov.city[cityindex];  			 
	        			 if (city.code == data.city) {//设置市
	        				 $scope.merchant.city = city; 
	        				 for (areaindex in city.area) {
	        					 var area =  city.area[areaindex];
	        					 if (area.code == data.area) { //设置区
	        						 $scope.merchant.area = area; 
	        					 }
	        				 }				 
	        			 }
	        		 }	        		 
	        	 }	        	 
	         }
	         //设置开会行的默认省、市  
	         for (provindex in $scope.addresses) {
	        	 var prov = $scope.addresses[provindex];
	        	 if (prov.code == data.openBankProv) {//设置省份
	        		 $scope.merchant.openBankProv = prov;
	        		 for (cityindex in prov.city) {
	        			 var city = prov.city[cityindex];  			 
	        			 if (city.code == data.openBankCity) {//设置市
	        				 $scope.merchant.openBankCity = city; 	        							 
	        			 }
	        		 }	        		 
	        	 }	        	 
	         }          
	        });
	};
	
	$scope.saved = {};
    $scope.save = function(merchant) {
    	//日期格式化
    	 merchant.endTime = formatDateTime(new Date(merchant.endTime));
    	 merchant.identityBt = formatDateTime(new Date(merchant.identityBt));
    	 merchant.identityEt = formatDateTime(new Date(merchant.identityEt));
    	 merchant.organizeBarCodeBt = formatDateTime(new Date(merchant.organizeBarCodeBt));
    	 merchant.organizeBarCodeEt = formatDateTime(new Date(merchant.organizeBarCodeEt));
    	 merchant.taxBarCodeBt = formatDateTime(new Date(merchant.taxBarCodeBt));
    	 merchant.taxBarCodeEt = formatDateTime(new Date(merchant.taxBarCodeEt));
    	 //设置省、市、区
    	 merchant.prov=merchant.prov.code;
    	 merchant.city=merchant.city.code;
    	 merchant.area=merchant.area.code;
    	 merchant.openBankProv = merchant.openBankProv.code;
    	 merchant.openBankCity = merchant.openBankCity.code;    
    	 //图片转换
    	 merchant.operatePhoto = document.getElementById("operatePhoto").value;
    	 /*
    	 merchant.terminals  = "";
    	  $scope.tags.filter(function(item) {
    		 if(item.checked){
    			 merchant.terminals  += item.id + ",";
    		 }
	    });
	    */
    	 $scope.saved = angular.copy(merchant);
	     $http({
	        method  : 'put',
	        url     : '/ajax/merchant/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 //$state.go('app.table.merchant');
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});


