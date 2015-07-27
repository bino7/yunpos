//按钮资源JS

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
			dataheight : 200	
	}

	//删除参数
	var delOptions = {
			
	}
	
	//搜索参数
	var searchOptions = {
			
	}
	
	//自动以表格
	 $("#grid").jqGrid({
	        url: "res/app", 
	        datatype: "json",  
	        mtype: "GET",  
	        height: 'auto',  
	        width: 960,  
	        colModel: [  
	              {name:"applicationid",index:"applicationid",label:"应用ID",width:40},    
	              {name:"applicationcode",index:"applicationcode",label:"应用编号",width:80,sortable:false},  
	              {name:"applicationname",index:"applicationname",label:"应用名称",width:80,sortable:false},  
	              {name:"applicationdesc",index:"applicationdesc",label:"应用描述",width:160,sortable:false},  
	              {name:"showinmenu",index:"showinmenu",label:"是否在菜单显示",width:120,sortable:false}
	        ],  
	        viewrecords: true,  
	        rowNum: 15,  
	        rowList: [10,20,30],  
	        prmNames: {search: "search"},  
	        jsonReader: {  
	            root:"rows",  
	            records: "record",  
	            repeatitems : false  
	        },  
	        pager: "#pager",  
	        //caption: "用户列表",  
	        hidegrid: false,  
	        shrikToFit: true  
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