
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

ALTER TABLE `sys_merchant`
ADD COLUMN `serviceAlipay` varchar(100) NULL COMMENT '服务窗';
ALTER TABLE `sys_merchant`
ADD COLUMN `serviceWeixin` varchar(100) NULL COMMENT '公众号';

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

--20150924 许金彪  添加门店审核状态、审核意见
ALTER TABLE `sys_store`
ADD COLUMN `apprStatus`  int(4) NULL DEFAULT NULL COMMENT '审核状态' AFTER `status`;
ALTER TABLE `sys_store`
ADD COLUMN `apprRemark`  varchar(200) NULL COMMENT '审核意见' AFTER `apprStatus`;

--20150924 杨学勇  新增表
CREATE TABLE `sys_pay` (
  `id` int(11) NOT NULL,
  `payName` varchar(50) DEFAULT NULL COMMENT '支付名称',
  `payDes` varchar(200) DEFAULT NULL COMMENT '支付描述',
  `open` int(2) DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--20150928 许金彪 添加字段
ALTER TABLE `sys_store`
ADD COLUMN `createdAt` datetime NULL COMMENT '创建时间',
ADD COLUMN `createdBy` int(11) NULL COMMENT '创建人',
ADD COLUMN `updatedAt` datetime NULL COMMENT '更新时间',
ADD COLUMN `updatedBy` int(11) NULL COMMENT '更新人';



--20150924 杨学勇  新增字段
ALTER TABLE `sys_wechat_config`
ADD COLUMN `apiSecret` longtext COMMENT 'api秘钥（接口签名使用）' AFTER `mchId`;

ALTER TABLE `sys_pay`
ADD COLUMN `mark` varchar(255)  DEFAULT NULL COMMENT '标识' AFTER `open`;

--20151008 杨学勇  新增字段
ALTER TABLE `sys_transaction`
ADD COLUMN `trade_no`  varchar(64) NULL COMMENT '第三方支付订单号（支付宝交易号trade_no/微信支付订单号transaction_id）' AFTER `info`,
ADD COLUMN `subject`  varchar(256) NULL COMMENT '订单简要描述（支付宝订单标题subject /微信商品描述body）' AFTER `trade_no`;

--20151012 杨学勇  用户表新增字段组织结构号
ALTER TABLE `sys_user`
ADD COLUMN `orgNo`  varchar(20) NULL AFTER `orgId`;

--20151014 杨学勇  新增条码扫码商户异步回调地址（将支付宝异步回调信息）
ALTER TABLE `sys_alipay_config`
ADD COLUMN `merchantBarNotify`  varchar(255) NULL COMMENT '商户条码支付异步回调地址' AFTER `merchanAsynNotify`,
ADD COLUMN `merchantScanNotify`  varchar(255) NULL COMMENT '商户扫码异步回调地址' AFTER `merchantBarNotify`;

ALTER TABLE `sys_wechat_config`
ADD COLUMN `merchantScanNotify`  varchar(255) NULL COMMENT '商户扫码异步回调地址' AFTER `certPassword`;

--20151014 解决关键字冲突
ALTER TABLE `sys_alipay_config`
CHANGE COLUMN `key` `alipaymrikey`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付宝安全校验码' AFTER `pid`;

--20151014 微信新增字段
ALTER TABLE `sys_wechat_config`
ADD COLUMN `mchType`  tinyint(4) NULL COMMENT '类型（服务商0、特约商户1）' AFTER `mchId`,
ADD COLUMN `parentMchId`  varchar(20) NULL COMMENT '父级服务商' AFTER `mchType`;

--20151014 删除多余字段
ALTER TABLE `sys_org`
DROP COLUMN `extLevel`,
DROP COLUMN `extIsLeaf`,
DROP COLUMN `extLoaded`,
DROP COLUMN `extExpanded`,
DROP COLUMN `extParent`;


