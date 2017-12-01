delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') 
  and (price_product_id like '%0402' or price_product_id like '%0403');

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10402', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*0.4+16.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '20402', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*0.7+27.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '30402', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*1.4+55.6)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '40402', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*1.1+44.4)', '{"dis":"0.7"}', 'h', '/时');
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-DATA-DISK', '50403', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*0.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '60403', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*0.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '70403', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*1.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '80403', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*1.1)', '{"dis":"0.7"}', 'h', '/时');