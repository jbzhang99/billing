delete from cp_pricemaking_factor where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'KVS' and price_product_id like '%0106';
/* xxx0106 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'KVS', '10106', 'RegionId', 'cn-beijing'),
('ECITIC', 'KVS', '10106', 'Capacity', '1'),
('ECITIC', 'KVS', '20106', 'RegionId', 'cn-beijing'),
('ECITIC', 'KVS', '20106', 'Capacity', '2'),
('ECITIC', 'KVS', '30106', 'RegionId', 'cn-beijing'),
('ECITIC', 'KVS', '30106', 'Capacity', '4'),
('ECITIC', 'KVS', '40106', 'RegionId', 'cn-beijing'),
('ECITIC', 'KVS', '40106', 'Capacity', '8'),
('ECITIC', 'KVS', '50106', 'RegionId', 'cn-beijing'),
('ECITIC', 'KVS', '50106', 'Capacity', '16'),
('ECITIC', 'KVS', '60106', 'RegionId', 'cn-beijing'),
('ECITIC', 'KVS', '60106', 'Capacity', '32'),
('ECITIC', 'KVS', '70106', 'RegionId', 'cn-beijing'),
('ECITIC', 'KVS', '70106', 'Capacity', '64'),
('ECITIC', 'KVS', '80106', 'RegionId', 'cn-beijing'),
('ECITIC', 'KVS', '80106', 'Capacity', '128');