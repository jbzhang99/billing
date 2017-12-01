delete from cp_pricemaking_factor where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'KVS' and price_product_id like '%0506';
/* xxx0506 */
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'KVS', '10506', 'RegionId', 'cn-shanghai'),
('ECITIC', 'KVS', '10506', 'Capacity', '1'),
('ECITIC', 'KVS', '20506', 'RegionId', 'cn-shanghai'),
('ECITIC', 'KVS', '20506', 'Capacity', '2'),
('ECITIC', 'KVS', '30506', 'RegionId', 'cn-shanghai'),
('ECITIC', 'KVS', '30506', 'Capacity', '4'),
('ECITIC', 'KVS', '40506', 'RegionId', 'cn-shanghai'),
('ECITIC', 'KVS', '40506', 'Capacity', '8'),
('ECITIC', 'KVS', '50506', 'RegionId', 'cn-shanghai'),
('ECITIC', 'KVS', '50506', 'Capacity', '16'),
('ECITIC', 'KVS', '60506', 'RegionId', 'cn-shanghai'),
('ECITIC', 'KVS', '60506', 'Capacity', '32');