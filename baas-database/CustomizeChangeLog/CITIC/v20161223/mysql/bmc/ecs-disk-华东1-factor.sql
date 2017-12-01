delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') 
and (price_product_id like '%0202' or price_product_id like '%0203');
/* xxx0202 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10202', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-SYSTEM-DISK', '10202', 'SystemDisk.Category', 'cloud'),
('ECITIC', 'ECS-SYSTEM-DISK', '20202', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-SYSTEM-DISK', '20202', 'SystemDisk.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-SYSTEM-DISK', '30202', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-SYSTEM-DISK', '30202', 'SystemDisk.Category', 'cloud_ssd'),
('ECITIC', 'ECS-SYSTEM-DISK', '40202', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-SYSTEM-DISK', '40202', 'SystemDisk.Category', 'ephemeral_ssd');
/* xxx0203 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-DATA-DISK', '50203', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-DATA-DISK', '50203', 'DataDisk.n.Category', 'cloud'),
('ECITIC', 'ECS-DATA-DISK', '60203', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-DATA-DISK', '60203', 'DataDisk.n.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-DATA-DISK', '70203', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-DATA-DISK', '70203', 'DataDisk.n.Category', 'cloud_ssd'),
('ECITIC', 'ECS-DATA-DISK', '80203', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-DATA-DISK', '80203', 'DataDisk.n.Category', 'ephemeral_ssd');