delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') 
and (price_product_id like '%0702' or price_product_id like '%0703');
/* xxx0702 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10702', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-SYSTEM-DISK', '10702', 'SystemDisk.Category', 'cloud'),
('ECITIC', 'ECS-SYSTEM-DISK', '20702', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-SYSTEM-DISK', '20702', 'SystemDisk.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-SYSTEM-DISK', '30702', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-SYSTEM-DISK', '30702', 'SystemDisk.Category', 'cloud_ssd'),
('ECITIC', 'ECS-SYSTEM-DISK', '40702', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-SYSTEM-DISK', '40702', 'SystemDisk.Category', 'ephemeral_ssd');
/* xxx0703 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-DATA-DISK', '50703', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-DATA-DISK', '50703', 'DataDisk.n.Category', 'cloud'),
('ECITIC', 'ECS-DATA-DISK', '60703', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-DATA-DISK', '60703', 'DataDisk.n.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-DATA-DISK', '70703', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-DATA-DISK', '70703', 'DataDisk.n.Category', 'cloud_ssd'),
('ECITIC', 'ECS-DATA-DISK', '80703', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-DATA-DISK', '80703', 'DataDisk.n.Category', 'ephemeral_ssd');