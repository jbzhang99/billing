/**pm_deminsion_info维度信息表*/
DROP TABLE IF EXISTS `pm_dimension_info`;
CREATE TABLE `pm_dimension_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `TRADE_NAME` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '行业类型名称',
  `TRADE_CODE` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '行业类型编码',
  `MAIN_PRODUCT_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `MAIN_PRODUCT_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DIMENSION_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DIMENSION_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `COMMENTS` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `DIMENSION_SEQ` int(8) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=353 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


/**pm_deminsion_info_his维度信息表历史表*/
DROP TABLE IF EXISTS `pm_dimension_info_his`;
CREATE TABLE `pm_dimension_info_his` (
  `ID` bigint(20) NOT NULL,
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `TRADE_NAME` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '行业类型名称',
  `TRADE_CODE` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '行业类型编码',
  `MAIN_PRODUCT_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `MAIN_PRODUCT_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DIMENSION_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DIMENSION_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `COMMENTS` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `DIMENSION_SEQ` int(8) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


/**维度分支信息表*/
DROP TABLE IF EXISTS `pm_dimension_branch`;
CREATE TABLE `pm_dimension_branch` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `DIMENSION_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BRANCH_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BRANCH_CODE` varchar(64) COLLATE utf8_bin NOT NULL,
  `COMMENTS` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `MAIN_PRODUCT_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=485 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/**----维度分支信息表对应的历史表-------*/
DROP TABLE IF EXISTS `pm_dimension_branch_his`;
CREATE TABLE `pm_dimension_branch_his` (
  `ID` bigint(20) NOT NULL,
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `DIMENSION_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BRANCH_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BRANCH_CODE` varchar(64) COLLATE utf8_bin NOT NULL,
  `COMMENTS` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `MAIN_PRODUCT_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;




-- ----------------------------
-- `pm_category_info` 产品类目信息表
-- ----------------------------
DROP TABLE IF EXISTS `pm_category_info`;
CREATE TABLE `pm_category_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `CATEGORY_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_LEVEL` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_TYPE` varchar(16) COLLATE utf8_bin NOT NULL,
  `CATEGORY_ID` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `MEMBERS` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=706 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- ----------------------------
-- `pm_category_info_his`产品类目信息历史表
-- ----------------------------
DROP TABLE IF EXISTS `pm_category_info_his`;
CREATE TABLE `pm_category_info_his` (
  `ID` bigint(20) NOT NULL,
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `CATEGORY_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_LEVEL` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_TYPE` varchar(16) COLLATE utf8_bin NOT NULL,
  `CATEGORY_ID` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `MEMBERS` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/**产品目录信息表*/
DROP TABLE IF EXISTS `pm_catalog_info`;
CREATE TABLE `pm_catalog_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  `TENANT_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '租户ID',
  `MAIN_PRODUCT_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '主产品ID',
  `MAIN_PRODUCT_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '主产品名称',
  `CATEGORY_ID` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '规格类别归属的产品目录，最小级别的目录',
  `BILLING_CYCLE` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '计费周期,取值如下：hour,day,month,year',
  `SPEC_TYPE_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '规格类别名称',
  `SPEC_TYPE_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '规格类别ID，对应定价因子构成表的price_product_type字段',
  `SPEC_DETAIL_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '规格明细ID ,对应定价因子构成表的price_product_ID字段',
  `PRICE_POLICY` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '定价策略ID ',
  `TRADE_CODE` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/** 定价策略明细表*/

DROP TABLE IF EXISTS `pm_policy_detail`;
CREATE TABLE `pm_policy_detail` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `POLICY_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '策略ID',
  `DETAIL_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '明细ID,查询的时候，可以按这个排序，方面界面展示',
  `PRICE` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '价格,对于穷举类型，这里保存的金额，单位是元，小数点后面6位，即精确到千分之一厘;对于表达式类型，这里保存的是表达式，只有四则运算',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/** 定价策略因子表*/
DROP TABLE IF EXISTS `pm_policy_factor`;
CREATE TABLE `pm_policy_factor` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `DETAIL_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '明细ID',
  `VAR_CODE` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '变量编码,对应定价因子构成表中的FACTOR_NAME',
  `VAR_VALUE` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '变量值,对应定价因子构成表中的FACTOR_VALUE',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/** 定价策略信息表*/
DROP TABLE IF EXISTS `pm_policy_info`;
CREATE TABLE `pm_policy_info` (
  `POLICY_ID` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '策略ID',
  `POLICY_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '策略名称',
  `POLICY_TYPE` varchar(16) COLLATE utf8_bin NOT NULL COMMENT '取值范围：ENUM-穷举 LinearUp-线性递增  NoneLinearUp-非线性递增 LinearDown-线性递减 BRACKET-分档 UserDef-自定义',
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `COMMENTS` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`POLICY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/**定价策略变量表*/
DROP TABLE IF EXISTS `pm_policy_variable`;
CREATE TABLE `pm_policy_variable` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `POLICY_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '策略ID',
  `VAR_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '变量名称',
  `VAR_CODE` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '变量编码',
  `UNIT_NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '变量单位名称',
  `UNIT_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '变量单位编码',
  `UNIT_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '变量单位ID,从配置表里面关联时使用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `cp_pricemaking_mapping`;
CREATE TABLE `cp_pricemaking_mapping` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `MAPPING_BEFORE` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `MAPPING_AFTER` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `IS_VALID` char(1) COLLATE utf8_bin DEFAULT NULL,
  `COMMENT` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `pm_spec_type`;
CREATE TABLE `pm_spec_type` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增字段，用于唯一索引',
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `CATEGORY_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '类目ID',
  `SPEC_TYPE_NAME` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '规格类别名称',
  `SPEC_TYPE_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '规格类别ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;












