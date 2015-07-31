<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav nav-sidebar">
	<li class="active"><a href="#">首页</a></li>
	<li><a href="${ctx}/page/org">组织机构</a></li>
	<li><a href="${ctx}/page/user">用户</a></li>
	<li><a href="${ctx}/page/role">角色</a></li>
	<li><a href="${ctx}/page/fun">功能</a></li>
	<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">资源 <b class="caret"></b></a>
       <ul class="dropdown-menu">
         <li><a href="${ctx}/page/menu_res">菜单资源管理</a></li>
         <li><a href="${ctx}/page/app_res">app资源</a></li>
          <li><a href="${ctx}/page/button_res">按钮资源</a></li>
       </ul>
    </li>
</ul>