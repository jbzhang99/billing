delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '43a3921d-7479-43d0-a970-031699dcf9b6' and price_product_id like '%0015';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', '43a3921d-7479-43d0-a970-031699dcf9b6', '10015', 'PER_HOUR', 'EXPR', '#{service_add_01}*2000000+#{service_add_02}*30000000+#{service_add_03}*5000000+#{service_add_04}*2000000+#{service_add_05}*2000000+#{service_add_07}*50000000+#{service_add_08}*2000000+#{service_add_09}*2000000+#{service_add_10}*30000000+35000000', '', 'y', '/年'),
('ECITIC', '43a3921d-7479-43d0-a970-031699dcf9b6', '20015', 'PER_HOUR', 'EXPR', '#{service_add_01}*2000000+#{service_add_02}*30000000+#{service_add_03}*5000000+#{service_add_04}*2000000+#{service_add_05}*2000000+#{service_add_07}*50000000+#{service_add_08}*2000000+#{service_add_09}*2000000+#{service_add_10}*30000000+52000000', '', 'y', '/年'),
('ECITIC', '43a3921d-7479-43d0-a970-031699dcf9b6', '30015', 'PER_HOUR', 'EXPR', '#{service_add_01}*2000000+#{service_add_02}*30000000+#{service_add_03}*5000000+#{service_add_04}*2000000+#{service_add_05}*2000000+#{service_add_07}*50000000+#{service_add_08}*2000000+#{service_add_09}*2000000+#{service_add_10}*30000000+198000000', '', 'y', '/年'),
('ECITIC', '43a3921d-7479-43d0-a970-031699dcf9b6', '40015', 'PER_HOUR', 'EXPR', '#{service_add_01}*2000000+#{service_add_02}*30000000+#{service_add_03}*5000000+#{service_add_04}*2000000+#{service_add_05}*2000000+#{service_add_07}*50000000+#{service_add_08}*2000000+#{service_add_09}*2000000+#{service_add_10}*30000000+370000000', '', 'y', '/年');
