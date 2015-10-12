app.factory('AuthService', function ($http, Session) {
	var authService = {};
	//登录
	authService.login = function (credentials) {
		return $http.post("/ajax/login?username="+credentials.username+"&password="+credentials.password,{})
					.then(function (res) {
						var user = res.data.data.user;
						var menu = res.data.data.menu;
						Session.create(true, user.id,"",menu);
						return user;
					});
	};
	
	//登出
	//this.create = function (logined, userId, userRole,userAuthority) 
	authService.loginout = function (credentials) {
		return $http.post("/ajax/login?username="+credentials.username+"&password="+credentials.password,{})
					.then(function (res) {
						var user = res.data.data.user;
						var menu = res.data.data.menu;
						alert(menu);
						Session.create(true, user.id,"",menu);
						return user;
					});
	};
	
	//认证
	authService.isAuthenticated = function () {
		return !!Session.userId;
	};
	
	//授权
	authService.isAuthorized = function (authorizedRoles) {
		if (!angular.isArray(authorizedRoles)) {
			authorizedRoles = [authorizedRoles];
		}
		return (authService.isAuthenticated() &&
			authorizedRoles.indexOf(Session.userRole) !== -1);
	};
	return authService;	
});

//授权事件常量
app.constant('AUTH_EVENTS', {
	loginSuccess : 'auth-login-success',
	loginFailed : 'auth-login-failed',
	logoutSuccess : 'auth-logout-success',
	sessionTimeout : 'auth-session-timeout',
	notAuthenticated : 'auth-not-authenticated',
	notAuthorized : 'auth-not-authorized'
});

//角色常量
app.constant('USER_ROLES', {
	all : '*',
	admin : 'admin',
	editor : 'editor',
	guest : 'guest'
})

