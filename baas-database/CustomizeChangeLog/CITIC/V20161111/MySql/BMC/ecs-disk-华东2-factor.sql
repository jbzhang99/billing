delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') 
and (price_product_id like '%0502' or price_product_id like '%0503');
/* xxx0502 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10502', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-SYSTEM-DISK', '10502', 'SystemDisk.Category', 'cloud'),
('ECITIC', 'ECS-SYSTEM-DISK', '20502', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-SYSTEM-DISK', '20502', 'SystemDisk.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-SYSTEM-DISK', '30502', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-SYSTEM-DISK', '30502', 'SystemDisk.Category', 'cloud_ssd'),
('ECITIC', 'ECS-SYSTEM-DISK', '40502', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-SYSTEM-DISK', '40502', 'SystemDisk.Category', 'ephemeral_ssd');
/* xxx0503 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-DATA-DISK', '50503', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-DATA-DISK', '50503', 'DataDisk.n.Category', 'cloud'),
('ECITIC', 'ECS-DATA-DISK', '60503', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-DATA-DISK', '60503', 'DataDisk.n.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-DATA-DISK', '70503', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-DATA-DISK', '70503', 'DataDisk.n.Category', 'cloud_ssd'),
('ECITIC', 'ECS-DATA-DISK', '80503', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-DATA-DISK', '80503', 'DataDisk.n.Category', 'ephemeral_ssd');