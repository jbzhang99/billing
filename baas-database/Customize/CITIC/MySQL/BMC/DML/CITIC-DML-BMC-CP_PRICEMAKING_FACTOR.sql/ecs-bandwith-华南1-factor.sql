delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' and price_product_id like '%0604';
/* xxx0604 */
insert into cp_pricemaking_factor(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-BANDWITH', '10604', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-BANDWITH', '10604', 'InternetChargeType', 'PayByTraffic'),
('ECITIC', 'ECS-BANDWITH', '20604', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'ECS-BANDWITH', '20604', 'InternetChargeType', 'PayByBandwidth');