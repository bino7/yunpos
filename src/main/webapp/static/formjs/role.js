//角色管理JS
$(function(){
	
	//编辑参数
	var editOptions = {
			drag : true,
			resize : true,
			closeOnEscape : true,
			dataheight : 200	
	}
	
	//新增初始化参数
	var addOptions = {
			drag : true,
			resize : true,
			closeOnEscape : true,
			dataheight : 'auto'	
	}

	//删除参数
	var delOptions = {
			
	}
	
	//搜索参数
	var searchOptions = {
			
	}
	
	//自动以表格
	jQuery('#grid').jqGrid({
		width : 960,
		url : $ctx+"/sys/role",
		mtype: "GET",
		height : "auto",
		datatype : "json",
		colModel:[  
	              {name: "roleId" ,index: "roleId" ,label:"角色ID",width:40,sortable:true,editable : true,hidden:true},
	              {name:"roleName",index:"roleName",label:"角色名称",width:80,editable : true},
	              {name:"roleDesc",index:"roleDesc",label:"描述",width:80,editable : true}, 
	              {name:"orgId",index:"orgId",label:"所属组织机构",width:200,editable : true},
	              {name:"createUserId",index:"createUserId",label:"创建用户ID",width:160,editable : true},
	              //{name:"createDate",index:"createDate",label:"创建时间",width:120,editable : true},
	              {name:"modifyUserId",index:"modifyUserId",label:"修改用户ID",width:120,editable : true}
	              //{name:"modifyDate",index:"modifyDate",label:"修改时间",width:120,editable : true}
	        ], 
		pager : "#pager",
		editurl : "sys/role/operate",
		jsonReader: {  
        	id: "roleId",
            root:"rows",  
            total: "total",
            records: "records",
            repeatitems: false
        },  
        ondblClickRow: function(id) {
			jQuery(this).jqGrid('editGridRow', id, editOptions);
		}
	});
	 
	//开启键盘上下选择行数据
	jQuery('#grid').jqGrid('bindKeys');
	 
	 //导航栏定义
	 $("#grid").jqGrid('navGrid', '#pager', 
			 {edit : true,add : true,del : true},
			 editOptions,
			 addOptions,
			 delOptions,
			 searchOptions
	 );
	
});