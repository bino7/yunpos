'use strict';


angular.module('app', [
    'ngAnimate',
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
    'infinite-scroll'
]);
var formatDateTime = function (date) {  
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
};  
//var app = angular.module('app', ['ngTouch', 'ui.grid', 'ui.grid.edit', 'ui.grid.cellNav', 'addressFormatter']);