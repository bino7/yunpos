//菜单资源
$(function(){
	
	//编辑参数
	var editOptions = {
			drag : true,
			resize : true,
			closeOnEscape : true,
			dataheight : 200	
	};
	
	//新增初始化参数
	var addOptions = {
			drag : true,
			resize : true,
			closeOnEscape : true,
			dataheight : "auto"	
	};

	//删除参数
	var delOptions = {
			
	};
	
	//搜索参数
	var searchOptions = {
			
	};
	
	//自动以表格
	 $("#grid").jqGrid({
	        url: $ctx+"/res/menu",
	        datatype: "json",  
	        mtype: "GET", 
	        height: 'auto',
	        width: 960,  
	        colModel: [
	              {name:"menuId",index:"menuId",label:"系统菜单ID",width:40,editable : true,hidden:true},    
	              {name:"menuNo",index:"menuNo",label:"系统菜单编号",width:80,sortable:false,editable : true},  
	              {name:"applicationCode",index:"applicationCode",label:"应用编号",width:80,sortable:false,editable : true},  
	              {name:"menuParentNo",index:"menuParentNo",label:"父级菜单编号",width:160,sortable:false,editable : true},  
	              {name:"menuOrder",index:"menuOrder",label:"菜单排序编号",width:120,sortable:false,editable : true},
	              {name:"menuName",index:"menuName",label:"菜单名称",width:120,sortable:false,editable : true},
	              {name:"menuUrl",index:"menuUrl",label:"菜单URL",width:120,sortable:false,editable : true},
	              {name:"isVisible",index:"isVisible",label:"是否允许访问",width:120,sortable:false,editable : true},
	              {name:"isLeaf",index:"isLeaf",label:"是否子菜单",width:120,sortable:false,editable : true}
	        ],  
	        viewrecords: true,  
	        rowNum: 15,  
	        rowList: [10,20,30],  
	        prmNames: {search: "search"},  
	        editurl : "res/menu/operate",
	        jsonReader: { 
	        	id : "menuid",
	        	root:"rows",  
	            total: "total",
	            page: "page",
	            records: "records",
	            repeatitems: false
	        },  
	        pager: "#pager",  
	        hidegrid: false,  
	        shrikToFit: true,
	        ondblClickRow: function(id) {
				jQuery(this).jqGrid('editGridRow', id, editOptions);
			}
	    });
	 
	//开启键盘上下选择行数据
	jQuery('#tree').jqGrid('bindKeys');
		 
	//导航栏定义
	$("#grid").jqGrid('navGrid', '#pager', 
				 {edit : true,add : true,del : true},
				 editOptions,
				 addOptions,
				 delOptions,
				 searchOptions
		 );
	
});