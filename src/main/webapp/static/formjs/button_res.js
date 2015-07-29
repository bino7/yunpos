//按钮资源JS

$(function(){
	//编辑参数
	var editOptions = {
			drag : true,
			resize : true,
			closeOnEscape : true,
			dataheight : "auto"	
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
	        url: "res/button",
	        datatype: "json",
	        mtype: "GET",
	        height: "auto",
	        width: 960,  
	        colModel: [  
	 	              {name:"btnId",index:"btnId",label:"按钮ID",width:40,sortable:true,editable:true,hidden:true},
	 	              {name:"btnName",index: "btnName",label:"按钮名称",width:80,editable:true},
	 	              {name:"btnNo",index: "btnNo",label:"按钮编号",width:80,editable:true}, 
	 	              {name:"btnIcon",index: "btnIcon",label:"按钮css风格",width:200,editable:true},
	 	              {name:"menuNo",index:"menuNo",label:"按钮图标",width:160,editable:true},
	 	              {name:"initStatus",index:"initStatus",label:"初始状态",width:160,editable:true},
	 	              {name:"seqno",index:"seqno",label:"菜单编号",width:160,editable:true},
	 	              {name:"btnclass",index:"btnclass",label:"class",width:160,editable:true,edittype:"textarea"},
	 	              {name:"btnscript",index:"btnscript",label:"脚本",width:160,editable:true,edittype:"textarea"},
	 	              {name:"description",index:"description",label:"按钮脚本",width:160,editable:true}
	 	        ],  
	        //viewrecords: true,  
	        //rowNum: 15,  
	        rowList: [10,20,30],  
	        prmNames: {search: "search"},  
	        viewrecords: true,
	        altRows : true,
		    multiselect : true,
		    editurl : "res/button/operate",
	        jsonReader: {  
	        	id: "btnId",
	            root:"rows",  
	            total: "total",
	            records: "records",
	            repeatitems: false
	        },  
	        pager: "#pager",
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