//按钮资源JS

$(function(){
	//自动以表格
	 $("#grid").jqGrid({
	        url: "rest/button/list",  
	        datatype: "json",  
	        mtype: "POST",  
	        height: 350,  
	        width: 960,  
	        colModel: [  
	              {name:"btnid",index:"btnid",label:"按钮ID",width:40,sortable:true,sorttype:"int",},
	              {name:"btnname",index:"btnname",label:"按钮名称",width:80,sortable:false},  
	              {name:"btnno",index:"btnno",label:"按钮编号",width:80,sortable:false},  
	              {name:"btnicon",index:"btnicon",label:"按钮css风格",width:160,sortable:false},  
	              {name:"menuno",index:"menuno",label:"按钮图标",width:120,sortable:false},
	              {name:"initstatus",index:"initstatus",label:"初始状态",width:120,sortable:false},
	              {name:"seqno",index:"seqno",label:"菜单编号",width:120,sortable:false},
	              {name:"description",index:"description",label:"按钮脚本",width:120,sortable:false}
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
	 
	 
	 //导航栏定义
	 $("#grid").jqGrid('navGrid', '#pager', {edit : true,add : true,del : true});
	 
	 
	
});