delete from cp_pricemaking_factor where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' 
and price_product_id like '%0704';
/* xxx0704 */
insert into cp_pricemaking_factor(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ECS-BANDWITH', '10704', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-BANDWITH', '10704', 'InternetChargeType', 'PayByTraffic'),

('ECITIC', 'ECS-BANDWITH', '20704', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-BANDWITH', '20704', 'InternetChargeType', 'PayByBandwidth'),
('ECITIC', 'ECS-BANDWITH', '20704', 'InstanceChargeType', 'PostPaid'),

('ECITIC', 'ECS-BANDWITH', '30704', 'RegionId', 'cn-hongkong'),
('ECITIC', 'ECS-BANDWITH', '30704', 'InternetChargeType', 'PayByBandwidth'),
('ECITIC', 'ECS-BANDWITH', '30704', 'InstanceChargeType', 'PrePaid');
