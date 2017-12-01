-- ----------------------------
-- Table structure for amc_cc_charge_yyyydd
-- ----------------------------
DROP TABLE IF EXISTS `amc_cc_charge_yyyydd`;
CREATE TABLE `amc_cc_charge_yyyydd` (
  `CC_CHARGE_SEQ` bigint(15) NOT NULL,
  `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `COST_CENTER_ID` varchar(64) COLLATE utf8_bin NOT NULL,
  `SUBJECT_ID` bigint(10) NOT NULL,
  `APPORTION_ACCT_ID` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `AMOUNT` bigint(15) DEFAULT NULL,
  `LAST_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
