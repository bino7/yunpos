<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="img/favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="css/trirand/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="css/trirand/ui.jqgrid-bootstarp.css" />
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/dashboard.css" rel="stylesheet">

<script type="text/ecmascript" src="js/jquery.min.js"></script>
<script type="text/ecmascript" src="js/trirand/jquery.jqGrid.min.js"></script>
<script type="text/ecmascript" src="js/trirand/i18n/grid.locale-cn.js"></script>

<script src="js/ie-emulation-modes-warning.js"></script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">云铺管理平台</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse"></div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="#">主页 <span class="sr-only">(current)</span></a></li>
					<li><a href="org">组织机构</a></li>
					<li><a href="user" >用户</a></li>
					<li><a href="role">角色</a></li>
					<li class="active"><a href="fun.html">功能</a></li>
					<li><a href="res">资源</a></li>
				</ul>
			</div>
			<div id ="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">功能</h2>
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
								"url" : "treegridjs/all_crud/data.json",
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
			</div>
		</div>
	</div>



	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>

</html>
