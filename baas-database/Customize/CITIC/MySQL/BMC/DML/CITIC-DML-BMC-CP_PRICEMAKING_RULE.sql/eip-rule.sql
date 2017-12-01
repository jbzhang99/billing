delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'eip-instance' and price_product_id like '%0122';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'eip-instance', '110122', 'PER_HOUR', 'CONST', '20', '', 'h', '元/小时'),
('ECITIC', 'eip-instance', '210122', 'PER_HOUR', 'CONST', '480', '', 'h', '元/天');

delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'eip-bandwith' and price_product_id like '%0123';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'eip-bandwith', '110123', 'PER_HOUR', 'CONST', '800', '', 'gb', '元/GB'),
('ECITIC', 'eip-bandwith', '210123', 'PER_HOUR', 'STEP', '[{"start":"1", "end":"5", "value":"960"}, {"start":"6", "end":"200", "value":"3360"}]', 'Bandwidth', 'h', '元/天');
