delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-INSTANCE' and price_product_id like '%0201';
/*北京*/
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-INSTANCE',  '10201', 'PER_HOUR', 'EXPR', '#{dis}*(187.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '20201', 'PER_HOUR', 'EXPR', '#{dis}*(292.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '30201', 'PER_HOUR', 'EXPR', '#{dis}*(502.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '40201', 'PER_HOUR', 'EXPR', '#{dis}*(290.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '50201', 'PER_HOUR', 'EXPR', '#{dis}*(395.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '60201', 'PER_HOUR', 'EXPR', '#{dis}*(605.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '70201', 'PER_HOUR', 'EXPR', '#{dis}*(1025.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '80201', 'PER_HOUR', 'EXPR', '#{dis}*(1231.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '90201', 'PER_HOUR', 'EXPR', '#{dis}*(601.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '100201', 'PER_HOUR', 'EXPR', '#{dis}*(811.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '110201', 'PER_HOUR', 'EXPR', '#{dis}*(1223.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '120201', 'PER_HOUR', 'EXPR', '#{dis}*(1643.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '130201', 'PER_HOUR', 'EXPR', '#{dis}*(2483.1)', '{"dis":"0.7"}', 'h', '/时'),
                                                                                            
('ECITIC', 'ECS-INSTANCE', '140201', 'PER_HOUR', 'EXPR', '#{dis}*(32.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '150201', 'PER_HOUR', 'EXPR', '#{dis}*(71)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '160201', 'PER_HOUR', 'EXPR', '#{dis}*(133.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '170201', 'PER_HOUR', 'EXPR', '#{dis}*(230.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '180201', 'PER_HOUR', 'EXPR', '#{dis}*(132.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '190201', 'PER_HOUR', 'EXPR', '#{dis}*(180.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '200201', 'PER_HOUR', 'EXPR', '#{dis}*(278.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '210201', 'PER_HOUR', 'EXPR', '#{dis}*(472.5)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '220201', 'PER_HOUR', 'EXPR', '#{dis}*(276.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '230201', 'PER_HOUR', 'EXPR', '#{dis}*(373.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '240201', 'PER_HOUR', 'EXPR', '#{dis}*(567.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '250201', 'PER_HOUR', 'EXPR', '#{dis}*(956.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '260201', 'PER_HOUR', 'EXPR', '#{dis}*(1147.2)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '270201', 'PER_HOUR', 'EXPR', '#{dis}*(563.9)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '280201', 'PER_HOUR', 'EXPR', '#{dis}*(758.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '290201', 'PER_HOUR', 'EXPR', '#{dis}*(1139.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '300201', 'PER_HOUR', 'EXPR', '#{dis}*(1528.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '310201', 'PER_HOUR', 'EXPR', '#{dis}*(2306.1)', '{"dis":"0.7"}', 'h', '/时'),
                                                                                            
('ECITIC', 'ECS-INSTANCE', '320201', 'PER_HOUR', 'EXPR', '#{dis}*(60.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '330201', 'PER_HOUR', 'EXPR', '#{dis}*(85.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '340201', 'PER_HOUR', 'EXPR', '#{dis}*(190.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '350201', 'PER_HOUR', 'EXPR', '#{dis}*(400.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '360201', 'PER_HOUR', 'EXPR', '#{dis}*(820.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '370201', 'PER_HOUR', 'EXPR', '#{dis}*(1660.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '380201', 'PER_HOUR', 'EXPR', '#{dis}*(3340.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '390201', 'PER_HOUR', 'EXPR', '#{dis}*(137.1)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '400201', 'PER_HOUR', 'EXPR', '#{dis}*(293.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '410201', 'PER_HOUR', 'EXPR', '#{dis}*(606.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '420201', 'PER_HOUR', 'EXPR', '#{dis}*(1232.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '430201', 'PER_HOUR', 'EXPR', '#{dis}*(2485)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '440201', 'PER_HOUR', 'EXPR', '#{dis}*(4989.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '450201', 'PER_HOUR', 'EXPR', '#{dis}*(247.9)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '460201', 'PER_HOUR', 'EXPR', '#{dis}*(515.3)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '470201', 'PER_HOUR', 'EXPR', '#{dis}*(1050)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '480201', 'PER_HOUR', 'EXPR', '#{dis}*(2119.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '490201', 'PER_HOUR', 'EXPR', '#{dis}*(4258.3)', '{"dis":"0.7"}', 'h', '/时');



