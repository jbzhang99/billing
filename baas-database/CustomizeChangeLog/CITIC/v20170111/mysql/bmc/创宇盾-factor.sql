/* xxx0015*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '43a3921d-7479-43d0-a970-031699dcf9b6' and price_product_id like '%0015';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', '43a3921d-7479-43d0-a970-031699dcf9b6',  '10015', 'package', 'vip_yunaq_signed'),
('ECITIC', '43a3921d-7479-43d0-a970-031699dcf9b6',  '20015', 'package', 'vip_yunaq_upper'),
('ECITIC', '43a3921d-7479-43d0-a970-031699dcf9b6',  '30015', 'package', 'vip_yunaq_pro'),
('ECITIC', '43a3921d-7479-43d0-a970-031699dcf9b6',  '40015', 'package', 'vip_yunaq_finance');
