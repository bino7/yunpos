<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="nav nav-sidebar">
	<li class="active"><a href="#">首页</a></li>
	<shiro:hasPermission name="menu:page/org"><li><a href="${ctx}/page/org">组织机构</a></li></shiro:hasPermission>	
	<shiro:hasPermission name="menu:page/user"><li><a href="${ctx}/page/user">用户管理</a></li></shiro:hasPermission>
	<shiro:hasPermission name="menu:page/role"><li><a href="${ctx}/page/role">角色管理</a></li></shiro:hasPermission>
	<shiro:hasPermission name="menu:page/user_role"><li><a href="${ctx}/page/user_role">用户角色管理</a></li></shiro:hasPermission>
	<shiro:hasPermission name="menu:page/privilege"><li><a href="${ctx}/page/privilege">权限管理</a></li></shiro:hasPermission>
	<shiro:hasPermission name="menu:/page/fun"><li><a href="${ctx}/page/fun">功能</a></li></shiro:hasPermission>
	<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown">资源 <b class="caret"></b></a>
       <ul class="dropdown-menu">
         <li><a href="${ctx}/page/menu_res">菜单资源管理</a></li>
         <li><a href="${ctx}/page/app_res">app资源管理</a></li>
          <li><a href="${ctx}/page/button_res">按钮资源</a></li>
       </ul>
    </li>
</ul>