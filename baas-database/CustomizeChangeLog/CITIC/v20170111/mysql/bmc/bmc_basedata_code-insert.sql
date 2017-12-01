INSERT INTO `bmc_basedata_code` (`id`, `tenant_id`, `param_type`, `param_code`, `param_name`, `default_value`, `comments`, `parent_code`)
VALUES
	(10136, 'ECITIC', 'POLICY_TYPE', 'STEP', '阶梯', '', NULL, NULL),
	(10137, 'ECITIC', 'POLICY_TYPE', 'BRACKET', '分档', '', NULL, NULL);

INSERT INTO `bmc_basedata_code` (`id`, `tenant_id`, `param_type`, `param_code`, `param_name`, `default_value`, `comments`, `parent_code`)
VALUES
	(10151, 'ECITIC', 'VAR_TYPE', 'SINGLE', '单值', '', NULL, NULL),
	(10152, 'ECITIC', 'VAR_TYPE', 'INTERVAL', '区间', '', NULL, NULL);

INSERT INTO `bmc_basedata_code` (`id`, `tenant_id`, `param_type`, `param_code`, `param_name`, `default_value`, `comments`, `parent_code`)
VALUES
	(10129, 'ECITIC', 'POLICY_VAR_UNIT', 'year', '年', '', NULL, NULL),
	(10130, 'ECITIC', 'POLICY_VAR_UNIT', 'people/year', '人/年', '', NULL, NULL),
	(10131, 'ECITIC', 'POLICY_VAR_UNIT', 'port/year', '端口/年', '', NULL, NULL);
