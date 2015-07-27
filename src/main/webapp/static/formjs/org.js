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

	jQuery('#tree').jqGrid({
		width : 960,
		url : "static/treegridjs/all_crud/data.json",
		height : "auto",
		datatype : "json",
		colModel : [ {
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
		} ],
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