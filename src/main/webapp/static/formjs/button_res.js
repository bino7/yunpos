var URL = $ctx + "/ajax/button";

$(document).ready(function() {
	var initArray = {0:"停用",1:"正常"};
	
	$("#grid").jqGrid({
		url:URL, 
		editurl:URL, 
		datatype:"json", 
		jsonReader:{repeatitems:false, id:"id"}, // the unique ID of a row
		colModel: [  
	 	              {name:"id",index:"id",label:"按钮ID",width:40,sortable:true,editable:true,hidden:true},
	 	              {name:"btnName",index: "btnName",label:"按钮名称",width:160,editable:true},
	 	              {name:"btnNo",index: "btnNo",label:"按钮编号",width:160,editable:true}, 
	 	              {name:"btnIcon",index: "btnIcon",label:"按钮css风格",width:200,editable:true},
	 	              {name:"menuNo",index:"menuNo",label:"按钮图标",width:160,editable:true},
	 	              {
	 	            	  name:"initStatus",index:"initStatus",label:"初始状态",width:160,editable:true,
	 	            	  edittype:'select',
		            	  editoptions : {value: "0:停用;1:启用",defaultValue:1},
		            	  formatter:function(v,opt,rec){return initArray[v]},
		            	  unformat:function(v){if(v=='停用')return '0';return '1'}
	 	              },
	 	              {
	 	            	  name:"seqno",index:"seqno",label:"菜单编号",width:160,editable:true,
	 	            	  edittype : "select",
				          editoptions : {value:getMenuSeqSelectList(URL+"/select")} 	
	 	            	  
	 	              },
	 	              {name:"btnclass",index:"btnclass",label:"样式",width:160,editable:true,edittype:"textarea"},
	 	              {name:"btnscript",index:"btnscript",label:"脚本",width:160,editable:true,edittype:"textarea"},
	 	              {name:"description",index:"description",label:"按钮脚本",width:160,editable:true}
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

	
	function getMenuSeqSelectList(url) {
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
							str += jsonobj[i].menuNo + ":" + jsonobj[i].name + ";";
						}else{
							str += jsonobj[i].menuNo + ":" + jsonobj[i].name;
						}
					}
				}
			}
		});
		return str;
	}
	
});
