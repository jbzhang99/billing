/* x0008 */
delete from cp_pricemaking_factor where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313' and price_product_id like '%0008';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313', '10008', 'Measure', 'Measure1'),
('ECITIC', '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313', '20008', 'Measure', 'Measure2'),
('ECITIC', '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313', '30008', 'Measure', 'Measure4'),
('ECITIC', '20e7fd16-e5b0-46ec-bc8a-5a4a8232b313', '40008', 'Measure', 'Measure6');
