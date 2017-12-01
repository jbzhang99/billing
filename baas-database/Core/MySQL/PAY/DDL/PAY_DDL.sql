CREATE TABLE `pay_center_log` (
  `pay_id` bigint(18) NOT NULL,
  `tenant_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `order_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `batch_no` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ori_order_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `trade_order_id` varchar(40) COLLATE utf8_bin NOT NULL,
  `subject` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `request_source` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `pay_request_type` int(2) NOT NULL DEFAULT '1',
  `pay_amount` bigint(15) NOT NULL,
  `CURRENCY_UNIT` varchar(2) CHARACTER SET utf8 NOT NULL COMMENT '1、RMB；\n2、$;',
  `pay_org_id` varchar(6) COLLATE utf8_bin NOT NULL,
  `pay_org_serial` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `notify_url` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `return_url` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
  `merchant_url` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `notify_id` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `status_chg_time` datetime NOT NULL,
  `check_status` int(2) DEFAULT '0',
  `check_time` datetime DEFAULT NULL,
  `detail_data` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `reserved1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserved2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserved3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`pay_id`),
  KEY `idx_batch_no` (`batch_no`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_pay_center_log_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `pay_center_log_state` (
  `pay_id` bigint(18) NOT NULL,
  `tenant_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `order_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `batch_no` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `ori_order_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `trade_order_id` varchar(40) COLLATE utf8_bin NOT NULL,
  `subject` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `request_source` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `pay_request_type` int(2) NOT NULL DEFAULT '1',
  `pay_amount` bigint(15) NOT NULL,
  `CURRENCY_UNIT` varchar(2) CHARACTER SET utf8 NOT NULL COMMENT '1、RMB；\n2、$;',
  `pay_org_id` varchar(6) COLLATE utf8_bin NOT NULL,
  `pay_org_serial` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `buyer_email` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `return_email` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `draw_email` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `notify_url` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `return_url` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
  `merchant_url` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `notify_id` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `status_chg_time` datetime NOT NULL,
  `check_status` int(2) DEFAULT '0',
  `check_time` datetime DEFAULT NULL,
  `service_num` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `detail_data` varchar(2048) COLLATE utf8_bin DEFAULT NULL,
  `send_detail_data` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `receive_detail_data` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `reserved1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserved2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reserved3` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `sys_sequences` (
  `sequence_name` varchar(60) COLLATE utf8_bin NOT NULL,
  `start_by` bigint(20) unsigned NOT NULL,
  `increment_by` bigint(10) unsigned NOT NULL,
  `last_number` bigint(20) unsigned NOT NULL,
  `jvm_step_by` bigint(10) DEFAULT '1',
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `pay_terminal_org_rel` (
  `rel_id` bigint(18) NOT NULL,
  `tenant_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `request_source` varchar(8) COLLATE utf8_bin NOT NULL,
  `pay_org_code` varchar(8) COLLATE utf8_bin NOT NULL,
  `ico_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `pay_tenant_info` (
  `partner_id` varchar(5) COLLATE utf8_bin NOT NULL,
  `tenant_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '以租户的网站域名为编码',
  `tenant_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '租户名称',
  `state` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '1:正常 0:失效',
  PRIMARY KEY (`partner_id`),
  UNIQUE KEY `tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `pay_tenant_config` (
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `TENANT_NAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_TYPE` varchar(12) COLLATE utf8_bin NOT NULL,
  `CONFIG_NAME` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `CONFIG_INFO` varchar(4096) COLLATE utf8_bin NOT NULL DEFAULT '1',
  `STATE` varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;