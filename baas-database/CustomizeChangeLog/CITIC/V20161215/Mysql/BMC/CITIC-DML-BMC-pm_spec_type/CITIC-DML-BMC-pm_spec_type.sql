INSERT INTO `pm_spec_type` (`ID`, `TENANT_ID`, `CATEGORY_ID`, `SPEC_TYPE_NAME`, `SPEC_TYPE_ID`)
VALUES
(1, 'ECITIC', '202', '实例','CLOUD_HOST_13'),
(2, 'ECITIC', '202', '内存','CLOUD_HOST_01'),
(3, 'ECITIC', '202', 'CPU','CLOUD_HOST_02'),
(4, 'ECITIC', '202', '带宽-按使用量计费','CLOUD_HOST_03'),
(5, 'ECITIC', '202', '带宽-按固定带宽计费','CLOUD_HOST_04'),
(6, 'ECITIC', '202', '存储-系统盘-SSD云盘','CLOUD_HOST_05'),
(7, 'ECITIC', '202', '存储-系统盘-高效云盘','CLOUD_HOST_06'),
(8, 'ECITIC', '202', '存储-系统盘-普通云盘','CLOUD_HOST_07'),
(9, 'ECITIC', '202', '存储-系统盘-本地SSD磁盘','CLOUD_HOST_08'),
(10, 'ECITIC', '202', '存储-数据盘-SSD云盘','CLOUD_HOST_09'),
(11, 'ECITIC', '202', '存储-数据盘-高效云盘','CLOUD_HOST_10'),
(12, 'ECITIC', '202', '存储-数据盘-普通云盘','CLOUD_HOST_11'),
(13, 'ECITIC', '202', '存储-数据盘-本地SSD盘','CLOUD_HOST_12');


INSERT INTO `pm_spec_type` (`ID`, `TENANT_ID`, `CATEGORY_ID`, `SPEC_TYPE_NAME`, `SPEC_TYPE_ID`)
VALUES
(14, 'ECITIC', '212', '内存','CLOUD_DB_RDS_01'),
(15, 'ECITIC', '212', 'CPU','CLOUD_DB_RDS_02'),
(16, 'ECITIC', '212', '公网流量','CLOUD_DB_RDS_03'),
(17, 'ECITIC', '213', '存储容量','CLOUD_DB_REDIS_01'),
(18, 'ECITIC', '214', '存储费用','OSS_01'),
(19, 'ECITIC', '214', '流量费用-外网流出','OSS_02'),
(20, 'ECITIC', '214', '流量费用-CDN回源流出','OSS_03'),
(21, 'ECITIC', '214', '流量费用-跨区域复制流量','OSS_04'),
(22, 'ECITIC', '214', '请求费用','OSS_05'),
(23, 'ECITIC', '214', '数据处理费用','OSS_06'),
(24, 'ECITIC', '215', 'API调用','ONS_01'),
(25, 'ECITIC', '215', 'Topic资源占用','ONS_02'),
(26, 'ECITIC', '216', '实例租用费','LOAD_BALANCE_01'),
(27, 'ECITIC', '216', '公网流量费','LOAD_BALANCE_02'),
(28, 'ECITIC', '216', '公网带宽费','LOAD_BALANCE_03'),
(29, 'ECITIC', '217', '路由器','VPC_01'),
(31, 'ECITIC', '218', '配置费用','EIP_01'),
(32, 'ECITIC', '218', '流量费用','EIP_02');
