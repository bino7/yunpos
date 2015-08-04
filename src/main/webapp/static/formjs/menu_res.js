var URL = $ctx + "/ajax/menu";

$(document).ready(function() {
	$("#grid").jqGrid({
		url:URL, 
		editurl:URL, 
		datatype:"json", 
		jsonReader:{repeatitems:false, id:"id"}, // the unique ID of a row
		colModel: [
		              {name:"id",index:"id",label:"系统菜单ID",width:40,editable : true,hidden:true},    
		              {name:"menuNo",index:"menuNo",label:"编号",width:80,sortable:false,editable : true},  
		              {name:"applicationCode",index:"applicationCode",label:"应用编号",width:80,sortable:false,editable : true},  
		              {name:"menuOrder",index:"menuOrder",label:"排序号",width:120,sortable:false,editable : true},
		              {name:"menuName",index:"menuName",label:"名称",width:120,sortable:false,editable : true},
		              {name:"menuUrl",index:"menuUrl",label:"url",width:120,sortable:false,editable : true},
		              {
		            	  name:"menuParentNo",index:"menuParentNo",label:"父级编号",width:160,sortable:false,editable : true,
		            	  edittype:'select',
		            	  editoptions : {value:getSelectList(URL+"/select")}
		              },  
		              {
		            	  name:"isVisible",index:"isVisible",label:"是否允许访问",width:120,sortable:false,editable : true,
		            	  edittype:'select',
		            	  editoptions : {value: "0:否;1:是",defaultValue:1},
		              },
		              {
		            	  name:"isLeaf",index:"isLeaf",label:"是否子菜单",width:120,sortable:false,editable : true,
		            	  edittype:'select',
		            	  editoptions : {value: "0:否;1:是",defaultValue:0},
		              }
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

	jQuery('#grid').jqGrid('bindKeys');
	$("#grid").jqGrid("navGrid", "#pager", jqGridNavBarOptions, jqGridEditOptions, jqGridAddOptions, jqGridDelOptions, jqGridSearchOptions);

	
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
