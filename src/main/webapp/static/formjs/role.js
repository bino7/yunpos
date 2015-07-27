//角色管理JS
$(function(){
	//自动以表格
	 $("#grid").jqGrid({
	        url: "xx",  
	        datatype: "json",  
	        mtype: "GET",  
	        height: 350,  
	        width: 960,  
	        colModel: [  
	              {name:"roleid",index:"roleid",label:"角色ID",width:40},    
	              {name:"rolename",index:"rolename",label:"角色名称",width:80,sortable:false},  
	              {name:"roledesc",index:"roledesc",label:"描述",width:80,sortable:false},  
	              {name:"orgid",index:"orgid",label:"所属组织机构",width:160,sortable:false},  
	              {name:"createuserid",index:"createuserid",label:"创建用户ID",width:120,sortable:false},
	              {name:"createdate",index:"createdate",label:"创建时间",width:120,sortable:false},
	              {name:"modifyuserid",index:"modifyuserid",label:"修改用户ID",width:120,sortable:false},
	              {name:"modifydate",index:"modifydate",label:"修改时间",width:120,sortable:false}
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