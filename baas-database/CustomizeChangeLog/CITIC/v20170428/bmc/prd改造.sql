INSERT INTO `bmc_basedata_code` (`id`, `tenant_id`, `param_type`, `param_code`, `param_name`, `default_value`, `comments`, `parent_code`)
VALUES
 (1020, 'PUB', 'BILLING_MODE', 'package', '包年包月', NULL, '', ''),
 (1021, 'PUB', 'BILLING_MODE', 'volume', '按量计费', NULL, '', '');


alter table `pm_catalog_info` add `BILLING_MODE` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '计费模式';
alter table `pm_catalog_info` add `MODE_CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '计费模式编码';


alter table `pm_policy_detail` add `COMMENTS` varchar(128) COLLATE utf8_bin DEFAULT NULL;


INSERT INTO `bmc_basedata_code` (`id`, `tenant_id`, `param_type`, `param_code`, `param_name`, `default_value`, `comments`, `parent_code`)
VALUES
 (10161, 'PUB', 'PRICE_TYPE', 'yuan/day', '元/天', '', NULL, NULL),
 (10162, 'PUB', 'PRICE_TYPE', 'yuan/month', '元/月', '', NULL, NULL),
  (10163, 'PUB', 'PRICE_TYPE', 'yuan/year', '元/年', '', NULL, NULL);

INSERT INTO `bmc_basedata_code` (`id`, `tenant_id`, `param_type`, `param_code`, `param_name`, `default_value`, `comments`, `parent_code`)
VALUES
 (10138, 'ECITIC', 'POLICY_TYPE', 'EXPR', '表达式', '', NULL, NULL);

alter table `pm_policy_detail` add `PRICE_TYPE` varchar(64) COLLATE utf8_bin DEFAULT NULL;


INSERT INTO `cp_pricemaking_mapping` (`ID`, `TENANT_ID`, `MAPPING_BEFORE`, `MAPPING_AFTER`, `IS_VALID`, `COMMENT`)
VALUES
 (11, 'ECITIC', 'PRICE_TYPE:yuan/day', 'PER_HOUR', 'Y', ''),
 (12, 'ECITIC', 'PRICE_TYPE:yuan/month', 'PER_HOUR', 'Y', ''),
 (13, 'ECITIC', 'PRICE_TYPE:yuan/year', 'PER_HOUR', 'Y', ''),
 (31, 'ECITIC', 'PRICE_UNIT:yuan/day', '/d', 'Y', ''),
 (32, 'ECITIC', 'PRICE_UNIT:yuan/month', '/m', 'Y', ''),
 (33, 'ECITIC', 'PRICE_UNIT:yuan/year', '/y', 'Y', ''),
 (51, 'ECITIC', 'PRICE_UNIT_NAME:yuan/day', '/天', 'Y', ''),
 (52, 'ECITIC', 'PRICE_UNIT_NAME:yuan/month', '/月', 'Y', ''),
 (53, 'ECITIC', 'PRICE_UNIT_NAME:yuan/year', '/年', 'Y', '');
