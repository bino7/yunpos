
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

--修改字段类型
ALTER TABLE `sys_user`
ADD COLUMN `delete_status`  int NULL DEFAULT 0 COMMENT '删除状态' AFTER `fullname`;
--用户修改字段
ALTER TABLE `sys_user`
MODIFY COLUMN `status`  int(2) NULL DEFAULT NULL COMMENT '用户状态' AFTER `delete_status`;


--20151017 张旭  添加角色字段
ALTER TABLE `sys_role`
ADD COLUMN `roleProperty`  tinyint(2) NULL COMMENT '角色属性 1：通用 0:非通用' AFTER `modifyDate`;
ADD COLUMN `info`  vachar NULL COMMENT '备注' AFTER `roleProperty`;

--20151019 bino
DROP TABLE IF EXISTS `filter`;
CREATE TABLE filter
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    resource_id INT,
    group_id INT,
    filter_difinition_id INT,
    op INT,
    filter_value VARCHAR(255)
);
DROP TABLE IF EXISTS `filter_difinition`;
CREATE TABLE filter_difinition
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    resource_id INT,
    type INT,
    value_type INT,
    data_type INT,
    col_name VARCHAR(45),
    key_param VARCHAR(45),
    key_column VARCHAR(45),
    support_op INT,
    filter_values VARCHAR(255)
);
DROP TABLE IF EXISTS `filter_difinition_value`;
CREATE TABLE filter_difinition_value
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    filter_difinition_id INT,
    value VARCHAR(45)
);
DROP TABLE IF EXISTS `filter_group`;
CREATE TABLE filter_group
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    resource_id INT,
    parent_id INT,
    role_id INT NOT NULL
);
DROP TABLE IF EXISTS `resource`;
CREATE TABLE resource
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    table_name VARCHAR(45) NOT NULL,
    path VARCHAR(45) NOT NULL
);
DROP TABLE IF EXISTS `key_param`;
CREATE TABLE key_param
(
    name VARCHAR(45) PRIMARY KEY NOT NULL,
    `table` VARCHAR(45),
    `column` VARCHAR(45),
    primary_column VARCHAR(45)
);

--20150119 杨学勇 菜单字段类型修改
ALTER TABLE `sys_menu`
MODIFY COLUMN `menuNo`  varchar(10) NULL DEFAULT NULL COMMENT '系统菜单编号' AFTER `sequence`,
MODIFY COLUMN `menuParentNo`  varchar(10) NULL DEFAULT NULL COMMENT '父级菜单编号' AFTER `applicationCode`;


