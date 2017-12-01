delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE in ('ECS-SYSTEM-DISK','ECS-DATA-DISK') 
and (price_product_id like '%0702' or price_product_id like '%0703');

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-SYSTEM-DISK', '10702', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*0+15)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '20702', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*1+23)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '30702', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*1+0)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-SYSTEM-DISK', '40702', 'PER_HOUR', 'EXPR', '#{dis}*((#{SystemDisk.Size}-40)*0+31)', '{"dis":"1"}', 'h', '/时');
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ECS-DATA-DISK', '50703', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*0)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '60703', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '70703', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*1)', '{"dis":"1"}', 'h', '/时'),
('ECITIC', 'ECS-DATA-DISK', '80703', 'PER_HOUR', 'EXPR', '#{dis}*(#{DataDisk.n.Size}*0)', '{"dis":"1"}', 'h', '/时');