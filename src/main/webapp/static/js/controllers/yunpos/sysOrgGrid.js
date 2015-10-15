app.controller('SysOrgController', function($scope,$rootScope, $q,$http, ngTreetableParams,OrgService) {
	
//	$scope.init = function(){
//		
////	   var promise = OrgService.query();
////	   promise.then(function(data) {  // 调用承诺API获取数据 .resolve  
////		   $rootScope.orgs = data;  
////	    }, function(data) {  // 处理错误 .reject  
////	    	$rootScope.user = {error: '用户不存在！'};  
////	    });  
//	}
	
	var data =  $rootScope.orgs;
//	var data= [{
//		"id" : "46",
//		"sequence" : "null",
//		"orgNo" : "0002",
//		"orgName" : "ttt2",
//		"orgParentId" : "null",
//		"orgParentName" : "",
//		"orgParentNo" : "",
//		"level" : "1",
//		"createUserId" : "6",
//		"createDate" : "1444811928000",
//		"modifyUserId" : "null",
//		"modifyDate" : "1444822683000",
//		"isLeaf" : null,
//		"children" : []
//	}, {
//		"id" : "47",
//		"sequence" : "null",
//		"orgNo" : "0003",
//		"orgName" : "ttt2",
//		"orgParentId" : "null",
//		"orgParentName" : "",
//		"orgParentNo" : "",
//		"level" : "1",
//		"createUserId" : "6",
//		"createDate" : "1444811978000",
//		"modifyUserId" : null,
//		"modifyDate" : "1444822687000",
//		"isLeaf" : "null",
//		"children" : []
//	}, {
//		"id" : "77",
//		"sequence" : "null",
//		"orgNo" : "0004",
//		"orgName" : "ttt2",
//		"orgParentId" : "null",
//		"orgParentName" : "",
//		"orgParentNo" : "",
//		"level" : "1",
//		"createUserId" : "6",
//		"createDate" : "1444812011000",
//		"modifyUserId" : "null",
//		"modifyDate" : "1444822689000",
//		"isLeaf" : "null",
//		"children" : []
//	}, {
//		"id" : "49",
//		"sequence" : "null",
//		"orgNo" : "0005",
//		"orgName" : "ttt4",
//		"orgParentId" : null,
//		"orgParentName" : "",
//		"orgParentNo" : "",
//		"level" : "1",
//		"createUserId" : "6",
//		"createDate" : "1444812088000",
//		"modifyUserId" : "null",
//		"modifyDate" : "1444822692000",
//		"isLeaf" : "null",
//		"children" : []
//	}, {
//		"id" : "60",
//		"sequence" : "null",
//		"orgNo" : "0006",
//		"orgName" : "ttt4",
//		"orgParentId" : 1,
//		"orgParentName" : "",
//		"orgParentNo" : "",
//		"level" : "1",
//		"createUserId" : "6",
//		"createDate" : "1444812272000",
//		"modifyUserId" : "null",
//		"modifyDate" : "1444822694000",
//		"isLeaf" : "null",
//		"children" : [{
//				"id" : "51",
//				"sequence" : "null",
//				"orgNo" : "00060002",
//				"orgName" : "m1",
//				"orgParentId" : "null",
//				"orgParentName" : "",
//				"orgParentNo" : "0006",
//				"level" : "2",
//				"createUserId" : "13",
//				"createDate" : "1444812518000",
//				"modifyUserId" : "null",
//				"modifyDate" : "1444822697000",
//				"isLeaf" : "null",
//				"children" : []
//			}, {
//				"id" : "52",
//				"sequence" : "null",
//				"orgNo" : "00060003",
//				"orgName" : "m2",
//				"orgParentId" : "null",
//				"orgParentName" : "",
//				"orgParentNo" : "0006",
//				"level" : "2",
//				"createUserId" : "13",
//				"createDate" : "1444812953000",
//				"modifyUserId" : "null",
//				"modifyDate" : "1444822699000",
//				"isLeaf" : "null",
//				"children" : []
//			}
//		]
//	}, {
//		"id" : "53",
//		"sequence" : "null",
//		"orgNo" : "0007",
//		"orgName" : "m3",
//		"orgParentId" : "null",
//		"orgParentName" : "",
//		"orgParentNo" : "",
//		"level" : "1",
//		"createUserId" : "12",
//		"createDate" : "1444813087000",
//		"modifyUserId" : "null",
//		"modifyDate" : "1444822703000",
//		"isLeaf" : "null",
//		"children" : []
//	}
//];

		
    $scope.expanded_params = new ngTreetableParams({
    	getNodes: function(parent) {
    		//alert(JSON.stringify(parent));
    		return parent ? parent.children :data;
	    },
        getTemplate: function(node) {//模板
            return 'tree_node';
        },
        options: {//参数定制
        	 onNodeExpand: function() {
                 //console.log('A node was expanded!');
             }
        }
    });
	 
});