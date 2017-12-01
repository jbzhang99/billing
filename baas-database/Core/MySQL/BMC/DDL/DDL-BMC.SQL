 
/*请使用这种方式注释*/
/*这里的数据库对象可以有普通表、月表、索引、视图*/

CREATE TABLE bl_acct_info (
  TENANT_ID varchar(32) DEFAULT NULL COMMENT '租户ID',
  ACCT_ID varchar(32) NOT NULL COMMENT '账户ID',
  OWNER_TYPE varchar(4) DEFAULT NULL COMMENT '属主类型,CUST：某个客户的账户\nUSER：某个用户的账户',
  OWNER_ID varchar(32) DEFAULT NULL COMMENT '属主ID,CUST_ID或者SUBS_ID',
  ACCT_NAME varchar(32) DEFAULT NULL COMMENT '账户名称',
  ACCT_TYPE varchar(8) DEFAULT NULL COMMENT '账户类型,PRE：预付费\nPOST：后付费',
  CREATE_TIME datetime DEFAULT NULL COMMENT '创建时间',
  COMMENTS varchar(1024) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (ACCT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE bl_acctinfo_ext (
  EXT_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增字段',
  ACCT_ID varchar(32) NOT NULL COMMENT '账户ID',
  EXT_NAME varchar(32) NOT NULL COMMENT '名称',
  EXT_VALUE varchar(1024) DEFAULT NULL COMMENT '值',
  PRIMARY KEY (EXT_ID),
  UNIQUE KEY ACCT_ID (ACCT_ID,EXT_NAME) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE bl_custinfo (
  CUST_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '全系统唯一',
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL,
  CUST_NAME varchar(128) COLLATE utf8_bin NOT NULL,
  EXT_CUST_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户系统中设定的标识',
  CUST_TYPE varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT 'P-个人客户;G-单位客户',
  CUST_GRADE varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '取值范围：A,B,C,D,E',
  PROVINCE_CODE varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '参考省份定义表',
  CITY_CODE varchar(6) COLLATE utf8_bin DEFAULT NULL COMMENT '以0开头的区号',
  STATE varchar(16) COLLATE utf8_bin NOT NULL COMMENT 'Normal：正常;NoDoc：未返档;Register：注册;OweFee：欠费;Freeze：冻结',
  STATE_CHG_TIME datetime NOT NULL,
  REMARK varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  CONTACT_NO varchar(32) COLLATE utf8_bin DEFAULT NULL,
  EMAIL varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CUST_ADDRESS varchar(128) COLLATE utf8_bin DEFAULT NULL,
  ID_NUMBER varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (CUST_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bl_custinfo_ext (
  EXT_ID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  CUST_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '客户ID',
  EXT_NAME varchar(32) COLLATE utf8_bin NOT NULL COMMENT '扩展信息名称，取值参考：扩展因素定义表；不能重复',
  EXT_VALUE varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展信息的值，取值参考：扩展因素定义表',
  PRIMARY KEY (EXT_ID),
  UNIQUE KEY CUST_ID (CUST_ID,EXT_NAME) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=122352 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bl_subs_comm (
  PRODUCT_TYPE varchar(32) DEFAULT NULL,
  SUBS_ID varchar(32) NOT NULL COMMENT '用户ID',
  PRODUCT_ID varchar(32) DEFAULT NULL COMMENT '产品ID',
  SUBS_PRODUCT_ID varchar(32) NOT NULL COMMENT '产品实例ID,当用户订购多个相同的产品的时候，需要填写这个字段用于区分不同的产品实例。全系统唯一',
  RES_BONUS_FLAG varchar(1) DEFAULT NULL COMMENT '赠送标识,标识该产品是否为一个赠送的产品。\n取值：Y:是赠送；\nN-不是赠送。',
  ACTIVE_TIME datetime NOT NULL COMMENT '生效日期',
  INACTIVE_TIME datetime DEFAULT NULL COMMENT '失效日期',
  TENANT_ID varchar(32) DEFAULT NULL COMMENT '租户ID',
  CUST_ID varchar(32) DEFAULT NULL COMMENT '客户ID,冗余字段,方便查询',
  PRIMARY KEY (SUBS_ID,SUBS_PRODUCT_ID,ACTIVE_TIME)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/***************************************/
/* BL_SUBSCOMM_EXT  订购信息扩展表                        */ 
/* 作用： 保存产品订购信息表的扩展信息。以竖表形式存放，*/
/*   一个扩展信息一行。                                                        */ 
/* EXT_NAME 取值列表：                                                     */ 
/*   PACKAGE_FEE_CAL_DAY，含义：                         */ 
/*     套餐包的包费计算的时间，用类似crontab的表达式表示： */ 
/*     *　　*　　*　　*　　*　　*   （用空格分隔）              */ 
/*     分　时　 日　 月　 周　  年                                                      */ 
/*     第1列表示分钟1～59 每分钟用*或者 * /1表示                  */ 
/*     第2列表示小时1～23（0表示0点）                                         */ 
/*     第3列表示日期1～31                         */ 
/*     第4列表示月份1～12                         */ 
/*     第5列标识号星期0～6（0表示星期天）                                    */ 
/*     第6列表示年份2016～9999                    */ 
/***************************************/
CREATE TABLE bl_subscomm_ext (
  EXT_ID          int(11) NOT NULL AUTO_INCREMENT COMMENT '自增字段',
  PRODUCT_ID      varchar(32) NOT NULL COMMENT '产品ID',
  SUBS_PRODUCT_ID varchar(32) DEFAULT NULL COMMENT '产品订购实例ID',
  EXT_NAME        varchar(32) NOT NULL COMMENT '名称',
  EXT_VALUE       varchar(1024) DEFAULT NULL COMMENT '值',
  PRIMARY KEY (EXT_ID),
  UNIQUE KEY PRODUCT_ID (PRODUCT_ID,SUBS_PRODUCT_ID,EXT_NAME) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE bl_userinfo (
  TENANT_ID varchar(32) NOT NULL COMMENT '租户ID',
  CUST_ID varchar(32) DEFAULT NULL COMMENT '客户标识',
  SUBS_ID varchar(32) NOT NULL COMMENT '订购标识',
  ACCT_ID varchar(32) DEFAULT NULL COMMENT '帐户',
  SERVICE_ID varchar(64) DEFAULT NULL,
  DEAL_TIME datetime DEFAULT NULL COMMENT '受理时间',
  PROVINCE_CODE varchar(6) DEFAULT NULL COMMENT '服务标识归属省',
  CITY_CODE varchar(6) DEFAULT NULL COMMENT '服务标识归属地区',
  CHL_ID varchar(32) DEFAULT NULL COMMENT '发展渠道',
  DEV_ID varchar(32) DEFAULT NULL COMMENT '发展人',
  ACTIVE_TIME datetime NOT NULL COMMENT '生效时间',
  INACTIVE_TIME datetime DEFAULT NULL COMMENT '失效时间',
  REMARK varchar(1024) DEFAULT NULL COMMENT '备注',
  SERV_TYPE varchar(32) DEFAULT NULL COMMENT '业务类型',
  USER_TYPE varchar(32) DEFAULT NULL COMMENT '用户类型',
  USER_STATE varchar(32) DEFAULT NULL COMMENT '用户状态',
  policy_id varchar(32) DEFAULT NULL COMMENT '信控策略',
  PRIMARY KEY (SUBS_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE bl_userinfo_ext (
  EXT_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  SUBS_ID varchar(32) NOT NULL COMMENT '用户ID',
  EXT_NAME varchar(32) NOT NULL COMMENT '名称',
  EXT_VALUE varchar(1024) DEFAULT NULL COMMENT '值',
  PRIMARY KEY (EXT_ID),
  UNIQUE KEY SUBS_ID (SUBS_ID,EXT_NAME) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

CREATE TABLE bmc_basedata_code (
  id bigint(11) NOT NULL,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  param_type varchar(32) COLLATE utf8_bin DEFAULT NULL,
  param_code varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  param_name varchar(32) COLLATE utf8_bin DEFAULT NULL,
  default_value varchar(256) COLLATE utf8_bin DEFAULT NULL,
  comments varchar(256) COLLATE utf8_bin DEFAULT NULL,
  parent_code varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bmc_dataquality (
  id int(11) NOT NULL AUTO_INCREMENT,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  service_type varchar(32) COLLATE utf8_bin NOT NULL,
  source varchar(32) COLLATE utf8_bin DEFAULT NULL,
  tb_suffix_key varchar(50) COLLATE utf8_bin DEFAULT NULL,
  dup_key varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bmc_drquery_fieldrule (
  tableid varchar(6) COLLATE utf8_bin NOT NULL COMMENT '表名前缀编号',
  field_desc varchar(32) COLLATE utf8_bin NOT NULL COMMENT '字段描述',
  field_name varchar(32) COLLATE utf8_bin NOT NULL COMMENT '字段名称',
  field_type int(11) NOT NULL COMMENT '字段类型,0:INT  整型\r\n1:NUM  浮点\r\n2:STR  字符串\r\n3:DATE 日期\r\n4:LIST类型 允许有子节点',
  field_length int(11) DEFAULT NULL COMMENT '字段长度',
  parent_node varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '非空时表示需要填写字段名称 必须是在FIELD_NAME中出现的字段名;否则返回错误; 空时为缺省的responseMessage;',
  src_content varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '输出值,0:INT  整型 cast(FIELD_NAME as char)\r\n1:NUM  浮点 cast(FIELD_NAME as char)\r\n2:STR  字符串 FIELD_NAME\r\n3:DATE 日期 date_format(FIELD_NAME, '' % Y % m % d % H % i % S '')',
  default_output int(11) DEFAULT NULL COMMENT '是否作为输出字段,缺省为1 表示需要输出; 0: 不需要输出 ,不需要开放给用户',
  src_where varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '来源'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bmc_drquery_routerule (
  tenantId varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  systemId varchar(32) COLLATE utf8_bin NOT NULL COMMENT '系统ID',
  serviceType varchar(32) COLLATE utf8_bin NOT NULL COMMENT '业务类型',
  Tableid varchar(6) COLLATE utf8_bin NOT NULL COMMENT '表名前缀编号',
  tablename varchar(32) COLLATE utf8_bin NOT NULL COMMENT '表名前缀'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bmc_output_detail (
  detail_code bigint(12) NOT NULL AUTO_INCREMENT,
  info_code bigint(12) NOT NULL,
  column_name varchar(100) COLLATE utf8_bin NOT NULL,
  param_name varchar(100) COLLATE utf8_bin NOT NULL,
  is_key char(1) COLLATE utf8_bin NOT NULL,
  display_order int(5) NOT NULL,
  status char(1) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (detail_code)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bmc_output_info (
  info_code bigint(12) NOT NULL AUTO_INCREMENT COMMENT '标示id',
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  service_type varchar(32) COLLATE utf8_bin DEFAULT NULL,
  source varchar(32) COLLATE utf8_bin NOT NULL COMMENT '来源',
  table_prefix varchar(100) COLLATE utf8_bin DEFAULT NULL,
  table_postfix varchar(100) COLLATE utf8_bin DEFAULT NULL,
  output_type char(2) COLLATE utf8_bin NOT NULL COMMENT '输出类型，1:DB 2:Service',
  output_name varchar(100) COLLATE utf8_bin DEFAULT NULL,
  key_seq char(1) COLLATE utf8_bin NOT NULL,
  seq_name varchar(100) COLLATE utf8_bin DEFAULT NULL,
  status char(1) COLLATE utf8_bin NOT NULL,
  create_date datetime NOT NULL,
  PRIMARY KEY (info_code)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE bmc_record_fmt (
  id int(11) NOT NULL AUTO_INCREMENT,
  tenant_id varchar(32) COLLATE utf8_bin DEFAULT NULL,
  service_type varchar(32) COLLATE utf8_bin DEFAULT NULL,
  source varchar(32) COLLATE utf8_bin DEFAULT NULL,
  format_type smallint(6) DEFAULT NULL,
  field_serial int(11) DEFAULT NULL,
  field_name varchar(32) COLLATE utf8_bin DEFAULT NULL,
  field_code varchar(32) COLLATE utf8_bin DEFAULT NULL,
  data_type varchar(32) COLLATE utf8_bin DEFAULT NULL,
  comments varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  nullable varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '是否为空YES/NO',
  is_sn varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '是否索引',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=100028 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE cp_cyclefee_info (
  ID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  CYCLE_FEE_TPYE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '周期费类型，取值：\r\nMONTH,YEAR,DAY',
  CYCLE_FEE bigint(32) DEFAULT NULL COMMENT '周期费，单位：厘。',
  DETAIL_CODE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '来自CP_PRICE_DETAIL',
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE cp_ext_info (
  ID int(11) NOT NULL AUTO_INCREMENT,
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  SYSTEM_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '系统ID',
  EXT_OWNER varchar(10) COLLATE utf8_bin NOT NULL COMMENT '扩展因素属主',
  EXT_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '扩展因素编码',
  EXT_TYPE varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展因素类型',
  EXT_NAME varchar(32) COLLATE utf8_bin NOT NULL COMMENT '名称',
  EXT_VALUE varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '值',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE cp_factor_info (
  FACTOR_INFO_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  SYSTEM_ID varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '系统ID',
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  FACTOR_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '参考因素编码',
  FACTOR_NAME varchar(32) COLLATE utf8_bin NOT NULL COMMENT '因素名称',
  FACTOR_VALUE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '因素值',
  PRIMARY KEY (FACTOR_INFO_ID)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE cp_full_present (
  PRESENT_ID bigint(20) NOT NULL COMMENT 'id',
  DETAIL_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '详细编码',
  PRESENT_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '赠品类型',
  PRESENT_AMOUNT double DEFAULT NULL COMMENT '赠品数量',
  ACTIVE_TIME date DEFAULT NULL COMMENT '生效时间',
  INACTIVE_TIME date DEFAULT NULL COMMENT '失效时间',
  PRODUCT_IDS varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '参加产品id列表',
  PRODUCT_GIFT_IDS varchar(256) COLLATE utf8_bin NOT NULL COMMENT '参与赠送产品id列表',
  PRESENT_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '赠品编码',
  REACH_AMOUNT double DEFAULT NULL,
  UNIT varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACCOUNT_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '费用科目类型',
  RELATED_ACCOUNT varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '关联费用科目',
  ACTIVE_FLAG varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACTIVE_CYCLE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (PRESENT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE cp_full_reduce (
  REDUCE_ID bigint(20) NOT NULL COMMENT 'id',
  DETAIL_CODE varchar(32) COLLATE utf8_bin NOT NULL,
  REACH_AMOUNT double NOT NULL COMMENT '达到的金额',
  REDUCE_AMOUNT double NOT NULL,
  ACTIVE_TIME date NOT NULL,
  INACTIVE_TIME date NOT NULL,
  REDUCE_CODE varchar(32) COLLATE utf8_bin NOT NULL,
  PRODUCT_IDS varchar(256) COLLATE utf8_bin NOT NULL COMMENT '选择的产品id列表',
  UNIT varchar(32) COLLATE utf8_bin NOT NULL COMMENT '满减达到数量的单位',
  ACCOUNT_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '费用科目类型',
  RELATED_ACCOUNT varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '关联费用科目',
  PRIMARY KEY (REDUCE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/***************************************/
/* CP_PACKAGE_INFO  套餐包信息列表                        */ 
/* 作用：                保存计费类型为套餐包的具体信息             */ 
/***************************************/

DROP TABLE IF EXISTS `cp_package_info`;

CREATE TABLE cp_package_info (
  PACKAGE_ID  bigint(20)                   NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  DETAIL_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '来自CP_PRICE_DETAIL',
  AMOUNT      double DEFAULT NULL COMMENT '包内额度,资金或者资源. 当取值0的时候，表示不含资源量的套餐包',
  PRICE_VALUE double DEFAULT NULL COMMENT '单价,单位：厘，配合是否单价字段使用，在批价过程中取对应的值',
  TOTAL_PRICE_VALUE double DEFAULT NULL COMMENT '总价，单位：厘，配合是否单价字段使用，在批价过程中取对应的值。这个有存储冗余的缺陷',
  UNIT_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '单位编码：\r\n            MB-MB\r\n            KB-KB\r\n            60S-60秒\r\n            TIME-次\r\n            ITEM-条\r\n            ',
  UNIT_TYPE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '单位类型：\r\n            STREAM-流量\r\n            DURATION-时长\r\n            TIMES-次数\r\n            ITEMS-条数\r\n            \r\n            Select code_name,lebel_name\r\n            From cd\r\n            Where code_type = ‘PKG_UNIT_TYPE’\r\n            ',
  FACTOR_CODE varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '参考因素编码，自动生成，数据保存到参考因素列表中',
  EXCEED_TYPE varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '超出后计费类型, D：单价；T：透支',
  UNITPRICE_CODE varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '当超出后计费类型是单价的时候，这里就保存单价编码值，自动生成',
  EXT_CODE     varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展信息编码，自动生成',
  SUBJECT_CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  SERVICE_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  IS_TOTAL_PRICE varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '枚举值：YES 按照组合总价批价,NO 按照产品使用量批价',
  CYCLE_TYPE     varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '周期类型,取值范围：ONCE: 一次性;HOUR：小时;DAY：每天;MONTH：每月;YEAR：每年',
  CYCLE_INTERVAL int(5) NOT NULL COMMENT '周期间隔, 取值为1，2，3，…与周期类型一起使用，当周期类型取值MONTH，周期间隔取值1的时候，就是每1个月（即每月）的意思，如果周期间隔取值3，就是每3个月的意思。通过周期类型和周期间隔可以灵活表达各种套餐包周期。当周期类型取值ONCE时，该字段没有意义',
  PRIMARY KEY (PACKAGE_ID,DETAIL_CODE)
) ENGINE=InnoDB AUTO_INCREMENT=269 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='保存计费类型为套餐包的具体信息';

CREATE TABLE cp_price_basis (
  tenant_id varchar(32) COLLATE utf8_bin NOT NULL,
  detail_name varchar(32) COLLATE utf8_bin DEFAULT NULL,
  detail_code varchar(32) COLLATE utf8_bin DEFAULT NULL,
  measure_word varchar(32) COLLATE utf8_bin DEFAULT NULL,
  measure_word_code varchar(32) COLLATE utf8_bin NOT NULL,
  add_up_subject varchar(64) COLLATE utf8_bin NOT NULL,
  cal_way varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE cp_price_detail (
  DETAIL_ID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  PRICE_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '来自总表：CP_PRICE_INFO表',
  DETAIL_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '资费明细项目的名称',
  CHARGE_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'MONFEE-月费\r\n            Package-套餐包\r\n           Step-阶梯\r\n          Unit-单价\r\n            Select Code_name, label_name from CP_CODE where Code_type = ‘CHARGE_TYPE’\r\n            ',
  ACTIVE_TIME datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '生效时间',
  INACTIVE_TIME datetime DEFAULT NULL COMMENT '失效时间',
  DETAIL_CODE varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '明细编码',
  COMMENTS varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '说明',
  SERVICE_TYPE varchar(32) CHARACTER SET utf8 DEFAULT '' COMMENT '业务类型',
  PRIMARY KEY (DETAIL_ID)
) ENGINE=InnoDB AUTO_INCREMENT=1735 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='保存资费包含的明细类别';

/***************************************/
/* CP_PRICE_INFO  资费列表                                        */ 
/* 作用： 保存全部的资费列表，资费通过生失效时间动态切换*/ 
/***************************************/
CREATE TABLE cp_price_info (
  PRICE_INFO_ID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户标识',
  PRICE_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '资费编码，全系统唯一',
  PRICE_NAME varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '资费名称',
  ACTIVE_TIME datetime NOT NULL COMMENT '生效时间',
  INACTIVE_TIME datetime DEFAULT NULL COMMENT '失效时间',
  CREATE_TIME datetime DEFAULT NULL COMMENT '创建时间',
  OPERATOR_ID varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '操作员ID',
  COMMENTS varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '说明',
  PRODUCT_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  ACTIVE_STATUS varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CHARGE_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRODUCT_PRIORITY int(8) DEFAULT 100 COMMENT '产品优先级,用于区分产品使用时的优先级。数值越小越优先使用。这里把产品的使用优先级定义在产品上，将导致租户所有的产品都要在同一个数值空间里面排序，每个产品都要设置优先级，不设置的，则置缺省值100。优先级相同的产品，批价时随机使用。对于一个产品包含多个业务的资费是，如果语音和流量打包成的套餐包，已经无法再区分使用的优先级，如果要区分，则把包拆开来，定义2个产品。',
  PRIMARY KEY (PRICE_INFO_ID)
) ENGINE=InnoDB AUTO_INCREMENT=1819 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='保存全部的资费列表，资费通过生失效时间动态切换';

CREATE TABLE cp_step_info (
  SETP_ID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  DETAIL_CODE bigint(20) NOT NULL COMMENT '来自CP_PRICE_DETAIL',
  STEP_SEQ bigint(20) NOT NULL COMMENT '阶梯序号\r\n            1-	第一级\r\n            2-	第二级\r\n            3-	第三级\r\n            ',
  SECTION_A double DEFAULT NULL COMMENT '区间左值',
  SECTION_B double DEFAULT NULL COMMENT '区间右值',
  FACTOR_CODE varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '参考因素编码，自动生成，数据保存到参考因素列表中',
  PRICE_VALUE double DEFAULT NULL COMMENT '单价的值，单位：厘',
  UNIT_TYPE varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '单价的单位类型：\r\n            6S-6秒\r\n            7S-7秒\r\n            60S-分钟\r\n            180S-3分钟\r\n            KB\r\n            MB\r\n            ',
  TOTAL_PRICE_VALUE double DEFAULT NULL COMMENT '总价的值',
  EXT_CODE bigint(20) DEFAULT NULL COMMENT '扩展信息编码，自动生成',
  SUBJECT_CODE varchar(8) COLLATE utf8_bin DEFAULT NULL,
  SERVICE_TYPE varchar(32) COLLATE utf8_bin DEFAULT NULL,
  IS_PRICE_EQUAL varchar(1) COLLATE utf8_bin DEFAULT NULL,
  IS_TOTAL_PRICE varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '是否总价：YES 按照阶梯总价批价,NO 按照阶梯单价批价',
  PRIMARY KEY (SETP_ID)
) ENGINE=InnoDB AUTO_INCREMENT=1141 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='保存阶梯套餐的资费类型信息';

CREATE TABLE cp_unitprice_info (
  UNIT_PRICE_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  UNIT_PRICE_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '单价明细编码',
  PRICE_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '明细的名称',
  FACTOR_CODE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '参考因素编码',
  FEE_ITEM_CODE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '费用项编码',
  PRIMARY KEY (UNIT_PRICE_ID),
  UNIQUE KEY UNIT_PRICE_CODE (UNIT_PRICE_CODE)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE cp_unitprice_item (
  UNIT_ITEM_ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  FEE_ITEM_CODE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '费用项编码',
  FEE_TYPE int(1) NOT NULL COMMENT '费用类型',
  PRICE_VALUE double(20,6) DEFAULT NULL COMMENT '单价的值',
  UNIT_TYPE_VALUE double(20,6) DEFAULT NULL,
  UNIT_TYPE varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '单位类型',
  SUBJECT_CODE varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '科目编码',
  ACTIVE_TIME datetime NOT NULL COMMENT '生效时间',
  INACTIVE_TIME datetime DEFAULT NULL COMMENT '失效时间',
  ACTIVE_STATUS varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '取值范围：,NOTEFFECT：待生效,EFFECT：生效',
  ITEM_EXT_CODE varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展信息编码',
  comments varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (UNIT_ITEM_ID)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE pub_service_route (
  id int(11) NOT NULL AUTO_INCREMENT,
  route_type varchar(16) COLLATE utf8_bin NOT NULL,
  route_params varchar(128) COLLATE utf8_bin NOT NULL,
  route_class varchar(128) COLLATE utf8_bin NOT NULL,
  comments varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE real_charge_201605 (
  TENANT_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  CUST_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SUBS_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SERVICE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SUBJECT_ID varchar(8) COLLATE utf8_bin DEFAULT NULL,
  TOTAL decimal(20,6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE rtm_auth_info (
  ID int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户id',
  USER_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户id',
  PASSWD varchar(256) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE rtm_batch_log_201608 (
  BSN varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'bsn序列号',
  DATA_COUNT bigint(20) NOT NULL COMMENT 'bsn序列号的数据条数',
  CHECK_STATE char(2) COLLATE utf8_bin NOT NULL COMMENT 'N 表示没有核对,Y 表示已经核对',
  CHECK_RESULT varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '记录核对结果',
  IS_SWITCH char(2) COLLATE utf8_bin NOT NULL COMMENT 'Y 表示已经切换，N没有切换',
  SWITCH_TIME datetime DEFAULT NULL COMMENT '批次切换时间',
  REASON varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '记录核对失败原因',
  ADUIT_FINISH_TIME datetime DEFAULT NULL COMMENT '稽核完成时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE rtm_flow_mds (
  MODULE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  SOURCE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL,
  DEST_MDS varchar(48) COLLATE utf8_bin DEFAULT NULL,
  COMMENTS varchar(256) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE rtm_src_info (
  TENANT_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户id',
  INFO_TYPE varchar(32) COLLATE utf8_bin NOT NULL COMMENT '业务类型',
  INFO_SPLIT_FLAG varchar(16) COLLATE utf8_bin NOT NULL COMMENT '报文头分隔符 char(3)',
  record_split_flag varchar(32) COLLATE utf8_bin DEFAULT NULL,
  field_split_flag varchar(32) COLLATE utf8_bin DEFAULT NULL,
  COMMENTS varchar(512) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE rtm_src_record (
  INFO_ID varchar(32) COLLATE utf8_bin NOT NULL COMMENT '解析类型编码',
  FIELD_ID char(8) COLLATE utf8_bin NOT NULL COMMENT '业务数据编码',
  FIELD_NAME varchar(64) COLLATE utf8_bin NOT NULL COMMENT '字段名称',
  START_LOCAL char(16) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '字段开始位置',
  FIELD_LENGTH char(16) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '字段长度',
  FIELD_TYPE char(16) COLLATE utf8_bin NOT NULL DEFAULT 'String' COMMENT '字段长度',
  IS_SN char(8) COLLATE utf8_bin NOT NULL COMMENT 'Y 是sn，N不是sn',
  REMARK varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '字段注解'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sys_sequence_src (
  ID bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增',
  SEQUENCE_ID varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '序列标识',
  SEQUENCE_NAME varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '序列名称',
  MIN_VALUE bigint(20) DEFAULT '0' COMMENT '最小值，缺省是0',
  MAX_VALUE bigint(20) DEFAULT NULL COMMENT '最大值',
  CURRENT_VALUE bigint(20) DEFAULT NULL COMMENT '当前值',
  CYCLABLE varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '可循环，YES，NO',
  COMMENTS varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sys_sequences (
  sequence_name varchar(60) COLLATE utf8_bin NOT NULL,
  start_by bigint(20) unsigned NOT NULL,
  increment_by bigint(10) unsigned NOT NULL,
  last_number bigint(20) unsigned NOT NULL,
  jvm_step_by bigint(10) DEFAULT '1',
  PRIMARY KEY (sequence_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE rtm_param_info (
  tenant_id VARCHAR(32) COLLATE utf8_bin NOT NULL COMMENT '租户id',
  system_id VARCHAR(32) COLLATE utf8_bin NOT NULL COMMENT '业务id',
  source_id VARCHAR(32) COLLATE utf8_bin NOT NULL COMMENT '来源',
  param_name VARCHAR(32) COLLATE utf8_bin NOT NULL COMMENT '参数名称',
  param_value VARCHAR(32) COLLATE utf8_bin NOT NULL COMMENT '参数值'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `cp_pricemaking_factor` (
   `ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
   `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
   `PRICE_PRODUCT_TYPE` varchar(64) CHARACTER SET utf8 NOT NULL,
   `PRICE_PRODUCT_ID` varchar(16) COLLATE utf8_bin NOT NULL,
   `FACTOR_NAME` varchar(32) COLLATE utf8_bin NOT NULL,
   `FACTOR_VALUE` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
   PRIMARY KEY (`TENANT_ID`,`PRICE_PRODUCT_TYPE`,`FACTOR_NAME`,`PRICE_PRODUCT_ID`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1191 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='定价因子构成表';
 
 CREATE TABLE `cp_pricemaking_rule` (
   `ID` bigint(10) NOT NULL AUTO_INCREMENT,
   `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL,
   `PRICE_PRODUCT_TYPE` varchar(64) CHARACTER SET utf8 NOT NULL,
   `PRICE_PRODUCT_ID` varchar(16) COLLATE utf8_bin NOT NULL,
   `PRICE_TYPE` varchar(16) COLLATE utf8_bin NOT NULL,
   `RULE_CODE` varchar(16) COLLATE utf8_bin DEFAULT NULL,
   `RULE_EXPRESION` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
   `EXT_INFO` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
   `PRICE_UNIT` varchar(16) COLLATE utf8_bin DEFAULT NULL,
   `PRICE_UNIT_NAME` varchar(16) COLLATE utf8_bin DEFAULT NULL,
   `ACTIVE_TIME` datetime NOT NULL DEFAULT '2016-01-01 12:00:00',
   `INACTIVE_TIME` datetime DEFAULT '2099-01-01 12:00:00',
   PRIMARY KEY (`TENANT_ID`,`PRICE_PRODUCT_TYPE`,`PRICE_PRODUCT_ID`,`PRICE_TYPE`,`ACTIVE_TIME`),
   UNIQUE KEY `ID_UNIQUE` (`ID`)
 ) ENGINE=InnoDB AUTO_INCREMENT=623 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
 
 CREATE TABLE `cp_pricemaking_mapping` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `MAPPING_BEFORE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `MAPPING_AFTER` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `IS_VALID` char(1) COLLATE utf8_bin DEFAULT NULL,
  `COMMENT` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
