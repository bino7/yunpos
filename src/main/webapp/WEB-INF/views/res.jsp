<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard Template for Bootstrap</title>
</head>

<body>
	<h2 class="sub-header">资源</h2>
	<div class="table-responsive">
		<table id="tree"></table>
		<div id="pager"></div>

		<script type="text/javascript">
			jQuery(document).ready(function($) {
				jQuery('#tree').jqGrid({
					"width" : "960",
					"hoverrows" : false,
					"viewrecords" : false,
					"gridview" : true,
					"url" : "static/treegridjs/all_crud/data.json",
					"editurl" : "clientArray",
					"ExpandColumn" : "name",
					"height" : "auto",
					"sortname" : "account_id",
					"scrollrows" : true,
					"treeGrid" : true,
					"treedatatype" : "json",
					"treeGridModel" : "adjacency",
					"loadonce" : true,
					"rowNum" : 1000,
					"treeReader" : {
						"parent_id_field" : "parent_id",
						"level_field" : "level",
						"leaf_field" : "isLeaf",
						"expanded_field" : "expanded",
						"loaded" : "loaded",
						"icon_field" : "icon"
					},
					"datatype" : "json",
					"colModel" : [ {
						"name" : "account_id",
						"key" : true,
						"hidden" : true
					}, {
						"name" : "name",
						"label" : "单位",
						"width" : 170,
						"editable" : true
					}, {
						"name" : "acc_num",
						"label" : "创建人",
						"width" : 170,
						"editable" : true
					}, {
						"name" : "debit",
						"sorttype" : "numeric",
						"label" : "创建时间",
						"width" : 90,
						"formatter" : "number",
						"align" : "right",
						"editable" : true
					}, {
						"name" : "credit",
						"sorttype" : "numeric",
						"formatter" : "number",
						"label" : "修改人",
						"width" : 90,
						"align" : "right",
						"editable" : true
					}, {
						"name" : "balance",
						"sorttype" : "numeric",
						"formatter" : "number",
						"label" : "修改时间",
						"width" : 90,
						"align" : "right",
						"editable" : true
					}, {
						"name" : "parent_id",
						"hidden" : true
					} ],
					"pager" : "#pager"
				});
				// nable add
				jQuery('#tree').jqGrid('navGrid', '#pager', {
					"edit" : true,
					"add" : true,
					"del" : true,
					"search" : false,
					"refresh" : true,
					"view" : false,
					"excel" : false,
					"pdf" : false,
					"csv" : false,
					"columns" : false
				}, {
					"drag" : true,
					"resize" : true,
					"closeOnEscape" : true,
					"dataheight" : 160
				}, {
					"drag" : true,
					"resize" : true,
					"closeOnEscape" : true,
					"dataheight" : 160
				});
				jQuery('#tree').jqGrid('bindKeys');
			});
		</script>
	</div>
</body>

</html>
