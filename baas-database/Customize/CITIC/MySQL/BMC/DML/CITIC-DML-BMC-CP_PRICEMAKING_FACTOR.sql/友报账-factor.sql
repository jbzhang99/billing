/* x0016 */
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'a732806f-46c5-4386-9877-41c5687a5fbd' and price_product_id like '%0016';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbd', '10016', 'Measure', 'Measure1'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbd', '20016', 'Measure', 'Measure2'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbd', '30016', 'Measure', 'Measure4'),
('ECITIC', 'a732806f-46c5-4386-9877-41c5687a5fbd', '40016', 'Measure', 'Measure6');
