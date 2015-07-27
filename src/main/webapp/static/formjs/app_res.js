//按钮资源JS

$(function(){
	//自动以表格
	 $("#grid").jqGrid({
	        url: "rest/button",  
	        datatype: "json",  
	        mtype: "GET",  
	        height: 350,  
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
	            root:"gridModel",  
	            records: "record",  
	            repeatitems : false  
	        },  
	        pager: "#pager",  
	        //caption: "用户列表",  
	        hidegrid: false,  
	        shrikToFit: true  
	    });
	 
	 //导航栏定义
	 $("#grid").jqGrid('navGrid', '#pager', {edit : true,add : true,del : true});
	
});