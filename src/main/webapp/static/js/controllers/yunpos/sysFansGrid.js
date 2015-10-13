app.controller('SysFansCtrl', function($scope, $http, $state, $stateParams) {
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
        $scope.SysFansData = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.gridOptions = {
            data: 'SysFansData',
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
                         {field: 'sourceType', displayName: '来源类型', cellFilter : 'sourceType' ,enableCellEdit: false, sortable: false, width: 100},                        
                         {field: 'openId', displayName: '操作', enableCellEdit: false, sortable: false,  pinnable: false,
                          cellTemplate: '<div>'
                          // +	'<a ui-sref="app.table.sysFanScoreDetail({openId:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}" ><button class="btn btn-success">积分明细</button></a>' 
                           +	'<a ui-sref="app.table.sysMemberPayDetail({openId:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"><button class="btn btn-success">充值记录</button></a>'
                           +	'<a ui-sref="app.table.sysMemberUseDetail({openId:row.getProperty(col.field)})" id="{{row.getProperty(col.field)}}"><button class="btn btn-success">消费记录</button></a>'              	
                           +   '<span ng-controller="memberPay_shopCtrl"><script type="text/ng-template" id="memberPay_shop"><div ng-include="\'tpl/system/memberPay_shop.html\'"></div></script>'
                           +  '<button class="btn btn-success" ng-click="open(lg,\'memberPay_shop\',row)">充值</button></span>' 
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
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.userData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
//        	$scope.setPagingData($scope.userData, $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize);
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterText);
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

app.controller('memberPay_shopCtrl', ['$scope', '$http','$modal', '$log',
	function($scope,  $http,$modal, $log, $stateParams) {
		$scope.open = function(size, tempUrl, data) {
			$scope.items = {};
			$scope.items.fansId = data.entity.id;
			$scope.items.openId = data.entity.openId;
			$scope.items.nickName = data.entity.nickName;
			var modalInstance = $modal.open({
				templateUrl: tempUrl,
				controller: 'MemberpayModalInstanceCtrl',
				size: size,
				scope: $scope,
				resolve: {
					items: function() {
						return $scope.items;
					}
				}
			});
			$scope.payByShop = function(data) {
				//检查充值密码是否正确，暂时不检查
				var passwd = data.password;
				
				var balance = data.score;
				//对会员进行充值，形成充值记录和交易流水
				var sysMemberpay = {};
				sysMemberpay.appid_userId = $scope.items.openId;
				sysMemberpay.name = $scope.items.nickName;
				sysMemberpay.price = balance;
				sysMemberpay.payType = 2;//充值渠道为商家充值
				sysMemberpay.createdAt = parseDateTime(new Date(),"YYYY-MM-DD hh:mm:ss");
				//设置店铺名称和店铺ID,后面从用户登录处获取
				//sysMemberpay.shopID =
				//sysMemberpay.shopName = 
				//产生充值记录	
				$http({
					method: 'post',
					url: '/ajax/memberpay',
					params: sysMemberpay
				}).success(function(data) {
					var result = data.id;
					if (result != '') {
						//充值成功后，修改粉丝的余额
						$http({
							method: 'PUT',
							url: '/ajax/Fans/balance/' + $scope.items.fansId,
							params: {'balance':balance}
						}).success(function(data) {
							if (data) {
								modalInstance.close();//关闭窗口
							}
						}).error(function(data) {
							alert("更新余额失败,请重新再试！");
						});	
					}
				}).error(function(data) {
					alert("充值失败,请重新再试！");
				});
	       };	
	       $scope.cancel = function() {
	       	   modalInstance.close();//关闭窗口
		   }
		}
	}
]);


/**
 * 弹出框势实例化控制器
 */
	app.controller('MemberpayModalInstanceCtrl', ['$scope', '$http', '$modalInstance', 'items',function($scope, $http, $modalInstance, items) {
		$scope.items = items;
		/*
		$scope.selected = {
			item: $scope.items[0]
		};
		*/	
		}
	]);


/**
 * 这里是用户 新增 模块
 * 
 * @type {[type]}
 */
/*app.controller('UserAddCtrl', function($scope, $http, $state, $stateParams) {
    console.log($stateParams);
    
    $scope.master = {};

	  $scope.add = function(user) {
	    $scope.master = angular.copy(user);
	    $http({
	        method  : 'post',
	        url     : '/ajax/user',
	        params    : user  
	    }).success(function(data) {
	            console.log(data);
	            alert("添加成功！");
	            $state.go('app.table.user');
	    }).error(function(data){
	    	alert("出错");
	    });
	  };

	  $scope.reset = function() {
	    $scope.user = angular.copy($scope.master);
	  };

	  $scope.reset();
});*/


/**
 * 弹出框势实例化控制器
 */
app.controller('FansEditInstanceCtrl', ['$scope','$http', '$modalInstance', 'items', function($scope,$http, $modalInstance,items) {
	$scope.items = items;
}])
;

/**
 * 这里是用户编辑
 * @type {[type]}
 */

	app.controller('FansEditCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
		  $scope.items = ['item1', 'item2', 'item3'];
		  $scope.open = function (size,tempUrl,data) {
		  $scope.fans = data.entity;
		    var modalInstance = $modal.open({
		      templateUrl: tempUrl,
		      controller: 'FansEditInstanceCtrl',
		      size: size,
		      scope:$scope,
		      
//		      fans.sex=1?男:女,

		      resolve: {
		        items: function () {
		          return $scope.items;
		        }
		      }
		    });
	
	
	
	
	
   
	
		  }}]);

