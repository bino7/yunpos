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

-- ----------------------------
-- Table structure for data_rule
-- ----------------------------
DROP TABLE IF EXISTS `data_rule`;
CREATE TABLE `data_rule` (
  `RuleID` int(10) unsigned NOT NULL COMMENT '规则ID',
  `DataType` int(10) DEFAULT NULL COMMENT '数据类型',
  `DataRule` text COMMENT '数据规则',
  `UserID` int(10) unsigned DEFAULT NULL COMMENT '操作员ID',
  `RoleID` int(10) unsigned DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`RuleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据规则';

-- ----------------------------
-- Records of data_rule
-- ----------------------------

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `OrgID` int(10) unsigned NOT NULL COMMENT '组织机构ID',
  `OrgNo` varchar(255) DEFAULT NULL COMMENT '组织机构代码',
  `OrgName` varchar(255) DEFAULT NULL COMMENT '组织机构名称',
  `OrgParentID` int(10) unsigned DEFAULT NULL COMMENT '父级组织机构ID',
  `OrgParentName` varchar(255) DEFAULT NULL COMMENT '父级组织机构名称',
  `OrgParentNo` varchar(255) DEFAULT NULL COMMENT '父级组织机构代码',
  `CreateUserID` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  `ModifyUserID` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `ModifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `level` int(10) DEFAULT NULL COMMENT 'jqgrid树表扩展字段',
  `isLeaf` tinyint(1) DEFAULT NULL COMMENT 'jqgrid树表扩展字段',
  `loaded` tinyint(1) DEFAULT NULL COMMENT 'jqgrid树表扩展字段',
  `expanded` tinyint(1) DEFAULT NULL COMMENT 'jqgrid树表扩展字段',
  PRIMARY KEY (`OrgID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of org
-- ----------------------------

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `PrivilegeID` int(10) unsigned NOT NULL,
  `PrivilegeMaster` varchar(50) DEFAULT NULL,
  `PrivilegeMasterValue` int(10) DEFAULT NULL,
  `PrivilegeAccess` varchar(50) DEFAULT NULL,
  `PrivilegeAccessValue` int(10) DEFAULT NULL,
  `PrivilegeOperation` int(10) DEFAULT NULL,
  PRIMARY KEY (`PrivilegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能权限表';

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('1', 'role', '1', 'menu', '1', null);
INSERT INTO `privilege` VALUES ('2', 'role', '2', 'menu', '1', null);
INSERT INTO `privilege` VALUES ('3', 'role', '1', 'menu', '2', null);
INSERT INTO `privilege` VALUES ('4', 'role', '1', 'button', '1', null);
INSERT INTO `privilege` VALUES ('5', 'role', '1', 'button', '2', null);
INSERT INTO `privilege` VALUES ('6', 'role', '1', 'button', '3', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `RoleID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `RoleName` varchar(50) NOT NULL COMMENT '角色名称',
  `RoleDesc` varchar(255) DEFAULT NULL COMMENT '描述',
  `OrgID` int(10) unsigned DEFAULT NULL COMMENT '所属组织机构',
  `CreateUserID` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  `ModifyUserID` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `ModifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表（组织机构，岗位）';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', 'aaaaa', '1', '11', '2015-07-27 17:08:54', '1', '2015-07-27 17:09:01');
INSERT INTO `role` VALUES ('2', 'user', 'bbbb', '1', '1', '2015-07-28 18:43:50', null, null);
INSERT INTO `role` VALUES ('4', 'yang', '1', '1', '1', '2015-07-28 18:47:57', null, null);

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `ApplicationID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `ApplicationCode` int(10) DEFAULT NULL COMMENT '应用编号',
  `ApplicationName` varchar(50) DEFAULT NULL COMMENT '应用名称',
  `ApplicationDesc` varchar(255) DEFAULT NULL COMMENT '应用描述',
  `ShowInMenu` int(10) DEFAULT NULL COMMENT '是否在菜单显示',
  PRIMARY KEY (`ApplicationID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统应用';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('1', '10001', '系统app1', '商城系统', '1');
INSERT INTO `sys_app` VALUES ('2', '10002', '系统app2', '商城系统', '1');
INSERT INTO `sys_app` VALUES ('4', '1003', '22', '44', null);

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button` (
  `BtnID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '按钮ID',
  `BtnName` varchar(50) DEFAULT NULL COMMENT '按钮名称',
  `BtnNo` int(10) DEFAULT NULL COMMENT '按钮编号',
  `BtnClass` text COMMENT '按钮css风格',
  `BtnIcon` varchar(50) DEFAULT NULL COMMENT '按钮图标',
  `BtnScript` text COMMENT '按钮脚本',
  `MenuNo` int(10) DEFAULT NULL COMMENT '菜单编号',
  `InitStatus` int(10) DEFAULT NULL COMMENT '初始状态',
  `SeqNo` int(10) DEFAULT NULL,
  PRIMARY KEY (`BtnID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统按钮';

-- ----------------------------
-- Records of sys_button
-- ----------------------------
INSERT INTO `sys_button` VALUES ('1', '用户新增', '1', '1', 'ee', 'ee', '1', '1', '1');
INSERT INTO `sys_button` VALUES ('2', '用户删除', '2', '1', 'ee', 'ee', '1', '1', '2');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
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

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '1', '1', null, '1', 'user:add', 'rest/user', '1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `usgId` int(11) DEFAULT NULL COMMENT '分组ID',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(255) NOT NULL COMMENT '电子邮箱',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `fullname` varchar(255) DEFAULT NULL COMMENT '全名',
  `status` varchar(50) DEFAULT NULL COMMENT '用户状态',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `source` varchar(50) DEFAULT NULL,
  `lastLoginDatetime` datetime DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `updatedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_email` (`email`),
  UNIQUE KEY `idx_sys_users_username` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'sysadmin', null, 'sysadmin@elvea.cn', '54a42628b7507805dd1bae08f40ccaf6274cce1b', 'Administrator', null, '1', '046548c3b1e3ab57', null, 'SYSTEM', '2015-07-27 15:37:14', null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', '1', 'admin', null, 'admin@elvea.cn', 'db8ce0a0872d80fa31b7ebd0d0c06cf02a900e57', 'Administrator', null, '1', 'e416781ea9858934', null, 'SYSTEM', '2015-07-27 15:37:16', null, null, null, null);
INSERT INTO `sys_user` VALUES ('6', null, 'yang', null, '375455761@qq.com', '123456', 'www', '1111', null, null, null, null, null, '2015-07-28 17:22:10', '1', null, null);

-- ----------------------------
-- Table structure for sys_users_session
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_session`;
CREATE TABLE `sys_users_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `host` varchar(255) NOT NULL,
  `last_access_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `UserRoleID` int(10) unsigned NOT NULL COMMENT '用户角色表ID',
  `UserID` int(10) unsigned NOT NULL COMMENT '用户ID',
  `RoleID` int(10) unsigned NOT NULL COMMENT '角色ID',
  `CreateUserID` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  `ModifyUserID` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `ModifyDate` datetime DEFAULT NULL COMMENT '修改I时间',
  PRIMARY KEY (`UserRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', null, null, null, null);
INSERT INTO `user_role` VALUES ('2', '1', '2', null, null, null, null);

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
