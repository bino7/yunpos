$(function() {
	// 编辑参数
	var editOptions = {
		drag : true,
		resize : true,
		closeOnEscape : true,
		dataheight : "auto"
	}

	// 新增初始化参数
	var addOptions = {
		drag : true,
		resize : true,
		closeOnEscape : true,
		dataheight : "auto"
	}

	// 删除参数
	var delOptions = {

	}

	// 搜索参数
	var searchOptions = {

	}
	

	 jQuery("#tree").jqGrid({   
		    treeGrid: true,  
		    treeGridModel: 'adjacency', //treeGrid模式，跟json元数据有关  
		    ExpandColumn : 'orgName',     
		    width: 'auto',
		    height: "auto",
		    //scroll: "true",  
		    url: $ctx+'/sys/org',  
		    datatype: 'json',   
		    colModel:[      
		        {name:'orgId',index:'orgId',label:"ID", width:90,key: true,editable:true,hidden:true},//  
		        {name:'orgName',index:'orgName',label:"名称", width:110,editable:true},
		        {name:'orgNo',index:'orgNo',label:"组织机构代码", width:110,sorttype:"int",editable:true},      
		        {name:'orgParentName',index:'orgParentName',label:"父级名称", width:80},
		        {name:'orgParentNo',index:'orgParentNo',label:"父级代码", width:80},
		        {name:'createUserId',index:'createUserId',label:"创建用户ID", width:80},
		        {name:'createDate',index:'createDate',label:"创建时间", width:80},
		        {name:'modifyUserId',index:'modifyUserId',label:"修改用户ID", width:80},
		        {name:'modifyDate',index:'modifyDate',label:"修改时间", width:80},
		        {
		        	name:'orgParentId',index:'orgParentId',label:"父级ID", width:80,editable:true,
		        	edittype : "select",
		        	editoptions : {value:getOrgSelectList()} 	
		        },    
		        {
		        	name:'extLevel',index:'extLevel',label:"层级", width:80,editable:true,
		        	edittype : "select",
		        	editoptions : {value: "0:0;1:1;2:2",defaultValue:1}
		        },
		        {
		        	name:'extIsLeaf',index:'extIsLeaf',label:"左侧与否", width:80,editable:true,
		        	edittype : "select",
		        	editoptions : {value:"0:false;1:true",defaultValue:1} 
		        },
		        {
		        	name:'extLoaded',index:'extLoaded',label:"是否加载", width:80,editable:true,
		        	edittype : "select",
		        	editoptions : {value:"0:false;1:true",defaultValue:1}  
		        },
		        {
		        	name:'extExpanded',index:'extExpanded',label:"是否展开", width:80,editable:true,
		        	edittype : "select",
		        	editoptions : {value:"0:false;1:true",defaultValue:1} 
		        }
		     ],  
		    pager: "#pager",    
		    sortname: 'orgId',
		    tree_root_level:0,
		    editurl : "sys/org/operate",
		    jsonReader: {  
		      id:"orgId",
		      root: "rows",  
		      repeatitems : false  
		    },      
		    treeReader : {  
		      level_field: "extLevel",  
		      parent_id_field: "orgParentId",
		      leaf_field: "extIsLeaf",  
		      expanded_field: "extExpanded"
		    },  
		    mtype: "GET",
		    rowNum : "-1",     // 显示全部记录  
		    shrinkToFit:false,  // 控制水平滚动条  
		    ondblClickRow: function(id) {
				jQuery(this).jqGrid('editGridRow', id, editOptions);
			}
		 });
	
	// nable add
	jQuery('#tree').jqGrid('bindKeys');

	$("#tree").jqGrid('navGrid', '#pager', {
		edit : true,
		add : true,
		del : true
	}, editOptions, addOptions, delOptions, searchOptions);
	
	
	//获取下拉列表
	function getOrgSelectList(type) {
		var str ="";
		$.ajax({
			url : "sys/org/getOrgSelectList",
			async : false,
			success : function (data) {
				if(data!=null){
					var jsonobj = eval(data);
					var length = jsonobj.length;
					for(var i = 0; i < length; i++) {
						if(i != length - 1) {
							str += jsonobj[i].orgId + ":" + jsonobj[i].orgName + ";";
						}else{
							str += jsonobj[i].orgId + ":" + jsonobj[i].orgName;
						}
					}
				}
			}
		});
		return str;
	}
	


});