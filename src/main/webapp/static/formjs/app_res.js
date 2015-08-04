var URL = $ctx + "/ajax/app";

$(document).ready(function() {
	var showArray = {0:"否",1:"是"};
	
	$("#grid").jqGrid({
		url:URL, 
		editurl:URL, 
		datatype:"json", 
		jsonReader:{repeatitems:false, id:"id"}, // the unique ID of a row
		 colModel: [  
		              {name:"id" ,index: "id" ,label:"应用ID",width:40,sortable:true,editable : true,hidden:true},
		              {name:"applicationCode",index:"applicationCode",label:"应用编号",width:80,editable : true},
		              {name:"applicationName",index:"applicationName",label:"应用名称",width:80,editable : true}, 
		              {name:"applicationDesc",index:"applicationDesc",label:"应用描述",width:200,editable : true,edittype:'textarea'},
		              {
		            	  name:"showInMenu",index:"showInMenu",label:"是否在菜单显示",width:160,editable : true,
		            	  edittype : "select",
				          editoptions : {value:"0:否;1:是",defaultValue:1} ,
				          formatter:function(v,opt,rec){return showArray[v]},
		            	  unformat:function(v){if(v=='否')return '0';return '1'}
		              }
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

});
