/* x0018 */
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'ce4f19ab-d83f-4164-9974-9487b97cddcd' and price_product_id like '%0018';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'ce4f19ab-d83f-4164-9974-9487b97cddcd', '10018', 'service_level_id', '7149e374-08f3-4653-96dc-9607874b5851'),
('ECITIC', 'ce4f19ab-d83f-4164-9974-9487b97cddcd', '20018', 'service_level_id', 'a5e5c186-f59a-4e0b-8104-51222c0f86b5'),
('ECITIC', 'ce4f19ab-d83f-4164-9974-9487b97cddcd', '30018', 'service_level_id', 'e039d8b7-16d4-4dba-a509-05014fee8f35');
