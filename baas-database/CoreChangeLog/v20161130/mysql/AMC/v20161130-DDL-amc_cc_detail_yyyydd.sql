-- ----------------------------
-- Table structure for amc_cc_detail_yyyydd
-- ----------------------------
DROP TABLE IF EXISTS `amc_cc_detail_yyyydd`;
CREATE TABLE `amc_cc_detail_yyyydd` (
  `CC_DETAIL_SEQ` bigint(15) NOT NULL,
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `COST_CENTER_ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `SUBJECT_ID` bigint(8) NOT NULL,
  `APPORTION_ACCT_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `APPORTION_METHOD` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `APPORTION_VALUE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `AMOUNT` bigint(15) NOT NULL,
  `ACCT_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `CUST_ID` bigint(10) DEFAULT NULL,
  `SUBS_ID` bigint(10) DEFAULT NULL,
  `IS_APPORTION` char(1) COLLATE utf8_bin DEFAULT NULL,
  `DR_KEY` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
