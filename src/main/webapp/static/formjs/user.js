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
	        url: "sys/user",
	        datatype: "json",  
	        mtype: "GET",  
	        height: 'auto',
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
		         total: "total",
		         page: "page",
		         records: "records",
		         repeatitems: false,  
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