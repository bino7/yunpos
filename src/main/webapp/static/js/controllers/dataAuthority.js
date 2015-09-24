'use strict';

/* Controllers */
  // signin controller
        app.controller('dataAuthorityCtlr', ['$scope', '$http', '$state','$resource','$templateCache',
            'DataAuthorityResource','DifinitionValues','FilterDifinition','Filter', 'FilterGroup',
    function($scope, $http, $state,$resource,$templateCache,DataAuthorityResource,DifinitionValues,
             FilterDifinition,Filter,FilterGroup) {
    $scope.active = function () {
        return $scope.tabs.filter(function (tab) {
            return tab.active;
        })[0];
    };
        $scope.counter=1;$scope.greeting="hi bino";
    $scope.resources=DataAuthorityResource.query();
    $scope.cur={};
    $scope.errorMessage=null;
    $scope.opMapper={
        "EQ":"=","GE":">=","GT":">","LE":"<=","LT":"<","LIKE":"like","IN":"in"
    }
    $scope.opCodeMapper={
        "EQ":0,"GE":1,"GT":2,"LE":3,"LT":4,"LIKE":5,"IN":6
    }
    $scope.codeOpMapper={
        0:"=",1:">=",2:">",3:"<=",4:"<",5:"like",6:"in"
    }
    $scope.action=null;
    $scope.initGroup = function(g){
        g.editFilter=new Filter($scope.res.id);
        g.editFilter.group_id= g.id;
        g.editDifinition=new FilterDifinition();
    }
    $scope.initFilter = function(filter){
        filter.difinition.values=new DifinitionValues($scope.res.id,filter.difinition.id)
    }

    $scope.selectOp = function(filter,op){
        filter.op=op;
    }

    $scope.initDif = function(d){
        d.values=new DifinitionValues($scope.res.id,d.id);
        d.values.nextPage();
    }
    $scope.deleteFilter=function(f){
        $scope.filter.delete(f);
        $scope.refresh();
    }
    $scope.addFilter = function(filter){
        if(filter==null){
            alert(1);
            return;
        }
        if(filter.op==null){
            alert(2);
            $scope.errorMessage="选择一个操作";
            return;
        }
        if(filter.filter_value==null){
            alert(3);
            $scope.errorMessage="选择值";
            return;
        }
        $scope.filter.save(filter);
        $scope.refresh();
    }

    $scope.refresh=function(){
        $http.get("/resource").then(function(response) {
            $scope.resources=response.data;
        });
        var rid=$scope.res.id;
        $http.get("/resource/"+rid).then(function(response) {
            $scope.res=response.data;
        });
    }
    $scope.addFilterGroup = function (){
        $scope.filterGroup=new FilterGroup($scope.res.id);
        var filterGroup=$scope.filterGroup;
        filterGroup.resource_id=$scope.res.id;
        filterGroup.parent_id=0;
        filterGroup.save(filterGroup);
        $scope.refresh();
        $scope.filterGroup=null;
    }
    $scope.removeFilterGroup = function (g){
        $scope.filterGroup=new FilterGroup($scope.res.id);
        var filterGroup=$scope.filterGroup;
        filterGroup.remove(g);
        $scope.refresh();
        $scope.filterGroup=null;
    }
        $scope.saveOrUpdateRes=function(){
        if($scope.editRes.id==null) {
            DataAuthorityResource.save($scope.editRes);
        }else{
            DataAuthorityResource.update($scope.editRes);
        }
        $scope.resources = DataAuthorityResource.query();
        $scope.initEditResDifinitions($scope.editRes.id);
    }
    $scope.saveDifinition=function(d){

    }
    $scope.initEditResDifinitions=function(rid){
        $scope.editResDifiSer=new FilterDifinition(rid)
        $scope.editResDifinitions=$scope.editResDifiSer.query();
    }
    $scope.addDifinition=function(){
        $scope.editResDifinitions.push(new FilterDifinition($scope.editRes.id));
    }
    $scope.addResource=function(){
        $scope.action="EDIT_RESOURCE";
        $scope.editRes=new DataAuthorityResource();
    }
        $scope.editResource=function(res){
            $scope.action="EDIT_RESOURCE";
            $scope.editRes=res;
            $scope.initEditResDifinitions($scope.editRes.id);
        }
        $scope.deleteResource=function(res){
            DataAuthorityResource.remove(res);
            $scope.resources=DataAuthorityResource.query();
        }
    $scope.editRule = function(curRes){
        $scope.action="EDIT_RULE";
        $scope.res=curRes;
        $scope.cur.filterDifinition=new FilterDifinition(curRes.id);
        $scope.cur.difinitions = $scope.cur.filterDifinition.query();
        $scope.filter= new Filter($scope.res.id);
    };}])

    .factory('DataAuthorityResource', function($resource) {
        return $resource('/resource/:id',{id:'@_id'},{
            update:{
                method: 'PUT'
            }
        });
    })
    .directive("resource",function(){
        return {
            templateUrl: 'tpl/system/resource.html',
            transclude: true
        }
    })
    .directive("rule",function(){
        return {
            templateUrl: 'tpl/system/rule.html',
            transclude: true
        }
    })
    .factory('DifinitionValues', function($http) {
    var DifinitionValues = function(rid,did) {
        this.rid=rid;
        this.did=did;
        this.total=-1;
        this.items = [];
        this.busy = false;
        this.page = 0;
    };
    DifinitionValues.prototype.nextPage = function() {
        if (this.busy || (this.total!=-1 && this.total<this.items.length==false)) return;
        this.busy = true;
        this.page=this.page+1;
        var url="/resource/"+this.rid+"/filterDifinition/"+this.did+"/value/slice?offset="+(this.page-1)*50+"&limit=50";
        $http.get(url).success(function(data) {
            this.total=data.total;
            var items = data.valueList;
            for (var i = 0; i < items.length; i++) {
                this.items.push(items[i]);
            }
            this.busy = false;
        }.bind(this));
    };
    return DifinitionValues;
    })
    .factory('FilterDifinition', function($resource) {
        var FilterDifinition = function(rid){
            return $resource('/resource/'+rid+'/filterDifinition/:id');
        };

        return FilterDifinition;
    })
    .factory('Filter', function($resource) {
        var Filter = function(rid){
            var ret=$resource('/resource/'+rid+'/filter/:id');
            this.save=ret.save;
            this.delete=ret.delete;
        };
        return Filter;
    })
    .factory('FilterGroup', function($resource) {
        var FilterGroup = function(rid){
            var ret=$resource('/resource/'+rid+'/filterGroup/:id');
            this.save=ret.save;
            this.remove=ret.remove;
        };
        return FilterGroup;
    });


