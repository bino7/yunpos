// Option设置
var jqGridNavBarOptions = {
	edit : true,
	add : true,
	del : true,
	search : true,
	refresh : true
};

var jqGridEditOptions = {
	reloadAfterSubmit : true,
	mtype : "PUT",
	modal : true,
	closeAfterEdit : false,
	recreateForm : true,
	serializeEditData : serializeEditDataCallback,
	errorTextFormat : errorTextFormatCallback,
	onclickSubmit : onclickSubmitEditCallback,
	afterSubmit : afterSubmitCallback,
	//onclickPgButtons : cleanEditForm
};
var jqGridAddOptions = {
//	reloadAfterSubmit : true,
	mtype : "POST",
	modal : true,
	resize : true,
//	closeAfterAdd : true,
//	recreateForm : true,
	serializeEditData : serializeEditDataCallback,
	errorTextFormat : errorTextFormatCallback,
	afterSubmit : afterSubmitCallback
};
var jqGridDelOptions = {
	reloadAfterSubmit : true,
	mtype : "DELETE",
//	serializeDelData : serializeDelDataCallback,
	errorTextFormat : errorTextFormatCallback,
	onclickSubmit : onclickSubmitDelCallback
};
var jqGridSearchOptions = {
	sopt : [ "cn", "bw", "eq", "ne", "lt", "gt", "ew" ]
};




function onclickSubmitEditCallback(options, data) {
	cleanEditForm();
	options.url = URL + "/" + data.id;
}

function onclickSubmitDelCallback(options, rowid) {
	options.url = URL + "/" + rowid;
}


function serializeEditDataCallback(data) {
	var modifiedData = $.extend({}, data);

	if (modifiedData.id == "_empty") {
		modifiedData.id = 0;
	}

	delete modifiedData.oper;
	//alert(JSON.stringify(modifiedData));
	return $.param(modifiedData);

}

function serializeDelDataCallback(data) {

	return $.param({
		version : $("#dddd").jqGrid("getCell", data.id, "version")
	});
}


function errorTextFormatCallback(xhr) {
	if (xhr.responseJSON == null || xhr.responseJSON.globalErrorCode != null) {
		return; 
	}

	var validationErrors = xhr.responseJSON.validationErrors;

	if (validationErrors != null) {

		for (var i = 0; i < validationErrors.length; i += 2) {
			var selector = ".DataTD #" + validationErrors[i];
			$(selector).after(
					"<div class='field-error-message'>"
							+ validationErrors[i + 1] + "</div>");
		}
	}
	return xhr.responseJSON.localErrorMessage;
}


function cleanEditForm() {
	$(".field-error-message").remove();
}

function afterSubmitCallback(xhr, data) {
	data.version = xhr.responseJSON.version;
	return [ true, "", xhr.responseJSON.id ];
}


function loadErrorCallback(xhr, st, err) {
	if (xhr.responseJSON != null && xhr.responseJSON.localErrorMessage != null) {
		$("#error-message").html(xhr.responseJSON.localErrorMessage);
	}
}


function loadCompleteCallback(data) {
	$("#error-message").html("");
}



//获取下拉列表
$(document).ready(function() {
	function getSelectList(url) {
		var str ="";
		$.ajax({
			url :url,
			async : false,
			success : function (data) {
				if(data!=null){
					var jsonobj = eval(data);
					var length = jsonobj.length;
					for(var i = 0; i < length; i++) {
						if(i != length - 1) {
							str += jsonobj[i].id + ":" + jsonobj[i].name + ";";
						}else{
							str += jsonobj[i].id + ":" + jsonobj[i].name;
						}
					}
				}
			}
		});
		return str;
	}
});

