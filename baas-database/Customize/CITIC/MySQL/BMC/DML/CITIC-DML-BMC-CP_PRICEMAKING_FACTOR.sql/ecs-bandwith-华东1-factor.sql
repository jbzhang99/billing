delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' and price_product_id like '%0204';
/* xxx0204 */
insert into cp_pricemaking_factor(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-BANDWITH', '10204', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-BANDWITH', '10204', 'InternetChargeType', 'PayByTraffic'),
('ECITIC', 'ECS-BANDWITH', '20204', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'ECS-BANDWITH', '20204', 'InternetChargeType', 'PayByBandwidth');