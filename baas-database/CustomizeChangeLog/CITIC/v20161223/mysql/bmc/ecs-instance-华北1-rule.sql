delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-INSTANCE' and price_product_id like '%0401';
/**/
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-INSTANCE',  '10401', 'PER_HOUR', 'EXPR', '#{dis}*(187.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '20401', 'PER_HOUR', 'EXPR', '#{dis}*(292.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '30401', 'PER_HOUR', 'EXPR', '#{dis}*(502.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '40401', 'PER_HOUR', 'EXPR', '#{dis}*(290.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '50401', 'PER_HOUR', 'EXPR', '#{dis}*(395.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '60401', 'PER_HOUR', 'EXPR', '#{dis}*(605.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '70401', 'PER_HOUR', 'EXPR', '#{dis}*(1025.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '80401', 'PER_HOUR', 'EXPR', '#{dis}*(1231.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '90401', 'PER_HOUR', 'EXPR', '#{dis}*(601.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '100401', 'PER_HOUR', 'EXPR', '#{dis}*(811.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '110401', 'PER_HOUR', 'EXPR', '#{dis}*(1223.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '120401', 'PER_HOUR', 'EXPR', '#{dis}*(1643.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '130401', 'PER_HOUR', 'EXPR', '#{dis}*(2483.1)', '{"dis":"0.7"}', 'h', '/时'),
                                                                                            
('ECITIC', 'ECS-INSTANCE', '140401', 'PER_HOUR', 'EXPR', '#{dis}*(32.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '150401', 'PER_HOUR', 'EXPR', '#{dis}*(71)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '160401', 'PER_HOUR', 'EXPR', '#{dis}*(133.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '170401', 'PER_HOUR', 'EXPR', '#{dis}*(230.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '180401', 'PER_HOUR', 'EXPR', '#{dis}*(132.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '190401', 'PER_HOUR', 'EXPR', '#{dis}*(180.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '200401', 'PER_HOUR', 'EXPR', '#{dis}*(278.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '210401', 'PER_HOUR', 'EXPR', '#{dis}*(472.5)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '220401', 'PER_HOUR', 'EXPR', '#{dis}*(276.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '230401', 'PER_HOUR', 'EXPR', '#{dis}*(373.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '240401', 'PER_HOUR', 'EXPR', '#{dis}*(567.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '250401', 'PER_HOUR', 'EXPR', '#{dis}*(956.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '260401', 'PER_HOUR', 'EXPR', '#{dis}*(1147.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '270401', 'PER_HOUR', 'EXPR', '#{dis}*(563.9)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '280401', 'PER_HOUR', 'EXPR', '#{dis}*(758.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '290401', 'PER_HOUR', 'EXPR', '#{dis}*(1139.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '300401', 'PER_HOUR', 'EXPR', '#{dis}*(1528.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '310401', 'PER_HOUR', 'EXPR', '#{dis}*(2306.1)', '{"dis":"0.7"}', 'h', '/时'),
                                                                                            
('ECITIC', 'ECS-INSTANCE', '320401', 'PER_HOUR', 'EXPR', '#{dis}*(60.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '330401', 'PER_HOUR', 'EXPR', '#{dis}*(85.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '340401', 'PER_HOUR', 'EXPR', '#{dis}*(190.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '350401', 'PER_HOUR', 'EXPR', '#{dis}*(400.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '360401', 'PER_HOUR', 'EXPR', '#{dis}*(820.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '370401', 'PER_HOUR', 'EXPR', '#{dis}*(1660.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '380401', 'PER_HOUR', 'EXPR', '#{dis}*(3340.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '390401', 'PER_HOUR', 'EXPR', '#{dis}*(137.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '400401', 'PER_HOUR', 'EXPR', '#{dis}*(293.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '410401', 'PER_HOUR', 'EXPR', '#{dis}*(606.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '420401', 'PER_HOUR', 'EXPR', '#{dis}*(1232.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '430401', 'PER_HOUR', 'EXPR', '#{dis}*(2485)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '440401', 'PER_HOUR', 'EXPR', '#{dis}*(4989.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '450401', 'PER_HOUR', 'EXPR', '#{dis}*(247.9)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '460401', 'PER_HOUR', 'EXPR', '#{dis}*(515.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '470401', 'PER_HOUR', 'EXPR', '#{dis}*(1050)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '480401', 'PER_HOUR', 'EXPR', '#{dis}*(2119.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '490401', 'PER_HOUR', 'EXPR', '#{dis}*(4258.3)', '{"dis":"0.7"}', 'h', '/时'),

/*实例系列3*/
('ECITIC', 'ECS-INSTANCE',  '3330401', 'PER_HOUR', 'CONST', '1281', '', 'h', '/时'),
                            
('ECITIC', 'ECS-INSTANCE',  '8330401', 'PER_HOUR', 'CONST', '770', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '9330401', 'PER_HOUR', 'CONST', '392', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '10330401', 'PER_HOUR', 'CONST', '1519', '', 'h', '/时'),

('ECITIC', 'ECS-INSTANCE', '14330401', 'PER_HOUR', 'CONST', '511', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '15330401', 'PER_HOUR', 'CONST', '259', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '16330401', 'PER_HOUR', 'CONST', '1008', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '17330401', 'PER_HOUR', 'CONST', '210', '', 'h', '/时');


