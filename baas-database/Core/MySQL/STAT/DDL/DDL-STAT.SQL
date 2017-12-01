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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stat_accu_rule (
  ID int(11) NOT NULL AUTO_INCREMENT,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户标识，即企业的编码',
  SERVICE_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  STAT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  TABLE_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '支持月表方式',
  GROUP_FIELDS varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '除了累帐字段，指定表的其它字段，并且是主键字段',
  ACCU_FIELDS varchar(512) COLLATE utf8_bin DEFAULT NULL,
  COMMENTS varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  TABLE_NAME_RULE_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '表名称规则类型：\nFULL：完整表名\nACCT_MONTH：月表 – 账期\nSTART_TIME：月表 -开始时间',
  PRIMARY KEY (ID,TENANT_ID,SERVICE_TYPE,STAT_ID)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE stat_yyyymm (
  ID int(11) NOT NULL,
  CUST_ID varchar(32) CHARACTER SET utf8 NOT NULL,
  SUBS_ID varchar(32) CHARACTER SET utf8 NOT NULL,
  SERVICE_NUM varchar(32) COLLATE utf8_bin NOT NULL,
  SERVICE_TYPE varchar(32) COLLATE utf8_bin NOT NULL,
  AMOUNT double DEFAULT NULL,
  DURATION double DEFAULT NULL,
  SOURCE varchar(32) COLLATE utf8_bin NOT NULL,
  UPDATE_TIME timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

