delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'RDS' and price_product_id like '%0505';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'RDS',  '110505', 'PER_HOUR', 'EXPR', '#{dis}*(140+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '210505', 'PER_HOUR', 'EXPR', '#{dis}*(283+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '310505', 'PER_HOUR', 'EXPR', '#{dis}*(565+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '410505', 'PER_HOUR', 'EXPR', '#{dis}*(1087+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '510505', 'PER_HOUR', 'EXPR', '#{dis}*(1131+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '610505', 'PER_HOUR', 'EXPR', '#{dis}*(2175+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '710505', 'PER_HOUR', 'EXPR', '#{dis}*(2262+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '810505', 'PER_HOUR', 'EXPR', '#{dis}*(4349+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '910505', 'PER_HOUR', 'EXPR', '#{dis}*(8698+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '1010505', 'PER_HOUR', 'EXPR', '#{dis}*(13913+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '1110505', 'PER_HOUR', 'EXPR', '#{dis}*(17047+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '1210505', 'PER_HOUR', 'EXPR', '#{dis}*(26075+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
                     
('ECITIC', 'RDS', '2110505', 'PER_HOUR', 'EXPR', '#{dis}*(467+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2210505', 'PER_HOUR', 'EXPR', '#{dis}*(933+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2310505', 'PER_HOUR', 'EXPR', '#{dis}*(1857+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2410505', 'PER_HOUR', 'EXPR', '#{dis}*(2071+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2510505', 'PER_HOUR', 'EXPR', '#{dis}*(3286+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2610505', 'PER_HOUR', 'EXPR', '#{dis}*(6563+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2710505', 'PER_HOUR', 'EXPR', '#{dis}*(13135+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2810505', 'PER_HOUR', 'EXPR', '#{dis}*(28904+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2910505', 'PER_HOUR', 'EXPR', '#{dis}*(52539+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
                     
('ECITIC', 'RDS', '3110505', 'PER_HOUR', 'EXPR', '#{dis}*(588+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3210505', 'PER_HOUR', 'EXPR', '#{dis}*(1181+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3310505', 'PER_HOUR', 'EXPR', '#{dis}*(2343+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3410505', 'PER_HOUR', 'EXPR', '#{dis}*(2361+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3510505', 'PER_HOUR', 'EXPR', '#{dis}*(4465+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3610505', 'PER_HOUR', 'EXPR', '#{dis}*(4574+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3710505', 'PER_HOUR', 'EXPR', '#{dis}*(8931+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3810505', 'PER_HOUR', 'EXPR', '#{dis}*(17861+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3910505', 'PER_HOUR', 'EXPR', '#{dis}*(28758+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '4010505', 'PER_HOUR', 'EXPR', '#{dis}*(35286+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '4110505', 'PER_HOUR', 'EXPR', '#{dis}*(40833+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时');

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'RDS',  '120505', 'PER_HOUR', 'EXPR', '#{dis}*(140+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS',  '220505', 'PER_HOUR', 'EXPR', '#{dis}*(283+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS',  '320505', 'PER_HOUR', 'EXPR', '#{dis}*(565+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS',  '420505', 'PER_HOUR', 'EXPR', '#{dis}*(1087+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS',  '520505', 'PER_HOUR', 'EXPR', '#{dis}*(1131+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS',  '620505', 'PER_HOUR', 'EXPR', '#{dis}*(2175+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS',  '720505', 'PER_HOUR', 'EXPR', '#{dis}*(2262+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS',  '820505', 'PER_HOUR', 'EXPR', '#{dis}*(4349+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS',  '920505', 'PER_HOUR', 'EXPR', '#{dis}*(8698+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '1020505', 'PER_HOUR', 'EXPR', '#{dis}*(13913+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '1120505', 'PER_HOUR', 'EXPR', '#{dis}*(17047+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '1220505', 'PER_HOUR', 'EXPR', '#{dis}*(26075+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
                                                                                                                                  
('ECITIC', 'RDS', '2120505', 'PER_HOUR', 'EXPR', '#{dis}*(467+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS', '2220505', 'PER_HOUR', 'EXPR', '#{dis}*(933+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS', '2320505', 'PER_HOUR', 'EXPR', '#{dis}*(1857+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '2420505', 'PER_HOUR', 'EXPR', '#{dis}*(2071+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '2520505', 'PER_HOUR', 'EXPR', '#{dis}*(3286+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '2620505', 'PER_HOUR', 'EXPR', '#{dis}*(6563+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '2720505', 'PER_HOUR', 'EXPR', '#{dis}*(13135+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '2820505', 'PER_HOUR', 'EXPR', '#{dis}*(28904+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '2920505', 'PER_HOUR', 'EXPR', '#{dis}*(52539+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
                                                                                                                                  
('ECITIC', 'RDS', '3120505', 'PER_HOUR', 'EXPR', '#{dis}*(588+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS', '3220505', 'PER_HOUR', 'EXPR', '#{dis}*(1181+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '3320505', 'PER_HOUR', 'EXPR', '#{dis}*(2343+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '3420505', 'PER_HOUR', 'EXPR', '#{dis}*(2361+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '3520505', 'PER_HOUR', 'EXPR', '#{dis}*(4465+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '3620505', 'PER_HOUR', 'EXPR', '#{dis}*(4574+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '3720505', 'PER_HOUR', 'EXPR', '#{dis}*(8931+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '3820505', 'PER_HOUR', 'EXPR', '#{dis}*(17861+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '3920505', 'PER_HOUR', 'EXPR', '#{dis}*(28758+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '4020505', 'PER_HOUR', 'EXPR', '#{dis}*(35286+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '4120505', 'PER_HOUR', 'EXPR', '#{dis}*(40833+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时');              