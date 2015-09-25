
--20150914 杨学勇  新增字段
ALTER TABLE `sys_alipay_config`
ADD COLUMN `merchanSynNotify`  varchar(255) NULL COMMENT '支付宝wap第三方同步回调地址' AFTER `appPublicKey`,
ADD COLUMN `merchanAsynNotify`  varchar(255) NULL COMMENT '支付宝wap第三方异步回调地址' AFTER `merchanSynNotify`,
ADD COLUMN `synNotify`  varchar(255) NULL COMMENT '支付宝wap平台同步回调地址' AFTER `merchanAsynNotify`,
ADD COLUMN `asynNotify`  varchar(255) NULL COMMENT '支付宝wap平台异步回调地址' AFTER `synNotify`,
ADD COLUMN `devPublicKey`  longtext NULL COMMENT '开发者公钥（服务窗用）' AFTER `asynNotify`,
ADD COLUMN `devGetway`  varchar(255) NULL COMMENT '开发者网关（服务窗用）' AFTER `devPublicKey`;

--20150915 杨学勇  新增字段
ALTER TABLE `sys_transaction`
ADD COLUMN `user_order_no`  varchar(50) NULL COMMENT '商户订单号' AFTER `orderId`;

--20150915 杨学勇  新增字段
ALTER TABLE `sys_merchant`
ADD COLUMN `md5Key` varchar(100) NULL COMMENT '商户秘钥（MD5加密用）' AFTER `agentSerialNo`;

--20150915 林虎  新增字段
ALTER TABLE `sys_agentmerchant`
ADD COLUMN `status`  tinyint(2) NULL COMMENT '代理商状态' ;

ALTER TABLE `sys_agentmerchant`
ADD COLUMN `auditOpinion`  varchar(100) NULL COMMENT '审核意见' ;

--20150915 bino  新增字段
DROP TABLE IF EXISTS `filter`;
CREATE TABLE `filter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `filter_difinition_id` int(11) DEFAULT NULL,
  `op` int(11) DEFAULT NULL,
  `filter_value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `filter_difinition`;
CREATE TABLE `filter_difinition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `value_type` int(11) DEFAULT NULL,
  `data_type` int(11) DEFAULT NULL,
  `col_name` varchar(45) DEFAULT NULL,
  `key_param` varchar(45) DEFAULT NULL,
  `key_column` varchar(45) DEFAULT NULL,
  `support_op` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `filter_difinition_value`;
CREATE TABLE `filter_difinition_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filter_difinition_id` int(11) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `filter_group`;
CREATE TABLE `filter_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `key_param`;
CREATE TABLE `key_param` (
  `name` varchar(45) NOT NULL,
  `table` varchar(45) DEFAULT NULL,
  `column` varchar(45) DEFAULT NULL,
  `primary_column` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `table_name` varchar(45) NOT NULL,
  `path` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


--20150924 杨学勇  新增表
CREATE TABLE `sys_pay` (
  `id` int(11) NOT NULL,
  `payName` varchar(50) DEFAULT NULL COMMENT '支付名称',
  `payDes` varchar(200) DEFAULT NULL COMMENT '支付描述',
  `open` int(2) DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;