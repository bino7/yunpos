var URL = $ctx + "/ajax/userRole";

$(document).ready(function() {
	$("#grid").jqGrid({
		url:URL, 
		editurl:URL, 
		datatype:"json", 
		jsonReader:{repeatitems:false, id:"id"}, // the unique ID of a row
		colModel:[  
	              {name: "id" ,index: "id" ,label:"ID",width:40,sortable:true,editable : true,hidden:true},
	              {
	            	  name:"userId",index:"userId",label:"用户",width:80,editable : true,
	            	  edittype : "select",
			          editoptions : {value:getUserSelectList()} 
	              },
	              {
	            	  name:"roleId",index:"roleId",label:"角色",width:80,editable : true,
	            	  edittype : "select",
			          editoptions : {value:getRoleSelectList()} 
	              }, 
	              {name:"createUserId",index:"createUserId",label:"创建用户",width:160},
	              {name:"createDate",index:"createDate",label:"创建时间",width:120},
	              {name:"modifyUserId",index:"modifyUserId",label:"修改用户",width:120},
	              {name:"modifyDate",index:"modifyDate",label:"修改时间",width:120}
	        ], 
		rowNum:10,
		rowList:[10,20,30],
		height:'auto',
		width: 960,
		shrinkToFit:true,
		sortname:"id",
		sortorder:"asc",
		pager:"#pager",
		viewrecords:true,
		loadError:loadErrorCallback, // error handler; add/edit/delete errors are processed by other methods
		loadComplete:loadCompleteCallback, // is called after loading all data to the grid
		autoencode:true, //when set to true encodes (HTML encode) the incoming (from server) and posted data. It prevents Cross-site scripting (XSS) attacks.
		ondblClickRow: function(id) {
			jQuery(this).jqGrid('editGridRow', id, jqGridEditOptions);
		}
	});

	
	$("#grid").jqGrid("navGrid", "#pager", jqGridNavBarOptions, jqGridEditOptions, jqGridAddOptions, jqGridDelOptions, jqGridSearchOptions);

	//获取用户下拉列表
	function getUserSelectList(type) {
		var str ="";
		$.ajax({
			url : $ctx+"/ajax/user/select",
			async : false,
			success : function (data) {
				if(data!=null){
					var jsonobj = eval(data);
					var length = jsonobj.length;
					for(var i = 0; i < length; i++) {
						if(i != length - 1) {
							str += jsonobj[i].id + ":" + jsonobj[i].userName + ";";
						}else{
							str += jsonobj[i].id + ":" + jsonobj[i].userName;
						}
					}
				}
			}
		});
		return str;
	}
	
	//获取角色下拉列表
	function getRoleSelectList(type) {
		var str ="";
		$.ajax({
			url : "/ajax/role/select",
			async : false,
			success : function (data) {
				if(data!=null){
					var jsonobj = eval(data);
					var length = jsonobj.length;
					for(var i = 0; i < length; i++) {
						if(i != length - 1) {
							str += jsonobj[i].orgId + ":" + jsonobj[i].roleName + ";";
						}else{
							str += jsonobj[i].orgId + ":" + jsonobj[i].roleName;
						}
					}
				}
			}
		});
		return str;
	}
});
