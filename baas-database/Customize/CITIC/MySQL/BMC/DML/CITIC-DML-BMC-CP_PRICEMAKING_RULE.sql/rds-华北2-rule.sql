delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'RDS' and price_product_id like '%0105';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'RDS',  '110105', 'PER_HOUR', 'EXPR', '#{dis}*(330+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS',  '210105', 'PER_HOUR', 'EXPR', '#{dis}*(670+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS',  '310105', 'PER_HOUR', 'EXPR', '#{dis}*(1350+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS',  '410105', 'PER_HOUR', 'EXPR', '#{dis}*(2590+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS',  '510105', 'PER_HOUR', 'EXPR', '#{dis}*(2690+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS',  '610105', 'PER_HOUR', 'EXPR', '#{dis}*(5180+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS',  '710105', 'PER_HOUR', 'EXPR', '#{dis}*(5390+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS',  '810105', 'PER_HOUR', 'EXPR', '#{dis}*(10350+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS',  '910105', 'PER_HOUR', 'EXPR', '#{dis}*(20710+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '1010105', 'PER_HOUR', 'EXPR', '#{dis}*(33130+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '1110105', 'PER_HOUR', 'EXPR', '#{dis}*(40590+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '1210105', 'PER_HOUR', 'EXPR', '#{dis}*(62080+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
                     
('ECITIC', 'RDS', '2110105', 'PER_HOUR', 'EXPR', '#{dis}*(990+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '2210105', 'PER_HOUR', 'EXPR', '#{dis}*(1980+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '2310105', 'PER_HOUR', 'EXPR', '#{dis}*(3960+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '2410105', 'PER_HOUR', 'EXPR', '#{dis}*(4140+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '2510105', 'PER_HOUR', 'EXPR', '#{dis}*(6570+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '2610105', 'PER_HOUR', 'EXPR', '#{dis}*(13130+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '2710105', 'PER_HOUR', 'EXPR', '#{dis}*(26270+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '2810105', 'PER_HOUR', 'EXPR', '#{dis}*(57800+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '2910105', 'PER_HOUR', 'EXPR', '#{dis}*(105080+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
                     
('ECITIC', 'RDS', '3110105', 'PER_HOUR', 'EXPR', '#{dis}*(1260+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '3210105', 'PER_HOUR', 'EXPR', '#{dis}*(2530+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '3310105', 'PER_HOUR', 'EXPR', '#{dis}*(5020+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '3410105', 'PER_HOUR', 'EXPR', '#{dis}*(5060+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '3510105', 'PER_HOUR', 'EXPR', '#{dis}*(9570+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '3610105', 'PER_HOUR', 'EXPR', '#{dis}*(9800+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '3710105', 'PER_HOUR', 'EXPR', '#{dis}*(19140+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '3810105', 'PER_HOUR', 'EXPR', '#{dis}*(38270+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '3910105', 'PER_HOUR', 'EXPR', '#{dis}*(61630+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '4010105', 'PER_HOUR', 'EXPR', '#{dis}*(75610+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'RDS', '4110105', 'PER_HOUR', 'EXPR', '#{dis}*(87500+#{DBInstanceStorage}*1.7)', '{"dis":"0.7"}', 'h', '/时'),

('ECITIC', 'RDS', '4210105', 'PER_HOUR', 'EXPR', '200+#{DBInstanceStorage}*1.7', '', 'h', '/时'),
('ECITIC', 'RDS', '4310105', 'PER_HOUR', 'EXPR', '375+#{DBInstanceStorage}*1.7', '', 'h', '/时'),
('ECITIC', 'RDS', '4410105', 'PER_HOUR', 'EXPR', '750+#{DBInstanceStorage}*1.7', '', 'h', '/时'),
('ECITIC', 'RDS', '4510105', 'PER_HOUR', 'EXPR', '1500+#{DBInstanceStorage}*1.7', '', 'h', '/时'),
('ECITIC', 'RDS', '4610105', 'PER_HOUR', 'EXPR', '3000+#{DBInstanceStorage}*1.7', '', 'h', '/时'),
('ECITIC', 'RDS', '4710105', 'PER_HOUR', 'EXPR', '5662.5+#{DBInstanceStorage}*1.7', '', 'h', '/时'),
('ECITIC', 'RDS', '4810105', 'PER_HOUR', 'EXPR', '10200+#{DBInstanceStorage}*1.7', '', 'h', '/时'),
('ECITIC', 'RDS', '4910105', 'PER_HOUR', 'EXPR', '20400+#{DBInstanceStorage}*1.7', '', 'h', '/时'),
('ECITIC', 'RDS', '5010105', 'PER_HOUR', 'EXPR', '37250+#{DBInstanceStorage}*1.7', '', 'h', '/时');

commit;