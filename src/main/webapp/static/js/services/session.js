app.service('Session', function () {
	//创建session
	this.create = function (logined, userId, userRole,userAuthority) {
		this.logined = logined;
		this.userId = userId;
		this.userRole = userRole;
		this.userAuthority = userAuthority;
	};
	
	//销毁session
	this.destroy = function () {
		this.logined = false;
		this.userId = null;
		this.userRole = null;
		this.userAuthority=null;
	};
	return this;
})