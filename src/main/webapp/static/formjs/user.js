$(function() {
	// 编辑参数
	var editOptions = {
		drag : true,
		resize : true,
		closeOnEscape : true,
		dataheight : 'auto'
	}

	// 新增初始化参数
	var addOptions = {
		drag : true,
		resize : true,
		closeOnEscape : true,
		dataheight : 'auto'
	}

	// 删除参数
	var delOptions = {

	}

	// 搜索参数
	var searchOptions = {

	}

	jQuery('#grid').jqGrid({
		width : 960,
		url : "sys/user",
		mtype: "GET",
		height : "auto",
		datatype : "json",
		colModel:[  
	              {name: "id" ,index: "id" ,label:"ID",width:40,sortable:true,editable : true},
	              {name:"usgId",index:"usgId",label:"所在组ID",width:80,editable : true},
	              {name:"userName",index:"userName",label:"用户名",width:80,editable : true}, 
	              {name:"email",index:"email",label:"电子邮箱",width:200,editable : true},
	              {name:"password",index:"password",label:"密码",width:160,editable : true},
	              {name:"nickname",index:"nickname",label:"昵称",width:120,editable : true},
	              {name:"fullname",index:"fullname",label:"全名",width:120,editable : true},
	              {name:"status",index:"status",label:"状态",width:120,editable : true},
	              {name:"salt",index:"salt",label:"盐值",width:120,editable : true},
	              {name:"description",index:"description",label:"描述",width:120,editable : true},
	              {name:"source",index:"source",label:"描述",width:120,editable : true},
	              {name:"lastLoginDatetime",index:"lastLoginDatetime",label:"最后登录时间",width:200,editable : true}
	              //{name:"createdAt",index:"lastLoginDatetime",label:"创建时间",width:120,sortable:false},
	              //{name:"createdBy",index:"createdBy",label:"创建人",width:120,sortable:false},
	              //{name:"updatedAt",index:"lastLoginDatetime",label:"更新时间",width:120,sortable:false},
	              //{name:"updatedBy",index:"lastLoginDatetime",label:"更新人",width:120,sortable:false},
	        ], 
		pager : "#pager"
	});
	
	// 键盘上下选行
	jQuery('#grid').jqGrid('bindKeys');
	
	//导航
	$("#grid").jqGrid('navGrid', '#pager', {
		edit : true,
		add : true,
		del : true
	}, editOptions, addOptions, delOptions, searchOptions);

});