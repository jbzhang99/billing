delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' 
and price_product_id like '%0504';

insert into cp_pricemaking_rule(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-BANDWITH', '10504', 'PER_UNIT1G', 'EXPR', '#{dis}*(560)', '{"dis":"1"}', 'gb', '/GB'),
('ECITIC', 'ECS-BANDWITH', '20504', 'PER_HOUR', 'STEP', '[{"start":"1", "end":"5", "value":"44"}, {"start":"6", "end":"9999999999", "value":"175"}]', 'InternetMaxBandwidthOut', 'h', '/时')/*,
('ECITIC', 'ECS-BANDWITH', '30504', 'PER_MONTH', 'EXPR', '16100', '', 'h', '/时');
('ECITIC', 'ECS-BANDWITH', '40504', 'PER_MONTH', 'EXPR', '32200', '', 'h', '/时');
('ECITIC', 'ECS-BANDWITH', '50504', 'PER_MONTH', 'EXPR', '49700', '', 'h', '/时');
('ECITIC', 'ECS-BANDWITH', '60504', 'PER_MONTH', 'EXPR', '67200', '', 'h', '/时');
('ECITIC', 'ECS-BANDWITH', '70504', 'PER_MONTH', 'EXPR', '87500', '', 'h', '/时')*/;
