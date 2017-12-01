/*smartcloud*/
/* xxx07 */
delete from cp_pricemaking_factor where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'smartcloud' and price_product_id like '%07';
insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'smartcloud', '107', 'ServiceType', '基础'),
('ECITIC', 'smartcloud', '207', 'ServiceType', '高级'),     
('ECITIC', 'smartcloud', '307', 'ServiceType', '企业');     
