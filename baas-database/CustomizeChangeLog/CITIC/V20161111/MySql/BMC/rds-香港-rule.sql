delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'RDS' and price_product_id like '%0705';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'RDS',  '110705', 'PER_HOUR', 'EXPR', '#{dis}*(202+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '210705', 'PER_HOUR', 'EXPR', '#{dis}*(408+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '310705', 'PER_HOUR', 'EXPR', '#{dis}*(565+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '410705', 'PER_HOUR', 'EXPR', '#{dis}*(817+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '510705', 'PER_HOUR', 'EXPR', '#{dis}*(1633+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '610705', 'PER_HOUR', 'EXPR', '#{dis}*(3141+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '710705', 'PER_HOUR', 'EXPR', '#{dis}*(3268+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '810705', 'PER_HOUR', 'EXPR', '#{dis}*(6282+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS',  '910705', 'PER_HOUR', 'EXPR', '#{dis}*(12564+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '1010705', 'PER_HOUR', 'EXPR', '#{dis}*(21583+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '1110705', 'PER_HOUR', 'EXPR', '#{dis}*(24623+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '1210705', 'PER_HOUR', 'EXPR', '#{dis}*(40561+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
                     /*
('ECITIC', 'RDS', '2110705', 'PER_HOUR', 'EXPR', '#{dis}*(0+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2210705', 'PER_HOUR', 'EXPR', '#{dis}*(0+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2310705', 'PER_HOUR', 'EXPR', '#{dis}*(0+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2410705', 'PER_HOUR', 'EXPR', '#{dis}*(2071+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2510705', 'PER_HOUR', 'EXPR', '#{dis}*(3286+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2610705', 'PER_HOUR', 'EXPR', '#{dis}*(6563+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2710705', 'PER_HOUR', 'EXPR', '#{dis}*(13135+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2810705', 'PER_HOUR', 'EXPR', '#{dis}*(28904+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '2910705', 'PER_HOUR', 'EXPR', '#{dis}*(52539+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
                     */
('ECITIC', 'RDS', '3110705', 'PER_HOUR', 'EXPR', '#{dis}*(823+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3210705', 'PER_HOUR', 'EXPR', '#{dis}*(1828+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3310705', 'PER_HOUR', 'EXPR', '#{dis}*(3289+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3410705', 'PER_HOUR', 'EXPR', '#{dis}*(3311+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3510705', 'PER_HOUR', 'EXPR', '#{dis}*(6777+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3610705', 'PER_HOUR', 'EXPR', '#{dis}*(6919+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3710705', 'PER_HOUR', 'EXPR', '#{dis}*(12583+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3810705', 'PER_HOUR', 'EXPR', '#{dis}*(23219+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '3910705', 'PER_HOUR', 'EXPR', '#{dis}*(40262+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '4010705', 'PER_HOUR', 'EXPR', '#{dis}*(45871+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'RDS', '4110705', 'PER_HOUR', 'EXPR', '#{dis}*(57167+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时');

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'RDS',  '120705', 'PER_HOUR', 'EXPR', '#{dis}*(202+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                 
('ECITIC', 'RDS',  '220705', 'PER_HOUR', 'EXPR', '#{dis}*(408+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                 
('ECITIC', 'RDS',  '320705', 'PER_HOUR', 'EXPR', '#{dis}*(565+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                 
('ECITIC', 'RDS',  '420705', 'PER_HOUR', 'EXPR', '#{dis}*(817+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                 
('ECITIC', 'RDS',  '520705', 'PER_HOUR', 'EXPR', '#{dis}*(1633+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS',  '620705', 'PER_HOUR', 'EXPR', '#{dis}*(3141+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS',  '720705', 'PER_HOUR', 'EXPR', '#{dis}*(3268+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS',  '820705', 'PER_HOUR', 'EXPR', '#{dis}*(6282+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS',  '920705', 'PER_HOUR', 'EXPR', '#{dis}*(12564+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '1020705', 'PER_HOUR', 'EXPR', '#{dis}*(21583+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '1120705', 'PER_HOUR', 'EXPR', '#{dis}*(24623+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '1220705', 'PER_HOUR', 'EXPR', '#{dis}*(40561+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
                                      /*                                                                                            
('ECITIC', 'RDS', '2120705', 'PER_HOUR', 'EXPR', '#{dis}*(467+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS', '2220705', 'PER_HOUR', 'EXPR', '#{dis}*(933+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),                
('ECITIC', 'RDS', '2320705', 'PER_HOUR', 'EXPR', '#{dis}*(1857+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '2420705', 'PER_HOUR', 'EXPR', '#{dis}*(2071+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '2520705', 'PER_HOUR', 'EXPR', '#{dis}*(3286+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '2620705', 'PER_HOUR', 'EXPR', '#{dis}*(6563+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),               
('ECITIC', 'RDS', '2720705', 'PER_HOUR', 'EXPR', '#{dis}*(13135+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '2820705', 'PER_HOUR', 'EXPR', '#{dis}*(28904+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '2920705', 'PER_HOUR', 'EXPR', '#{dis}*(52539+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
                                          */                                                                                        
('ECITIC', 'RDS', '3120705', 'PER_HOUR', 'EXPR', '#{dis}*(823+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),              
('ECITIC', 'RDS', '3220705', 'PER_HOUR', 'EXPR', '#{dis}*(1828+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),             
('ECITIC', 'RDS', '3320705', 'PER_HOUR', 'EXPR', '#{dis}*(3289+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),             
('ECITIC', 'RDS', '3420705', 'PER_HOUR', 'EXPR', '#{dis}*(3311+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),             
('ECITIC', 'RDS', '3520705', 'PER_HOUR', 'EXPR', '#{dis}*(6777+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),             
('ECITIC', 'RDS', '3620705', 'PER_HOUR', 'EXPR', '#{dis}*(6919+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),             
('ECITIC', 'RDS', '3720705', 'PER_HOUR', 'EXPR', '#{dis}*(12583+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),            
('ECITIC', 'RDS', '3820705', 'PER_HOUR', 'EXPR', '#{dis}*(23219+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),            
('ECITIC', 'RDS', '3920705', 'PER_HOUR', 'EXPR', '#{dis}*(40262+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),            
('ECITIC', 'RDS', '4020705', 'PER_HOUR', 'EXPR', '#{dis}*(45871+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时'),            
('ECITIC', 'RDS', '4120705', 'PER_HOUR', 'EXPR', '#{dis}*(57167+#{DBInstanceStorage}*1)', '{"dis":"1"}', 'h', '/时');            