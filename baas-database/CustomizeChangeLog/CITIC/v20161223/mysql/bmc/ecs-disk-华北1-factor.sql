delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') 
  and (price_product_id like '%0402' or price_product_id like '%0403');
/* xxx0402 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10402', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-SYSTEM-DISK', '10402', 'SystemDisk.Category', 'cloud'),
('ECITIC', 'ECS-SYSTEM-DISK', '20402', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-SYSTEM-DISK', '20402', 'SystemDisk.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-SYSTEM-DISK', '30402', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-SYSTEM-DISK', '30402', 'SystemDisk.Category', 'cloud_ssd'),
('ECITIC', 'ECS-SYSTEM-DISK', '40402', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-SYSTEM-DISK', '40402', 'SystemDisk.Category', 'ephemeral_ssd');
/* xxx0403 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-DATA-DISK', '50403', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-DATA-DISK', '50403', 'DataDisk.n.Category', 'cloud'),
('ECITIC', 'ECS-DATA-DISK', '60403', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-DATA-DISK', '60403', 'DataDisk.n.Category', 'cloud_efficiency'),
('ECITIC', 'ECS-DATA-DISK', '70403', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-DATA-DISK', '70403', 'DataDisk.n.Category', 'cloud_ssd'),
('ECITIC', 'ECS-DATA-DISK', '80403', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-DATA-DISK', '80403', 'DataDisk.n.Category', 'ephemeral_ssd');