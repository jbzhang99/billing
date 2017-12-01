/*slb实例 xxx0610*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-instance' and price_product_id like '%0610';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
/*公网 xxx10610 */
('ECITIC', 'slb-instance',  '110610', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'slb-instance',  '110610', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '110610', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-instance',  '210610', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'slb-instance',  '210610', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '210610', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20610 */
('ECITIC', 'slb-instance',  '120610', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'slb-instance',  '120610', 'AddressType', 'intranet');

/*slb流量 xxx0611*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-bandwith' and price_product_id like '%0611';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'slb-bandwith',  '110611', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'slb-bandwith',  '110611', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '110611', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-bandwith',  '210611', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'slb-bandwith',  '210611', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '210611', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20611 */
('ECITIC', 'slb-bandwith',  '120611', 'RegionId', 'cn-shenzhen'),
('ECITIC', 'slb-bandwith',  '120611', 'AddressType', 'intranet');
