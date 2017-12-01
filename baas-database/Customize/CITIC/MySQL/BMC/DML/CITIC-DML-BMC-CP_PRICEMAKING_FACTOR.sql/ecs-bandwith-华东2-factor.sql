delete from cp_pricemaking_factor where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' 
and price_product_id like '%0504';
/* xxx0504 */
insert into cp_pricemaking_factor(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-BANDWITH', '10504', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-BANDWITH', '10504', 'InternetChargeType', 'PayByTraffic'),

('ECITIC', 'ECS-BANDWITH', '20504', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-BANDWITH', '20504', 'InternetChargeType', 'PayByBandwidth'),
('ECITIC', 'ECS-BANDWITH', '20504', 'InstanceChargeType', 'PostPaid'),

('ECITIC', 'ECS-BANDWITH', '30504', 'RegionId', 'cn-shanghai'),
('ECITIC', 'ECS-BANDWITH', '30504', 'InternetChargeType', 'PayByBandwidth'),
('ECITIC', 'ECS-BANDWITH', '30504', 'InstanceChargeType', 'PrePaid');



