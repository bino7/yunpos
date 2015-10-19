'use strict';

var modules = ['ngAnimate',
               'ngCookies',
               'ngResource',
               'ngSanitize',
               'ngTouch',
               'ngStorage',
               'ui.router',
               'ui.bootstrap',
               'ui.load',
               'ui.jq',
               'ui.validate',
               'oc.lazyLoad',
               'pascalprecht.translate',
               'ui.grid', 
               'ui.grid.treeView',
               'ui.grid.edit', 
               'ui.grid.cellNav',
               'infinite-scroll',
               'ngFileUpload',
               'ngTreetable',
               'integralui',
                'dataAuthority',
                'jsTag',
                'isteven-multi-select'

               ];
//全局app
var app = angular.module('app', modules), permissionList,user;


var formatDateTime = function (date) {  
	if(date instanceof Date){
	    var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? ('0' + m) : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    var h = date.getHours(); 
	    h = h < 10 ? ('0' + h) : h;  
	    var minute = date.getMinutes();  
	    minute = minute < 10 ? ('0' + minute) : minute; 
	    var s = date.getSeconds();  
	    s = s < 10 ? ('0' + s) : s; 
	    return y + '-' + m + '-' + d+' '+h+':'+minute + ":" + s;  
	}else {
		return date;
	}
	
};  
/**
 * 日期转换为字符串
 */
var parseDateTime = function formatDate(date, format) {
    if (arguments.length < 2 && !date.getTime) {
        format = date;
        date = new Date();
    }
    typeof format != 'string' && (format = 'YYYY年MM月DD日 hh时mm分ss秒');
    var week = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', '日', '一', '二', '三', '四', '五', '六'];
    return format.replace(/YYYY|YY|MM|DD|hh|mm|ss|星期|周|www|week/g, function(a) {
        switch (a) {
        case "YYYY": return date.getFullYear();
        case "YY": return (date.getFullYear()+"").slice(2);
        case "MM": return date.getMonth() + 1;
        case "DD": return date.getDate();
        case "hh": return date.getHours();
        case "mm": return date.getMinutes();
        case "ss": return date.getSeconds();
        case "星期": return "星期" + week[date.getDay() + 7];
        case "周": return "周" +  week[date.getDay() + 7];
        case "week": return week[date.getDay()];
        case "www": return week[date.getDay()].slice(0,3);
        }
    });
}
//var app = angular.module('app', ['ngTouch', 'ui.grid', 'ui.grid.edit', 'ui.grid.cellNav', 'addressFormatter']);