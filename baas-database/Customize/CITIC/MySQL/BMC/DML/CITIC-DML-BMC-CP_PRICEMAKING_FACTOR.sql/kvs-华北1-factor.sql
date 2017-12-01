delete from cp_pricemaking_factor where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'KVS' and price_product_id like '%0406';
/* xxx0406 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'KVS',  '90406', 'RegionId', 'cn-qingdao'),
('ECITIC', 'KVS',  '90406', 'Capacity', '1'),
('ECITIC', 'KVS', '100406', 'RegionId', 'cn-qingdao'),
('ECITIC', 'KVS', '100406', 'Capacity', '2'),
('ECITIC', 'KVS', '110406', 'RegionId', 'cn-qingdao'),
('ECITIC', 'KVS', '110406', 'Capacity', '4'),
('ECITIC', 'KVS', '120406', 'RegionId', 'cn-qingdao'),
('ECITIC', 'KVS', '120406', 'Capacity', '8'),
('ECITIC', 'KVS', '130406', 'RegionId', 'cn-qingdao'),
('ECITIC', 'KVS', '130406', 'Capacity', '16'),
('ECITIC', 'KVS', '140406', 'RegionId', 'cn-qingdao'),
('ECITIC', 'KVS', '140406', 'Capacity', '32'),
('ECITIC', 'KVS', '150406', 'RegionId', 'cn-qingdao'),
('ECITIC', 'KVS', '150406', 'Capacity', '64');