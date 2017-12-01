delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ce4f19ab-d83f-4164-9974-9487b97cddcd' and price_product_id like '%0018';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', 'ce4f19ab-d83f-4164-9974-9487b97cddcd', '10018', 'PER_HOUR', 'CONST', '14000000', '', 'n', '/Äê'),
('ECITIC', 'ce4f19ab-d83f-4164-9974-9487b97cddcd', '20018', 'PER_HOUR', 'CONST', '19000000', '', 'n', '/Äê'),
('ECITIC', 'ce4f19ab-d83f-4164-9974-9487b97cddcd', '30018', 'PER_HOUR', 'CONST', '9000000', '', 'n', '/Äê');