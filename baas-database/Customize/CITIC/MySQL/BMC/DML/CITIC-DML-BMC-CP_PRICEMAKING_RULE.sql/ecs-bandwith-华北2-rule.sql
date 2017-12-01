delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-BANDWITH' and price_product_id like '%0104';

insert into cp_pricemaking_rule(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-BANDWITH', '10104', 'PER_UNIT1G', 'EXPR', '#{dis}*(720)', '{"dis":"0.7"}', 'gb', '/GB'),
('ECITIC', 'ECS-BANDWITH', '20104', 'PER_HOUR', 'STEP', '[{"start":"1", "end":"5", "value":"43.75"}, {"start":"6", "end":"9999999999", "value":"175"}]', 'InternetMaxBandwidthOut', 'h', '/时');
