delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') and (price_product_id like '%0102' or price_product_id like '%0103');
/* xxx0102 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10102', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-SYSTEM-DISK', '10102', 'SystemDisk.Category', 'cloud'),
('ECITIC', 'ECS-SYSTEM-DISK', '20102', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-SYSTEM-DISK', '20102', 'SystemDisk.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-SYSTEM-DISK', '30102', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-SYSTEM-DISK', '30102', 'SystemDisk.Category', 'cloud_ssd'),
('ECITIC', 'ECS-SYSTEM-DISK', '40102', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-SYSTEM-DISK', '40102', 'SystemDisk.Category', 'ephemeral_ssd');
/* xxx0103 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-DATA-DISK', '50103', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-DATA-DISK', '50103', 'DataDisk.n.Category', 'cloud'),
('ECITIC', 'ECS-DATA-DISK', '60103', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-DATA-DISK', '60103', 'DataDisk.n.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-DATA-DISK', '70103', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-DATA-DISK', '70103', 'DataDisk.n.Category', 'cloud_ssd'),
('ECITIC', 'ECS-DATA-DISK', '80103', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-DATA-DISK', '80103', 'DataDisk.n.Category', 'ephemeral_ssd');