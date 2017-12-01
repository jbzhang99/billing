/*请使用这种方式注释*/
/*这里的数据库对象可以有普通表、月表、索引、视图*/

CREATE TABLE ebilling_shm_table_db (
  ID int(11) NOT NULL,
  DB_CONNECT varchar(64) COLLATE utf8_bin NOT NULL,
  USER_NAME varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PASS_WORD varchar(64) COLLATE utf8_bin DEFAULT NULL,
  URL varchar(64) COLLATE utf8_bin DEFAULT NULL,
  DRIVER_CLASS_NAME varchar(64) COLLATE utf8_bin DEFAULT NULL,
  DB_TYPE char(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (DB_CONNECT)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE ebilling_shm_table_info (
  ID int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  TABLE_ID int(11) NOT NULL COMMENT '表序号',
  IS_ENABLE int(1) NOT NULL DEFAULT '1' COMMENT '启动的开关',
  INDEX_KEY varchar(400) COLLATE utf8_bin NOT NULL COMMENT '索引字段名，如果是组合键索引，通过'':''分隔，如联合索引为name:num',
  TABLE_NAME varchar(32) COLLATE utf8_bin NOT NULL COMMENT '表名',
  INDEX_MODE int(1) NOT NULL COMMENT '索引类型，0为主键索引，1为组合索引索引\r\n逐渐索引必须有，再有组合索引',
  STORAGE_TYPE int(1) NOT NULL DEFAULT '1' COMMENT '存储表每一行记录的方式，0为hash，1为string',
  INDEX_COUNT bigint(20) NOT NULL COMMENT '索引最大值,一般为DATA_COUNT的30%',
  DATA_COUNT bigint(20) NOT NULL COMMENT '数据最大值',
  IS_PARA int(1) NOT NULL COMMENT '是否为参数表，0为非参数表，增量刷新；1为参数表，全量刷新',
  INDEX_ID int(11) NOT NULL COMMENT '提供表索引的编码',
  HASH_TYPE int(11) NOT NULL COMMENT 'hash的类型，0为数字，1为字符串',
  LESSEE_NAME varchar(40) COLLATE utf8_bin NOT NULL COMMENT '租户名称',
  DB_CONNECT varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '数据库名字',
  tenant_id char(8) COLLATE utf8_bin DEFAULT NULL,
  table_key varchar(120) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1111206 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE ebilling_shm_table_record (
  ID int(11) NOT NULL COMMENT '主键',
  TABLE_ID int(11) NOT NULL COMMENT '实体表ID',
  TABLE_NAME varchar(40) COLLATE utf8_bin NOT NULL,
  FIELD_SEQ int(11) NOT NULL COMMENT '字段序列号',
  FIELD_NAME varchar(32) COLLATE utf8_bin NOT NULL COMMENT '实体字段名',
  FIELD_TYPE int(11) NOT NULL COMMENT '0 varchar\r\n1 int\r\n2 bigint\r\n3 date\r\n4 datetime\r\n5 timestamp',
  FIELD_FORMAT varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '字段加载到内存的格式，如时间：yyyy-MM-dd HH:mm:ss',
  FIELD_SIZE int(11) DEFAULT NULL COMMENT '字段长度',
  IS_HASHKEY int(1) NOT NULL COMMENT '0为否，1为是，即支持索引,需要注意的是，如果该列为主键的话，那么设置为0，因为主键默认支持索引，不需要再建立索引',
  IS_PRIMARY int(11) NOT NULL COMMENT '是否主键',
  SEARCH_TYPE varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '查找类型',
  tenant_id char(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_account_role_rel (
  tenant_id varchar(50) COLLATE utf8_bin NOT NULL,
  account_role_rel_id bigint(20) NOT NULL,
  account_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  active_time datetime NOT NULL,
  inactive_time datetime NOT NULL,
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  create_account_id bigint(20) DEFAULT NULL COMMENT '对应操作员的账号ID',
  update_account_id bigint(20) DEFAULT NULL COMMENT '对应操作员的账号ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_func (
  func_id bigint(20) NOT NULL,
  func_name varchar(50) COLLATE utf8_bin DEFAULT NULL,
  state varchar(2) COLLATE utf8_bin NOT NULL COMMENT ' 1：正常   2：失效',
  func_url varchar(200) COLLATE utf8_bin DEFAULT NULL,
  func_type varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '1：租户菜单、2：运维菜单',
  func_sort bigint(20) DEFAULT NULL COMMENT '租户内排序',
  parent_func_id bigint(20) DEFAULT NULL,
  func_css_class varchar(50) COLLATE utf8_bin DEFAULT NULL,
  func_pic varchar(100) COLLATE utf8_bin DEFAULT NULL,
  active_time datetime DEFAULT NULL,
  inactive_time datetime DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  create_account_id bigint(20) DEFAULT NULL,
  update_account_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (func_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_role (
  tenant_id varchar(50) COLLATE utf8_bin NOT NULL,
  role_id bigint(20) NOT NULL,
  role_name varchar(50) COLLATE utf8_bin NOT NULL,
  state varchar(2) COLLATE utf8_bin NOT NULL COMMENT ' 1：正常   2：失效',
  role_desc varchar(200) COLLATE utf8_bin DEFAULT NULL,
  active_time datetime NOT NULL,
  inactive_time datetime NOT NULL,
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  create_account_id bigint(20) DEFAULT NULL,
  update_account_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_role_func_rel (
  tenant_id varchar(50) COLLATE utf8_bin NOT NULL,
  role_func_rel_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  func_id bigint(20) NOT NULL,
  active_time datetime NOT NULL,
  inactive_time datetime NOT NULL,
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  create_account_id bigint(20) DEFAULT NULL,
  update_account_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (role_func_rel_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_subject (
  tenant_id varchar(32) COLLATE utf8_bin NOT NULL,
  industry_code varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '',
  subject_id bigint(8) NOT NULL,
  subject_name varchar(64) COLLATE utf8_bin NOT NULL,
  subject_desc varchar(255) COLLATE utf8_bin DEFAULT NULL,
  subject_type varchar(32) COLLATE utf8_bin NOT NULL,
  unit_name varchar(10) COLLATE utf8_bin DEFAULT NULL,
  create_time datetime NOT NULL,
  create_oper_id varchar(12) COLLATE utf8_bin DEFAULT NULL,
  chnl_id varchar(7) COLLATE utf8_bin DEFAULT NULL,
  tax_type int(1) DEFAULT NULL,
  goods_type varchar(8) COLLATE utf8_bin DEFAULT NULL,
  res_subject_pri varchar(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (tenant_id,industry_code,subject_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_subject_fee (
  SUBJECT_ID bigint(8) NOT NULL,
  SETTLE_PRI bigint(8) NOT NULL,
  CAL_SCORE varchar(1) COLLATE utf8_bin NOT NULL DEFAULT '0',
  PRIMARY KEY (SUBJECT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_subject_fund (
  tenant_id varchar(32) COLLATE utf8_bin NOT NULL,
  industry_code varchar(2) COLLATE utf8_bin NOT NULL DEFAULT '',
  subject_id bigint(8) NOT NULL,
  fund_type varchar(2) COLLATE utf8_bin NOT NULL,
  is_cash varchar(1) COLLATE utf8_bin NOT NULL,
  use_mode varchar(1) COLLATE utf8_bin NOT NULL,
  can_settle_all varchar(1) COLLATE utf8_bin NOT NULL,
  valid_type varchar(1) COLLATE utf8_bin NOT NULL,
  use_pri bigint(8) NOT NULL,
  refund_rate bigint(4) NOT NULL,
  can_trans varchar(1) COLLATE utf8_bin NOT NULL,
  can_clean_fund varchar(1) COLLATE utf8_bin NOT NULL,
  can_del_book varchar(1) COLLATE utf8_bin NOT NULL,
  cal_score varchar(1) COLLATE utf8_bin NOT NULL,
  print_mode varchar(1) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (tenant_id,industry_code,subject_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_sys_param (
  GUIDKEY varchar(32) COLLATE utf8_bin NOT NULL,
  TYPE_CODE varchar(40) COLLATE utf8_bin NOT NULL,
  PARAM_CODE varchar(40) COLLATE utf8_bin NOT NULL,
  COLUMN_VALUE varchar(512) COLLATE utf8_bin NOT NULL,
  COLUMN_DESC varchar(512) COLLATE utf8_bin NOT NULL,
  SUB_PARAM_CODE varchar(512) COLLATE utf8_bin DEFAULT NULL,
  DISPORD int(4) NOT NULL,
  DESCB varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  STATE varchar(4) COLLATE utf8_bin NOT NULL,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  SYSTEM_ID varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (GUIDKEY),
  KEY idx_tenant_id (TENANT_ID),
  KEY idx_type_code (TYPE_CODE),
  KEY idx_param_code (PARAM_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sys_sequences (
  Sequence_name varchar(50) COLLATE utf8_bin NOT NULL,
  jvm_step_by int(11) NOT NULL,
  increment_by int(11) NOT NULL DEFAULT '1',
  start_by int(11) DEFAULT NULL,
  last_number int(11) DEFAULT NULL,
  PRIMARY KEY (Sequence_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

