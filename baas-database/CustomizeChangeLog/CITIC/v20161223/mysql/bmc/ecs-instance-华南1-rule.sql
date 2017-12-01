delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-INSTANCE' and price_product_id like '%0601';
/*北京*/
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-INSTANCE',  '10601', 'PER_HOUR', 'EXPR', '#{dis}*(187.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '20601', 'PER_HOUR', 'EXPR', '#{dis}*(292.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '30601', 'PER_HOUR', 'EXPR', '#{dis}*(502.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '40601', 'PER_HOUR', 'EXPR', '#{dis}*(290.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '50601', 'PER_HOUR', 'EXPR', '#{dis}*(395.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '60601', 'PER_HOUR', 'EXPR', '#{dis}*(605.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '70601', 'PER_HOUR', 'EXPR', '#{dis}*(1025.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '80601', 'PER_HOUR', 'EXPR', '#{dis}*(1231.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '90601', 'PER_HOUR', 'EXPR', '#{dis}*(601.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '100601', 'PER_HOUR', 'EXPR', '#{dis}*(811.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '110601', 'PER_HOUR', 'EXPR', '#{dis}*(1223.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '120601', 'PER_HOUR', 'EXPR', '#{dis}*(1643.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '130601', 'PER_HOUR', 'EXPR', '#{dis}*(2483.1)', '{"dis":"0.7"}', 'h', '/时'),
                                                                                            
('ECITIC', 'ECS-INSTANCE', '140601', 'PER_HOUR', 'EXPR', '#{dis}*(32.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '150601', 'PER_HOUR', 'EXPR', '#{dis}*(71)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '160601', 'PER_HOUR', 'EXPR', '#{dis}*(133.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '170601', 'PER_HOUR', 'EXPR', '#{dis}*(230.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '180601', 'PER_HOUR', 'EXPR', '#{dis}*(132.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '190601', 'PER_HOUR', 'EXPR', '#{dis}*(180.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '200601', 'PER_HOUR', 'EXPR', '#{dis}*(278.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '210601', 'PER_HOUR', 'EXPR', '#{dis}*(472.5)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '220601', 'PER_HOUR', 'EXPR', '#{dis}*(276.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '230601', 'PER_HOUR', 'EXPR', '#{dis}*(373.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '240601', 'PER_HOUR', 'EXPR', '#{dis}*(567.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '250601', 'PER_HOUR', 'EXPR', '#{dis}*(956.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '260601', 'PER_HOUR', 'EXPR', '#{dis}*(1147.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '270601', 'PER_HOUR', 'EXPR', '#{dis}*(563.9)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '280601', 'PER_HOUR', 'EXPR', '#{dis}*(758.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '290601', 'PER_HOUR', 'EXPR', '#{dis}*(1139.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '300601', 'PER_HOUR', 'EXPR', '#{dis}*(1528.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '310601', 'PER_HOUR', 'EXPR', '#{dis}*(2306.1)', '{"dis":"0.7"}', 'h', '/时'),
                                                                                            
('ECITIC', 'ECS-INSTANCE', '320601', 'PER_HOUR', 'EXPR', '#{dis}*(60.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '330601', 'PER_HOUR', 'EXPR', '#{dis}*(85.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '340601', 'PER_HOUR', 'EXPR', '#{dis}*(190.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '350601', 'PER_HOUR', 'EXPR', '#{dis}*(400.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '360601', 'PER_HOUR', 'EXPR', '#{dis}*(820.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '370601', 'PER_HOUR', 'EXPR', '#{dis}*(1660.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '380601', 'PER_HOUR', 'EXPR', '#{dis}*(3340.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '390601', 'PER_HOUR', 'EXPR', '#{dis}*(137.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '400601', 'PER_HOUR', 'EXPR', '#{dis}*(293.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '410601', 'PER_HOUR', 'EXPR', '#{dis}*(606.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '420601', 'PER_HOUR', 'EXPR', '#{dis}*(1232.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '430601', 'PER_HOUR', 'EXPR', '#{dis}*(2485)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '440601', 'PER_HOUR', 'EXPR', '#{dis}*(4989.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '450601', 'PER_HOUR', 'EXPR', '#{dis}*(247.9)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '460601', 'PER_HOUR', 'EXPR', '#{dis}*(515.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '470601', 'PER_HOUR', 'EXPR', '#{dis}*(1050)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '480601', 'PER_HOUR', 'EXPR', '#{dis}*(2119.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '490601', 'PER_HOUR', 'EXPR', '#{dis}*(4258.3)', '{"dis":"0.7"}', 'h', '/时');



