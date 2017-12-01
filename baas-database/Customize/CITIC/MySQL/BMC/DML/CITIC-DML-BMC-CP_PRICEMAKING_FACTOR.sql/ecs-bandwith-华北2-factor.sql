delete from cp_pricemaking_factor where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' and price_product_id like '%0104';
/* xxx0104 */
insert into cp_pricemaking_factor(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-BANDWITH', '10104', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-BANDWITH', '10104', 'InternetChargeType', 'PayByTraffic'),
('ECITIC', 'ECS-BANDWITH', '20104', 'RegionId', 'cn-beijing'),
('ECITIC', 'ECS-BANDWITH', '20104', 'InternetChargeType', 'PayByBandwidth');