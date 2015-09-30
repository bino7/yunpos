'use strict';

/* Controllers */
  // signin controller
        app.controller('dataAuthorityCtlr', ['$scope', '$http', '$state','$resource','$templateCache',
            'DataAuthorityResource','DifinitionValues','FilterDifinition','Filter', 'FilterGroup','JSTagsCollection',
    function($scope, $http, $state,$resource,$templateCache,DataAuthorityResource,DifinitionValues,
             FilterDifinition,Filter,FilterGroup,JSTagsCollection) {
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
    $scope.modernBrowsers = [
        {	name: "Opera",ticked: true	},
        {	icon: "[...]/internet_explorer.png...",	name: "Internet Explorer",	maker: "Microsoft",	ticked: false	},
        {	icon: "[...]/firefox-icon.png...",	name: "Firefox",	maker: "Mozilla Foundation",	ticked: true	},
        {	icon: "[...]/safari_browser.png...",	name: "Safari",	maker: "Apple",	ticked: false	},
        {	icon: "[...]/chrome.png...",	name: "Chrome",	maker: "Google",	ticked: true	}
    ];
    $scope.initGroup = function(g){
        g.editFilter=new Filter($scope.res.id);
        g.editFilter.resourceId= $scope.res.id;
        g.editFilter.groupId= g.id;
        g.editDifinition=new FilterDifinition($scope.res.id);
    }

    $scope.selectOp = function(filter,op){
        filter.op=op;
    }

    /*$scope.initDif = function(d){
        d.values=new DifinitionValues($scope.res.id,d.id);
        d.values.nextPage();
    }*/

        $scope.initFilter=function(f){
            if(f.valueType==2) {
                var mutilValues = [];
                var fvalues = f.filterValue.split(",").slice(0,-1);
                for (var i = 0; i < f.difiniValues.length; i++) {
                    var ticked = false;
                    for (var j = 0; j < fvalues.length; j++) {
                        if (fvalues[j] == f.difiniValues[i]) {
                            ticked = true;
                            break;
                        }
                    }
                    mutilValues.push({value: f.difiniValues[i], ticked: ticked});
                }
                f.mutilValues = mutilValues;
                f.outMutilValues=[];
            }
        }

        $scope.initEditFilter=function(g){
            if(g.editDifinition.valueType==2) {
                var mutilValues = [];
                for (var i = 0; i < g.editDifinition.values.length; i++) {
                    var ticked = false;
                    mutilValues.push({value: g.editDifinition.values[i], ticked: ticked});
                }
                g.editFilter.mutilValues = mutilValues;
                g.editFilter.outMutilValues=[];
            }
        }

        $scope.fClick=function(data,f){
            console.info(data);
            console.info(f);
            var filterValue="";
            for (var i=0;i<f.mutilValues.length;i++) {
                var v= f.mutilValues[i];
                if(v.ticked){
                    filterValue+= v.value+",";
                }
            }
            f.filterValue=filterValue;
            if(f.id!=null){
                $scope.updateFilter(f);
            }
        }

    $scope.updateFilter=function(f){
        console.info(f);
        $http.put("/resource/"+$scope.res.id+"/filter",f).then(function(){
            $scope.refresh();
        });
    }
    $scope.deleteFilter=function(f){
        $http.delete("/resource/"+$scope.res.id+"/filter/"+ f.id).then(function(){
            $scope.refresh();
        });
    }
    $scope.addFilter = function(f){
        if(f==null){
            return;
        }
        if(f.op==null){
            $scope.errorMessage="选择一个操作";
            return;
        }
        if(f.mutilValues!=null){
            var filterValue="";
            for (var i=0;i<f.mutilValues.length;i++) {
                var v= f.mutilValues[i];
                if(v.ticked){
                    filterValue+= v.value+",";
                }
            }
            f.filterValue=filterValue;
        }
        if(f.filterValue==null){
            $scope.errorMessage="选择值";
            return;
        }

        /*if(dif.dataType==0 || dif.dataType==2){
            filter.filter_value='"'+filter.filter_value+'"';
        }*/
        $http.post("/resource/"+ $scope.res.id+"/filter",f).then(function(){
            $scope.refresh();
        });
    }
        $scope.filterMutilValues=function(filter){
            var mutilValues=[];
            for(var i=0;i<filter.difiniValues.length;i++){
                mutilValues.push({value:filter.difiniValues[i],ticked:false});
            }
            return mutilValues;
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
            $http.post("/resource",$scope.editRes).then(function(rsp){
                $scope.editRes=rsp.data;
                $scope.resources = DataAuthorityResource.query();
                $scope.initEditResDifinitions($scope.editRes.id);
            });
        }else{
                DataAuthorityResource.update($scope.editRes);
                $scope.resources = DataAuthorityResource.query();
                $scope.initEditResDifinitions($scope.editRes.id);
        }
    }

    $scope.saveDifinition=function(d){

        var tags= d.jsTagOptions.tags;
        d.values=tags.getTagValues();

        if(d.id==null){
            $http.post("/resource/"+ $scope.editRes.id+"/filterDifinition",d).then(function(){
                $scope.initEditResDifinitions(d.resource_id);
            });
        }else{
            $http.put("/resource/"+ $scope.editRes.id+"/filterDifinition/"+ d.id,d).then(function(){
                $scope.initEditResDifinitions(d.resource_id);
            });
        }

    }

    $scope.removeDifinition=function(d){
        var server=new FilterDifinition(d.resource_id);
        server.delete(d);
        $scope.initEditResDifinitions(d.resource_id);
    }
    $scope.initEditResDifinitions=function(rid){
        var server=new FilterDifinition(rid);
        $scope.editResDifinitions=server.query();
    }
    $scope.initJsTag=function(d){
        var jsTagOptions={
            'edit': true,
            'defaultTags': [],
            'breakCodes': [
                13, // Return
            ],
            'splitter': ";",
            'texts': {
                'inputPlaceHolder': "bbyong",
                'removeSymbol': String.fromCharCode(215)
            }
        };

        var collection=new JSTagsCollection(d.values);

        jsTagOptions.tags=collection;

        var filterSameValue=function(tag){
            var tags=collection.tags;
            for(var i=0;i<collection.tagsCounter;i++){
                if(tags[i].value==tag.value){
                    return;
                }
            }
        }

        //collection._onBeforeAddListenerList=[filterSameValue];
        //collection._onAddListenerList=[addValue];

        d.jsTagOptions=jsTagOptions;
    }
    $scope.addDifinition=function(){


        var newDifinition={resource_id:$scope.editRes.id,values:[]};

        $scope.initJsTag(newDifinition);

        $scope.editResDifinitions.push(newDifinition);
    }
    $scope.filterSameValue=function(tag){
        console.info($scope.tags);
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
        $http.get("/resource/"+$scope.res.id+"/filterDifinition").then(function(rsp){
            $scope.difinitions = rsp.data;
        });
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
    .factory('DifinitionValues', function($resource) {
        var DifinitionValues = function(rid,did){
            return $resource('/resource/'+rid+'/filterDifinition/'+did+"/values");
        };
        return DifinitionValues;
    })
    /*.factory('DifinitionValues', function($http) {
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
    })*/
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
    })
            .factory('JSTagsCollection', ['JSTag', '$filter', function(JSTag, $filter) {

                // Constructor
                function JSTagsCollection(defaultTags) {
                    this.tags = {};
                    this.tagsCounter = 0;
                    for (var defaultTagKey in defaultTags) {
                        var defaultTagValue = defaultTags[defaultTagKey];
                        this.addTag(defaultTagValue);
                    }

                    this._onBeforeAddListenerList=[];
                    this._onAddListenerList = [];
                    this._onRemoveListenerList = [];

                    this.unsetActiveTags();
                    this.unsetEditedTag();
                }

                // *** Methods *** //

                // *** Object manipulation methods *** //

                // Adds a tag with received value
                JSTagsCollection.prototype.addTag = function(value) {
                    angular.forEach(this._onBeforeAddListenerList, function (callback) {
                        callback(newTag);
                    });
                    var tagIndex = this.tagsCounter;
                    this.tagsCounter++;

                    var newTag = new JSTag(value, tagIndex);
                    this.tags[tagIndex] = newTag;
                    angular.forEach(this._onAddListenerList, function (callback) {
                        callback(newTag);
                    });
                }

                // Removes the received tag
                JSTagsCollection.prototype.removeTag = function(tagIndex) {
                    var tag = this.tags[tagIndex];
                    delete this.tags[tagIndex];
                    angular.forEach(this._onRemoveListenerList, function (callback) {
                        callback(tag);
                    });
                }

                JSTagsCollection.prototype.onAdd = function onAdd(callback) {
                    this._onAddListenerList.push(callback);
                };

                JSTagsCollection.prototype.onRemove = function onRemove(callback) {
                    this._onRemoveListenerList.push(callback);
                };

                // Returns the number of tags in collection
                JSTagsCollection.prototype.getNumberOfTags = function() {
                    return getNumberOfProperties(this.tags);
                }

                // Returns an array with all values of the tags
                JSTagsCollection.prototype.getTagValues = function() {
                    var tagValues = [];
                    for (var tag in this.tags) {
                        tagValues.push(this.tags[tag].value);
                    }
                    return tagValues;
                }

                // Returns the previous tag before the tag received as input
                // Returns same tag if it's the first
                JSTagsCollection.prototype.getPreviousTag = function(tag) {
                    var firstTag = getFirstProperty(this.tags);
                    if (firstTag.id === tag.id) {
                        // Return same tag if we reached the beginning
                        return tag;
                    } else {
                        return getPreviousProperty(this.tags, tag.id);
                    }
                }

                // Returns the next tag after  the tag received as input
                // Returns same tag if it's the last
                JSTagsCollection.prototype.getNextTag = function(tag) {
                    var lastTag = getLastProperty(this.tags);
                    if (tag.id === lastTag.id) {
                        // Return same tag if we reached the end
                        return tag;
                    } else {
                        return getNextProperty(this.tags, tag.id);
                    }
                }

                // *** Active methods *** //

                // Checks if a specific tag is active
                JSTagsCollection.prototype.isTagActive = function(tag) {
                    return $filter("inArray")(tag, this._activeTags);
                };

                // Sets tag to active
                JSTagsCollection.prototype.setActiveTag = function(tag) {
                    if (!this.isTagActive(tag)) {
                        this._activeTags.push(tag);
                    }
                };

                // Sets the last tag to be active
                JSTagsCollection.prototype.setLastTagActive = function() {
                    if (getNumberOfProperties(this.tags) > 0) {
                        var lastTag = getLastProperty(this.tags);
                        this.setActiveTag(lastTag);
                    }
                };

                // Unsets an active tag
                JSTagsCollection.prototype.unsetActiveTag = function(tag) {
                    var removedTag = this._activeTags.splice(this._activeTags.indexOf(tag), 1);
                };

                // Unsets all active tag
                JSTagsCollection.prototype.unsetActiveTags = function() {
                    this._activeTags = [];
                };

                // Returns a JSTag only if there is 1 exactly active tags, otherwise null
                JSTagsCollection.prototype.getActiveTag = function() {
                    var activeTag = null;
                    if (this._activeTags.length === 1) {
                        activeTag = this._activeTags[0];
                    }

                    return activeTag;
                };

                // Returns number of active tags
                JSTagsCollection.prototype.getNumOfActiveTags = function() {
                    return this._activeTags.length;
                };

                // *** Edit methods *** //

                // Gets the edited tag
                JSTagsCollection.prototype.getEditedTag = function() {
                    return this._editedTag;
                };

                // Checks if a tag is edited
                JSTagsCollection.prototype.isTagEdited = function(tag) {
                    return tag === this._editedTag;
                };

                // Sets the tag in the _editedTag member
                JSTagsCollection.prototype.setEditedTag = function(tag) {
                    this._editedTag = tag;
                };

                // Unsets the 'edit' flag on a tag by it's given index
                JSTagsCollection.prototype.unsetEditedTag = function() {
                    // Kill empty tags!
                    if (this._editedTag !== undefined &&
                        this._editedTag !== null &&
                        this._editedTag.value === "") {
                        this.removeTag(this._editedTag.id);
                    }

                    this._editedTag = null;
                }

                return JSTagsCollection;
            }]);

// *** Extension methods used to iterate object like a dictionary. Used for the tags. *** //
// TODO: Find another place for these extension methods. Maybe filter.js
// TODO: Maybe use a regular array instead and delete them all :)

// Gets the number of properties, including inherited
function getNumberOfProperties(obj) {
    return Object.keys(obj).length;
}

// Get the first property of an object, including inherited properties
function getFirstProperty(obj) {
    var keys = Object.keys(obj);
    return obj[keys[0]];
}

// Get the last property of an object, including inherited properties
function getLastProperty(obj) {
    var keys = Object.keys(obj);
    return obj[keys[keys.length - 1]];
}

// Get the next property of an object whos' properties keys are numbers, including inherited properties
function getNextProperty(obj, propertyId) {
    var keys = Object.keys(obj);
    var indexOfProperty = keys.indexOf(propertyId.toString());
    var keyOfNextProperty = keys[indexOfProperty + 1];
    return obj[keyOfNextProperty];
}

// Get the previous property of an object whos' properties keys are numbers, including inherited properties
function getPreviousProperty(obj, propertyId) {
    var keys = Object.keys(obj);
    var indexOfProperty = keys.indexOf(propertyId.toString());
    var keyOfPreviousProperty = keys[indexOfProperty - 1];
    return obj[keyOfPreviousProperty];
}



