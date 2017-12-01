/*
 Navicat Premium Data Transfer

 Source Server         : dev_baas_amc1
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : 10.1.235.245
 Source Database       : dev_baas_amc1

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : utf-8

 Date: 04/20/2017 21:04:50 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `amc_charge_adjust_yyyymm`
-- ----------------------------
DROP TABLE IF EXISTS `amc_charge_adjust_yyyymm`;
CREATE TABLE `amc_charge_adjust_yyyymm` (
  `ADJUST_SEQ` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '调账明细编号',
  `CHARGE_SEQ` bigint(15) NOT NULL COMMENT '费用明细编号',
  `TENANT_ID` varchar(32) NOT NULL COMMENT '租户ID',
  `ACCT_ID` varchar(32) NOT NULL COMMENT '账户ID',
  `SERVICE_ID` varchar(64) DEFAULT NULL COMMENT '服务号码',
  `SUBJECT_ID` bigint(8) NOT NULL COMMENT '调账科目代码',
  `TOTAL_AMOUNT` bigint(15) NOT NULL COMMENT '原始科目总额',
  `ADJUST_AFTERWARDS` bigint(15) NOT NULL COMMENT '原始调整总额',
  `DISC_TOTAL_AMOUNT` bigint(15) NOT NULL COMMENT '原始优惠总额',
  `BALANCE` bigint(15) NOT NULL COMMENT '原始未销余额',
  `ADJUST_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '调帐日期',
  `CUST_ID` bigint(10) NOT NULL COMMENT '客户标识',
  `OPERATOR` varchar(64) NOT NULL COMMENT '操作人',
  `ADJSUT_VALUE` bigint(15) NOT NULL COMMENT '调整值',
  `ADJUST_REASON` varchar(100) DEFAULT NULL COMMENT '调账描述',
  PRIMARY KEY (`ADJUST_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调账日志表';

SET FOREIGN_KEY_CHECKS = 1;
