var URL = $ctx + "/ajax/user";

//var jqGridNavBarOptions = {edit:true, add:true, del:true, search:true, refresh:true};
//var jqGridEditOptions = {reloadAfterSubmit:false, mtype:"PUT",  modal: true, closeAfterEdit:true, recreateForm:true, serializeEditData:serializeEditDataCallback, errorTextFormat:errorTextFormatCallback, onclickSubmit:onclickSubmitEditCallback, afterSubmit:afterSubmitCallback, onclickPgButtons:cleanEditForm};
//var jqGridAddOptions =  {reloadAfterSubmit:false, mtype:"POST", modal: true, closeAfterAdd:true,  recreateForm:true, serializeEditData:serializeEditDataCallback, errorTextFormat:errorTextFormatCallback,                                          afterSubmit:afterSubmitCallback};
//var jqGridDelOptions =  {reloadAfterSubmit:false, mtype:"DELETE",                                                    serializeDelData:serializeDelDataCallback,   errorTextFormat:errorTextFormatCallback, onclickSubmit:onclickSubmitDelCallback};
//var jqGridSearchOptions = {sopt:["cn","bw","eq","ne","lt","gt","ew"]};


var jqGridNavBarOptions = {
		edit : true,
		add : true,
		del : true,
		search : true,
		refresh : true
	};
	var jqGridEditOptions = {
		reloadAfterSubmit : false,
		mtype : "PUT",
		modal : true,
		closeAfterEdit : true,
		recreateForm : true,
		serializeEditData : serializeEditDataCallback,
		errorTextFormat : errorTextFormatCallback,
		onclickSubmit : onclickSubmitEditCallback,
		afterSubmit : afterSubmitCallback,
		onclickPgButtons : cleanEditForm
	};
	var jqGridAddOptions = {
		reloadAfterSubmit : false,
		mtype : "POST",
		modal : true,
		closeAfterAdd : true,
		recreateForm : true,
		serializeEditData : serializeEditDataCallback,
		errorTextFormat : errorTextFormatCallback,
		afterSubmit : afterSubmitCallback
	};
	var jqGridDelOptions = {
		reloadAfterSubmit : false,
		mtype : "DELETE",
		serializeDelData : serializeDelDataCallback,
		errorTextFormat : errorTextFormatCallback,
		onclickSubmit : onclickSubmitDelCallback
	};
	var jqGridSearchOptions = {
		sopt : ["cn", "bw", "eq", "ne", "lt", "gt", "ew"]
	};


$(document).ready(function() {
	//setupGrid();
	$("#kdtBtn").click(function(){
		//client_id:7718b70dad904f6f3bclient_secret:05703a2a336f893f53f72d6406bce731client_name:云铺redirect_uri:http://y.o2o520.com
       var client_id = "7718b70dad904f6f3b";
       var response_type = "code";
       var state = "gdylstate";     
       var redirect_uri= "http://y.o2o520.com/index.php?g=User";
       var serialNo = "08600719332";//商户编号
       
       
       
       var url = "https://open.koudaitong.com/oauth/authorize?client_id=" + client_id;
       url += "&response_type=" + response_type;
       url += "&state=" + state;
       //url += "&redirect_uri=" + window.location;
       url += "&redirect_uri=" + redirect_uri; 
       //location.href = url;
       
 /*     
       var code = getParam("code");
       if (code == null) {
    	   location.href = url;
       }else {
    	 	$.ajax({
   		    type:"GET",
		    url : $ctx+"/ajax/kdtToken",
		    async : false,
		    data: { "grant_type": "authorization_code","code":code,"redirect_uri":redirect_uri,"serialNo":serialNo},
		    success : function (data) {
			  alert(data);
		    }
	    });  
       }
 */   
       
       var code= "d70e5ac1e806f2146596e81a8e5ff0622478cd22";


   	$.ajax({
   		type:"GET",
		url : $ctx+"/ajax/kdtToken",
		async : false,
		data: { "grant_type": "authorization_code","code":code,"redirect_uri":redirect_uri,"serialNo":serialNo},
		success : function (data) {
			alert(data);
		}
	});

		
		
		
		
		
	});
	
});

function getParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function setupGrid() {
	var statuss = {0:"停用",1:"正常"};
	
	$("#grid").jqGrid({
		url:URL, // source of data for the grid
		editurl:URL, // URL that is used to add/edit/delete records
		datatype:"json", // type of server response; request data type is configured separately 
		jsonReader:{repeatitems:false, id:"id"}, // the unique ID of a row
		colModel:[  
	              {name: "id" ,index: "id" ,label:"ID",sortable:true,readonly:true,editable : true,hidden:true},
	              {name:"usgId",index:"usgId",label:"组ID",width:40,editable : true,hidden:true},
	              {name:"userName",index:"userName",label:"用户名",width:160,editable : true}, 
	              {name:"email",index:"email",label:"电子邮箱",width:160,editable : true},
	              {name:"phone",index:"phone",label:"电话",width:160,editable : true},
	              {name:"password",index:"password",label:"密码",width:160,editable : true,hidden:true},
	              {name:"nickname",index:"nickname",label:"昵称",width:120,editable : true},
	              {name:"fullname",index:"fullname",label:"全名",width:120,editable : true},
	              {name:"status",index:"status",label:"状态",width:80,editable : true,
	            	  edittype:'select',
	            	  editoptions : {value: "0:停用;1:正常",defaultValue:1},
	            	  formatter:function(v,opt,rec){return statuss[v]},
	            	  unformat:function(v){if(v=='停用')return '0';return '1'}
	              },
	              //{name:"salt",index:"salt",label:"盐值",width:120,editable : true},
	              {name:"description",index:"description",label:"描述",width:120,editable : true,edittype:'textarea'},
	              //{name:"source",index:"source",label:"来源",width:80,editable : true},
	              {name:"lastLoginDatetime",index:"lastLoginDatetime",label:"最后登录时间",width:220}
	              //{name:"createdAt",index:"createdAt",label:"创建时间",width:120,sortable:false},
	              //{name:"createdBy",index:"createdBy",label:"创建人",width:120,sortable:false},
	              //{name:"updatedAt",index:"lastLoginDatetime",label:"更新时间",width:120,sortable:false},
	              //{name:"updatedBy",index:"lastLoginDatetime",label:"更新人",width:120,sortable:false},
	        ],
		rowNum:5,
		rowList:[5,10,20],
		height:114,
		width: 960,
		//shrinkToFit:true,
		sortname:"id",
		sortorder:"asc",
		pager:"#pager",
		viewrecords:true,
		//caption:gridCaption, // defined in the JSP file for localization purposes
		loadError:loadErrorCallback, // error handler; add/edit/delete errors are processed by other methods
		loadComplete:loadCompleteCallback, // is called after loading all data to the grid
		autoencode:true, //when set to true encodes (HTML encode) the incoming (from server) and posted data. It prevents Cross-site scripting (XSS) attacks.
		//The posted data should not be encoded - it's de-encoded in serializeEditDataCallback().
		ondblClickRow: function(id) {
			jQuery(this).jqGrid('editGridRow', id, jqGridEditOptions);
		}
	});

	// The add/edit/delete/search buttons in the navigation bar are initialized here, but are not used.
	$("#grid").jqGrid("navGrid", "#pager", jqGridNavBarOptions, jqGridEditOptions, jqGridAddOptions, jqGridDelOptions, jqGridSearchOptions);
	
	
	
}


//callbacks that are called before the request is sent to the server --------------

//Fires after the submit button is clicked and the postdata is constructed.
//Parameters passed to this event are: a options array of the method and the posted data array.
//The event should return array of type {} which extends the postdata array. 
function onclickSubmitEditCallback(options, data) {
	cleanEditForm();
	options.url = URL + "/" + data.id;
}

function onclickSubmitDelCallback(options, rowid) {
	options.url = URL + "/" + rowid;
	// return { field1:value1 }; // will be sent to the server
}

//Serialize the data passed to the AJAX request. The function should return the serialized data.
//This event can be used when a custom data should be passed to the server (JSON, XML, etc.)
//The method receives the data which will be posted to the server.
function serializeEditDataCallback(data) {
	// create a copy
	var modifiedData = $.extend({}, data);

	// if it's a new record, replace its ID
	if(modifiedData.id == "_empty") {
		modifiedData.id = 0;
	}

	// the operation is passed as an HTTP method, this field is not necessary
	delete modifiedData.oper;

	//The "autoencode:true" option HTML-encodes incoming and posted data.
	//The posted data should not be encoded. It is de-encoded here.
	modifiedData.name = $.jgrid.htmlDecode(modifiedData.name);
	
	// encode the data in `application/x-www-form-urlencoded`
	return $.param(modifiedData);

	// Remove the comment to return JSON instead
	// return JSON.stringify(modifiedData);
	// You also need to add `ajaxEditOptions: { contentType: "application/json" }` to `edit` options
}

//This callback is similar to serializeEditDataCallback(), but it is called when a row is deleted.
function serializeDelDataCallback(data) {
	
	return $.param( { version: $("#grid").jqGrid("getCell", data.id, "version") } );
}

//callbacks that are called when the response is received from server -----------------


//This callback is called when an error happens during an add/edit/delete operation on the server.
//It should return a string which is displayed at the top of the dialog.
function errorTextFormatCallback(xhr) {

	// If the response does not have JSON data, or it contains an error message that should be displayed on a separate error page...
	if(xhr.responseJSON == null || xhr.responseJSON.globalErrorCode != null) {
		return; // the error should be processed globally
	}
	
	var validationErrors = xhr.responseJSON.validationErrors;
		
	if(validationErrors != null) {

		for (var i = 0; i < validationErrors.length; i += 2) {
			var selector = ".DataTD #" + validationErrors[i];
			$(selector).after( "<div class='field-error-message'>" + validationErrors[i+1] + "</div>" );
		}
	}

	return xhr.responseJSON.localErrorMessage;
}

//removes error icons from an add/edit form
function cleanEditForm() {
	$(".field-error-message").remove();
}

//set the ID of the new row in the grid 
function afterSubmitCallback(xhr, data) {
	data.version = xhr.responseJSON.version;
	// the id in the returned array is used for new records only; it is ignored for updated records
	return [ true, "", xhr.responseJSON.id ];
}

//error handler for the grid data requests (not for add/edit/delete operations)
function loadErrorCallback(xhr, st, err) {

	if(xhr.responseJSON != null && xhr.responseJSON.localErrorMessage != null) {
		// #error-message is a <span> tag in the HTML page
		$("#error-message").html(xhr.responseJSON.localErrorMessage);
	}
}

//This callback is called after loading all data to the grid. It cleans the error message on the main page (NOT in add/edit/delete dialogs). 
function loadCompleteCallback(data) {

	//clean the error message (#error-message is a <span> tag in the HTML page)
	$("#error-message").html("");
}





