app.controller('SysMembercardTemplateListCtrl',  function($scope, $http, $state, $stateParams) {
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
    /*
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
               {field: 'title', displayName: '会员卡标题', width: 120,  pinnable: false,  sortable: false}, 
               {field: 'typeDescription', displayName: '卡券类型', enableCellEdit: false , width: 120}, 
               {field: 'validityDate' , displayName: '有效期', enableCellEdit: false, width: 320},
               {field: 'createdBy',displayName: '投放平台',enableCellEdit: false, width: 120}, 
               {field: 'statusDescription',displayName: '投放状态',enableCellEdit: false, width: 140}, 
               {field: 'id', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                cellTemplate: '<div><a ui-sref="app.table.sysCardTemplateDetail({id:row.getProperty(col.field)})" '
                	+ 'id="{{row.getProperty(col.field)}}"> <button>详情{{row.status}}</button> </a> ' 
                	+ '<button ng-click="send({id:row.getProperty(col.field) , sysCardTemplate:row, status:1})">投放</button>'
                	+ '<button ng-click="deleted({id:row.getProperty(col.field) , sysCardTemplate:row})">删除</button></div>'
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
    */
});



/**
 * 这里是卡券 新增 模块
 * 
 * @type {[type]}
 */
app.controller('SysMembercardTemplateAddCtrl', function($scope, $http, $state, $stateParams) { 
	$scope.cardBaseinfo = {};
	$scope.membercardTemplate = {};
	$scope.request = {};
	$scope.save = function(sysCardBaseinfo, sysMembercardTemplate) {
		$scope.cardBaseinfo = angular.copy(sysCardBaseinfo);
		$scope.cardBaseinfo.begin_times = formatDateTime(sysCardBaseinfo.begin_times);
		$scope.cardBaseinfo.end_times = formatDateTime(sysCardBaseinfo.end_times);
		//设置其他值
		switch (sysCardBaseinfo.code_type_t){
			case '0'://仅显示文本
			    $scope.cardBaseinfo.code_type = 'CODE_TYPE_TEXT';
				break;
			case '1'://显示二维码+文本
			    $scope.cardBaseinfo.code_type = 'CODE_TYPE_QRCODE';
				break;	
			case '2'://显示条形码+文本
			    $scope.cardBaseinfo.code_type = 'CODE_TYPE_BARCODE';
				break;		
			default:
				break;
		}
		$scope.cardBaseinfo.can_share = sysCardBaseinfo.can_share ? 1:0;//是否可分享
		$scope.cardBaseinfo.can_give_friend = sysCardBaseinfo.can_give_friend ? 1:0;//是否可转赠
		var logo = document.getElementById("logo").value;
    	if (logo!="") {
    		$scope.cardBaseinfo.logo_url = logo;
    	}
		
		$scope.cardBaseinfo.brand_name = '移领广东';//先写死，后面再从登录用户中读取
				
		$scope.membercardTemplate = angular.copy(sysMembercardTemplate);
		//设置会员卡的其他值
		$scope.membercardTemplate.card_type = 'MEMBER_CARD';
		$scope.membercardTemplate.auto_activate = 0; 
		$scope.membercardTemplate.wx_activate = 1; //一键开卡
		$scope.membercardTemplate.supply_bonus = 0; //不显示积分
		$scope.membercardTemplate.supply_balance = 0; //不支持储值
		$scope.request = angular.extend($scope.cardBaseinfo, $scope.membercardTemplate);
		
		$http({
			method: 'post',
			url: '/ajax/membercardTemplate',
			params: $scope.request
		}).success(function(data) {
			console.log(data);
			alert("添加成功！");
			$state.go('app.table.sysMembercardTemplate');
		}).error(function(data) {
			alert("出错");
		});
	};

});