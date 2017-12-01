1.编译打包
gradle build -x test
2.生成镜像
docker build -t baas-res-deposit-client:v1.0 .
3. 运行镜像
docker run -d --name=aiopt-baas-res-deposit-client --net=host -p 14111:8080 
-e "SDK_MODE=1" 
-e "ZK_ADDRESS=10.1.130.84:39181" 
-e "CCS_NAME=aiopt-baas-res-deposit-client" 
-e "DUBBO_REGISTRY_ADDR=10.1.228.222:19181" 
-e "DUBBO_PROTOCOL=dubbo" 
-e "DUBBO_REGISTRY_FILE=./runner-balance-center-dubbo-registry.cache" 
baas-res-deposit-client:v1.0

4.  10.27版本数据库变动
    DROP TABLE IF EXISTS `fail_msg_log`;
    CREATE TABLE `fail_msg_log` (
      `ID` int(11) NOT NULL,
      `SYSTEM_ID` varchar(32) COLLATE utf8_bin NOT NULL,
      `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL,
      `MSG` varchar(1000) COLLATE utf8_bin NOT NULL,
      `ERROR_MSG` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
      `TYPE` varchar(32) COLLATE utf8_bin NOT NULL,
      `STATUS` varchar(32) COLLATE utf8_bin NOT NULL,
      `DATE` varchar(32) COLLATE utf8_bin DEFAULT NULL,
      PRIMARY KEY (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

    INSERT INTO `sys_sequences` VALUES ('RES_DUP_LOG$ID$SEQ', '1', '1', '0', '10');
    INSERT INTO `sys_sequences` (`sequence_name`, `start_by`, `increment_by`, `last_number`, `jvm_step_by`)
    VALUES('FAIL_MSG_LOG$ID$SEQ', 1, 1, 0, 10);

5.  共享缓存所需表及对应索引列
    bl_subs_comm subs_id:tenant_id
    bl_subscomm_ext product_id:subs_product_id:ext_name
    bl_userinfo_ext ext_name:subs_Id
    cp_price_detail price_code:charge_type
    cp_package_info detail_code:service_type
    cp_price_info price_code:tenant_id
    bl_subs_comm subs_id:tenant_id:product_id:subs_product_id