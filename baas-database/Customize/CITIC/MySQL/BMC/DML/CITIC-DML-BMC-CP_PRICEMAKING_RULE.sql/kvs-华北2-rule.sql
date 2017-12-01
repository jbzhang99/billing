delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'KVS' and price_product_id like '%0106';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'KVS', '10106', 'PER_HOUR', 'EXPR', '#{dis}*(250)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'KVS', '20106', 'PER_HOUR', 'EXPR', '#{dis}*(500)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'KVS', '30106', 'PER_HOUR', 'EXPR', '#{dis}*(1000)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'KVS', '40106', 'PER_HOUR', 'EXPR', '#{dis}*(2000)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'KVS', '50106', 'PER_HOUR', 'EXPR', '#{dis}*(4000)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'KVS', '60106', 'PER_HOUR', 'EXPR', '#{dis}*(8000)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'KVS', '70106', 'PER_HOUR', 'EXPR', '#{dis}*(16000)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'KVS', '80106', 'PER_HOUR', 'EXPR', '#{dis}*(38400)', '{"dis":"0.7"}', 'h', '/时');