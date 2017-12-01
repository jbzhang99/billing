INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) VALUES ('ECITIC', 'SLB', 'ALISLB', '1', '0', '业务标识', 'service_id', 'STRING', '业务标识', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) VALUES ('ECITIC', 'SLB', 'ALISLB', '1', '1', '服务实例', 'instance_id', 'STRING', '服务实例', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) VALUES ('ECITIC', 'SLB', 'ALISLB', '1', '2', '区域', 'region_id', 'STRING', '区域', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) VALUES ('ECITIC', 'SLB', 'ALISLB', '1', '3', '使用量', 'usage_amount', 'STRING', '使用量', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) VALUES ('ECITIC', 'SLB', 'ALISLB', '1', '4', '开始时间', 'start_time', 'STRING', '开始时间', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) VALUES ('ECITIC', 'SLB', 'ALISLB', '1', '5', '报文类型', 'record_type', 'STRING', '报文类型', NULL, NULL);

INSERT INTO `pub_service_route` (`route_type`, `route_params`, `route_class`, `comments`) VALUES ('BMC', 'ECITIC,SLB,ALISLB', 'com.ai.baas.bmc.srv.flow.adapt.EciticRuleProcessor', NULL);

INSERT INTO `bmc_output_info` (`info_code`, `tenant_id`, `service_type`, `source`, `table_prefix`, `table_postfix`, `output_type`, `output_name`, `key_seq`, `seq_name`, `status`, `create_date`) VALUES ('14', 'ECITIC', 'SLB', 'ALISLB', 'DR', NULL, '1', 'hbaseOutput', 'N', NULL, 'A', '2016-12-16 00:00:00');

INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'cust_id', 'cust_id', 'N', '1', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'subs_id', 'subs_id', 'N', '2', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'acct_id', 'acct_id', 'N', '3', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'service_id', 'service_id', 'N', '4', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'instance_id', 'instance_id', 'N', '5', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'usage_amount', 'usage_amount', 'N', '6', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'start_time', 'start_time', 'N', '7', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'region_id', 'region_id', 'N', '8', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'record_type', 'record_type', 'N', '9', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'tenant_id', 'tenant_id', 'N', '10', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'service_type', 'service_type', 'N', '11', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'source', 'source', 'N', '12', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'bsn', 'bsn', 'N', '13', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'sn', 'sn', 'N', '14', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'account_period', 'account_period', 'N', '15', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'fee1', 'fee1', 'N', '16', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'subject1', 'subject1', 'N', '17', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'fee2', 'fee2', 'N', '18', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'subject2', 'subject2', 'N', '19', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'fee3', 'fee3', 'N', '20', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'subject3', 'subject3', 'N', '21', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('14', 'product_id', 'product_id', 'N', '22', 'A');
commit;