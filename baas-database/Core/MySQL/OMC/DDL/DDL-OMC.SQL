/*请使用这种方式注释*/
/*这里的数据库对象可以有普通表、月表、索引、视图*/


CREATE TABLE omc_avoidscout (
  AVOID_SEQ varchar(32) DEFAULT NULL,
  TENANT_ID varchar(32) DEFAULT NULL,
  SYSTEM_ID varchar(32) DEFAULT NULL,
  OWNER_ID varchar(32) DEFAULT NULL,
  OWNER_TYPE varchar(10) DEFAULT NULL COMMENT 'subs 用户 acct 账户  cust 客户',
  SPE_TYPE varchar(20) DEFAULT NULL COMMENT 'STOP 停  URGE 催  STOPANDURGE 停和催  定义到用户级\n            REDLIST 红名单  如果按照账户信控 可定义到账户，如果是按照客户信控可定义到客户',
  EFF_DATE datetime DEFAULT NULL,
  EXP_DATE datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='免催停信息定义到用户\n免停只能定义到用户';

CREATE TABLE omc_bms_interface (
  serial_no bigint(15) DEFAULT NULL,
  acct_id varchar(32) DEFAULT NULL,
  subs_id varchar(32) DEFAULT NULL,
  scout_type varchar(16) DEFAULT NULL,
  bms_data varchar(1024) DEFAULT NULL,
  interface_data varchar(1024) DEFAULT NULL COMMENT '{"balance":-270.00,"pileid":"B973F8F7699","statid":"111111","iccid":"iccid10707","subs_id":"10707","scout_type":"stop"}',
  service_type int(4) DEFAULT NULL,
  priority int(4) DEFAULT NULL,
  channel int(4) DEFAULT NULL,
  insert_time datetime DEFAULT NULL,
  deal_flag int(1) DEFAULT NULL,
  deal_time datetime DEFAULT NULL,
  remark varchar(2000) DEFAULT NULL,
  retry_times int(4) DEFAULT NULL,
  tenant_id varchar(32) DEFAULT NULL,
  system_id varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE omc_bms_interface_201607 (
  serial_no bigint(15) DEFAULT NULL,
  acct_id varchar(32) DEFAULT NULL,
  subs_id varchar(32) DEFAULT NULL,
  scout_type varchar(16) DEFAULT NULL,
  bms_data varchar(1024) DEFAULT NULL,
  interface_data varchar(1024) DEFAULT NULL COMMENT '{"balance":-270.00,"pileid":"B973F8F7699","statid":"111111","iccid":"iccid10707","subs_id":"10707","scout_type":"stop"}',
  service_type int(4) DEFAULT NULL,
  priority int(4) DEFAULT NULL,
  channel int(4) DEFAULT NULL,
  insert_time datetime DEFAULT NULL,
  deal_flag int(1) DEFAULT NULL,
  deal_time datetime DEFAULT NULL,
  remark varchar(2000) DEFAULT NULL,
  retry_times int(4) DEFAULT NULL,
  tenant_id varchar(32) DEFAULT NULL,
  system_id varchar(32) DEFAULT NULL,
  bak_time datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE omc_creditfee (
  SYSTEM_ID varchar(32) DEFAULT NULL COMMENT '垂直产品系统的ID',
  TENANT_ID varchar(32) DEFAULT NULL COMMENT '垂直产品中企业或个人租户的ID',
  CREDIT_SEQ varchar(32) DEFAULT NULL,
  OWNER_ID varchar(32) DEFAULT NULL,
  OWNER_TYPE varchar(32) DEFAULT NULL COMMENT 'subs 用户 acct 账户  cust 客户',
  CREDIT_TYPE varchar(32) DEFAULT NULL COMMENT '取值范围：TMP-临时；DYM-动态',
  CREDIT_LINE bigint(20) DEFAULT NULL,
  EFF_TIME datetime DEFAULT NULL,
  EXP_TIME datetime DEFAULT NULL,
  RESOURCE_CODE varchar(10) DEFAULT NULL COMMENT 'CASH 金额，单位：分\r\nVOICE 语音的时长，单位：秒\r\nSM 短信的条数，单位：条\r\nDATA 数据的字节数，单位：K'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE omc_event (
  tenant_id varchar(32) DEFAULT NULL,
  source_type varchar(20) DEFAULT NULL,
  event_id bigint(20) NOT NULL,
  owner_id varchar(32) DEFAULT NULL,
  owner_type varchar(10) DEFAULT NULL COMMENT '/serv 用户 /acct 账户 /cust 客户 /group 群组',
  event_type varchar(10) DEFAULT NULL COMMENT '/main 主业务 /sub 子业务 /time 内部调度',
  insert_date datetime DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  sco_flag int(11) DEFAULT NULL COMMENT '0 未处理  1 处理完成',
  PRIMARY KEY (event_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE omc_policy_para (
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  policyId varchar(8) COLLATE utf8_bin DEFAULT NULL,
  para_name varchar(50) COLLATE utf8_bin DEFAULT NULL,
  para_type varchar(20) COLLATE utf8_bin DEFAULT NULL,
  para_value varchar(32) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_scout_action_define (
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  scout_type varchar(16) COLLATE utf8_bin DEFAULT NULL,
  business_code varchar(8) COLLATE utf8_bin DEFAULT NULL,
  scout_rule varchar(40) COLLATE utf8_bin DEFAULT NULL,
  inf_commond varchar(40) COLLATE utf8_bin DEFAULT NULL,
  sms_template int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_scout_log (
  logid bigint(20) DEFAULT NULL,
  SourceType varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  ownertype varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  owner_id varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  business_code varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  scout_type varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  status varchar(3) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  insettime datetime DEFAULT NULL,
  scout_rule varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  balanceinfo varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  parainfo varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  bak_time datetime NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE omc_scout_log_201607 (
  logid bigint(20) DEFAULT NULL,
  SourceType varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  ownertype varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  owner_id varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  business_code varchar(8) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  scout_type varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  status varchar(3) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  insettime datetime DEFAULT NULL,
  scout_rule varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  balanceinfo varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  parainfo varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  bak_time datetime DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE omc_scout_policy (
  policyId varchar(32) COLLATE utf8_bin DEFAULT NULL,
  policy_name varchar(40) COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  policyDescribe varchar(100) COLLATE utf8_bin DEFAULT NULL,
  policyType varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT 'main\r\n            subs',
  status varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '1 生效  0 失效',
  eff_date datetime DEFAULT NULL,
  exp_date datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_scout_rule (
  rule_id bigint(20) DEFAULT NULL,
  policyId bigint(20) DEFAULT NULL,
  scout_rule varchar(40) COLLATE utf8_bin DEFAULT NULL,
  scout_type varchar(16) COLLATE utf8_bin DEFAULT NULL,
  balance_floor bigint(20) DEFAULT NULL,
  balance_ceil bigint(20) DEFAULT NULL,
  owe_maxdays int(11) DEFAULT NULL,
  owe_mindays int(11) DEFAULT NULL,
  charge_ceil bigint(20) DEFAULT NULL,
  charge_floor bigint(20) DEFAULT NULL,
  cust_type varchar(2) COLLATE utf8_bin DEFAULT NULL,
  acct_type varchar(2) COLLATE utf8_bin DEFAULT NULL,
  user_type varchar(2) COLLATE utf8_bin DEFAULT NULL,
  cust_level varchar(2) COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  section_type varchar(128) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_scout_status (
  sco_seq bigint(20) DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  subs_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  acct_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  cust_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  business_code varchar(8) COLLATE utf8_bin DEFAULT NULL,
  status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  last_status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  status_time datetime DEFAULT NULL,
  notify_time datetime DEFAULT NULL,
  notify_times int(11) DEFAULT NULL,
  notify_status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  notify_type varchar(40) COLLATE utf8_bin DEFAULT NULL,
  scout_info varchar(200) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_scout_status_201607 (
  sco_seq bigint(20) DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  subs_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  acct_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  cust_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  business_code varchar(8) COLLATE utf8_bin DEFAULT NULL,
  status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  last_status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  status_time datetime DEFAULT NULL,
  notify_time datetime DEFAULT NULL,
  notify_times int(11) DEFAULT NULL,
  notify_status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  notify_type varchar(40) COLLATE utf8_bin DEFAULT NULL,
  scout_info varchar(200) COLLATE utf8_bin DEFAULT NULL,
  bak_time datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_speurgestop (
  owner_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  owner_type varchar(10) COLLATE utf8_bin DEFAULT NULL,
  speType int(11) DEFAULT NULL COMMENT '1 免停 2 免催 3 免催',
  effDate datetime DEFAULT NULL,
  expDate datetime DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_tenant_para (
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  para_name varchar(50) COLLATE utf8_bin DEFAULT NULL,
  para_type varchar(20) COLLATE utf8_bin DEFAULT NULL,
  para_value varchar(10) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_urge_interface (
  serial_no bigint(20) DEFAULT NULL,
  tenant_id varchar(32) DEFAULT NULL,
  system_id varchar(32) DEFAULT NULL,
  owner_type varchar(10) DEFAULT NULL COMMENT 'subs 用户\r\n            acct  账户\r\n            cust  客户',
  owner_id varchar(32) DEFAULT NULL,
  urge_info varchar(200) DEFAULT NULL COMMENT '预警内容采用json格式存放\r\n            {"template_id":"5054","phone":"13355558888","limit_value":"10.00","limit_type":""}',
  insert_time datetime DEFAULT NULL,
  retry_times int(11) DEFAULT NULL,
  deal_flag int(11) DEFAULT '9' COMMENT '0 初始\r\n            1 处理成功\r\n            2 处理失败',
  deal_time datetime DEFAULT NULL,
  remark varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE omc_urge_interface_201607 (
  serial_no bigint(20) DEFAULT NULL,
  tenant_id varchar(32) DEFAULT NULL,
  system_id varchar(32) DEFAULT NULL,
  owner_type varchar(10) DEFAULT NULL COMMENT 'subs 用户\r\n            acct  账户\r\n            cust  客户',
  owner_id varchar(32) DEFAULT NULL,
  urge_info varchar(200) DEFAULT NULL COMMENT '预警内容采用json格式存放\r\n            {"template_id":"5054","phone":"13355558888","limit_value":"10.00","limit_type":""}',
  insert_time datetime DEFAULT NULL,
  retry_times int(11) DEFAULT NULL,
  deal_flag int(11) DEFAULT '9' COMMENT '0 初始\r\n            1 处理成功\r\n            2 处理失败',
  deal_time datetime DEFAULT NULL,
  remark varchar(512) DEFAULT NULL,
  bak_time datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE omc_urge_status (
  urge_serial bigint(20) DEFAULT NULL,
  owner_type varchar(10) COLLATE utf8_bin DEFAULT NULL,
  owner_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  urge_type varchar(12) COLLATE utf8_bin DEFAULT NULL,
  business_code varchar(8) COLLATE utf8_bin DEFAULT NULL,
  status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  last_status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  status_time datetime DEFAULT NULL,
  notify_time datetime DEFAULT NULL,
  notify_times int(11) DEFAULT NULL,
  notify_status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  notify_type varchar(10) COLLATE utf8_bin DEFAULT NULL,
  scout_info varchar(200) COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_urge_status_201607 (
  urge_serial bigint(20) DEFAULT NULL,
  owner_type varchar(10) COLLATE utf8_bin DEFAULT NULL,
  owner_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  urge_type varchar(12) COLLATE utf8_bin DEFAULT NULL,
  business_code varchar(8) COLLATE utf8_bin DEFAULT NULL,
  status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  last_status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  status_time datetime DEFAULT NULL,
  notify_time datetime DEFAULT NULL,
  notify_times int(11) DEFAULT NULL,
  notify_status varchar(3) COLLATE utf8_bin DEFAULT NULL,
  notify_type varchar(10) COLLATE utf8_bin DEFAULT NULL,
  scout_info varchar(200) COLLATE utf8_bin DEFAULT NULL,
  system_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  bak_time datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sys_sequence_credit (
  sequence_name varchar(32) COLLATE utf8_bin NOT NULL COMMENT '序列名称，字符串，长度是32',
  current_value bigint(20) NOT NULL COMMENT '序列当前的值，数值',
  min_value bigint(20) NOT NULL COMMENT '序列的最小值，数值',
  max_value bigint(20) NOT NULL COMMENT '序列的最大值，数值',
  is_cycle varchar(3) COLLATE utf8_bin NOT NULL COMMENT '是否循环，取值：YES，NO',
  PRIMARY KEY (sequence_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sys_sequences (
  sequence_name varchar(60) CHARACTER SET utf8 NOT NULL,
  start_by bigint(20) unsigned NOT NULL,
  increment_by bigint(10) unsigned NOT NULL,
  last_number bigint(20) unsigned NOT NULL,
  jvm_step_by bigint(10) DEFAULT '1',
  PRIMARY KEY (sequence_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

