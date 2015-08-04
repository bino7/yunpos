var URL = $ctx + "/ajax/org";

$(document).ready(
		function() {
			var status = {0:"否",1:"是"};
			$("#tree").jqGrid({
				url : URL,
				width:960,
				height:"auto",
				sortname : 'orgNo',
				rowNum:1000,
				treeGrid : true,
				treeGridModel : 'adjacency', // treeGrid模式，跟json元数据有关
				ExpandColumn : 'orgName',
				editurl : URL,
				datatype : "json",
				jsonReader : {
					repeatitems : false,
					id : "id"
				}, 
				colModel:[      
					        {name:'id',index:'id',label:"ID", width:10,key: true,editable:true,hidden:true},  
					        {name:'orgName',index:'orgName',label:"名称", width:200,editable:true},
					        {name:'orgNo',index:'orgNo',label:"组织机构代码", width:110,sorttype:"int",editable:true},      
					        {name:'orgParentName',index:'orgParentName',label:"父级名称", width:80},
					        {name:'orgParentNo',index:'orgParentNo',label:"父级代码", width:80},
					        {name:'createUserId',index:'createUserId',label:"创建用户ID", width:80},
					        {name:'createDate',index:'createDate',label:"创建时间", width:160},
					        {name:'modifyUserId',index:'modifyUserId',label:"修改用户ID", width:80},
					        {name:'modifyDate',index:'modifyDate',label:"修改时间", width:160},
					        {name:'orgParentId',index:'orgParentId',label:"父级ID", width:80,editable:true,hidden:true},
					        {name:'extLevel',index:'extLevel',label:"层级", width:80,editable:true,hidden:true}
					     ],
				pager : "#pager",
			
				//tree_root_level : 0,
				jsonReader : {
					id : "id",
					root : "rows",
					repeatitems : false
				},
				treeReader : {
					level_field : "extLevel",
					parent_id_field : "orgParentId",
					leaf_field : "extIsLeaf",
					expanded_field : "extExpanded",
					loaded:"extLoaded"
				},
				shrinkToFit : false, // 控制水平滚动条
				ondblClickRow : function(id) {
					jQuery(this).jqGrid('editGridRow', id, editOptions);
				}
			});

			$("#tree").jqGrid("navGrid", "#pager", jqGridNavBarOptions,
					jqGridEditOptions, jqGridAddOptions, jqGridDelOptions,
					jqGridSearchOptions);

			
		});
