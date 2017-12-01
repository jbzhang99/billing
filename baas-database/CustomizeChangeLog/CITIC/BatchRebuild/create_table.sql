CREATE TABLE `pm_basedata_code` (
  `id` bigint(11) NOT NULL,
  `tenant_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `param_type` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `param_code` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `param_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `price_type` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `default_value` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `parent_code` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `subject_type` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `billing_type` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `is_free` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `can_buy_period` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `comments` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `batch_failure_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户id',
  `order_id` varchar(64) NOT NULL COMMENT '订单id',
  `fail_code` varchar(64) NOT NULL COMMENT '错单编码',
  `instance_id` varchar(64) DEFAULT NULL COMMENT '实例id',
  `order_json` text NOT NULL COMMENT '原始订单',
  `fail_reason` varchar(1024) DEFAULT NULL COMMENT '错误原因',
  `source` varchar(64) NOT NULL COMMENT '错误来源',
  `fail_time` datetime NOT NULL COMMENT '失败时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='订单错单表';

