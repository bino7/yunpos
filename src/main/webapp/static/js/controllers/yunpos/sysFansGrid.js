app.controller('SysFansCtrl', function($scope, $http, $state, $stateParams) {
    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    }; 
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [250, 500, 1000],
        pageSize: 250,
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
                         {field: 'nickName', displayName: '昵称', enableCellEdit: false,  sortable: false, width: 100}, 
                         {field: 'memberCardTel', displayName: '手机号码', width: 100,  pinnable: false}, 
                         {field: 'address', displayName: '所在地区', enableCellEdit: false, sortable: false, width: 220},
                         {field: 'balance', displayName: '余额', enableCellEdit: false, sortable: false, width: 80},
                         {field: 'score', displayName: '可用积分', enableCellEdit: false, sortable: false, width: 80},
                         {field: 'subscribeTime', displayName: '关注时间', cellFilter : 'date:"yyyy-MM-dd HH:mm:ss"',enableCellEdit: false, sortable: false, width: 150}, 
                         {field: 'sourceType', displayName: '来源类型', cellFilter : 'sourceType' ,enableCellEdit: false, sortable: false, width: 150},                        
                         {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                          cellTemplate: '<div>'
                           +	'<a ui-sref="app.table.sysFanScoreDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"><button>积分明细</button></a>' 
                           +	'<a ui-sref="app.table.sysPayrecordDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"><button>充值记录</button></a>'
                           +	'<a ui-sref="app.table.sysUserecordDetail({id:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"><button>消费记录</button></a>'              	
                           +	'</div>'
                      }],
            enablePaging: true,
            showFooter: true,
            totalServerItems: 'totalServerItems',
            pagingOptions: $scope.pagingOptions,
            filterOptions: $scope.filterOptions
        };
    
    app.filter('sourceType',function(){
        return function(sourceType){
        	var type;
        	switch (sourceType){
        	case 0:
        		type = "公众号";
        		break;
        	case 1:
        		type = "服务窗";
        		break;
        	default:
        		type = "自行注册";    	
        	}
            return type;
        }
    });
    
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                $http.get('js/controllers/largeLoad.json').success(function (largeLoad) {    
                    data = largeLoad.filter(function(item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data,page,pageSize);
                });            
            } else {
            	var url = '/ajax/merchantFans/search?serialNo=' + '08600719332';
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
    
    $scope.search = function() {
		var ft = $scope.filterText;
        var data = $scope.sysFansData.filter(function(item) {
        	var nickName = item.nickName==null ? "":item.nickName ;
        	var memberCardTel = item.memberCardTel==null ? "":item.memberCardTel ;	
        	return nickName.indexOf(ft) != -1 || memberCardTel.indexOf(ft) != -1;
       });
       $scope.setPagingData(data, $scope.pagingOptions.currentPage , $scope.pagingOptions.pageSize);
	};
});