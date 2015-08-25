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

/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : yunpos

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-08-25 13:59:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `applicationCode` int(10) DEFAULT NULL COMMENT '应用编号',
  `applicationName` varchar(50) DEFAULT NULL COMMENT '应用名称',
  `applicationDesc` varchar(255) DEFAULT NULL COMMENT '应用描述',
  `showInMenu` int(10) DEFAULT NULL COMMENT '是否在菜单显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统应用';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('1', '10001', '系统app1', '商城系统', '1');
INSERT INTO `sys_app` VALUES ('2', '10002', '系统app2', '商城系统', '1');
INSERT INTO `sys_app` VALUES ('6', '1007', 'eee', 'eee', '1');
INSERT INTO `sys_app` VALUES ('7', '1007', 'eeee', 'eee', '1');
INSERT INTO `sys_app` VALUES ('8', '1007', 'WWW', 'WW', '0');
INSERT INTO `sys_app` VALUES ('9', '2009', 'wwww', 'ww', '1');
INSERT INTO `sys_app` VALUES ('10', '2009', '2www', 'ww', '1');

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '按钮ID',
  `btnName` varchar(50) DEFAULT NULL COMMENT '按钮名称',
  `btnNo` int(10) DEFAULT NULL COMMENT '按钮编号',
  `btnClass` text COMMENT '按钮css风格',
  `btnIcon` varchar(50) DEFAULT NULL COMMENT '按钮图标',
  `btnScript` text COMMENT '按钮脚本',
  `menuNo` int(10) DEFAULT NULL COMMENT '菜单编号',
  `InitStatus` int(10) DEFAULT NULL COMMENT '初始状态',
  `seqNo` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统按钮';

-- ----------------------------
-- Records of sys_button
-- ----------------------------
INSERT INTO `sys_button` VALUES ('1', '用户新增', '1', '1', 'ee', 'ee', '1', '1', '1');
INSERT INTO `sys_button` VALUES ('2', '用户删除', '2', '1', 'rrr', 'ee', '1', '1', null);

-- ----------------------------
-- Table structure for sys_data_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_rule`;
CREATE TABLE `sys_data_rule` (
  `id` int(10) unsigned NOT NULL COMMENT '规则ID',
  `dataType` int(10) DEFAULT NULL COMMENT '数据类型',
  `dataRule` text COMMENT '数据规则',
  `userId` int(10) unsigned DEFAULT NULL COMMENT '操作员ID',
  `roleId` int(10) unsigned DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据规则';

-- ----------------------------
-- Records of sys_data_rule
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '系统菜单ID',
  `sequence` int(10) DEFAULT NULL,
  `menuNo` int(10) DEFAULT NULL COMMENT '系统菜单编号',
  `applicationCode` int(10) DEFAULT NULL COMMENT '应用编号',
  `menuParentNo` int(10) DEFAULT NULL COMMENT '父级菜单编号',
  `menuOrder` int(10) DEFAULT NULL COMMENT '菜单排序编号',
  `menuName` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menuUrl` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `isVisible` int(10) DEFAULT NULL COMMENT '是否允许访问',
  `isLeaf` int(10) DEFAULT NULL COMMENT '是否子菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', null, '1', '1', null, '1', '组织机构', 'page/org', '1', null);
INSERT INTO `sys_menu` VALUES ('2', null, '2', '2', null, '2', '用户管理', 'page/user', '1', null);
INSERT INTO `sys_menu` VALUES ('3', null, '3', '3', null, '3', '角色管理', 'page/role', '1', null);
INSERT INTO `sys_menu` VALUES ('4', null, '4', '4', null, '4', '用户角色管理', 'page/user_role', '1', null);
INSERT INTO `sys_menu` VALUES ('5', null, '5', '5', null, '5', '权限管理', 'page/privilege', '1', null);
INSERT INTO `sys_menu` VALUES ('6', null, '6', '6', null, '6', '资源', '', '1', null);
INSERT INTO `sys_menu` VALUES ('7', null, '7', '7', '6', '7', '菜单资源管理', 'page/menu_res', '1', null);
INSERT INTO `sys_menu` VALUES ('8', null, '8', '8', '6', '8', 'app资源管理', 'page/app_res', '1', null);
INSERT INTO `sys_menu` VALUES ('9', null, '9', '9', '6', '9', '按钮资源', 'page/button_res', '1', null);
-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '组织机构ID',
  `orgNo` varchar(255) DEFAULT NULL COMMENT '组织机构代码',
  `orgName` varchar(255) DEFAULT NULL COMMENT '组织机构名称',
  `orgParentId` int(10) unsigned DEFAULT NULL COMMENT '父级组织机构ID',
  `orgParentName` varchar(255) DEFAULT NULL COMMENT '父级组织机构名称',
  `orgParentNo` varchar(255) DEFAULT NULL COMMENT '父级组织机构代码',
  `level` int(255) DEFAULT NULL COMMENT '层级',
  `createUserId` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  `extLevel` int(10) DEFAULT NULL COMMENT 'jqgrid树表扩展字段',
  `extIsLeaf` tinyint(1) DEFAULT NULL COMMENT 'jqgrid树表扩展字段',
  `extLoaded` tinyint(1) DEFAULT NULL COMMENT 'jqgrid树表扩展字段',
  `extExpanded` tinyint(1) DEFAULT NULL COMMENT 'jqgrid树表扩展字段',
  `extParent` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('24', '1', 'cash', null, null, null, null, '1', '2015-08-03 17:54:06', null, null, '0', '0', '1', '1', null);
