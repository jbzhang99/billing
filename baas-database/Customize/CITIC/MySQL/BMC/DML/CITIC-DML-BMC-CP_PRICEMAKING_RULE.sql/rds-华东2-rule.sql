delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'RDS' and price_product_id like '%0505';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'RDS',  '110505', 'PER_HOUR', 'EXPR', '140+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS',  '210505', 'PER_HOUR', 'EXPR', '283+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS',  '310505', 'PER_HOUR', 'EXPR', '565+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS',  '410505', 'PER_HOUR', 'EXPR', '1087+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS',  '510505', 'PER_HOUR', 'EXPR', '1131+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS',  '610505', 'PER_HOUR', 'EXPR', '2175+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS',  '710505', 'PER_HOUR', 'EXPR', '2262+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS',  '810505', 'PER_HOUR', 'EXPR', '4349+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS',  '910505', 'PER_HOUR', 'EXPR', '8698+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '1010505', 'PER_HOUR', 'EXPR', '13913+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '1110505', 'PER_HOUR', 'EXPR', '17047+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '1210505', 'PER_HOUR', 'EXPR', '26075+#{DBInstanceStorage}*1', '', 'h', '/时'),
                     
('ECITIC', 'RDS', '2110505', 'PER_HOUR', 'EXPR', '467+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '2210505', 'PER_HOUR', 'EXPR', '933+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '2310505', 'PER_HOUR', 'EXPR', '1857+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '2410505', 'PER_HOUR', 'EXPR', '2071+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '2510505', 'PER_HOUR', 'EXPR', '3286+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '2610505', 'PER_HOUR', 'EXPR', '6563+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '2710505', 'PER_HOUR', 'EXPR', '13135+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '2810505', 'PER_HOUR', 'EXPR', '28904+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '2910505', 'PER_HOUR', 'EXPR', '52539+#{DBInstanceStorage}*1', '', 'h', '/时'),
                     
('ECITIC', 'RDS', '3110505', 'PER_HOUR', 'EXPR', '588+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '3210505', 'PER_HOUR', 'EXPR', '1181+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '3310505', 'PER_HOUR', 'EXPR', '2343+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '3410505', 'PER_HOUR', 'EXPR', '2361+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '3510505', 'PER_HOUR', 'EXPR', '4465+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '3610505', 'PER_HOUR', 'EXPR', '4574+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '3710505', 'PER_HOUR', 'EXPR', '8931+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '3810505', 'PER_HOUR', 'EXPR', '17861+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '3910505', 'PER_HOUR', 'EXPR', '28758+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4010505', 'PER_HOUR', 'EXPR', '35286+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4110505', 'PER_HOUR', 'EXPR', '40833+#{DBInstanceStorage}*1', '', 'h', '/时'),

('ECITIC', 'RDS', '4210505', 'PER_HOUR', 'EXPR', '200+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4310505', 'PER_HOUR', 'EXPR', '375+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4410505', 'PER_HOUR', 'EXPR', '750+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4510505', 'PER_HOUR', 'EXPR', '1500+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4610505', 'PER_HOUR', 'EXPR', '3000+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4710505', 'PER_HOUR', 'EXPR', '5662.5+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4810505', 'PER_HOUR', 'EXPR', '10200+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '4910505', 'PER_HOUR', 'EXPR', '20400+#{DBInstanceStorage}*1', '', 'h', '/时'),
('ECITIC', 'RDS', '5010505', 'PER_HOUR', 'EXPR', '37250+#{DBInstanceStorage}*1', '', 'h', '/时');
       