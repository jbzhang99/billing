/*service_id:58b3cc2618e1fb442d8f4077*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'eip-instance' and price_product_id like '%0122';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'eip-instance',  '110122', 'RegionId', 'cn-beijing'),
('ECITIC', 'eip-instance',  '110122', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'eip-instance',  '210122', 'RegionId', 'cn-beijing'),
('ECITIC', 'eip-instance',  '210122', 'InternetChargeType', 'paybybandwidth');

delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'eip-bandwith' and price_product_id like '%0123';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'eip-bandwith',  '110123', 'RegionId', 'cn-beijing'),
('ECITIC', 'eip-bandwith',  '110123', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'eip-bandwith',  '210123', 'RegionId', 'cn-beijing'),
('ECITIC', 'eip-bandwith',  '210123', 'InternetChargeType', 'paybybandwidth');
