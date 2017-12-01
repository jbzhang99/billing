/*slb实例 xxx0710*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-instance' and price_product_id like '%0710';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
/*公网 xxx10710 */
('ECITIC', 'slb-instance',  '110710', 'RegionId', 'cn-hongkong'),
('ECITIC', 'slb-instance',  '110710', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '110710', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-instance',  '210710', 'RegionId', 'cn-hongkong'),
('ECITIC', 'slb-instance',  '210710', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '210710', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20710 */
('ECITIC', 'slb-instance',  '120710', 'RegionId', 'cn-hongkong'),
('ECITIC', 'slb-instance',  '120710', 'AddressType', 'intranet');

/*slb流量 xxx0711*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-bandwith' and price_product_id like '%0711';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'slb-bandwith',  '110711', 'RegionId', 'cn-hongkong'),
('ECITIC', 'slb-bandwith',  '110711', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '110711', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-bandwith',  '210711', 'RegionId', 'cn-hongkong'),
('ECITIC', 'slb-bandwith',  '210711', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '210711', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20711 */
('ECITIC', 'slb-bandwith',  '120711', 'RegionId', 'cn-hongkong'),
('ECITIC', 'slb-bandwith',  '120711', 'AddressType', 'intranet');
