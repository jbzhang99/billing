delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' 
and price_product_id like '%0504';

insert into cp_pricemaking_rule(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-BANDWITH', '10504', 'PER_UNIT1G', 'EXPR', '#{dis}*(560)', '{"dis":"1"}', 'gb', '/GB'),
('ECITIC', 'ECS-BANDWITH', '20504', 'PER_HOUR', 'STEP', '[{"start":"1", "end":"5", "value":"44"}, {"start":"6", "end":"9999999999", "value":"175"}]', 'InternetMaxBandwidthOut', 'h', '/时'),
('ECITIC', 'ECS-BANDWITH', '30504', 'PER_MONTH', 'BRACKET', '[{"start":"1", "end":"1", "value":"16100"}, {"start":"2", "end":"2", "value":"32200"}, {"start":"3", "end":"3", "value":"49700"},{"start":"4", "end":"4", "value":"67200"},{"start":"5", "end":"5", "value":"87500"} {"start":"6", "end":"9999999999", "value":"87500+(#{InternetMaxBandwidthOut}-5)*56000"}]', 'InternetMaxBandwidthOut', 'h', '/时');

