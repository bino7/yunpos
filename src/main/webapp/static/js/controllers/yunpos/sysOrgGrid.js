app.controller('SysOrgController', function($scope, $q,$http, ngTreetableParams) {
		
    $scope.expanded_params = new ngTreetableParams({
    	getNodes: function(parent) {
	        var deferred = $q.defer();
	        //vendor/modules/ui-grid/data/500_complex2.json
	        $http({method  : 'get', url: 'vendor/modules/ui-grid/data/500_complex2.json' }).success(function(data) {
	        		deferred.resolve(data);
		        });
	        return deferred.promise;
	    },

        getTemplate: function(node) {//模板
            return 'tree_node';
        },
        options: {//参数定制
        	 onNodeExpand: function() {
                 console.log('A node was expanded!');
             }
        }
    });
	 
});