INSERT INTO `sys_org` VALUES ('25', '2', 'cash_1', '24', 'cash', '1', null, '1', '2015-08-03 17:54:31', null, null, '1', '1', '1', '1', '24');
INSERT INTO `sys_org` VALUES ('26', '4', 'moush', null, null, null, null, '1', '2015-08-03 17:57:24', null, null, '0', '1', '1', '1', null);
INSERT INTO `sys_org` VALUES ('27', '3', 'cash_2', '24', 'cash', '1', null, '1', '2015-08-03 17:57:45', null, null, '1', '1', '1', '1', '24');

-- ----------------------------
-- Table structure for sys_pay_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_pay_order`;
CREATE TABLE `sys_pay_order` (
  `id` int(11) NOT NULL,
  `payOrderNo` varchar(50) DEFAULT NULL COMMENT '支付流水号',
  `status` tinyint(4) DEFAULT NULL COMMENT '支付状态',
  `payCode` varchar(50) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  `userId` int(11) DEFAULT NULL COMMENT '收银员ID',
  `price` decimal(10,0) DEFAULT NULL COMMENT '支付金额',
  `barCode` varchar(20) DEFAULT NULL COMMENT '条码',
  `imei` varchar(20) DEFAULT NULL,
  `deviceType` tinyint(4) DEFAULT NULL,
  `notify_time` datetime DEFAULT NULL COMMENT '异步回调时间',
  `trade_no` varchar(50) DEFAULT NULL COMMENT '支付宝交易号',
  `seller_email` varchar(50) DEFAULT NULL COMMENT '卖家支付宝账号',
  `buyer_email` varchar(50) DEFAULT NULL,
  `seller_id` varchar(50) DEFAULT NULL,
  `buyer_id` varchar(50) DEFAULT NULL COMMENT '买家支付宝用户号',
  `body` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `createAt` datetime DEFAULT NULL COMMENT '创建时间',
  `createBy` int(11) DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL COMMENT '更新时间',
  `updateBy` int(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_pay_order
-- ----------------------------

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `privilegeMaster` varchar(50) DEFAULT NULL,
  `privilegeMasterValue` int(10) DEFAULT NULL,
  `privilegeAccess` varchar(50) DEFAULT NULL,
  `privilegeAccessValue` int(10) DEFAULT NULL,
  `privilegeOperation` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='功能权限表';

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
INSERT INTO `sys_privilege` VALUES ('3', 'role', '8', 'menu', '2', null);
INSERT INTO `sys_privilege` VALUES ('4', 'role', '8', 'menu', '3', null);
INSERT INTO `sys_privilege` VALUES ('5', 'role', '8', 'menu', '4', null);
INSERT INTO `sys_privilege` VALUES ('6', 'role', '8', 'menu', '5', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `roleName` varchar(50) NOT NULL COMMENT '角色名称',
  `roleDesc` varchar(255) DEFAULT NULL COMMENT '描述',
  `orgId` int(10) unsigned DEFAULT NULL COMMENT '所属组织机构',
  `orgName` varchar(255) DEFAULT NULL,
  `createUserId` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色表（组织机构，岗位）';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('8', 'admin', '系统管理员', '24', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `orgId` int(11) DEFAULT NULL COMMENT '组织结构id',
  `orgName` varchar(255) DEFAULT NULL COMMENT '组织结构名',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', null, 'sysadmin', null, 'sysadmin@elvea.cn', '54a42628b7507805dd1bae08f40ccaf6274cce1b', 'Administrator', null, '1', '046548c3b1e3ab57', null, 'SYSTEM', '2015-07-27 15:37:14', null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', '1', null, 'admin', '', 'admin@elvea.cn', '54a42628b7507805dd1bae08f40ccaf6274cce1b', 'Administrator', '', '0', null, '', null, null, null, null, '2015-08-03 09:49:19', '1');
INSERT INTO `sys_user` VALUES ('5', '1', null, 'yang', '18200982382', '375455761@qq.com', '123456', '1111', '1111', '0', null, '111', null, null, null, null, '2015-08-03 09:47:44', '1');

-- ----------------------------
-- Table structure for sys_users_session
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_session`;
CREATE TABLE `sys_users_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sessionId` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `host` varchar(255) NOT NULL,
  `lastAccessTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_session
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色表ID',
  `userId` int(10) unsigned NOT NULL COMMENT '用户ID',
  `roleId` int(10) unsigned NOT NULL COMMENT '角色ID',
  `createUserId` int(10) unsigned DEFAULT NULL COMMENT '创建用户ID',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyUserId` int(10) unsigned DEFAULT NULL COMMENT '修改用户ID',
  `modifyDate` datetime DEFAULT NULL COMMENT '修改I时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('8', '1', '8', '1', '2015-08-04 11:19:30', null, null);
