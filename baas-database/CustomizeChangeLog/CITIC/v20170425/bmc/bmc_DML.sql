--------rtm配置
INSERT INTO `rtm_flow_mds` VALUES ('BMC', 'NAT', 'baas_bmc_service_in_queue', null);
INSERT INTO `rtm_flow_mds` VALUES ('BMC', 'SHARE', 'baas_bmc_service_in_queue', null);
/**测试环境将720改为200即可  账期切换时间*/  
INSERT INTO `rtm_param_info` VALUES ('ECITIC', 'NAT', 'ALINAT', 'TRANSTIME', '720');
INSERT INTO `rtm_param_info` VALUES ('ECITIC', 'SHARE', 'ALISHARE', 'TRANSTIME', '720');

INSERT INTO `rtm_src_info`  VALUES ('ECITIC', 'NAT', '', '', '', NULL);
INSERT INTO `rtm_src_info`  VALUES ('ECITIC', 'SHARE', '', '', '', NULL);

INSERT INTO `rtm_src_record` VALUES ('ALINAT', '1', 'source_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALINAT', '2', 'service_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALINAT', '3', 'instance_id', '0', '1', 'String', '1', null);
INSERT INTO `rtm_src_record` VALUES ('ALINAT', '4', 'region_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALINAT', '5', 'usage_amount', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALINAT', '6', 'start_time', '0', '1', 'String', '1', null);
INSERT INTO `rtm_src_record` VALUES ('ALINAT', '7', 'record_type', '0', '1', 'String', '1', null);
 
INSERT INTO `rtm_src_record` VALUES ('ALISHARE', '1', 'source_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALISHARE', '2', 'service_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALISHARE', '3', 'instance_id', '0', '1', 'String', '1', null);
INSERT INTO `rtm_src_record` VALUES ('ALISHARE', '4', 'region_id', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALISHARE', '5', 'usage_amount', '0', '1', 'String', '0', null);
INSERT INTO `rtm_src_record` VALUES ('ALISHARE', '6', 'start_time', '0', '1', 'String', '1', null);
INSERT INTO `rtm_src_record` VALUES ('ALISHARE', '7', 'record_type', '0', '1', 'String', '1', null);  
-------bmc数据库
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) 
VALUES ('ECITIC', 'NAT', 'ALINAT', '1', '0', '业务标识', 'service_id', 'STRING', '业务标识', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'NAT', 'ALINAT', '1', '1', '服务实例', 'instance_id', 'STRING', '服务实例', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'NAT', 'ALINAT', '1', '2', '区域', 'region_id', 'STRING', '区域', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'NAT', 'ALINAT', '1', '3', '使用量', 'usage_amount', 'STRING', '使用量', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) 
VALUES ('ECITIC', 'NAT', 'ALINAT', '1', '4', '开始时间', 'start_time', 'STRING', '开始时间', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'NAT', 'ALINAT', '1', '5', '报文类型', 'record_type', 'STRING', '报文类型', NULL, NULL); 
 
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) 
VALUES ('ECITIC', 'SHARE', 'ALISHARE', '1', '0', '业务标识', 'service_id', 'STRING', '业务标识', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'SHARE', 'ALISHARE', '1', '1', '服务实例', 'instance_id', 'STRING', '服务实例', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'SHARE', 'ALISHARE', '1', '2', '区域', 'region_id', 'STRING', '区域', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'SHARE', 'ALISHARE', '1', '3', '使用量', 'usage_amount', 'STRING', '使用量', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`) 
VALUES ('ECITIC', 'SHARE', 'ALISHARE', '1', '4', '开始时间', 'start_time', 'STRING', '开始时间', NULL, NULL);
INSERT INTO `bmc_record_fmt` (`tenant_id`, `service_type`, `source`, `format_type`, `field_serial`, `field_name`, `field_code`, `data_type`, `comments`, `nullable`, `is_sn`)
 VALUES ('ECITIC', 'SHARE', 'ALISHARE', '1', '5', '报文类型', 'record_type', 'STRING', '报文类型', NULL, NULL); 
  
 
 
INSERT INTO `pub_service_route` (`route_type`, `route_params`, `route_class`, `comments`) 
VALUES ('BMC', 'ECITIC,NAT,ALINAT', 'com.ai.baas.bmc.srv.flow.adapt.EciticRuleProcessor', NULL);

INSERT INTO `pub_service_route` (`route_type`, `route_params`, `route_class`, `comments`) 
VALUES ('BMC', 'ECITIC,SHARE,ALISHARE', 'com.ai.baas.bmc.srv.flow.adapt.EciticRuleProcessor', NULL);


 --18 的值需要改到表最大
INSERT INTO `bmc_output_info` (`info_code`, `tenant_id`, `service_type`, `source`, `table_prefix`, `table_postfix`, `output_type`, `output_name`, `key_seq`, `seq_name`, `status`, `create_date`) VALUES
                                                   ('18', 'ECITIC', 'NAT', 'ALINAT', 'DR', NULL, '1', 'hbaseOutput', 'N', NULL, 'A', '2017-02-16 00:00:00');
 
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'cust_id', 'cust_id', 'N', '1', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'subs_id', 'subs_id', 'N', '2', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'acct_id', 'acct_id', 'N', '3', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'service_id', 'service_id', 'N', '4', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'instance_id', 'instance_id', 'N', '5', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'usage_amount', 'usage_amount', 'N', '6', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'start_time', 'start_time', 'N', '7', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'region_id', 'region_id', 'N', '8', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'record_type', 'record_type', 'N', '9', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'tenant_id', 'tenant_id', 'N', '10', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'service_type', 'service_type', 'N', '11', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'source', 'source', 'N', '12', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'bsn', 'bsn', 'N', '13', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'sn', 'sn', 'N', '14', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'account_period', 'account_period', 'N', '15', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'fee1', 'fee1', 'N', '16', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'subject1', 'subject1', 'N', '17', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'fee2', 'fee2', 'N', '18', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'subject2', 'subject2', 'N', '19', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'fee3', 'fee3', 'N', '20', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'subject3', 'subject3', 'N', '21', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('18', 'product_id', 'product_id', 'N', '22', 'A');



INSERT INTO `bmc_output_info` (`info_code`, `tenant_id`, `service_type`, `source`, `table_prefix`, `table_postfix`, `output_type`, `output_name`, `key_seq`, `seq_name`, `status`, `create_date`) VALUES
                                                   ('19', 'ECITIC', 'SHARE', 'ALISHARE', 'DR', NULL, '1', 'hbaseOutput', 'N', NULL, 'A', '2017-02-16 00:00:00');
 
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'cust_id', 'cust_id', 'N', '1', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'subs_id', 'subs_id', 'N', '2', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'acct_id', 'acct_id', 'N', '3', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'service_id', 'service_id', 'N', '4', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'instance_id', 'instance_id', 'N', '5', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'usage_amount', 'usage_amount', 'N', '6', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'start_time', 'start_time', 'N', '7', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'region_id', 'region_id', 'N', '8', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'record_type', 'record_type', 'N', '9', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'tenant_id', 'tenant_id', 'N', '10', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'service_type', 'service_type', 'N', '11', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'source', 'source', 'N', '12', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'bsn', 'bsn', 'N', '13', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'sn', 'sn', 'N', '14', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'account_period', 'account_period', 'N', '15', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'fee1', 'fee1', 'N', '16', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'subject1', 'subject1', 'N', '17', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'fee2', 'fee2', 'N', '18', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'subject2', 'subject2', 'N', '19', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'fee3', 'fee3', 'N', '20', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'subject3', 'subject3', 'N', '21', 'A');
INSERT INTO `bmc_output_detail` (`info_code`, `column_name`, `param_name`, `is_key`, `display_order`, `status`) VALUES ('19', 'product_id', 'product_id', 'N', '22', 'A');




--新服务service_id  NAT,SHARE,MBS,DESIGN

insert into `pm_basedata_code`(`id`,`tenant_id`,`param_type`,`param_code`,`param_name`,`price_type`,`default_value`,`parent_code`,`subject_type`,`billing_type`,`is_free`,`can_buy_period`,`comments`) values (132,'ECITIC','PRODUCT_CATALOG','58f6f22318795506e40112fc','NatGateway','NAT',null,'1','','usage;','0',null,null);
insert into `pm_basedata_code`(`id`,`tenant_id`,`param_type`,`param_code`,`param_name`,`price_type`,`default_value`,`parent_code`,`subject_type`,`billing_type`,`is_free`,`can_buy_period`,`comments`) values (133,'ECITIC','PRODUCT_CATALOG','58f86433434fa917ce66a178','BandwidthPackage','SHARE',null,'1','','usage;','0',null,null);
insert into `pm_basedata_code`(`id`,`tenant_id`,`param_type`,`param_code`,`param_name`,`price_type`,`default_value`,`parent_code`,`subject_type`,`billing_type`,`is_free`,`can_buy_period`,`comments`) values (134,'ECITIC','PRODUCT_CATALOG','201704242009327278','citicbankMBS','MBS',null,'1','','package;','0',null,null);
insert into `pm_basedata_code`(`id`,`tenant_id`,`param_type`,`param_code`,`param_name`,`price_type`,`default_value`,`parent_code`,`subject_type`,`billing_type`,`is_free`,`can_buy_period`,`comments`) values (135,'ECITIC','PRODUCT_CATALOG','f8b176f3-8191-489b-9abb-ea3ced517964','bangdesign','DESIGN',null,'1','','package;','0',null,null);






commit;
 