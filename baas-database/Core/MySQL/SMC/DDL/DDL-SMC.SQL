/*template*/
/*请使用这种方式注释*/
/*这里的数据库对象可以有普通表、月表、索引、视图*/

CREATE TABLE bmc_dataquality (
  id int(11) NOT NULL AUTO_INCREMENT,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  service_id varchar(32) COLLATE utf8_bin NOT NULL,
  source varchar(32) COLLATE utf8_bin DEFAULT NULL,
  tb_suffix_key varchar(50) COLLATE utf8_bin DEFAULT NULL,
  dup_key varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bmc_record_fmt (
  id int(11) NOT NULL AUTO_INCREMENT,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  service_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  source varchar(32) COLLATE utf8_bin DEFAULT NULL,
  format_type smallint(6) DEFAULT NULL,
  field_serial int(11) DEFAULT NULL,
  field_name varchar(32) COLLATE utf8_bin DEFAULT NULL,
  field_code varchar(32) COLLATE utf8_bin DEFAULT NULL,
  data_type varchar(32) COLLATE utf8_bin DEFAULT NULL,
  comments varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=98030 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE demo (
  order_id varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE demo_tst (
  order_id varchar(500) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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

CREATE TABLE omc_bms_interface_201604 (
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
  tenant_id varchar(8) COLLATE utf8_bin DEFAULT NULL,
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

CREATE TABLE omc_scout_log_201604 (
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

CREATE TABLE omc_urge_interface_201604 (
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

CREATE TABLE omc_urge_status_201604 (
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

CREATE TABLE stl_bill_data (
  BILL_ID bigint(20) NOT NULL,
  BILL_FROM varchar(8) COLLATE utf8_bin NOT NULL,
  BATCH_NO varchar(32) COLLATE utf8_bin NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  POLICY_CODE varchar(32) COLLATE utf8_bin NOT NULL,
  STL_OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  STL_ELEMENT_ID bigint(20) NOT NULL,
  STL_ELEMENT_SN varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_STYLE_SN varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_TIME_SN varchar(16) COLLATE utf8_bin NOT NULL,
  BILL_START_TIME datetime NOT NULL,
  BILL_END_TIME datetime NOT NULL,
  ORIG_FEE float NOT NULL,
  CHECK_STATE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  DIFF_FEE float DEFAULT NULL,
  CHECK_STATE_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CHECK_TIME datetime DEFAULT NULL,
  ADJUST_FEE float DEFAULT NULL,
  FINAL_FEE float DEFAULT NULL,
  ADJUST_TIME datetime DEFAULT NULL,
  ADJUST_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ADJUST_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  EXTEND_STR varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (BILL_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_bill_data_201605 (
  BILL_ID bigint(20) NOT NULL,
  BILL_FROM varchar(8) COLLATE utf8_bin NOT NULL,
  BATCH_NO varchar(32) COLLATE utf8_bin NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  POLICY_CODE varchar(32) COLLATE utf8_bin NOT NULL,
  STL_OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  STL_ELEMENT_ID bigint(20) NOT NULL,
  STL_ELEMENT_SN varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_STYLE_SN varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_TIME_SN varchar(16) COLLATE utf8_bin NOT NULL,
  BILL_START_TIME datetime NOT NULL,
  BILL_END_TIME datetime NOT NULL,
  ORIG_FEE float NOT NULL,
  CHECK_STATE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  DIFF_FEE float DEFAULT NULL,
  CHECK_STATE_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CHECK_TIME datetime DEFAULT NULL,
  ADJUST_FEE float DEFAULT NULL,
  FINAL_FEE float DEFAULT NULL,
  ADJUST_TIME datetime DEFAULT NULL,
  ADJUST_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ADJUST_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  EXTEND_STR varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (BILL_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_bill_detail_style_item (
  ITEM_ID bigint(16) NOT NULL,
  BILL_STYLE_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ITEM_CODE varchar(64) COLLATE utf8_bin NOT NULL,
  ITEM_TITLE varchar(64) COLLATE utf8_bin NOT NULL,
  ITEM_OWNER varchar(8) COLLATE utf8_bin NOT NULL,
  CHECK_FLAG varchar(8) COLLATE utf8_bin NOT NULL,
  OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ELEMENT_ID bigint(16) NOT NULL,
  SORT_ID int(8) NOT NULL,
  IS_SPLIT_ITEM varchar(4) COLLATE utf8_bin NOT NULL DEFAULT 'NO' COMMENT '是否拆分项：YES/NO',
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ITEM_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_bill_item_data (
  BILL_ITEM_ID bigint(20) NOT NULL,
  BILL_ID bigint(20) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ITEM_TYPE varchar(8) COLLATE utf8_bin NOT NULL,
  FEE_ITEM_ID varchar(32) COLLATE utf8_bin NOT NULL,
  TOTAL_FEE float NOT NULL,
  CHECK_STATE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  DIFF_FEE float DEFAULT NULL,
  CHECK_STATE_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CHECK_TIME datetime DEFAULT NULL,
  ADJUST_TIME datetime DEFAULT NULL,
  ADJUST_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ADJUST_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (BILL_ITEM_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_bill_item_data_201605 (
  BILL_ITEM_ID bigint(20) NOT NULL,
  BILL_ID bigint(20) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ITEM_TYPE varchar(8) COLLATE utf8_bin NOT NULL,
  FEE_ITEM_ID varchar(32) COLLATE utf8_bin NOT NULL,
  TOTAL_FEE float NOT NULL,
  CHECK_STATE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  DIFF_FEE float DEFAULT NULL,
  CHECK_STATE_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CHECK_TIME datetime DEFAULT NULL,
  ADJUST_TIME datetime DEFAULT NULL,
  ADJUST_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ADJUST_DESC varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (BILL_ITEM_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_bill_style (
  BILL_STYLE_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_STYLE_SN varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_TITLE varchar(64) COLLATE utf8_bin NOT NULL,
  STATE varchar(8) COLLATE utf8_bin NOT NULL,
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (BILL_STYLE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_bill_style_item (
  ITEM_ID bigint(16) NOT NULL,
  BILL_STYLE_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ITEM_CODE varchar(64) COLLATE utf8_bin NOT NULL,
  ITEM_TITLE varchar(64) COLLATE utf8_bin NOT NULL,
  ITEM_OWNER varchar(8) COLLATE utf8_bin NOT NULL,
  OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ELEMENT_ID bigint(16) NOT NULL,
  SORT_ID int(8) NOT NULL,
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ITEM_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_element (
  ELEMENT_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ELEMENT_CODE varchar(64) COLLATE utf8_bin NOT NULL,
  ELEMENT_NAME varchar(64) COLLATE utf8_bin NOT NULL,
  OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ATTR_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  IS_SETTLEMENT varchar(8) COLLATE utf8_bin NOT NULL,
  VALUE_TYPE varchar(8) COLLATE utf8_bin NOT NULL,
  IS_NECESSARY varchar(8) COLLATE utf8_bin NOT NULL,
  ELEMENT_TYPE varchar(8) COLLATE utf8_bin NOT NULL,
  DATA_CREATE_TYPE varchar(8) COLLATE utf8_bin NOT NULL,
  IS_PRIMARY_KEY varchar(8) COLLATE utf8_bin NOT NULL,
  UNIT varchar(16) COLLATE utf8_bin DEFAULT NULL,
  SORT_ID int(8) DEFAULT NULL,
  ELEMENT_DESC varchar(256) COLLATE utf8_bin DEFAULT NULL,
  STATISTICS_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  STATISTICS_CYC varchar(64) COLLATE utf8_bin DEFAULT NULL,
  STATISTICS_OBJECT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  STATISTICS_ELEMENT_ID bigint(16) DEFAULT NULL,
  STATE varchar(8) COLLATE utf8_bin NOT NULL,
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ELEMENT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_element_attr (
  ATTR_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ELEMENT_ID bigint(16) NOT NULL,
  sub_object_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SUB_ELEMENT_ID bigint(16) NOT NULL,
  REL_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  REL_VALUE varchar(1024) COLLATE utf8_bin NOT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ATTR_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_import_log (
  LOG_ID bigint(20) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  IMP_FILE_NAME varchar(128) COLLATE utf8_bin NOT NULL,
  IMP_FILE_URL varchar(128) COLLATE utf8_bin NOT NULL,
  DATA_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_TIME_SN varchar(16) COLLATE utf8_bin NOT NULL,
  IMPORT_TIME datetime NOT NULL,
  IMPORT_RECORDS bigint(20) NOT NULL,
  BATCH_NO varchar(32) COLLATE utf8_bin DEFAULT NULL,
  RST_FILE_NAME varchar(128) COLLATE utf8_bin DEFAULT NULL,
  RST_FILE_URL varchar(128) COLLATE utf8_bin DEFAULT NULL,
  STATE varchar(32) COLLATE utf8_bin NOT NULL,
  STATE_DESC varchar(128) COLLATE utf8_bin DEFAULT NULL,
  OPT_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  OPT_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  STATE_CHG_TIME datetime NOT NULL,
  PRIMARY KEY (LOG_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_policy (
  POLICY_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  POLICY_CODE varchar(32) COLLATE utf8_bin NOT NULL,
  POLICY_NAME varchar(64) COLLATE utf8_bin NOT NULL,
  START_TIME datetime NOT NULL,
  END_TIME datetime NOT NULL,
  POLICY_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  POLICY_DESC varchar(512) COLLATE utf8_bin DEFAULT NULL,
  EXEC_CYCLE varchar(8) COLLATE utf8_bin NOT NULL,
  EXEC_TIME_STR varchar(32) COLLATE utf8_bin NOT NULL,
  DATA_OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  DATA_ELEMENT_ID bigint(16) NOT NULL,
  STL_OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  STL_ELEMENT_ID bigint(16) NOT NULL,
  stl_element_sn varchar(32) COLLATE utf8_bin NOT NULL,
  BILL_STYLE_SN varchar(32) COLLATE utf8_bin NOT NULL,
  CHECK_FEE_FLAG varchar(8) COLLATE utf8_bin NOT NULL,
  STATE varchar(8) COLLATE utf8_bin NOT NULL,
  CREATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (POLICY_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_policy_item (
  ITEM_ID bigint(16) NOT NULL,
  POLICY_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (ITEM_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_policy_item_condition (
  CONDITION_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ITEM_ID bigint(16) NOT NULL,
  POLICY_ID bigint(16) NOT NULL,
  OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ELEMENT_ID bigint(16) NOT NULL,
  MATCH_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  MATCH_VALUE varchar(1024) COLLATE utf8_bin NOT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (CONDITION_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_policy_item_plan (
  PLAN_ID bigint(16) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ITEM_ID bigint(16) NOT NULL,
  POLICY_ID bigint(16) NOT NULL,
  OBJECT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  ELEMENT_ID bigint(16) NOT NULL,
  FEE_ITEM varchar(32) COLLATE utf8_bin NOT NULL,
  PLAN_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  CAL_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  CAL_VALUE varchar(1024) COLLATE utf8_bin NOT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (PLAN_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_sys_param (
  GUIDKEY varchar(32) COLLATE utf8_bin NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  TYPE_CODE varchar(40) COLLATE utf8_bin NOT NULL,
  PARAM_CODE varchar(40) COLLATE utf8_bin NOT NULL,
  COLUMN_VALUE varchar(128) COLLATE utf8_bin NOT NULL,
  COLUMN_DESC varchar(128) COLLATE utf8_bin NOT NULL,
  SUB_PARAM_CODE varchar(128) COLLATE utf8_bin DEFAULT NULL,
  PARENT_VALUE varchar(128) COLLATE utf8_bin DEFAULT NULL,
  DISPORD int(4) NOT NULL,
  DESCB varchar(128) COLLATE utf8_bin DEFAULT NULL,
  STATE varchar(4) COLLATE utf8_bin NOT NULL,
  UPDATE_DEPT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_OPER_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  UPDATE_TIME datetime DEFAULT NULL,
  PRIMARY KEY (GUIDKEY)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stl_test (
  test_id int(11) DEFAULT NULL,
  test_name varchar(45) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='test';

CREATE TABLE stl_test_201603 (
  test_id int(11) DEFAULT NULL,
  test_name varchar(45) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='test';

CREATE TABLE sys_sequence_credit (
  sequence_name varchar(32) COLLATE utf8_bin NOT NULL COMMENT '序列名称，字符串，长度是32',
  current_value bigint(20) NOT NULL COMMENT '序列当前的值，数值',
  min_value bigint(20) NOT NULL COMMENT '序列的最小值，数值',
  max_value bigint(20) NOT NULL COMMENT '序列的最大值，数值',
  is_cycle varchar(3) COLLATE utf8_bin NOT NULL COMMENT '是否循环，取值：YES，NO',
  PRIMARY KEY (sequence_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sys_sequences (
  SEQUENCE_NAME varchar(60) COLLATE utf8_bin NOT NULL,
  START_BY bigint(20) unsigned NOT NULL,
  INCREMENT_BY bigint(10) unsigned NOT NULL,
  LAST_NUMBER bigint(20) unsigned NOT NULL,
  JVM_STEP_BY bigint(10) DEFAULT '1',
  PRIMARY KEY (SEQUENCE_NAME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE omc_scout_status_201604 (
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

