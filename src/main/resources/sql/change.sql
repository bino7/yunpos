
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

ALTER TABLE `sys_agentmerchant`
ADD COLUMN `status`  tinyint(2) NULL COMMENT '代理商状态' ;
ADD COLUMN `auditOpinion`  varchar(100) NULL COMMENT '审核意见' ;




