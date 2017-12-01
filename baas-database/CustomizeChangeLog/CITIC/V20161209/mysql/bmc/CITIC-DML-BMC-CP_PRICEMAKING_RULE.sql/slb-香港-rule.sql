/**/
delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-instance' and price_product_id like '%0710';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'slb-instance', '110710', 'PER_HOUR', 'CONST', '39', '', 'h', '/小时'),
('ECITIC', 'slb-instance', '210710', 'PER_HOUR', 'CONST', '0', '', 'h', '/小时'),
('ECITIC', 'slb-instance', '120710', 'PER_HOUR', 'CONST', '0', '', 'h', '');
/**/
delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-bandwith' and price_product_id like '%0711';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'slb-bandwith', '110711', 'PER_HOUR', 'CONST', '700', '', 'gb', '/GB'),
('ECITIC', 'slb-bandwith', '210711', 'PER_HOUR', 'STEP', '[{"start":"1", "end":"5", "value":"28"}, {"start":"6", "end":"9999999999", "value":"98"}]', 'Bandwidth', 'h', '/小时'),
('ECITIC', 'slb-bandwith', '120711', 'PER_HOUR', 'CONST', '0', '', 'h', '');
