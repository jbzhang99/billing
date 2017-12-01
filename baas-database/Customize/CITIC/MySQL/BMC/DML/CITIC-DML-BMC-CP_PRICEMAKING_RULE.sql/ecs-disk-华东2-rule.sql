delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') 
and (price_product_id like '%0502' or price_product_id like '%0503');

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10502', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*0+12)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '20502', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*0+14)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '30502', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*1+39)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '40502', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*1+31)', '{"dis":"1"}', 'h', '/时');
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-DATA-DISK', '50503', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*0)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '60503', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*0)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '70503', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '80503', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*1)', '{"dis":"1"}', 'h', '/时');