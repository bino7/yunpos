//按钮资源JS

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
			dataheight : 200	
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
	 	              {name:"btnid",index:"btnid",label:"按钮ID",width:40,sortable:true,editable:true},
	 	              {name:"btnname",index: "btnname",label:"按钮名称",width:80,editable:true},
	 	              {name:"btnno",index: "btnno",label:"按钮编号",width:80,editable:true}, 
	 	              {name:"btnicon",index: "btnicon",label:"按钮css风格",width:200,editable:true},
	 	              {name:"menuno",index:"menuno",label:"按钮图标",width:160,editable:true},
	 	              {name:"initstatus",index:"initstatus",label:"初始状态",width:160,editable:true},
	 	              {name:"seqno",index:"seqno",label:"菜单编号",width:160,editable:true},
	 	              {name:"description",index:"description",label:"按钮脚本",width:160,editable:true}
	 	        ],  
	        //viewrecords: true,  
	        //rowNum: 15,  
	        rowList: [10,20,30],  
	        prmNames: {search: "search"},  
	        jsonReader: {  
	            root:"rows",  
	            total: "total",
	            records: "records",
	            repeatitems: false
	        },  
	        pager: "#pager"
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