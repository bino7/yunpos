$(function(){
	//自动以表格
	 $("#grid").jqGrid({
	        url: "user/list",  
	        datatype: "json",  
	        mtype: "GET",  
	        height: 350,  
	        width: 960,  
	        colModel: [  
	              {name:"id",index:"id",label:"ID",width:40},    
	              {name:"usgId",index:"usgId",label:"所在组ID",width:80,sortable:false},  
	              {name:"userName",index:"userName",label:"用户名",width:80,sortable:false},  
	              {name:"email",index:"email",label:"电子邮箱",width:200,sortable:false},  
	              //{name:"password",index:"password",label:"密码",width:160,sortable:false},  
	              {name:"nickname",index:"nickname",label:"昵称",width:120,sortable:false},
	              {name:"fullname",index:"fullname",label:"全名",width:120,sortable:false},
	              {name:"status",index:"status",label:"状态",width:120,sortable:false},
	              {name:"salt",index:"salt",label:"盐值",width:120,sortable:false},
	              {name:"description",index:"description",label:"描述",width:120,sortable:false},
	              {name:"source",index:"source",label:"描述",width:120,sortable:false},
	              {name:"lastLoginDatetime",index:"lastLoginDatetime",label:"最后登录时间",sortable:false,width:200}
	              //{name:"createdAt",index:"lastLoginDatetime",label:"创建时间",width:120,sortable:false},
	              //{name:"createdBy",index:"createdBy",label:"创建人",width:120,sortable:false},
	              //{name:"updatedAt",index:"lastLoginDatetime",label:"更新时间",width:120,sortable:false},
	              //{name:"updatedBy",index:"lastLoginDatetime",label:"更新人",width:120,sortable:false},
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
	 
	 //导航栏定义
	 $("#grid").jqGrid('navGrid', '#pager', {edit : true,add : true,del : true});
	
});