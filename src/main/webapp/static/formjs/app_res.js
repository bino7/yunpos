//按钮资源JS

$(function(){
	
	//编辑参数
	var editOptions = {
			drag : true,
			resize : true,
			closeOnEscape : true,
			reloadAfterSubmit:true,
			closeAfterEdit:true, 
			dataheight : 200	
	}
	
	//新增初始化参数
	var addOptions = {
			drag : true,
			resize : true,
			closeOnEscape : true,
			closeAfterAdd:true,
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
	        url: "res/app", 
	    	width : 960,
			mtype: "GET",
			height : "auto",
			datatype : "json", 
	        colModel: [  
	              {name:"applicationid" ,index: "applicationid" ,label:"应用ID",width:40,sortable:true,editable : true,hidden:true},
	              {name:"applicationcode",index:"applicationcode",label:"应用编号",width:80,editable : true},
	              {name:"applicationname",index:"applicationname",label:"应用名称",width:80,editable : true}, 
	              {name:"applicationdesc",index:"applicationdesc",label:"应用描述",width:200,editable : true},
	              {name:"showinmenu",index:"showinmenu",label:"是否在菜单显示",width:160,editable : true}
	        ],  
	        viewrecords: true,
	        altRows : true,
		    multiselect : true,
		    editurl : "res/app/operate",
	        rowNum: 10,  
	        rowList: [10,20,30],  
	        prmNames: {search: "search"},
	        pager: "#pager",
	        jsonReader: {  
	        	id: "applicationid",
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