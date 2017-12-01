
insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values


/*实例系列3*/
('ECITIC', 'ECS-INSTANCE',  '330501', 'PER_HOUR', 'CONST', '1281', '', 'h', '/时'),
                            
('ECITIC', 'ECS-INSTANCE',  '830501', 'PER_HOUR', 'CONST', '770', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE',  '930501', 'PER_HOUR', 'CONST', '392', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '1030501', 'PER_HOUR', 'CONST', '1519', '', 'h', '/时'),

('ECITIC', 'ECS-INSTANCE', '1430501', 'PER_HOUR', 'CONST', '511', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '1530501', 'PER_HOUR', 'CONST', '259', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '1630501', 'PER_HOUR', 'CONST', '1008', '', 'h', '/时'),
('ECITIC', 'ECS-INSTANCE', '1730501', 'PER_HOUR', 'CONST', '210', '', 'h', '/时');
