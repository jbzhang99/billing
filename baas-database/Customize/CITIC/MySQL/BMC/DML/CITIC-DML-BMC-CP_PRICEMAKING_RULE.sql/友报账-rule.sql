delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'a732806f-46c5-4386-9877-41c5687a5fbd' and price_product_id like '%0016';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbd', '10016', 'PER_HOUR', 'CONST', '0', '', 'n', '/定'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbd', '20016', 'PER_HOUR', 'CONST', '0', '', 'n', '/定'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbd', '30016', 'PER_HOUR', 'CONST', '0', '', 'n', '/定'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbd', '40016', 'PER_HOUR', 'CONST', '0', '', 'n', '/定');