app.controller('SysCardCouponListCtrl',  function($scope, $http, $state, $stateParams) {
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
        $scope.sysCardCouponListData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'sysCardCouponListData',
            rowCoupon: '<div style="height: 100%"><div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell ">' +
                '<div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }"> </div>' +
                '<div ng-cell></div>' +
                '</div></div>',
            multiSelect: false,
            enableCellSelection: true,
            enableRowSelection: false,
            enableCellEdit: true,
          //  enablePinning: true,
            columnDefs: [
               {field: 'title', displayName: '卡券名称', width: 120,  pinnable: false,  sortable: false}, 
               {field: 'typeDescription', displayName: '卡券类型', enableCellEdit: false , width: 120}, 
               {field: 'validityDate' , displayName: '有效期', enableCellEdit: false, width: 180},
               {field: 'createdBy',displayName: '投放平台',enableCellEdit: false, width: 120}, 
               {field: 'createdAt',displayName: '投放状态',enableCellEdit: false, width: 140}, 
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellCoupon: '<div><a ui-sref="app.table.sysCardCouponDetail({id:row.getProperty(col.field)})" '
                	+ 'id="{{row.getProperty(col.field)}}"> <button>详情{{row.status}}</button> </a> ' 
                	+ '<button ng-click="updateStatus({id:row.getProperty(col.field) , sysCardCoupon:row, status:1})">投放</button>'
                	+ '<button ng-click="deleted({id:row.getProperty(col.field) , sysCardCoupon:row})">删除</button></div>'
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
                data = $scope.sysCardCouponData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/sysCardCoupon/search').success(function (largeLoad) {
                    $scope.sysCardCouponData = largeLoad.rows;
                    $scope.setPagingData($scope.sysCardCouponData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.sysCardCouponData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.sysCardCouponData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);

    $scope.updateStatus = function(obj) {
    	obj.sysCardCoupon.entity.status = obj.status;
	     $http({
	        method  : 'put',
	        url     : '/ajax/sysCardCoupon/updateStatus/' + obj.id,
	        params  :  obj.sysCardCoupon.entity 
	     }).success(function() {
	    	 alert("更新成功！");
	    //	 $scope.sysCardCouponData[obj.sysCardCoupon.rowIndex] = obj.sysCardCoupon.entity;
	    //	 $scope.setPagingData($scope.sysCardCouponData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("更新失败！");
	     });
	};
	
	  $scope.deleted = function(obj ,row) {
		     $http({
		        method  : 'delete',
		        url     : '/ajax/sysCardCoupon/' + obj.id,
		        params  : {"id":obj.id}
		     }).success(function() {
		    	 alert("删除成功！");
		    	 $scope.sysCardCouponListData.splice(obj.sysCardCoupon.rowIndex, 1);
		    	 $scope.setPagingData($scope.sysCardCouponListData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
		     }).error(function(data,status,headers,config){
		      	alert("删除失败！");
		     });
		};
	
	 $scope.search = function() {
		  var ft = $scope.filterText;
          var data = $scope.sysCardCouponData.filter(function(item) {
                  return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
         });
         $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
    
});



/**
 * 这里是卡券 新增 模块
 * 
 * @type {[type]}
 */
app.controller('SysCardCouponAddCtrl', function($scope, $http, $state, $stateParams) {
    console.log($stateParams);
    
    $scope.master = {};

	  $scope.add = function(sysCardCoupon) {
		sysCardCoupon.endDate = formatDateTime(sysCardCoupon.endDate);
		sysCardCoupon.startDate = formatDateTime(sysCardCoupon.startDate);
	    $scope.master = angular.copy(sysCardCoupon);
	    $http({
	        method  : 'post',
	        url     : '/ajax/sysCardCoupon',
	        params    : sysCardCoupon  
	    }).success(function(data) {
	            console.log(data);
	            alert("添加成功！");
	            $state.go('app.table.sysCardCoupon');
	    }).error(function(data){
	    	alert("出错");
	    });
	  };

	  $scope.reset = function() {
	    $scope.sysCardCoupon = angular.copy($scope.master);
	  };

	  $scope.reset();
});


/**
 * 这里是卡券编辑
 * @type {[type]}
 */
app.controller('SysCardCouponDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/sysCardCoupon/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.sysCardCoupon = data;
	            $scope.sysCardCoupon.oldPassword = data.password;
	            $scope.sysCardCoupon.password = "";
	            $scope.newPassword = "";
	           // alert(data.id);
	        });
	};
	 $scope.saved = {};
     $scope.save = function(sysCardCoupon) {
    	 sysCardCoupon.endTime = formatDateTime(sysCardCoupon.endTime);
    	 if(sysCardCoupon.password == ""){
    		 sysCardCoupon.password = sysCardCoupon.oldPassword;
    	 }
    	 sysCardCoupon.newPassword = sysCardCoupon.password ;
    	 $scope.saved = angular.copy(sysCardCoupon);
	     $http({
	        method  : 'put',
	        url     : '/ajax/sysCardCoupon/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 $state.go('app.table.sysCardCoupon');
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});



/**
 * 这里是卡券企业信息
 * @type {[type]}
 */
app.controller('sysCardCouponInfoCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/sysCardCoupon/1'
	    }).success(function(data) {
	           // console.log(data);
	            $scope.sysCardCoupon = data;
	            $scope.sysCardCoupon.oldPassword = data.password;
	            $scope.sysCardCoupon.password = "";
	            $scope.newPassword = "";
	            if($scope.sysCardCoupon.identityCard != null && $scope.sysCardCoupon.identityCard != '' ){
	            	document.getElementById("identityCardImg").className  = "thumb";
	            	document.getElementById("identityCardImg").src = $scope.sysCardCoupon.identityCard;
	            	document.getElementById("identityCardButton").removeAttribute("disabled");
	            }
	            if($scope.sysCardCoupon.businessLicense != null && $scope.sysCardCoupon.businessLicense != '' ){
	            	document.getElementById("businessLicenseImg").className  = "thumb";
	            	document.getElementById("businessLicenseImg").src = $scope.sysCardCoupon.businessLicense;
	            	document.getElementById("businessLicenseButton").removeAttribute("disabled");
	            }
	        });
	};
	 $scope.saved = {};
     $scope.save = function(sysCardCoupon) {
    	 sysCardCoupon.endTime = formatDateTime(sysCardCoupon.endTime);
    	 sysCardCoupon.businessLicense = document.getElementById("businessLicense").value;
    	 sysCardCoupon.identityCard = document.getElementById("identityCard").value;
    	 if(sysCardCoupon.password == ""){
    		 sysCardCoupon.password = sysCardCoupon.oldPassword;
    	 }
    	 sysCardCoupon.newPassword = sysCardCoupon.password ;
    	 $scope.saved = angular.copy(sysCardCoupon);
	     $http({
	        method  : 'put',
	        url     : '/ajax/sysCardCoupon/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	};
	
     //上传图片
     $scope.onFileSelect = function($files) {    
    	    for (var i = 0; i < $files.length; i++) {      var file = $files[i];
    	      $scope.upload = $upload.upload({
    	    	method  : 'post',
    	        url: '/ajax/upload',  
    	        data: {myObj: $scope.myModelObj},
    	        file: file,  
    	      }).progress(function(evt) {        
    	    	  console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
    	      }).success(function(data, status, headers, config) {        
    	        console.log(data);
    	      });     
    	    }    
    	    };
});
