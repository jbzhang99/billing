/*slb实例 xxx0510*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-instance' and price_product_id like '%0510';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
/*公网 xxx10510 */
('ECITIC', 'slb-instance',  '110510', 'RegionId', 'cn-shanghai'),
('ECITIC', 'slb-instance',  '110510', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '110510', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-instance',  '210510', 'RegionId', 'cn-shanghai'),
('ECITIC', 'slb-instance',  '210510', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '210510', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20510 */
('ECITIC', 'slb-instance',  '120510', 'RegionId', 'cn-shanghai'),
('ECITIC', 'slb-instance',  '120510', 'AddressType', 'intranet');

/*slb流量 xxx0511*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-bandwith' and price_product_id like '%0511';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'slb-bandwith',  '110511', 'RegionId', 'cn-shanghai'),
('ECITIC', 'slb-bandwith',  '110511', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '110511', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-bandwith',  '210511', 'RegionId', 'cn-shanghai'),
('ECITIC', 'slb-bandwith',  '210511', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '210511', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20511 */
('ECITIC', 'slb-bandwith',  '120511', 'RegionId', 'cn-shanghai'),
('ECITIC', 'slb-bandwith',  '120511', 'AddressType', 'intranet');
