--------rtm����
INSERT INTO `rtm_flow_mds` VALUES ('BMC', 'DISK', 'baas_bmc_service_in_queue', null);
 
/**���Ի�����720��Ϊ200����  �����л�ʱ��*/  
INSERT INTO `rtm_param_info` VALUES ('ECITIC', 'DISK', 'ALIDISK', 'TRANSTIME', '720');

INSERT INTO `rtm_src_info`  VALUES ('ECITIC', 'DISK', '', '', '', NULL);

 
INSERT INTO `rtm_src_record` VALUES ('ALIDISK', '1', 'source_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALIDISK', '2', 'service_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALIDISK', '3', 'instance_id', '0', '1', 'String', '1', null);
INSERT INTO `rtm_src_record` VALUES ('ALIDISK', '4', 'region_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALIDISK', '5', 'usage_amount', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALIDISK', '6', 'start_time', '0', '1', 'String', '1', null);
INSERT INTO `rtm_src_record` VALUES ('ALIDISK', '7', 'record_type', '0', '1', 'String', '1', null);
 
 
 
 
 
 
-------bmc���ݿ�
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) 
VALUES ('ECITIC', 'DISK', 'ALIDISK', '1', '0', 'ҵ���ʶ', 'service_id', 'STRING', 'ҵ���ʶ', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'DISK', 'ALIDISK', '1', '1', '����ʵ��', 'instance_id', 'STRING', '����ʵ��', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'DISK', 'ALIDISK', '1', '2', '����', 'region_id', 'STRING', '����', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'DISK', 'ALIDISK', '1', '3', 'ʹ����', 'usage_amount', 'STRING', 'ʹ����', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) 
VALUES ('ECITIC', 'DISK', 'ALIDISK', '1', '4', '��ʼʱ��', 'start_time', 'STRING', '��ʼʱ��', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'DISK', 'ALIDISK', '1', '5', '��������', 'record_type', 'STRING', '��������', NULL, NULL); 
 
INSERT INTO `pub_service_route` (`route_type`, `route_params`, `route_class`, `comments`) 
VALUES ('BMC', 'ECITIC,DISK,ALIDISK', 'com.ai.baas.bmc.srv.flow.adapt.EciticRuleProcessor', NULL);
 --15 ��ֵ��Ҫ�ĵ������
INSERT INTO `bmc_output_info` (`info_code`, `tenant_id`, `service_type`, `source`, `table_prefix`, `table_postfix`, `output_type`, `output_name`, `key_seq`, `seq_name`, `status`, `create_date`) VALUES
                                                   ('16', 'ECITIC', 'DISK', 'ALIDISK', 'DR', NULL, '1', 'hbaseOutput', 'N', NULL, 'A', '2017-02-16 00:00:00');
 
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'cust_id', 'cust_id', 'N', '1', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'subs_id', 'subs_id', 'N', '2', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'acct_id', 'acct_id', 'N', '3', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'service_id', 'service_id', 'N', '4', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'instance_id', 'instance_id', 'N', '5', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'usage_amount', 'usage_amount', 'N', '6', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'start_time', 'start_time', 'N', '7', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'region_id', 'region_id', 'N', '8', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'record_type', 'record_type', 'N', '9', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'tenant_id', 'tenant_id', 'N', '10', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'service_type', 'service_type', 'N', '11', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'source', 'source', 'N', '12', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'bsn', 'bsn', 'N', '13', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'sn', 'sn', 'N', '14', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'account_period', 'account_period', 'N', '15', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'fee1', 'fee1', 'N', '16', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'subject1', 'subject1', 'N', '17', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'fee2', 'fee2', 'N', '18', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'subject2', 'subject2', 'N', '19', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'fee3', 'fee3', 'N', '20', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'subject3', 'subject3', 'N', '21', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('16', 'product_id', 'product_id', 'N', '22', 'A');
commit;
 