delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '97acb1b1-fbb3-4b4f-9c23-95acba084ac6' and price_product_id like '%0017';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', '97acb1b1-fbb3-4b4f-9c23-95acba084ac6', '10017', 'PER_HOUR', 'CONST', '0', '', 'n', '/定'),
('ECITIC', '97acb1b1-fbb3-4b4f-9c23-95acba084ac6', '20017', 'PER_HOUR', 'CONST', '5000000', '', 'n', '/定'),
('ECITIC', '97acb1b1-fbb3-4b4f-9c23-95acba084ac6', '30017', 'PER_HOUR', 'CONST', '10000000', '', 'n', '/定'),
('ECITIC', '97acb1b1-fbb3-4b4f-9c23-95acba084ac6', '40017', 'PER_HOUR', 'CONST', '8000000', '', 'n', '/定');