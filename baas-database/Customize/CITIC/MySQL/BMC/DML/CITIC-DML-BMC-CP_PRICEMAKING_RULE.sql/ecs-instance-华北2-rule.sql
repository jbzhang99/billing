delete from cp_pricemaking_rule where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ECS-INSTANCE' and price_product_id like '%0101';
/*北京*/
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-INSTANCE',  '10101', 'PER_HOUR', 'EXPR', '#{dis}*(920)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '20101', 'PER_HOUR', 'EXPR', '#{dis}*(1610)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '30101', 'PER_HOUR', 'EXPR', '#{dis}*(2990)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '40101', 'PER_HOUR', 'EXPR', '#{dis}*(1180)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '50101', 'PER_HOUR', 'EXPR', '#{dis}*(1870)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '60101', 'PER_HOUR', 'EXPR', '#{dis}*(3250)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '70101', 'PER_HOUR', 'EXPR', '#{dis}*(6020)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '80101', 'PER_HOUR', 'EXPR', '#{dis}*(6540)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '90101', 'PER_HOUR', 'EXPR', '#{dis}*(2390)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '100101', 'PER_HOUR', 'EXPR', '#{dis}*(3770)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '110101', 'PER_HOUR', 'EXPR', '#{dis}*(4810)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '120101', 'PER_HOUR', 'EXPR', '#{dis}*(7570)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '130101', 'PER_HOUR', 'EXPR', '#{dis}*(13100)', '{"dis":"0.7"}', 'h', '/时'),
                                              
('ECITIC', 'ECS-INSTANCE', '140101', 'PER_HOUR', 'EXPR', '#{dis}*(260)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '150101', 'PER_HOUR', 'EXPR', '#{dis}*(420)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '160101', 'PER_HOUR', 'EXPR', '#{dis}*(740)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '170101', 'PER_HOUR', 'EXPR', '#{dis}*(1380)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '180101', 'PER_HOUR', 'EXPR', '#{dis}*(540)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '190101', 'PER_HOUR', 'EXPR', '#{dis}*(860)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '200101', 'PER_HOUR', 'EXPR', '#{dis}*(1500)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '210101', 'PER_HOUR', 'EXPR', '#{dis}*(2780)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '220101', 'PER_HOUR', 'EXPR', '#{dis}*(1100)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '230101', 'PER_HOUR', 'EXPR', '#{dis}*(1740)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '240101', 'PER_HOUR', 'EXPR', '#{dis}*(3020)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '250101', 'PER_HOUR', 'EXPR', '#{dis}*(5580)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '260101', 'PER_HOUR', 'EXPR', '#{dis}*(6060)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '270101', 'PER_HOUR', 'EXPR', '#{dis}*(2220)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '280101', 'PER_HOUR', 'EXPR', '#{dis}*(3500)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '290101', 'PER_HOUR', 'EXPR', '#{dis}*(4460)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '300101', 'PER_HOUR', 'EXPR', '#{dis}*(7020)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '310101', 'PER_HOUR', 'EXPR', '#{dis}*(12140)', '{"dis":"0.7"}', 'h', '/时'),
                                              
('ECITIC', 'ECS-INSTANCE', '320101', 'PER_HOUR', 'EXPR', '#{dis}*(310)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '330101', 'PER_HOUR', 'EXPR', '#{dis}*(420)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '340101', 'PER_HOUR', 'EXPR', '#{dis}*(870)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '350101', 'PER_HOUR', 'EXPR', '#{dis}*(1770)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '360101', 'PER_HOUR', 'EXPR', '#{dis}*(3570)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '370101', 'PER_HOUR', 'EXPR', '#{dis}*(7170)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '380101', 'PER_HOUR', 'EXPR', '#{dis}*(14370)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '390101', 'PER_HOUR', 'EXPR', '#{dis}*(640)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '400101', 'PER_HOUR', 'EXPR', '#{dis}*(1310)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '410101', 'PER_HOUR', 'EXPR', '#{dis}*(2650)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '420101', 'PER_HOUR', 'EXPR', '#{dis}*(5340)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '430101', 'PER_HOUR', 'EXPR', '#{dis}*(10700)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '440101', 'PER_HOUR', 'EXPR', '#{dis}*(21440)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '450101', 'PER_HOUR', 'EXPR', '#{dis}*(1120)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '460101', 'PER_HOUR', 'EXPR', '#{dis}*(2260)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '470101', 'PER_HOUR', 'EXPR', '#{dis}*(4550)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '480101', 'PER_HOUR', 'EXPR', '#{dis}*(9140)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '490101', 'PER_HOUR', 'EXPR', '#{dis}*(18300)', '{"dis":"0.7"}', 'h', '/时');



