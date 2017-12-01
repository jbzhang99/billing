delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'KVS' and price_product_id like '%0506';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'KVS', '10506', 'PER_HOUR', 'EXPR', '#{dis}*(119)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'KVS', '20506', 'PER_HOUR', 'EXPR', '#{dis}*(236)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'KVS', '30506', 'PER_HOUR', 'EXPR', '#{dis}*(473)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'KVS', '40506', 'PER_HOUR', 'EXPR', '#{dis}*(945)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'KVS', '50506', 'PER_HOUR', 'EXPR', '#{dis}*(1890)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'KVS', '60506', 'PER_HOUR', 'EXPR', '#{dis}*(3780)', '{"dis":"1"}', 'h', '/时');