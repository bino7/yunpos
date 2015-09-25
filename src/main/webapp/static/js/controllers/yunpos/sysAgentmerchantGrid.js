app.controller('AgentmerchantListCtrl',  function($scope, $http, $state, $stateParams) {
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
        $scope.agentmerchantListData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'agentmerchantListData',
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
               {field: 'userName', displayName: '代理商名', width: 120,  pinnable: false,  sortable: false}, 
               {field: 'nickname', displayName: '昵称', enableCellEdit: false , width: 120}, 
               {field: 'companyName', displayName: '公司名称', enableCellEdit: false, width: 180},
               {field: 'createdBy',displayName: '添加人',enableCellEdit: false, width: 120}, 
               {field: 'createdAt',displayName: '添加时间',enableCellEdit: false, width: 140}, 
               {field: 'status', displayName: '状态', enableCellEdit: false, width: 60 }, 
               {field: 'auditStatus', displayName: '审核状态', enableCellEdit: false, width: 100 }, 
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate: '<div><a ui-sref="app.table.agentmerchantDetail({id:row.getProperty(col.field)})" '
                	+ 'id="{{row.getProperty(col.field)}}"> <button>查看编辑{{row.status}}</button> </a> ' 
                	+ '<button ng-if="row.getProperty(\'status\')==0" ng-click="updateStatus({id:row.getProperty(col.field) , agentmerchant:row, status:1})">启用<button>'
                	+ '<button ng-if="row.getProperty(\'status\')==1" ng-click="updateStatus({id:row.getProperty(col.field) , agentmerchant:row, status:0})">停用<button></div>'
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
                data = $scope.agentmerchantData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/agentmerchant/search').success(function (largeLoad) {
                    $scope.agentmerchantData = largeLoad.rows;
                    $scope.setPagingData($scope.agentmerchantData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.agentmerchantData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.agentmerchantData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);

    $scope.updateStatus = function(obj) {
    	obj.agentmerchant.entity.status = obj.status;
	     $http({
	        method  : 'put',
	        url     : '/ajax/agentmerchant/updateStatus/' + obj.id,
	        params  :  obj.agentmerchant.entity 
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
          var data = $scope.agentmerchantData.filter(function(item) {
                  return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
         });
         $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
    
});



/**
 * 这里是代理商 新增 模块
 * 
 * @type {[type]}
 */
app.controller('AgentmerchantAddCtrl', function($scope, $http, $state, $stateParams) {
    console.log($stateParams);
    
    $scope.master = {};

	  $scope.add = function(agentmerchant) {
		agentmerchant.endTime = formatDateTime(agentmerchant.endTime);
	    $scope.master = angular.copy(agentmerchant);
	    $http({
	        method  : 'post',
	        url     : '/ajax/agentmerchant',
	        params    : agentmerchant  
	    }).success(function(data) {
	            console.log(data);
	            alert("添加成功！");
	            $state.go('app.table.agentmerchant');
	    }).error(function(data){
	    	alert("出错");
	    });
	  };

	  $scope.reset = function() {
	    $scope.agentmerchant = angular.copy($scope.master);
	  };

	  $scope.reset();
});


/**
 * 这里是代理商编辑
 * @type {[type]}
 */
app.controller('AgentmerchantDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/agentmerchant/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.agentmerchant = data;
	           // alert(data.id);
	        });
	};
	 $scope.saved = {};
     $scope.save = function(agentmerchant) {
    	 agentmerchant.endTime = formatDateTime(agentmerchant.endTime);
    	 $scope.saved = angular.copy(agentmerchant);
	     $http({
	        method  : 'put',
	        url     : '/ajax/agentmerchant/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 $state.go('app.table.agentmerchant');
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});



/**
 * 这里是代理商企业信息
 * @type {[type]}
 */
app.controller('AgentmerchantInfoCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/agentmerchant/1'
	    }).success(function(data) {
	           // console.log(data);
	            $scope.agentmerchant = data;
	           // alert(data.id);
	        });
	};
	 $scope.saved = {};
     $scope.save = function(agentmerchant) {
    	 agentmerchant.endTime = formatDateTime(agentmerchant.endTime);
    	 $scope.saved = angular.copy(agentmerchant);
	     $http({
	        method  : 'put',
	        url     : '/ajax/agentmerchant/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 $state.go('app.table.agentmerchant');
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
