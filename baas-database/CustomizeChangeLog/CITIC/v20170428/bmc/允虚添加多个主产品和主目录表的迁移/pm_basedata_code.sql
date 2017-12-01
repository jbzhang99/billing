/*
Navicat MySQL Data Transfer

Source Server         : BAAS-BMC
Source Server Version : 50624
Source Host           : 10.1.235.245:31306
Source Database       : dev_baas_bmc1

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-04-28 11:33:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pm_basedata_code`
-- ----------------------------
DROP TABLE IF EXISTS `pm_basedata_code`;
CREATE TABLE `pm_basedata_code` (
  `id` bigint(11) NOT NULL,
  `tenant_id` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `param_type` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `param_code` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `param_name` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `price_type` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '',
  `default_value` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `parent_code` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `subject_type` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `billing_type` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `is_free` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `can_buy_period` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `comments` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`price_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

