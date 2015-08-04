var URL = $ctx + "/ajax/role";

$(document).ready(function() {
	$("#grid").jqGrid({
		url:URL, 
		editurl:URL, 
		datatype:"json", 
		jsonReader:{repeatitems:false, id:"id"}, // the unique ID of a row
		colModel:[  
	              {name: "id" ,index: "id" ,label:"角色",width:40,sortable:true,editable : true,hidden:true},
	              {name:"roleName",index:"roleName",label:"名称",width:160,editable : true},
	              {
	            	  name:"orgId",index:"orgId",label:"组织",width:200,editable : true,
	            	  edittype:'select',
	            	  editoptions : {value:getOrgSelectList()}
	              },
	              {name:"roleDesc",index:"roleDesc",label:"描述",width:200,editable : true,edittype:'textarea'}, 
	              {name:"createUserId",index:"createUserId",label:"创建用户",width:60},
	              //{name:"createDate",index:"createDate",label:"创建时间",width:120,editable : true},
	              {name:"modifyUserId",index:"modifyUserId",label:"修改用户",width:60}
	              //{name:"modifyDate",index:"modifyDate",label:"修改时间",width:120,editable : true}
	        ], 
		rowNum:5,
		rowList:[5,10,20],
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

	jQuery('#grid').jqGrid('bindKeys');
	$("#grid").jqGrid("navGrid", "#pager", jqGridNavBarOptions, jqGridEditOptions, jqGridAddOptions, jqGridDelOptions, jqGridSearchOptions);
	
	
	function getOrgSelectList(){
		var str ="";
		$.ajax({
			url : $ctx + "/ajax/org/select",
			async : false,
			success : function (data) {
				if(data!=null){
					var jsonobj = eval(data);
					var length = jsonobj.length;
					for(var i = 0; i < length; i++) {
						if(i != length - 1) {
							str += jsonobj[i].id + ":" + jsonobj[i].orgName + ";";
						}else{
							str += jsonobj[i].id + ":" + jsonobj[i].orgName;
						}
					}
				}
			}
		});
		return str;
	}
	
	
});
