/* x0017 */
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '97acb1b1-fbb3-4b4f-9c23-95acba084ac6' and price_product_id like '%0017';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', '97acb1b1-fbb3-4b4f-9c23-95acba084ac6', '10017', 'service_level_id', '2f33db03-2656-4ee1-9e16-077fcdc0a7c4'),
('ECITIC', '97acb1b1-fbb3-4b4f-9c23-95acba084ac6', '20017', 'service_level_id', 'cd926eb1-85cd-4f47-8105-e46619d07dae'),
('ECITIC', '97acb1b1-fbb3-4b4f-9c23-95acba084ac6', '30017', 'service_level_id', 'd84cad62-d8a6-4705-aa5a-5068da5aca64'),
('ECITIC', '97acb1b1-fbb3-4b4f-9c23-95acba084ac6', '40017', 'service_level_id', 'eee9147f-1d0b-445d-91a6-f9dc078f111c');
