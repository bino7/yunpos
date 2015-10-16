app.factory('OrgService', [ '$http', '$q', function($http, $q) {
	return {
		query : function() {
			var deferred = $q.defer(); // 声明延后执行，表示要去监控后面的执行
			$http({
				method : 'get',
				url : 'vendor/modules/ui-grid/data/500_complex4.json'
			}).success(function(data, status, headers, config) {
				deferred.resolve(data); // 声明执行成功，即http请求数据成功，可以返回数据了
			}).error(function(data, status, headers, config) {
				deferred.reject(data); // 声明执行失败，即服务器返回错误
			});
			return deferred.promise; // 返回承诺，这里并不是最终数据，而是访问最终数据的API
		} // end query
	};
} ]);