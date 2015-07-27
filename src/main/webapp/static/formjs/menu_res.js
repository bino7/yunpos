//菜单资源
$(function(){
	//自动以表格
	 $("#grid").jqGrid({
	        url: "xx",  
	        datatype: "json",  
	        mtype: "GET",  
	        height: 350,  
	        width: 960,  
	        colModel: [  
	              {name:"menuid",index:"menuid",label:"系统菜单ID",width:40},    
	              {name:"menuno",index:"usgId",label:"系统菜单编号",width:80,sortable:false},  
	              {name:"applicationcode",index:"applicationcode",label:"应用编号",width:80,sortable:false},  
	              {name:"menuparentno",index:"menuparentno",label:"父级菜单编号",width:160,sortable:false},  
	              {name:"menuorder",index:"menuorder",label:"菜单排序编号",width:120,sortable:false},
	              {name:"menuname",index:"menuname",label:"菜单名称",width:120,sortable:false},
	              {name:"menuurl",index:"menuurl",label:"菜单URL",width:120,sortable:false},
	              {name:"isvisible",index:"isvisible",label:"是否允许访问",width:120,sortable:false},
	              {name:"isleaf",index:"isleaf",label:"是否子菜单",width:120,sortable:false}
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