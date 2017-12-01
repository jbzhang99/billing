delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313' and price_product_id like '%0008';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313', '10008', 'PER_HOUR', 'CONST', '0', '', 'n', '/定'),
('ECITIC', '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313', '20008', 'PER_HOUR', 'CONST', '0', '', 'n', '/定'),
('ECITIC', '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313', '30008', 'PER_HOUR', 'CONST', '0', '', 'n', '/定'),
('ECITIC', '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313', '40008', 'PER_HOUR', 'CONST', '0', '', 'n', '/定');