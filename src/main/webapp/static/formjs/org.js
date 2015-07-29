$(function() {
	// 编辑参数
	var editOptions = {
		drag : true,
		resize : true,
		closeOnEscape : true,
		dataheight : 200
	}

	// 新增初始化参数
	var addOptions = {
		drag : true,
		resize : true,
		closeOnEscape : true,
		dataheight : 200
	}

	// 删除参数
	var delOptions = {

	}

	// 搜索参数
	var searchOptions = {

	}
	
	 jQuery("#tree").jqGrid({   
		    treeGrid: true,  
		    treeGridModel: 'adjacency', //treeGrid模式，跟json元数据有关  
		    ExpandColumn : 'orgName',     
		    width: 'auto',
		    height: "auto",
		    scroll: "true",  
		    url: 'sys/org',  
		    datatype: 'json',   
		    colModel:[      
		        {name:'orgId',index:'orgId',label:"ID", width:90,sorttype:"int",editable : true,hidden:true},      
		        {name:'orgNo',index:'orgNo',label:"代码", width:110,sorttype:"int",editable:true,hidden:true},      
		        {name:'orgName',index:'orgName',label:"名称", width:110,editable:true},      
		        {name:'orgParentId',index:'orgParentId',label:"父级ID", width:80,editable:true},        
		        {name:'orgParentName',index:'orgParentName',label:"父级名称", width:80,editable:true},       
		        {name:'orgParentNo',index:'orgParentNo',label:"父级代码", width:80,editable:true},
		        {name:'createUserId',index:'createUserId',label:"创建用户ID", width:80,editable:true},
		        {name:'createDate',index:'createDate',label:"创建时间", width:80,editable:true},
		        {name:'modifyUserId',index:'modifyUserId',label:"修改用户ID", width:80,editable:true},
		        {name:'modifyDate',index:'modifyDate',label:"修改时间", width:80,editable:true},
		        {name:'level',index:'level',label:"level", width:80,editable:true},
		        {name:'isLeaf',index:'isLeaf',label:"isleaf", width:80,editable:true},
		        {name:'loaded',index:'loaded',label:"loaded", width:80,editable:true},
		        {name:'expanded',index:'expanded',label:"expanded", width:80,editable:true}
		     ],  
		    pager: "false",    
		    sortname: 'orgId',      
		    sortorder: "desc",   
		    jsonReader: {      
		      root: "rows",    
		      repeatitems : false  
		    },      
		    treeReader : {  
		      level_field: "level",  
		      parent_id_field: "orgParentId",
		      leaf_field: "isLeaf",  
		      expanded_field: "expanded"  
		    },  
		    mtype: "GET",
		    rowNum : "-1",     // 显示全部记录  
		    shrinkToFit:false  // 控制水平滚动条  
		 });  
	


	// nable add
	jQuery('#tree').jqGrid('bindKeys');

	$("#tree").jqGrid('navGrid', '#pager', {
		edit : true,
		add : true,
		del : true
	}, editOptions, addOptions, delOptions, searchOptions);

});