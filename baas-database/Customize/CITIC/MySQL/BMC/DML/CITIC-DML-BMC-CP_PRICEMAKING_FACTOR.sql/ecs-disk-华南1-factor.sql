delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') 
  and (price_product_id like '%0602' or price_product_id like '%0603');
/* xxx0602 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10602', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-SYSTEM-DISK', '10602', 'SystemDisk.Category', 'cloud'),
('ECITIC', 'ECS-SYSTEM-DISK', '20602', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-SYSTEM-DISK', '20602', 'SystemDisk.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-SYSTEM-DISK', '30602', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-SYSTEM-DISK', '30602', 'SystemDisk.Category', 'cloud_ssd'),
('ECITIC', 'ECS-SYSTEM-DISK', '40602', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-SYSTEM-DISK', '40602', 'SystemDisk.Category', 'ephemeral_ssd');
/* xxx0603 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-DATA-DISK', '50603', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-DATA-DISK', '50603', 'DataDisk.n.Category', 'cloud'),
('ECITIC', 'ECS-DATA-DISK', '60603', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-DATA-DISK', '60603', 'DataDisk.n.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-DATA-DISK', '70603', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-DATA-DISK', '70603', 'DataDisk.n.Category', 'cloud_ssd'),
('ECITIC', 'ECS-DATA-DISK', '80603', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-DATA-DISK', '80603', 'DataDisk.n.Category', 'ephemeral_ssd');