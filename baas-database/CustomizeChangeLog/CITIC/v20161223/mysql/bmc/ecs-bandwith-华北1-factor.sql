delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' and price_product_id like '%0404';
/* xxx0404 */
insert into cp_pricemaking_factor(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-BANDWITH', '10404', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-BANDWITH', '10404', 'InternetChargeType', 'PayByTraffic'),
('ECITIC', 'ECS-BANDWITH', '20404', 'RegionId', 'cn-qingdao'),
('ECITIC', 'ECS-BANDWITH', '20404', 'InternetChargeType', 'PayByBandwidth');