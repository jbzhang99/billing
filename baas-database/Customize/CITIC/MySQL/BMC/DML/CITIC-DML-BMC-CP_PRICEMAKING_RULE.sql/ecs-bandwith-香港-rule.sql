delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' 
and price_product_id like '%0704';

insert into cp_pricemaking_rule(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-BANDWITH', '10704', 'PER_UNIT1G', 'EXPR', '#{dis}*(700)', '{"dis":"1"}', 'gb', '/GB'),
('ECITIC', 'ECS-BANDWITH', '20704', 'PER_HOUR', 'STEP', '[{"start":"1", "end":"5", "value":"56"}, {"start":"6", "end":"9999999999", "value":"175"}]', 'InternetMaxBandwidthOut', 'h', '/时'),
('ECITIC', 'ECS-BANDWITH', '30704', 'PER_MONTH', 'BRACKET', '[{"start":"1", "end":"1", "value":"21000"}, {"start":"2", "end":"2", "value":"42000"}, {"start":"3", "end":"3", "value":"63000"},{"start":"4", "end":"4", "value":"84000"},{"start":"5", "end":"5", "value":"105000"} {"start":"6", "end":"9999999999", "value":"105000+(#{InternetMaxBandwidthOut}-5)*70000"}]', 'InternetMaxBandwidthOut', 'h', '/时');
