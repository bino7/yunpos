app.controller('SysCardTemplateListCtrl',  function($scope, $http, $state, $stateParams) {
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
        $scope.sysCardTemplateListData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'sysCardTemplateListData',
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
               {field: 'title', displayName: '卡券名称', width: 220,  pinnable: false,  sortable: false}, 
               {field: 'typeDescription', displayName: '卡券类型', enableCellEdit: false , width: 120}, 
               {field: 'validityDate' , displayName: '有效期', enableCellEdit: false, width: 320},
               {field: 'createdBy',displayName: '投放平台',enableCellEdit: false, width: 120}, 
               {field: 'statusDescription',displayName: '投放状态',enableCellEdit: false, width: 140}, 
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate: '<div><a ui-sref="app.table.sysCardTemplateDetail({id:row.getProperty(col.field)})" '
                	+ 'id="{{row.getProperty(col.field)}}"> <button>详情{{row.status}}</button> </a> ' 
                	+ '<button ng-if="row.getProperty(\'status\')==0" ng-click="deleted({id:row.getProperty(col.field) , sysCardTemplate:row})">删除</button>'
                	+ '<button ng-if="row.getProperty(\'status\')==0" ng-click="send({id:row.getProperty(col.field) , sysCardTemplate:row, status:1})">投放</button>'
                	+ '</div>'
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
                data = $scope.sysCardTemplateData.filter(function(item) {
                     return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                });
                $scope.setPagingData(data,page,pageSize);
            } else {
                $http.get('/ajax/sysCardTemplate/search').success(function (largeLoad) {
                    $scope.sysCardTemplateData = largeLoad.rows;
                    $scope.setPagingData($scope.sysCardTemplateData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.sysCardTemplateData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.sysCardTemplateData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);

    $scope.send = function(obj) {
    	obj.sysCardTemplate.entity.status = obj.status;
	     $http({
	        method  : 'put',
	        url     : '/ajax/sysCardTemplate/send/' + obj.id,
	        params  :  obj.sysCardTemplate.entity 
	     }).success(function() {
	    	 alert("投放成功！");
	    //	 $scope.sysCardTemplateData[obj.sysCardTemplate.rowIndex] = obj.sysCardTemplate.entity;
	    //	 $scope.setPagingData($scope.sysCardTemplateData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
	     }).error(function(data,status,headers,config){
	      	alert("投放失败！");
	     });
	};
	
	  $scope.deleted = function(obj ,row) {
		     $http({
		        method  : 'delete',
		        url     : '/ajax/sysCardTemplate/' + obj.id,
		        params  : {"id":obj.id}
		     }).success(function() {
		    	 alert("删除成功！");
		    	 $scope.sysCardTemplateListData.splice(obj.sysCardTemplate.rowIndex, 1);
		    	 $scope.setPagingData($scope.sysCardTemplateListData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
		     }).error(function(data,status,headers,config){
		      	alert("删除失败！");
		     });
		};
	
	 $scope.search = function() {
		  var ft = $scope.filterText;
          var data = $scope.sysCardTemplateData.filter(function(item) {
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
app.controller('SysCardTemplateAddCtrl', function($scope, $http, $state, $stateParams) {
    console.log($stateParams);
    
    $scope.master = {};

	  $scope.add = function(sysCardTemplate) {
		sysCardTemplate.endDate = formatDateTime(sysCardTemplate.endDate);
		sysCardTemplate.startDate = formatDateTime(sysCardTemplate.startDate);
		sysCardTemplate.logo = document.getElementById("logo").value;
	    $scope.master = angular.copy(sysCardTemplate);
	    $http({
	        method  : 'post',
	        url     : '/ajax/sysCardTemplate',
	        params    : sysCardTemplate  
	    }).success(function(data) {
	            console.log(data);
	            alert("添加成功！");
	            $state.go('app.table.sysCardTemplate');
	    }).error(function(data){
	    	alert("出错");
	    });
	  };

	  $scope.reset = function() {
	    $scope.sysCardTemplate = angular.copy($scope.master);
	  };

	  $scope.reset();
});


/**
 * 这里是卡券编辑
 * @type {[type]}
 */
app.controller('SysCardTemplateDetailCtrl', function($scope, $http, $state, $stateParams) {
    $scope.processForm = function() {
	    $http({
	        method  : 'get',
	        url     : '/ajax/sysCardTemplate/'+ $stateParams.id
	    }).success(function(data) {
	           // console.log(data);
	            $scope.sysCardTemplate = data;
	            
	            if($scope.sysCardTemplate.logo != null && $scope.sysCardTemplate.logo != '' ){
	            	document.getElementById("logoImg").className  = "thumb";
	            	document.getElementById("logoImg").src = $scope.sysCardTemplate.logo;
	            	document.getElementById("logoButton").removeAttribute("disabled");
	            }
	        });
	};
	 $scope.saved = {};
     $scope.save = function(sysCardTemplate) {
    	 sysCardTemplate.endTime = formatDateTime(sysCardTemplate.endTime);
    	 if(sysCardTemplate.password == ""){
    		 sysCardTemplate.password = sysCardTemplate.oldPassword;
    	 }
    	 sysCardTemplate.newPassword = sysCardTemplate.password ;
    	 $scope.saved = angular.copy(sysCardTemplate);
	     $http({
	        method  : 'put',
	        url     : '/ajax/sysCardTemplate/' + $scope.saved.id,
	        params  : $scope.saved
	     }).success(function(data) {
	    	 alert("保存成功！");
	    	 $state.go('app.table.sysCardTemplate');
	     }).error(function(data,status,headers,config){
	      	alert("保存失败！");
	     });
	}
});


