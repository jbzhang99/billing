/*smartcloud*/
delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'smartcloud' and price_product_id like '%07';
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'smartcloud', '107', 'PER_HOUR', 'CONST', '36644000', '', 'h', '/年'),
('ECITIC', 'smartcloud', '207', 'PER_HOUR', 'CONST', '73134000', '', 'h', '/年'),
('ECITIC', 'smartcloud', '307', 'PER_HOUR', 'CONST', '106581000', '', 'h', '/年');