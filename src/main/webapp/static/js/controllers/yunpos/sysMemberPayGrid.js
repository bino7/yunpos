app.controller('SysMemberPayDetail', function($scope, $http, $state, $stateParams) {
	$scope.openId = $stateParams.openId;
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
        $scope.myData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    

    $scope.gridOptions = {
            data: 'myData',
            rowTemplate: '<div style="height: 100%"><div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell ">' +
                '<div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }"> </div>' +
                '<div ng-cell></div>' +
                '</div></div>',
            multiSelect: false,
            enableCellSelection: true,
            enableRowSelection: false,
            enableCellEdit: true,
            //enablePinning: true,
            columnDefs: [
                         {field: 'transNum', displayName: '交易流水号', enableCellEdit: false,  sortable: false, width: 100}, 
                         {field: 'price', displayName: '金额', enableCellEdit: false,  sortable: false, width: 100}, 
                         {field: 'info', displayName: '备注', enableCellEdit: false,  sortable: false, width: 100}, 
                         {field: 'payType', displayName: '交易渠道', cellFilter : 'payType',enableCellEdit: false, sortable: false, width: 220},                    
                         {field: 'payStatus', displayName: '交易类型',  cellFilter : 'payStatus', enableCellEdit: false, sortable: false, width: 80},
                         {field: 'shopName', displayName: '门店', enableCellEdit: false, sortable: false, width: 80},
                         {field: 'name', displayName: '用户', enableCellEdit: false, sortable: false, width: 80},
                         {field: 'payNum', displayName: '支付流水号', enableCellEdit: false, sortable: false, width: 80},           
                         {field: 'createdAt', displayName: '交易时间', cellFilter : 'date:"yyyy-MM-dd HH:mm:ss"',enableCellEdit: false, sortable: false, width: 150}, 
                        ],
            enablePaging: true,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            filterOptions: $scope.filterOptions
        };
    
    app.filter('payType',function(){
        return function(sourceType){
        	var type;
        	switch (sourceType){
        	case 0:
        		type = "线上充值";
        		break;
        	case 1:
        		type = "线下充值";
        		break;
        	default:
        		type = "商家充值";    	
        	}
            return type;
        }
    });
    
    app.filter('payStatus',function(){
        return function(sourceType){
        	var type;
        	switch (sourceType){
        	case 0:
        		type = "交易失败";
        		break;
        	case 1:
        		type = "交易中";
        		break;
        	default:
        		type = "交易成功";    	
        	}
            return type;
        }
    });
    
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        setTimeout(function () {
            var data;
            if (searchText) {
            	/*
                var ft = searchText.toLowerCase();
                $http.get('js/controllers/largeLoad.json').success(function (largeLoad) {    
                    data = largeLoad.filter(function(item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data,page,pageSize);
                });  
                */          
            } else {
            	var url = '/ajax/memberpay/userId?openId=' + $scope.openId;
                $http.get(url).success(function (largeLoad) {
                	$scope.sysFansData = largeLoad.rows;
                    $scope.setPagingData($scope.sysFansData,page,pageSize);
                });
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    
    $scope.back = function() {
    	$state.go('app.table.fans');
	};
	
});