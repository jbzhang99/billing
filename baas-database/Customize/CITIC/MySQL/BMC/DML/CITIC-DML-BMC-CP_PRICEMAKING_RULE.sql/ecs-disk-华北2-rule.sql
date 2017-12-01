delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') and (price_product_id like '%0102' or price_product_id like '%0103');

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10102', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*0.42+16.8)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '20102', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*0.7+28)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '30102', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*1.4+56)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '40102', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*3.3+132)', '{"dis":"0.7"}', 'h', '/时');
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-DATA-DISK', '50103', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*0.42)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '60103', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*0.7)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '70103', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*1.4)', '{"dis":"0.7"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '80103', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*3.3)', '{"dis":"0.7"}', 'h', '/时');