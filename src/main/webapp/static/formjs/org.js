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
		    ExpandColumn : 'orgname',     
		    width: 'auto',
		    height: "auto",
		    scroll: "true",  
		    url: 'sys/org',  
		    datatype: 'json',   
		    colModel:[      
		        {name:'orgid',index:'orgid',label:"ID", width:90,sorttype:"int",editable : true,hidden:true},      
		        {name:'orgno',index:'orgno',label:"代码", width:110,sorttype:"int",editable:true,hidden:true},      
		        {name:'orgname',index:'orgname',label:"名称", width:80,editable:true},      
		        {name:'orgparentid',index:'orgparentid',label:"父级ID", width:80,editable:true},        
		        {name:'orgparentname',index:'orgparentname',label:"父级名称", width:80,editable:true},       
		        {name:'orgparentno',index:'orgparentno',label:"父级代码", width:80,editable:true},
		        {name:'createuserid',index:'createuserid',label:"创建用户ID", width:80,editable:true},
		        {name:'createdate',index:'createdate',label:"创建时间", width:80,editable:true},
		        {name:'modifyuserid',index:'modifyuserid',label:"修改用户ID", width:80,editable:true},
		        {name:'modifydate',index:'modifydate',label:"修改时间", width:80,editable:true},
		        {name:'level',index:'level',label:"level", width:80,editable:true},
		        {name:'isleaf',index:'isleaf',label:"isleaf", width:80,editable:true},
		        {name:'loaded',index:'loaded',label:"loaded", width:80,editable:true},
		        {name:'expanded',index:'expanded',label:"expanded", width:80,editable:true}
		     ],  
		    pager: "false",    
		    sortname: 'orgid',      
		    sortorder: "desc",   
		    jsonReader: {      
		      root: "rows",    
		      repeatitems : false  
		    },      
		    treeReader : {  
		      level_field: "level",  
		      parent_id_field: "orgparentid",
		      leaf_field: "isleaf",  
		      expanded_field: "expanded"  
		    },  
		    mtype: "GET",
		    rowNum : "-1",     // 显示全部记录  
		    shrinkToFit:false  // 控制水平滚动条  
		 });  
	
	

	jQuery('#tree').jqGrid({
		width : 960,
		url : "static/treegridjs/all_crud/data.json",
		height : "auto",
		datatype : "json",
		treeGrid: true,
		treeGridModel : 'adjacency',
		colModel : [{
				name : "orgid",
				key : true,
				hidden : true
			}, {
				name : "orgno",
				label : "单位",
				width : 170,
				editable : true
			}, {
				name : "acc_num",
				label : "创建人",
				width : 170,
				editable : true
			}, {
				name : "debit",
				sorttype : "numeric",
				label : "创建时间",
				width : 90,
				formatter : "number",
				align : "right",
				editable : true
			}, {
				name : "credit",
				sorttype : "numeric",
				formatter : "number",
				label : "修改人",
				width : 90,
				align : "right",
				editable : true
			}, {
				name : "balance",
				sorttype : "numeric",
				formatter : "number",
				label : "修改时间",
				width : 90,
				align : "right",
				editable : true
			}, {
				name : "parent_id",
				hidden : true
			}
		],
		pager : "#pager"
	});

	// nable add
	jQuery('#tree').jqGrid('bindKeys');

	$("#tree").jqGrid('navGrid', '#pager', {
		edit : true,
		add : true,
		del : true
	}, editOptions, addOptions, delOptions, searchOptions);

});