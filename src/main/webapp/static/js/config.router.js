'use strict';

/**
 * Config for the router
 */

//全局配置信息
app.run(['$rootScope', '$state', '$stateParams',function ($rootScope,   $state,   $stateParams) {
	 $rootScope.$state = $state;
     $rootScope.$stateParams = $stateParams;   
     
   //监听$stateChangeStart事件并作相应的逻辑处理
//     $rootScope.$on('$stateChangeStart',function(event, toState, toParams, fromState, fromParams){
//    		if(toState.name=='login')return;// 如果是进入登录界面则允许
//    		// 如果用户不存在
//    		if(!$rootScope.isLogined){
//    			event.preventDefault();// 取消默认跳转行为
//    			$state.go("login",{from:fromState.name,w:'notLogin'});//跳转到登录界面
//    		}
//    	});
}]);






app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider,   $urlRouterProvider) {
	  	 //设置默认路由
	  	 //$urlRouterProvider.otherwise('/app/home');
          $urlRouterProvider.otherwise('/login');
          $stateProvider
	         .state('login', {//登录页面
	              url: '/login',
	              templateUrl: 'tpl/system/sys_login.html',
	              resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/yunpos/loginController.js'] );
                      }]
                  }
	          })
	          .state('app.thirdPartyLogin', {//第三方登录
                  url: '/thirdpartylogin',
                  templateUrl: 'tpl/system/sys_thirdPartyLogin.html',
	                  resolve: {
	                      deps: ['$ocLazyLoad',
	                        function( $ocLazyLoad ){
	                          return $ocLazyLoad.load('ngGrid').then(
	                              function(){
	                                  return $ocLazyLoad.load('js/controllers/yunpos/sysThirdPartyLogin.js');
	                              }
	                          );
	                      }]
	                  }
              })
	          .state('register', {
                  url: '/register',
                  templateUrl: 'tpl/system/sys_register.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/yunpos/signupController.js'] );
                      }]
                  }
              })
              .state('app.table.org', {
                  url: '/org',
                  templateUrl: 'tpl/system/sys_org.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysOrgGrid.js'] );
            		  }]
            	  }
              })
              .state('forgotpwd', {
                  url: '/forgotpwd',
                  templateUrl: 'tpl/system/page_forgotpwd.html'
              })
              .state('404', {
                  url: '/404',
                  templateUrl: 'tpl/system/page_404.html'
              })
              .state('app', {//通用框架基础,内部设置ui-view占位符将其他页面输入到通用框架占位符处
                  abstract: true,
                  url: '/app',
                  templateUrl: 'tpl/app.html'
              })
              .state('app.home', {//首页路由
                  url: '/home',
                  templateUrl: 'tpl/app_dashboard_v1.html',
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                        return $ocLazyLoad.load(['js/controllers/chart.js']);
                    }]
                  }
              })
              
              // table
              .state('app.table', {
                  url: '/table',
                  template: '<div ui-view></div>'
              })
              .state('app.table.fans', {//粉丝管理
                  url: '/fans',
                  templateUrl: 'tpl/system/fans_datalist.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysFansGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.member', {//会员管理
                  url: '/member',
                  templateUrl: 'tpl/system/sys_member.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysMemberGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.sysMemberPayDetail', {//充值记录明细管理
                  url: '/memberPayDetail/:openId',
                  templateUrl: 'tpl/system/memberPay_dataList.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysMemberPayGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.sysMemberUseDetail', {//消费记录明细管理
                  url: '/memberUseDetail/:openId',
                  templateUrl: 'tpl/system/memberUse_dataList.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysMemberUseGrid.js');
                              }
                          );
                      }]
                  }
              })
              
              
              
              .state('app.table.integralsetting', {//积分设置
                  url: '/integral/setting',
                  templateUrl: 'tpl/system/sys_integral_setting.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                	    function( $ocLazyLoad ){
                		  return $ocLazyLoad.load('ngGrid').then(
                		      function(){
                		    	  return $ocLazyLoad.load('js/controllers/yunpos/sysMemberIngetralGrid.js');
                		      }
                		  );
                	  }]
                  }
              })
              .state('app.table.transaction', {//交易流水管理
                  url: '/transaction',
                  templateUrl: 'tpl/system/transaction_datalist.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysTransacionGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.tables.sharemanage', {//分润管理
                  url: '/share',
                  templateUrl: 'tpl/system/sys_share_manage.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysShareGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.share', {//分润结算
                  url: '/share',
                  templateUrl: 'tpl/system/share_datalist.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysShareGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.sharesetting', {//分润设置
                  url: '/share/setting',
                  templateUrl: 'tpl/system/share_setting.html'
              })
              .state('app.table.sysorder', {//订单
                  url: '/order',
                  templateUrl: 'tpl/system/sys_order.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysOrderGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.merchantorder', {//商户订单
                  url: '/merchant/order',
                  templateUrl: 'tpl/system/sys_merchant_order.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysMerchantOrderGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.storeorder', {//门店订单
                  url: '/store/order',
                  templateUrl: 'tpl/system/sys_store_order.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/SysStoreOrderGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.menu', {//菜单管理
                  url: '/menu',
                  templateUrl: 'tpl/system/sys_menu.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysMenuGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.role', {//角色管理
                  url: '/role',
                  templateUrl: 'tpl/system/sys_role.html'
              })
              .state('app.table.user', {//用户管理
                  url: '/user',
                  templateUrl: 'tpl/system/sys_user.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysUserGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.userDetail', {//用户管理
                  url: '/userDetail/:id',
                  templateUrl: 'tpl/system/sys_user_detail.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysUserGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.userAdd', {//用户新增
                  url: '/userAdd',
                  templateUrl: 'tpl/system/sys_user_add.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysUserGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.agentmerchant', {//代理商管理
                  url: '/agentmerchant',
                  templateUrl: 'tpl/system/sys_agentmerchant.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysAgentmerchantGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.agentmerchantDetail', {//代理商管理
                  url: '/agentmerchantDetail/:id',
                  templateUrl: 'tpl/system/sys_agentmerchant_detail.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysAgentmerchantGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.agentmerchantAdd', {//代理商新增
                  url: '/agentmerchantAdd',
                  templateUrl: 'tpl/system/sys_agentmerchant_add.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysAgentmerchantGrid.js');
                              }
                          );
                      }]
                  }
              })
               .state('app.table.merchant', {//商户管理
                  url: '/merchant',
                  templateUrl: 'tpl/system/sys_merchant.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysMerchantGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.merchantDetail', {//商户管理
                  url: '/merchantDetail/:id',
                  templateUrl: 'tpl/system/sys_merchant_detail.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysMerchantGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.merchantAdd', {//商户新增
                  url: '/merchantAdd',
                  templateUrl: 'tpl/system/sys_merchant_add.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysMerchantGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.store', {//门店管理
                  url: '/store',
                  templateUrl: 'tpl/system/sys_store.html',
                	  resolve: {
                          deps: ['$ocLazyLoad',
                            function( $ocLazyLoad ){
                              return $ocLazyLoad.load('ngGrid').then(
                                  function(){
                                      return $ocLazyLoad.load('js/controllers/yunpos/sysStoreGrid.js');
                                  }
                              );
                          }]
                      }
              })
              .state('app.table.storeDetail', {//门店管理查看审批
                  url: '/storeDetail/:id',
                  templateUrl: 'tpl/system/sys_store_detail.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysStoreGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.merchantStore', {//门店管理(商户)
            	  url: '/merchantStore',
            	  templateUrl: 'tpl/system/sys_merchant_store.html',
            	  resolve: {
            		  deps: ['$ocLazyLoad',
            		         function( $ocLazyLoad ){
            			  return $ocLazyLoad.load('ngGrid').then(
            					  function(){
            						  return $ocLazyLoad.load('js/controllers/yunpos/sysMerchantStoreGrid.js');
            					  }
            			  );
            		  }]
            	  }
              })
              .state('app.table.merchantStoreDetail', {//门店管理查看审批
            	  url: '/merchantStoreDetail/:id',
            	  templateUrl: 'tpl/system/sys_merchant_store_detail.html',
            	  resolve: {
            		  deps: ['$ocLazyLoad',
            		         function( $ocLazyLoad ){
            			  return $ocLazyLoad.load('ngGrid').then(
            					  function(){
            						  return $ocLazyLoad.load('js/controllers/yunpos/sysMerchantStoreGrid.js');
            					  }
            			  );
            		  }]
            	  }
              })
              .state('app.table.storeAdd', {//门店新增
                  url: '/storeAdd',
                  templateUrl: 'tpl/system/sys_merchant_store_add.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysMerchantStoreGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.sysMerchantInfo', {//商户企业信息
                	  url: '/merchantInfo/:id',
                      templateUrl: 'tpl/system/sys_merchant_info.html',
                          resolve: {
                              deps: ['$ocLazyLoad',
                                function( $ocLazyLoad ){
                                  return $ocLazyLoad.load('ngGrid').then(
                                      function(){
                                          return $ocLazyLoad.load('js/controllers/yunpos/sysMerchantGrid.js');
                                      }
                                  );
                              }]
                          }
                    	  
              })
               .state('app.table.sysAgentmerchantInfo', {//代理商企业信息
                  url: '/agentmerchantInfo/:id',
                  templateUrl: 'tpl/system/sys_agentmerchant_info.html',
               /*   resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                 return $ocLazyLoad.load('js/controllers/yunpos/sysAgentmerchantGrid.js');
                              }
                          );
                      }]*/
               resolve: {
                   deps: ['uiLoad',
                     function( uiLoad ){
                       return uiLoad.load( ['js/controllers/yunpos/sysAgentmerchantGrid.js'] );
                   }]
               }
              })
              .state('app.table.pay', {//支付管理
                  url: '/pay/:id',
                  templateUrl: 'tpl/system/sys_pay.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/yunpos/sysPayGrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.alipaPayEdit', {//用户管理
                  url: '/pay/alipay/:id',
                  templateUrl: 'tpl/system/sys_pay_alipay_edit.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.alipayBar', {//用户管理
                  url: '/pay/alipay/bar/:id',
                  templateUrl: 'tpl/system/pay/sys_alipay_bar.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
               .state('app.table.alipayScan', {//用户管理
                  url: '/pay/alipay/scan/:id',
                  templateUrl: 'tpl/system/pay/sys_alipay_scan.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
               .state('app.table.alipayWap', {//用户管理
                  url: '/pay/alipay/wap/:id',
                  templateUrl: 'tpl/system/pay/sys_alipay_wap.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.wechatPayEdit', {//用户管理
                  url: '/pay/wechat/:id',
                  templateUrl: 'tpl/system/sys_pay_wechat_edit.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.wechatBar', {//用户管理
                  url: '/pay/wechat/bar/:id',
                  templateUrl: 'tpl/system/pay/sys_wechat_bar.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.wechatScan', {//用户管理
                  url: '/pay/wechat/scan/:id',
                  templateUrl: 'tpl/system/pay/sys_wechat_scan.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              
              //测试页面
               .state('app.table.alipayBarTest', {//用户管理
                  url: '/test/alipay/bar/test',
                  templateUrl: 'tpl/system/pay/sys_alipay_bar_test.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
               .state('app.table.alipayScanTest', {//用户管理
                  url: '/test/alipay/scan/test',
                  templateUrl: 'tpl/system/pay/sys_alipay_scan_test.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.alipayWapTest', {//用户管理
                  url: '/test/alipay/wap/test',
                  templateUrl: 'tpl/system/pay/sys_alipay_wap_test.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.wechatBarTest', {//用户管理
                 url: '/test/wechat/bar/test',
                 templateUrl: 'tpl/system/pay/sys_wechat_bar_test.html',
                  //templateUrl: 'tpl/system/sys_alipay_test_tab.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.wechatScanTest', {//用户管理
                  url: '/test/wechat/scan/test',
                  templateUrl: 'tpl/system/pay/sys_wechat_scan_test.html',
                  resolve: {
            		  deps: ['uiLoad',
            		         function( uiLoad ){
            			  return uiLoad.load( ['js/controllers/yunpos/sysPayGrid.js'] );
            		  }]
            	  }
              })
              .state('app.table.paypassword', {//支付密码
                  url: '/paypassword/:id',
                  templateUrl: 'tpl/system/sys_paypassword.html',
	                  resolve: {
	                      deps: ['$ocLazyLoad',
	                        function( $ocLazyLoad ){
	                          return $ocLazyLoad.load('ngGrid').then(
	                              function(){
	                                  return $ocLazyLoad.load('js/controllers/yunpos/sysUserGrid.js');
	                              }
	                          );
	                      }]
	                  }
              })          
              // form
              .state('app.form', {
                  url: '/form',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad){
                          return uiLoad.load('js/controllers/form.js');
                      }]
                  }
              })
              .state('app.form.elements', {
                  url: '/elements',
                  templateUrl: 'tpl/form_elements.html'
              })
              .state('app.form.validation', {
                  url: '/validation',
                  templateUrl: 'tpl/form_validation.html'
              })
              .state('app.form.wizard', {
                  url: '/wizard',
                  templateUrl: 'tpl/form_wizard.html'
              })
              .state('app.form.fileupload', {
                  url: '/fileupload',
                  templateUrl: 'tpl/form_fileupload.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('angularFileUpload').then(
                              function(){
                                 return $ocLazyLoad.load('js/controllers/file-upload.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.imagecrop', {
                  url: '/imagecrop',
                  templateUrl: 'tpl/form_imagecrop.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('ngImgCrop').then(
                              function(){
                                 return $ocLazyLoad.load('js/controllers/imgcrop.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.select', {
                  url: '/select',
                  templateUrl: 'tpl/form_select.html',
                  controller: 'SelectCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ui.select').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/select.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.slider', {
                  url: '/slider',
                  templateUrl: 'tpl/form_slider.html',
                  controller: 'SliderCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('vr.directives.slider').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/slider.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.editor', {
                  url: '/editor',
                  templateUrl: 'tpl/form_editor.html',
                  controller: 'EditorCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('textAngular').then(
                              function(){
                                  return $ocLazyLoad.load('js/controllers/editor.js');
                              }
                          );
                      }]
                  }
              })
              
              // pages
              .state('app.page', {
                  url: '/page',
                  template: '<div ui-view class="fade-in-down"></div>'
              })
              .state('app.page.profile', {
                  url: '/profile',
                  templateUrl: 'tpl/page_profile.html'
              })
              .state('app.page.post', {
                  url: '/post',
                  templateUrl: 'tpl/page_post.html'
              })
              .state('app.page.search', {
                  url: '/search',
                  templateUrl: 'tpl/page_search.html'
              })
              .state('app.page.invoice', {
                  url: '/invoice',
                  templateUrl: 'tpl/page_invoice.html'
              })
              .state('app.page.price', {
                  url: '/price',
                  templateUrl: 'tpl/page_price.html'
              })
              .state('app.docs', {
                  url: '/docs',
                  templateUrl: 'tpl/docs.html'
              })
              // others
              .state('lockme', {
                  url: '/lockme',
                  templateUrl: 'tpl/page_lockme.html'
              })
             
              

              // fullCalendar
              .state('app.calendar', {
                  url: '/calendar',
                  templateUrl: 'tpl/app_calendar.html',
                  // use resolve to load other dependences
                  resolve: {
                      deps: ['$ocLazyLoad', 'uiLoad',
                        function( $ocLazyLoad, uiLoad ){
                          return uiLoad.load(
                            ['vendor/jquery/fullcalendar/fullcalendar.css',
                              'vendor/jquery/fullcalendar/theme.css',
                              'vendor/jquery/jquery-ui-1.10.3.custom.min.js',
                              'vendor/libs/moment.min.js',
                              'vendor/jquery/fullcalendar/fullcalendar.min.js',
                              'js/app/calendar/calendar.js']
                          ).then(
                            function(){
                              return $ocLazyLoad.load('ui.calendar');
                            }
                          )
                      }]
                  }
              })

              // mail
              .state('app.mail', {
                  abstract: true,
                  url: '/mail',
                  templateUrl: 'tpl/mail.html',
                  // use resolve to load other dependences
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/mail/mail.js',
                                               'js/app/mail/mail-service.js',
                                               'vendor/libs/moment.min.js'] );
                      }]
                  }
              })
              .state('app.mail.list', {
                  url: '/inbox/{fold}',
                  templateUrl: 'tpl/mail.list.html'
              })
              .state('app.mail.detail', {
                  url: '/{mailId:[0-9]{1,4}}',
                  templateUrl: 'tpl/mail.detail.html'
              })
              .state('app.mail.compose', {
                  url: '/compose',
                  templateUrl: 'tpl/mail.new.html'
              })

              .state('layout', {
                  abstract: true,
                  url: '/layout',
                  templateUrl: 'tpl/layout.html'
              })
              .state('layout.fullwidth', {
                  url: '/fullwidth',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_fullwidth.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_fullwidth.html'
                      }
                  },
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/vectormap.js'] );
                      }]
                  }
              })
              .state('layout.mobile', {
                  url: '/mobile',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_mobile.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_mobile.html'
                      }
                  }
              })
              .state('layout.app', {
                  url: '/app',
                  views: {
                      '': {
                          templateUrl: 'tpl/layout_app.html'
                      },
                      'footer': {
                          templateUrl: 'tpl/layout_footer_fullwidth.html'
                      }
                  },
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/controllers/tab.js'] );
                      }]
                  }
              })
              .state('apps', {
                  abstract: true,
                  url: '/apps',
                  templateUrl: 'tpl/layout.html'
              })
              .state('apps.note', {
                  url: '/note',
                  templateUrl: 'tpl/apps_note.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/note/note.js',
                                               'vendor/libs/moment.min.js'] );
                      }]
                  }
              })
              .state('apps.contact', {
                  url: '/contact',
                  templateUrl: 'tpl/apps_contact.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/contact/contact.js'] );
                      }]
                  }
              })
              .state('app.weather', {
                  url: '/weather',
                  templateUrl: 'tpl/apps_weather.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(
                              {
                                  name: 'angular-skycons',
                                  files: ['js/app/weather/skycons.js',
                                          'vendor/libs/moment.min.js', 
                                          'js/app/weather/angular-skycons.js',
                                          'js/app/weather/ctrl.js' ] 
                              }
                          );
                      }]
                  }
              })
              .state('music', {
                  url: '/music',
                  templateUrl: 'tpl/music.html',
                  controller: 'MusicCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load([
                            'com.2fdevs.videogular', 
                            'com.2fdevs.videogular.plugins.controls', 
                            'com.2fdevs.videogular.plugins.overlayplay',
                            'com.2fdevs.videogular.plugins.poster',
                            'com.2fdevs.videogular.plugins.buffering',
                            'js/app/music/ctrl.js', 
                            'js/app/music/theme.css'
                          ]);
                      }]
                  }
              })
                .state('music.home', {
                    url: '/home',
                    templateUrl: 'tpl/music.home.html'
                })
                .state('music.genres', {
                    url: '/genres',
                    templateUrl: 'tpl/music.genres.html'
                })
                .state('music.detail', {
                    url: '/detail',
                    templateUrl: 'tpl/music.detail.html'
                })
                .state('music.mtv', {
                    url: '/mtv',
                    templateUrl: 'tpl/music.mtv.html'
                })
                .state('music.mtvdetail', {
                    url: '/mtvdetail',
                    templateUrl: 'tpl/music.mtv.detail.html'
                })
                .state('music.playlist', {
                    url: '/playlist/{fold}',
                    templateUrl: 'tpl/music.playlist.html'
                })

              .state('app.role_authority', {
                  url:'/role_authority',
                  templateUrl: 'tpl/system/role_authority.html'
              })
              .state('app.authority_man', {
                  url:'/authority_man',
                  templateUrl: 'tpl/system/authority_man.html'
              }) 
              .state('app.table.sysCardTemplate', {//云卡券
            	  url: '/sysCardTemplate',
                  templateUrl: 'tpl/system/card/sys_card_template.html',
                      resolve: {
                          deps: ['$ocLazyLoad',
                            function( $ocLazyLoad ){
                              return $ocLazyLoad.load('ngGrid').then(
                                  function(){
                                      return $ocLazyLoad.load('js/controllers/yunpos/card/sysCardTemplateGrid.js');
                                  }
                              );
                          }]
                      }
                	  
          }) .state('app.table.sysCardTemplateAdd', {//用户新增
              url: '/sysCardTemplateAdd',
              templateUrl: 'tpl/system/card/sys_card_template_add.html',
              resolve: {
                  deps: ['$ocLazyLoad',
                    function( $ocLazyLoad ){
                      return $ocLazyLoad.load('ngGrid').then(
                          function(){
                              return $ocLazyLoad.load('js/controllers/yunpos/card/sysCardTemplateGrid.js');
                          }
                      );
                  }]
              }
          })
      }
    ]
  );