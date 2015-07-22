$(function() {
	jQuery(grid_selector).jqGrid({
		subGrid : false,
		url : "/rest/user/list",
		datatype : "json",
		height : 450,
		colNames : ['', 'ID', '姓名', '性别', '邮箱', '联系电话', '生日', '所属部门', '角色', '是否禁用', '最后登录时间'],
		colModel : [ {
			name : '',
			index : '',
			width : 80,
			fixed : true,
			sortable : false,
			resize : false,
			formatter : 'actions',
			formatoptions : {
				keys : true,
				//delbutton : false,//disable delete button
				delOptions : {
					recreateForm : true,
					beforeShowForm : beforeDeleteCallback
				}
				//editformbutton:true, editOptions:{recreateForm:true, beforeShowForm:beforeEditCallback}
			}
		}, {
			name : 'id',
			index : 'id',
			label : 'ID',
			width : 60,
			sorttype : "long",
			search : false
		}, {
			name : 'userName',
			index : 'userName',
			label : '姓名',
			width : 100,
			editable : true,
			editoptions : {size : "20", maxlength : "50"},
			searchoptions : {sopt : ['cn']},
			editrules : {required : true}
		}, {
			name : 'sexCn',
			index : 'sex',
			label : '性别',
			width : 80,
			editable : true,
			edittype : "select",
			editoptions : {value : "1:男;2:女"},
			search : false
		}, {
			name : 'email',
			index : 'email',
			label : '邮箱',
			width : 160,
			editable : true,
			editoptions : {size : "20", maxlength : "30"},
			searchoptions : {sopt : ['eq']},
			editrules : {required : true}
		}, {
			name : 'phone',
			index : 'phone',
			label : '联系电话',
			width : 110,
			editable : true,
			editoptions : {size : "20", maxlength : "20"},
			search : false
		}, {
			name : 'birthday',
			index : 'birthday',
			label : '生日',
			width : 110,
			editable : true,
			readonly : true,
			search : false,
			sorttype : 'date',
			unformat : pickDate
		}, {
			name : 'departmentValue',
			index : 'departmentKey',
			label : '所属部门',
			width : 120,
			editable : true,
			edittype : "select",
			//editoptions : {value : "YFB:研发部;XZB:行政部"},
			editoptions : {
				dataUrl : "${contextPath}/sys/department/getDepartmentSelectList"
			},
			search : false
		}, {
			name : 'roleCn',
			index : 'role',
			label : '角色',
			width : 100,
			editable : true,
			edittype : "select",
			editoptions : {value : "ROLE_ADMIN:超级管理员;ROLE_RESTRICTED_ADMIN:普通管理员;ROLE_USER:普通用户"},
			search : false
		}, {
			name : 'statusCn',
			index : 'status',
			label : '是否禁用',
			width : 80,
			editable : true,
			edittype : "checkbox",
			editoptions : {value : "是:否"},
			unformat : aceSwitch,
			search : false
		}, {
			name : 'lastLoginTime',
			index : 'lastLoginTime',
			label : '最后登录时间',
			width : 150,
			sorttype : "date",
			search : false
		} ],
		//scroll : 1, // set the scroll property to 1 to enable paging with scrollbar - virtual loading of records
		sortname : "id",
		sortorder : "asc",
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : pager_selector,
		altRows : true,
		//toppager : true,
		multiselect : true,
		//multikey : "ctrlKey",
        multiboxonly : true,
		loadComplete : function() {
			var table = this;
			setTimeout(function(){
				styleCheckbox(table);
				updateActionIcons(table);
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		},
		editurl : "${contextPath}/sys/sysuser/operateSysUser"
		//caption : "用户管理列表",
		//autowidth : true,
		/**
		grouping : true, 
		groupingView : { 
			 groupField : ['name'],
			 groupDataSorted : true,
			 plusicon : 'fa fa-chevron-down bigger-110',
			 minusicon : 'fa fa-chevron-up bigger-110'
		},
		*/
	});
	
	
	// enable datepicker
	function pickDate(cellvalue, options, cell) {
		setTimeout(function() {
			$(cell).find('input[type=text]').datepicker({
				format : 'yyyy-mm-dd',
				autoclose : true,
			    language: 'zh-CN'
			});
		}, 0);
	}
	
	

	// navButtons
	jQuery(grid_selector).jqGrid('navGrid', pager_selector, { // navbar options
		edit : true,
		editicon : 'ace-icon fa fa-pencil blue',
		add : true,
		addicon : 'ace-icon fa fa-plus-circle purple',
		del : true,
		delicon : 'ace-icon fa fa-trash-o red',
		search : true,
		searchicon : 'ace-icon fa fa-search orange',
		refresh : true,
		refreshicon : 'ace-icon fa fa-refresh blue',
		view : true,
		viewicon : 'ace-icon fa fa-search-plus grey'
	}, {
		// edit record form
		// closeAfterEdit: true,
		// width: 700,
		recreateForm : true,
		beforeShowForm : function(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
			style_edit_form(form);
		},
		errorTextFormat: function (response) {
			var result = eval('('+response.responseText+')');
		    return result.message;
		}
	}, {
		// new record form
		// width: 700,
		closeAfterAdd : true,
		recreateForm : true,
		viewPagerButtons : false,
		beforeShowForm : function(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
			style_edit_form(form);
		},
		errorTextFormat: function (response) {
			var result = eval('('+response.responseText+')');
		    return result.message;
		}
	}, {
		// delete record form
		recreateForm : true,
		beforeShowForm : function(e) {
			var form = $(e[0]);
			if (form.data('styled'))
				return false;
			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
			style_delete_form(form);
			form.data('styled', true);
		},
		onClick : function(e) {
			// alert(1);
		}
	}, {
		// search form
		recreateForm : true,
		afterShowSearch : function(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
			style_search_form(form);
		},
		afterRedraw : function() {
			style_search_filters($(this));
		},
		multipleSearch : true 
		/**
		 * multipleGroup:true, showQuery: true
		 */
	}, {
		// view record form
		recreateForm : true,
		beforeShowForm : function(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
		}
	})

});
