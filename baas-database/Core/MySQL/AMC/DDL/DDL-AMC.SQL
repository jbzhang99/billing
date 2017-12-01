/*请使用这种方式注释*/
/*这里的数据库对象可以有普通表、月表、索引、视图*/

CREATE TABLE amc_charge_YYYYMM (
  CHARGE_SEQ bigint(15) NOT NULL,
  ACCT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  SUBS_ID bigint(10) DEFAULT NULL,
  SERVICE_ID varchar(20) COLLATE utf8_bin DEFAULT NULL,
  SUBJECT_ID bigint(8) NOT NULL,
  TOTAL_AMOUNT bigint(15) NOT NULL,
  ADJUST_AFTERWARDS bigint(15) NOT NULL DEFAULT '0',
  DISC_TOTAL_AMOUNT bigint(15) NOT NULL DEFAULT '0',
  BALANCE bigint(15) NOT NULL,
  PAY_STATUS bigint(2) NOT NULL DEFAULT '0',
  LAST_PAY_DATE datetime NOT NULL,
  CUST_ID bigint(10) NOT NULL,
  CUST_TYPE bigint(2) DEFAULT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_charge_yyyydd (
  CHARGE_SEQ bigint(15) NOT NULL,
  ACCT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  SUBS_ID bigint(10) DEFAULT NULL,
  SERVICE_ID varchar(20) COLLATE utf8_bin DEFAULT NULL,
  SUBJECT_ID bigint(8) NOT NULL,
  TOTAL_AMOUNT bigint(15) NOT NULL,
  ADJUST_AFTERWARDS bigint(15) NOT NULL DEFAULT '0',
  DISC_TOTAL_AMOUNT bigint(15) NOT NULL DEFAULT '0',
  BALANCE bigint(15) NOT NULL,
  PAY_STATUS bigint(2) NOT NULL DEFAULT '0',
  LAST_PAY_DATE datetime NOT NULL,
  CUST_ID bigint(10) NOT NULL,
  CUST_TYPE bigint(2) DEFAULT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_deduct_rule (
  TENANT_ID varchar(32) NOT NULL,
  FUND_SUBJECT varchar(32) NOT NULL,
  FEE_SUBJECT varchar(32) NOT NULL,
  PRIMARY KEY (TENANT_ID,FUND_SUBJECT,FEE_SUBJECT)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE amc_dr_bill_subject_map (
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  DR_SUBJECT varchar(8) COLLATE utf8_bin NOT NULL,
  BILL_SUBJECT varchar(8) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_fund_book (
  tenant_id varchar(32) NOT NULL,
  cust_id varchar(32) DEFAULT NULL,
  acct_id varchar(32) NOT NULL,
  book_id bigint(8) NOT NULL,
  subject_type varchar(2) NOT NULL,
  subject_id bigint(8) NOT NULL,
  balance bigint(15) NOT NULL,
  feature_code varchar(50) DEFAULT NULL,
  book_status varchar(2) NOT NULL,
  effect_date datetime NOT NULL,
  expire_date datetime NOT NULL,
  create_time datetime NOT NULL,
  subs_freeze_id bigint(10) DEFAULT NULL,
  subs_id bigint(10) DEFAULT NULL,
  cust_name varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  cust_grade varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '取值范围：A,B,C,D,E',
  PRIMARY KEY (book_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE amc_fund_detail_201612 (
  serial_code varchar(17) NOT NULL,
  pay_serial_code varchar(17) NOT NULL,
  opt_type varchar(4) NOT NULL,
  cust_id varchar(32) DEFAULT NULL,
  acct_id varchar(32) NOT NULL,
  book_id bigint(8) NOT NULL,
  subject_id bigint(8) NOT NULL,
  balance_pre bigint(15) NOT NULL DEFAULT '0',
  total_amount bigint(15) NOT NULL,
  trans_summary varchar(500) DEFAULT NULL,
  remark varchar(255) DEFAULT NULL,
  value_date datetime NOT NULL,
  create_time datetime NOT NULL,
  PRIMARY KEY (serial_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE amc_fund_serial_201608 (
  tenant_id varchar(32) CHARACTER SET utf8 NOT NULL,
  system_id varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  pay_serial_code varchar(17) CHARACTER SET utf8 NOT NULL,
  peer_serial_code varchar(60) CHARACTER SET utf8 NOT NULL,
  cancel_serial_code varchar(17) CHARACTER SET utf8 DEFAULT NULL,
  opt_type varchar(4) CHARACTER SET utf8 NOT NULL,
  total_amount bigint(15) NOT NULL,
  trans_summary varchar(500) CHARACTER SET utf8 NOT NULL,
  pay_rule_id bigint(9) DEFAULT NULL,
  pay_status varchar(1) CHARACTER SET utf8 NOT NULL,
  cust_id1 varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  acct_id1 varchar(32) CHARACTER SET utf8 NOT NULL,
  acct_name1 varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  cust_id2 varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  acct_id2 varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  acct_name2 varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  create_time datetime NOT NULL,
  pay_time datetime NOT NULL,
  last_status_date datetime NOT NULL,
  remark varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  CUST_GRADE varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '取值范围：A,B,C,D,E',
  CUST_NAME varchar(128) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_invoice_YYYYMM (
  INVOICE_SEQ bigint(15) NOT NULL,
  ACCT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SUBS_ID bigint(10) DEFAULT NULL,
  SERVICE_ID varchar(30) COLLATE utf8_bin DEFAULT NULL,
  TOTAL_AMOUNT bigint(15) NOT NULL,
  ADJUST_AFTERWARDS bigint(15) NOT NULL DEFAULT '0',
  DISC_TOTAL_AMOUNT bigint(15) NOT NULL DEFAULT '0',
  BALANCE bigint(15) NOT NULL,
  PAY_STATUS bigint(2) NOT NULL DEFAULT '0',
  LAST_PAY_DATE datetime NOT NULL,
  PRINT_TIMES bigint(4) NOT NULL DEFAULT '0',
  CUST_ID bigint(10) NOT NULL,
  CUST_TYPE bigint(2) DEFAULT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  CUST_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CUST_GRADE varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_invoice_yyyydd (
  INVOICE_SEQ bigint(15) NOT NULL,
  ACCT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SUBS_ID bigint(10) DEFAULT NULL,
  SERVICE_ID varchar(30) COLLATE utf8_bin DEFAULT NULL,
  TOTAL_AMOUNT bigint(15) NOT NULL,
  ADJUST_AFTERWARDS bigint(15) NOT NULL DEFAULT '0',
  DISC_TOTAL_AMOUNT bigint(15) NOT NULL DEFAULT '0',
  BALANCE bigint(15) NOT NULL,
  PAY_STATUS bigint(2) NOT NULL DEFAULT '0',
  LAST_PAY_DATE datetime NOT NULL,
  PRINT_TIMES bigint(4) NOT NULL DEFAULT '0',
  CUST_ID bigint(10) NOT NULL,
  CUST_TYPE bigint(2) DEFAULT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  CUST_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CUST_GRADE varchar(1) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_owe_info (
  TENANT_ID varchar(32) NOT NULL COMMENT '租户id',
  ACCT_ID varchar(32) NOT NULL COMMENT '账户标识',
  BALANCE decimal(20,6) DEFAULT NULL COMMENT '未销帐单总额',
  MONTH varchar(6) DEFAULT NULL,
  CREATE_TIME datetime DEFAULT NULL,
  CONFIRM_TIME datetime DEFAULT NULL,
  CUST_NAME varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  CUST_GRADE varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '取值范围：A,B,C,D,E',
  CUST_ID varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '全系统唯一',
  PRIMARY KEY (TENANT_ID,ACCT_ID),
  UNIQUE KEY index_tenant_acct (TENANT_ID,ACCT_ID) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE amc_product_detail (
  TENANT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRODUCT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIORITY varchar(4) COLLATE utf8_bin DEFAULT NULL,
  BILL_SUBJECT_ID varchar(8) COLLATE utf8_bin DEFAULT NULL,
  REF_SUBJECT_ID varchar(8) COLLATE utf8_bin DEFAULT NULL,
  NEW_SUBJECT_ID varchar(8) COLLATE utf8_bin DEFAULT NULL,
  CALC_CONDITION varchar(8) COLLATE utf8_bin DEFAULT NULL,
  CALC_TYPE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  UNIQUE KEY IDX_PRODUCT_ID (TENANT_ID,PRODUCT_ID) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_product_ext (
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRODUCT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  EXT_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL,
  EXT_VALUE varchar(32) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_product_info (
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRODUCT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRODUCT_NAME varchar(32) COLLATE utf8_bin NOT NULL,
  PRIORITY varchar(4) COLLATE utf8_bin NOT NULL,
  EFFECT_DATE datetime NOT NULL,
  EXPIRE_DATE datetime NOT NULL,
  STATUS varchar(2) COLLATE utf8_bin NOT NULL,
  CREATE_TIME datetime NOT NULL,
  remark varchar(255) COLLATE utf8_bin DEFAULT NULL,
  CALC_TYPE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (PRODUCT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_res_book (
  tenant_id varchar(32) COLLATE utf8_bin NOT NULL,
  book_id bigint(10) NOT NULL,
  owner_id bigint(10) NOT NULL,
  owner_type int(2) NOT NULL,
  subject_id bigint(8) NOT NULL,
  resource_type int(3) NOT NULL,
  create_time datetime NOT NULL,
  effect_time datetime NOT NULL,
  expire_time datetime NOT NULL,
  total_amount decimal(15,6) NOT NULL,
  deduct_amount decimal(15,6) NOT NULL,
  present_amount decimal(15,6) NOT NULL,
  exchange_amount decimal(15,6) NOT NULL,
  book_status varchar(2) COLLATE utf8_bin NOT NULL,
  allow_present int(2) NOT NULL,
  allow_convert int(2) NOT NULL,
  allow_clear int(2) NOT NULL,
  source_type int(2) NOT NULL,
  source_id bigint(10) NOT NULL,
  use_version bigint(10) NOT NULL,
  goods_type varchar(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (book_id),
  KEY idx_owner_id (owner_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_res_book_rest_amount (
  tenant_id varchar(32) COLLATE utf8_bin NOT NULL,
  owner_id bigint(10) NOT NULL,
  owner_type int(2) NOT NULL,
  resource_type int(3) NOT NULL,
  rest_amount decimal(15,6) NOT NULL,
  last_update_time datetime NOT NULL,
  KEY idx_owner_id (owner_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_res_opera_detail (
  tenant_id varchar(32) COLLATE utf8_bin NOT NULL,
  system_id varchar(32) COLLATE utf8_bin NOT NULL,
  external_id varchar(32) COLLATE utf8_bin NOT NULL,
  owner_id bigint(10) NOT NULL,
  owner_type int(2) NOT NULL,
  resource_type int(3) NOT NULL,
  change_amount decimal(15,6) NOT NULL,
  opt_type int(2) NOT NULL,
  opt_time datetime NOT NULL,
  book_status char(1) COLLATE utf8_bin NOT NULL,
  KEY idx_external_id (external_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_settle_detail_YYYYMM (
  serial_code varchar(17) COLLATE utf8_bin DEFAULT NULL,
  tenant_id varchar(18) COLLATE utf8_bin DEFAULT NULL,
  busi_oper_code varchar(8) COLLATE utf8_bin DEFAULT NULL,
  acct_id bigint(10) DEFAULT NULL,
  settle_mode int(2) DEFAULT NULL,
  settle_type int(2) DEFAULT NULL,
  book_id bigint(8) DEFAULT NULL,
  subs_id bigint(10) DEFAULT NULL,
  svc_type int(4) DEFAULT NULL,
  fund_subject_id bigint(8) DEFAULT NULL,
  cycle_month varchar(6) COLLATE utf8_bin DEFAULT NULL,
  invoice_seq bigint(15) DEFAULT NULL,
  charge_seq bigint(15) DEFAULT NULL,
  fee_subject_id bigint(8) DEFAULT NULL,
  total bigint(15) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  status int(2) DEFAULT NULL,
  last_status_date datetime DEFAULT NULL,
  settle_order bigint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_settle_log_201612 (
  TENANT_ID varchar(32) NOT NULL DEFAULT '',
  SERIAL_CODE varchar(17) NOT NULL DEFAULT '',
  BUSI_OPER_CODE varchar(8) DEFAULT NULL,
  ACCT_ID varchar(32) DEFAULT NULL,
  SETTLE_MODE int(2) DEFAULT NULL,
  SETTLE_TYPE int(2) DEFAULT NULL,
  TOTAL bigint(15) DEFAULT NULL,
  STATUS int(2) DEFAULT NULL,
  LAST_STATUS_DATE date DEFAULT NULL,
  CANCEL_SERIAL_CODE varchar(17) DEFAULT NULL,
  CREATE_TIME date DEFAULT NULL,
  PRIMARY KEY (TENANT_ID,SERIAL_CODE),
  UNIQUE KEY index_dseduct_rule_tenant_serial (TENANT_ID,SERIAL_CODE) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE bmc_dataquality (
  id int(11) NOT NULL AUTO_INCREMENT,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  service_id varchar(32) COLLATE utf8_bin NOT NULL,
  source varchar(32) COLLATE utf8_bin DEFAULT NULL,
  tb_suffix_key varchar(50) COLLATE utf8_bin DEFAULT NULL,
  dup_key varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sys_sequences (
  sequence_name varchar(60) COLLATE utf8_bin NOT NULL,
  start_by bigint(20) unsigned NOT NULL,
  increment_by bigint(10) unsigned NOT NULL,
  last_number bigint(20) unsigned NOT NULL,
  jvm_step_by bigint(10) DEFAULT '1',
  PRIMARY KEY (sequence_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_cc_charge_yyyydd (
  CC_CHARGE_SEQ bigint(15) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  COST_CENTER_ID varchar(64) COLLATE utf8_bin NOT NULL,
  SUBJECT_ID bigint(10) NOT NULL,
  APPORTION_ACCT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  AMOUNT bigint(15) DEFAULT NULL,
  LAST_DATE datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE amc_cc_detail_yyyydd (
  CC_DETAIL_SEQ bigint(15) NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  COST_CENTER_ID varchar(64) COLLATE utf8_bin NOT NULL,
  SUBJECT_ID bigint(8) NOT NULL,
  APPORTION_ACCT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  APPORTION_METHOD varchar(10) COLLATE utf8_bin DEFAULT NULL,
  APPORTION_VALUE varchar(20) COLLATE utf8_bin DEFAULT NULL,
  AMOUNT bigint(15) NOT NULL,
  ACCT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CUST_ID bigint(10) DEFAULT NULL,
  SUBS_ID bigint(10) DEFAULT NULL,
  IS_APPORTION char(1) COLLATE utf8_bin DEFAULT NULL,
  DR_KEY varchar(200) COLLATE utf8_bin DEFAULT NULL,
  CREATE_DATE datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
