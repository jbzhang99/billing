delete from bmc_basedata_code where tenant_id = 'ECITIC' and param_type = 'ECITIC_BANK' and param_code = 'URL';
INSERT INTO `bmc_basedata_code` (`id`, `tenant_id`, `param_type`, `param_code`, `param_name`, `default_value`) 
VALUES ('10012', 'ECITIC', 'ECITIC_BANK', 'URL', '请求中信银行地址,即银企直联地址', 'http://10.248.4.24:6799/DLink/DLServlet');

delete from bmc_basedata_code where tenant_id = 'ECITIC' and param_type = 'ECITIC_BANK' and param_code = 'USER_NAME';
INSERT INTO `bmc_basedata_code` (`id`, `tenant_id`, `param_type`, `param_code`, `param_name`, `default_value`) 
VALUES ('10013', 'ECITIC', 'ECITIC_BANK', 'USER_NAME', '请求中信银行用户名', 'ZXYW11');
