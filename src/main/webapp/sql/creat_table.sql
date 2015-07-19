-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.0.20-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 yunpos 的数据库结构
CREATE DATABASE IF NOT EXISTS `yunpos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `yunpos`;


-- 导出  表 yunpos.data_rule 结构
CREATE TABLE IF NOT EXISTS `data_rule` (
  `RuleID` int(10) unsigned NOT NULL COMMENT '规则ID',
  `DataType` int(10) DEFAULT NULL COMMENT '数据类型',
  `DataRule` text COMMENT '数据规则',
  `UserID` int(10) unsigned DEFAULT NULL COMMENT '操作员ID',
  `RoleID` int(10) unsigned DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`RuleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据规则';

-- 数据导出被取消选择。


-- 导出  表 yunpos.org 结构
CREATE TABLE IF NOT EXISTS `org` (
  `OrgID` int(10) unsigned NOT NULL COMMENT '组织机构ID',
  `OrgNo` varchar(255) DEFAULT NULL COMMENT '组织机构代码',
  `OrgName` varchar(255) DEFAULT NULL COMMENT '组织机构名称',
  `OrgParentID` int(10) unsigned DEFAULT NULL COMMENT '父级组织机构ID',
  `OrgParentName` varchar(255) DEFAULT NULL COMMENT '父级组织机构名称',
  `OrgParentNo` varchar(255) DEFAULT NULL COMMENT '父级组织机构代码',
  `CreateUserID` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifyUserID` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `ModifyDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`OrgID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- 数据导出被取消选择。


-- 导出  表 yunpos.privilege 结构
CREATE TABLE IF NOT EXISTS `privilege` (
  `PrivilegeID` int(10) unsigned NOT NULL,
  `PrivilegeMaster` varchar(50) DEFAULT NULL, COMMENT='主体（角色/用户）'
  `PrivilegeMasterValue` int(10) DEFAULT NULL, COMMENT='主体Id'
  `PrivilegeAccess` varchar(50) DEFAULT NULL, COMMENT='资源类型'
  `PrivilegeAccessValue` int(10) DEFAULT NULL COMMENT='资源ID（按钮/菜单）';
  `PrivilegeOperation` int(10) DEFAULT NULL ，COMMENT='CRUD';
  PRIMARY KEY (`PrivilegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能权限表';

-- 数据导出被取消选择。


-- 导出  表 yunpos.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `RoleID` int(10) unsigned NOT NULL COMMENT '角色ID',
  `RoleName` varchar(50) NOT NULL COMMENT '角色名称',
  `RoleDesc` varchar(255) DEFAULT NULL COMMENT '描述',
  `OrgID` int(10) unsigned DEFAULT NULL COMMENT '所属组织机构',
  `CreateUserID` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifyUserID` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `ModifyDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表（组织机构，岗位）';

-- 数据导出被取消选择。


-- 导出  表 yunpos.sys_app 结构
CREATE TABLE IF NOT EXISTS `sys_app` (
  `ApplicationID` int(10) unsigned NOT NULL COMMENT '应用ID',
  `ApplicationCode` int(10) DEFAULT NULL COMMENT '应用编号',
  `ApplicationName` varchar(50) DEFAULT NULL COMMENT '应用名称',
  `ApplicationDesc` varchar(255) DEFAULT NULL COMMENT '应用描述',
  `ShowInMenu` int(10) DEFAULT NULL COMMENT '是否在菜单显示',
  PRIMARY KEY (`ApplicationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统应用';

-- 数据导出被取消选择。


-- 导出  表 yunpos.sys_button 结构
CREATE TABLE IF NOT EXISTS `sys_button` (
  `BtnID` int(10) unsigned NOT NULL COMMENT '按钮ID',
  `BtnName` varchar(50) DEFAULT NULL COMMENT '按钮名称',
  `BtnNo` int(10) DEFAULT NULL COMMENT '按钮编号',
  `BtnClass` text COMMENT '按钮css风格',
  `BtnIcon` varchar(50) DEFAULT NULL COMMENT '按钮图标',
  `BtnScript` text COMMENT '按钮脚本',
  `MenuNo` int(10) DEFAULT NULL COMMENT '菜单编号',
  `InitStatus` int(10) DEFAULT NULL COMMENT '初始状态',
  `SeqNo` int(10) DEFAULT NULL,
  PRIMARY KEY (`BtnID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统按钮';

-- 数据导出被取消选择。


-- 导出  表 yunpos.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `MenuID` int(10) unsigned NOT NULL COMMENT '系统菜单ID',
  `MenuNo` int(10) DEFAULT NULL COMMENT '系统菜单编号',
  `ApplicationCode` int(10) DEFAULT NULL COMMENT '应用编号',
  `MenuParentNo` int(10) DEFAULT NULL COMMENT '父级菜单编号',
  `MenuOrder` int(10) DEFAULT NULL COMMENT '菜单排序编号',
  `MenuName` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `MenuUrl` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `IsVisible` int(10) DEFAULT NULL COMMENT '是否允许访问',
  `IsLeaf` int(10) DEFAULT NULL COMMENT '是否子菜单',
  PRIMARY KEY (`MenuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- 数据导出被取消选择。


-- 导出  表 yunpos.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `UserID` int(10) unsigned NOT NULL COMMENT '用户ID',
  `LoginName` varchar(100) NOT NULL COMMENT '登录名称',
  `LoginPassword` varchar(100) NOT NULL COMMENT '登录密码',
  `OrgID` int(10) unsigned DEFAULT NULL COMMENT '所属组织机构',
  `RealName` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `Title` varchar(255) DEFAULT NULL COMMENT '描述',
  `Sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `Phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `CreateUserID` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifyUserID` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `ModifyDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 数据导出被取消选择。


-- 导出  表 yunpos.user_role 结构
CREATE TABLE IF NOT EXISTS `user_role` (
  `UserRoleID` int(10) unsigned NOT NULL COMMENT '用户角色表ID',
  `UserID` int(10) unsigned NOT NULL COMMENT '用户ID',
  `RoleID` int(10) unsigned NOT NULL COMMENT '角色ID',
  `CreateUserID` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ModifyUserID` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `ModifyDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改I时间',
  PRIMARY KEY (`UserRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
