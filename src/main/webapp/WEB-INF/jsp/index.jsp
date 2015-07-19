<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="taglibs.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="img/favicon.ico">

<title>Dashboard Template for Bootstrap</title>
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
					<li class="active"><a href="login">主页 <span class="sr-only">(current)</span></a></li>
					<li ><a href="org">组织机构</a></li>
					<li><a href="user" >用户</a></li>
					<li><a href="role">角色</a></li>
					<li><a href="fun">功能</a></li>
					<li><a href="res">资源</a></li>
				</ul>
			</div>
			<div id ="context" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">组织机构</h2>
				<div class="table-responsive">
					<h4>欢迎来到云铺后台!</h4>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
