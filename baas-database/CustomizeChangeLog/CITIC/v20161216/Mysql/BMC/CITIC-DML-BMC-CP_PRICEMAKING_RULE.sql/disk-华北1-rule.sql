delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '5836a0462fbaaf0aee0db7a2' and price_product_id like '%0412';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', '5836a0462fbaaf0aee0db7a2', '10412', 'PER_HOUR', 'EXPR', '#{Size}*0', '', 'h', '/月'),
('ECITIC', '5836a0462fbaaf0aee0db7a2', '20412', 'PER_HOUR', 'EXPR', '#{Size}*0', '', 'h', '/月'),
('ECITIC', '5836a0462fbaaf0aee0db7a2', '30412', 'PER_HOUR', 'EXPR', '#{Size}*1', '', 'h', '/月'),
('ECITIC', '5836a0462fbaaf0aee0db7a2', '40412', 'PER_HOUR', 'EXPR', '#{Size}*1', '', 'h', '/月');
