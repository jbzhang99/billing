/* x0009 */
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'a732806f-46c5-4386-9877-41c5687a5fbc' and price_product_id like '%0009';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbc', '10009', 'Measure', 'Measure1'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbc', '20009', 'Measure', 'Measure2'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbc', '30009', 'Measure', 'Measure3'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbc', '40009', 'Measure', 'Measure5');
