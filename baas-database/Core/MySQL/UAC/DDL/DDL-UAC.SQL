/*请使用这种方式注释*/
/*这里的数据库对象可以有表、索引、视图*/

CREATE TABLE gn_account (
  tenant_id varchar(50) COLLATE utf8_bin NOT NULL,
  account_id bigint(20) NOT NULL,
  account_name varchar(50) COLLATE utf8_bin DEFAULT NULL,
  nick_name varchar(50) COLLATE utf8_bin DEFAULT NULL,
  phone varchar(11) COLLATE utf8_bin DEFAULT NULL,
  email varchar(100) COLLATE utf8_bin DEFAULT NULL,
  account_password varchar(50) COLLATE utf8_bin DEFAULT NULL,
  account_type varchar(2) COLLATE utf8_bin DEFAULT NULL,
  account_level varchar(2) COLLATE utf8_bin DEFAULT NULL,
  state varchar(2) COLLATE utf8_bin DEFAULT NULL,
  active_time datetime DEFAULT NULL,
  inactive_time datetime DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  create_account_id bigint(20) DEFAULT NULL,
  update_account_id bigint(20) DEFAULT NULL,
  weixin varchar(50) COLLATE utf8_bin DEFAULT NULL,
  weibo varchar(50) COLLATE utf8_bin DEFAULT NULL,
  qq varchar(50) COLLATE utf8_bin DEFAULT NULL,
  last_update_pwd_time datetime DEFAULT NULL,
  token varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (account_id)
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

CREATE TABLE gn_industry (
  industry_code varchar(50) COLLATE utf8_bin NOT NULL COMMENT '行业编码',
  industry_name varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '行业名称',
  industry_desc varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '行业描述',
  PRIMARY KEY (industry_code)
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

CREATE TABLE gn_tenant (
  tenant_id varchar(50) COLLATE utf8_bin NOT NULL,
  tenant_name varchar(200) COLLATE utf8_bin NOT NULL,
  state varchar(2) COLLATE utf8_bin NOT NULL COMMENT ' 0：未签合同 1：已签合同   2:合同到期  3:合同中断',
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  create_account_id bigint(20) DEFAULT NULL COMMENT '对应操作员的账号ID',
  update_account_id bigint(20) DEFAULT NULL COMMENT '对应操作员的账号ID',
  industry_code varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '行业类型',
  tenant_address varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '公司地址',
  tenant_telephone varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '公司电话',
  PRIMARY KEY (tenant_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE gn_zuhu_test (
  tenant_id varchar(50) COLLATE utf8_bin NOT NULL,
  tenant_name varchar(200) COLLATE utf8_bin NOT NULL,
  state varchar(2) COLLATE utf8_bin NOT NULL COMMENT ' 0：未签合同 1：已签合同   2:合同到期  3:合同中断',
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  create_account_id bigint(20) DEFAULT NULL COMMENT '对应操作员的账号ID',
  update_account_id bigint(20) DEFAULT NULL COMMENT '对应操作员的账号ID',
  industry_code varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '行业类型',
  tenant_address varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '公司地址',
  tenant_telephone varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '公司电话',
  PRIMARY KEY (tenant_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sys_sequences (
  Sequence_name varchar(50) COLLATE utf8_bin NOT NULL,
  jvm_step_by int(11) NOT NULL,
  increment_by int(11) NOT NULL DEFAULT '1',
  start_by int(11) DEFAULT NULL,
  last_number int(11) DEFAULT NULL,
  PRIMARY KEY (Sequence_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

