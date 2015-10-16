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
                cellTemplate: '<div><a ui-sref="app.table.merchantReview({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"> <button>审核</button> </a>'
                + '<button ng-if="row.getProperty(\'status\')==0" ng-click="updateStatus({id:row.getProperty(col.field) , merchant:row, status:1})">启用</button>'
                + '<button ng-if="row.getProperty(\'status\')==1" ng-click="updateStatus({id:row.getProperty(col.field) , merchant:row, status:0})">停用</button></div>'
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
	
	$scope.updateStatus = function(obj) {
		var merchant={};
		merchant.id = obj.id;
		merchant.status = obj.status;
	     $http({
	        method  : 'put',
	        url     : '/ajax/merchant/updateStatus/' + obj.id,
	        params  :  merchant 
	     }).success(function() {
	    	 alert("更新成功！");
	    //	 $scope.agentmerchantData[obj.agentmerchant.rowIndex] = obj.agentmerchant.entity;
	    //	 $scope.setPagingData($scope.agentmerchantData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("更新失败！");
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
	         //图片显示
	     	document.getElementById("operatePhotoImg").src = $scope.merchant.operatePhoto;
	     	document.getElementById("businessLicenseImg").src = $scope.merchant.businessLicense;
	     	document.getElementById("identityCardOffImg").src = $scope.merchant.identityCardOff;
	     	document.getElementById("identityCardOnImg").src = $scope.merchant.identityCardOn;
	     	document.getElementById("organizeBarCodeImg").src = $scope.merchant.organizeBarCodePic;
	     	document.getElementById("taxBarCodeImg").src = $scope.merchant.taxBarCodePic;
	     	document.getElementById("bankCardOnImg").src = $scope.merchant.bankCardOn;
	     	document.getElementById("bankCardOffImg").src = $scope.merchant.bankCardOff;
	     	document.getElementById("openBankLicenseImg").src = $scope.merchant.openBankLicense;
	     	document.getElementById("signAccountImg").src = $scope.merchant.signAccount;


	         
	         
	         
	        });
	};
	
	$scope.saved = {};
    $scope.save = function(merchant) {
    	$scope.saved = angular.copy(merchant);
    	//日期格式化
    	$scope.saved.endTime = formatDateTime(new Date(merchant.endTime));
    	$scope.saved.identityBt = formatDateTime(new Date(merchant.identityBt));
    	$scope.saved.identityEt = formatDateTime(new Date(merchant.identityEt));
    	$scope.saved.organizeBarCodeBt = formatDateTime(new Date(merchant.organizeBarCodeBt));
    	$scope.saved.organizeBarCodeEt = formatDateTime(new Date(merchant.organizeBarCodeEt));
    	$scope.saved.taxBarCodeBt = formatDateTime(new Date(merchant.taxBarCodeBt));
    	$scope.saved.taxBarCodeEt = formatDateTime(new Date(merchant.taxBarCodeEt));
    	 //设置省、市、区
    	$scope.saved.prov=merchant.prov.code;
    	$scope.saved.city=merchant.city.code;
    	$scope.saved.area=merchant.area.code;
    	$scope.saved.openBankProv = merchant.openBankProv.code;
    	$scope.saved.openBankCity = merchant.openBankCity.code;    
    	 //图片转换
    	
    	var operatePhoto = document.getElementById("operatePhoto").value;
    	if (operatePhoto!="") {
    		$scope.saved.operatePhoto = operatePhoto;
    	}
    	var businessLicense = document.getElementById("businessLicense").value;
    	if (businessLicense!="") {
    		$scope.saved.businessLicense = businessLicense;
    	}
    	var identityCardOff = document.getElementById("identityCardOff").value;
    	if (identityCardOff!="") {
    		$scope.saved.identityCardOff = identityCardOff;
    	}
    	var identityCardOn = document.getElementById("identityCardOn").value;
    	if (identityCardOn!="") {
    		$scope.saved.identityCardOn = identityCardOn;
    	}
    	var organizeBarCodePic = document.getElementById("organizeBarCodePic").value;
    	if (organizeBarCodePic!="") {
    		$scope.saved.organizeBarCodePic = organizeBarCodePic;
    	}
    	var taxBarCodePic = document.getElementById("taxBarCodePic").value;
    	if (taxBarCodePic!="") {
    		$scope.saved.taxBarCodePic = taxBarCodePic;
    	}
    	var bankCardOn = document.getElementById("bankCardOn").value;
    	if (bankCardOn!="") {
    		$scope.saved.bankCardOn = bankCardOn;
    	}
    	var bankCardOff = document.getElementById("bankCardOff").value;
    	if (bankCardOff!="") {
    		$scope.saved.bankCardOff = bankCardOff;
    	}
    	var openBankLicense = document.getElementById("openBankLicense").value;
    	if (openBankLicense!="") {
    		$scope.saved.openBankLicense = openBankLicense;
    	}
    	var signAccount = document.getElementById("signAccount").value;
    	if (signAccount!="") {
    		$scope.saved.signAccount = signAccount;
    	}
    	
    	 /*
    	 merchant.terminals  = "";
    	  $scope.tags.filter(function(item) {
    		 if(item.checked){
    			 merchant.terminals  += item.id + ",";
    		 }
	    });
	    */
    	 
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

/**
 * 商户审核
 * @type {[type]}
 */
app.controller('MerchantReviewCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/merchant/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.merchant = data;
	        });
	};
	/**
	  * 审核商户
	  * @param {Object} merchant
	  */	
		$scope.reviewd = {};	
		$scope.review = function(merchant) {
			var auditStatus = merchant.auditStatus;
			if (auditStatus < 2 ) {
				alert("请对商户信息进行审核");
			} else {
				$scope.reviewd.id = merchant.id;
				$scope.reviewd.auditStatus = merchant.auditStatus;
				$scope.reviewd.auditMemo = merchant.auditMemo;			
				$http({
					method: 'put',
					url: '/ajax/merchantreview/' + $scope.reviewd.id,
					params: $scope.reviewd
				}).success(function(data) {
					if (data) {
						alert("审核成功");
					    $state.go('app.table.merchant');	
					}else {
						alert("审核失败！");
					}				
				}).error(function(data, status, headers, config) {
					alert("审核失败！");
				});
				
			}
		};
		
		$scope.back = function() {
			$state.go('app.table.merchant');	
		};
});

