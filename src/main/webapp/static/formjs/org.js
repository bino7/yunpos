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
		    ExpandColumn : 'username',       
		    scroll: "true",  
		    url: '',  
		    datatype: 'json',      
		    colNames:['','姓名','密码','年龄','地址','出生日期'],      
		    colModel:[      
		        {name:'id',index:'id',lable:"编号", width:90,sorttype:"int"},      
		        {name:'username',index:'username', width:110,sorttype:"int"},      
		        {name:'password',index:'password', width:80},      
		        {name:'age',index:'age', width:80},        
		        {name:'address',index:'address', width:80},       
		        {name:'time',index:'time', width:80,sorttype:"date"}        
		     ],  
		    pager: "false",    
		    sortname: 'id',      
		    sortorder: "desc",   
		        
		    jsonReader: {      
		      root: "rows",    
		      repeatitems : false  
		    },      
		    treeReader : {  
		      level_field: "level",  
		      parent_id_field: "parent",   
		      leaf_field: "isLeaf",  
		      expanded_field: "expanded"  
		    },  
		    caption: "jqGrid test",     
		    mtype: "POST",  
		    height: "auto",    // 设为具体数值则会根据实际记录数出现垂直滚动条  
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