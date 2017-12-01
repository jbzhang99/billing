/*slb实例 xxx0110*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-instance' and price_product_id like '%0110';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
/*公网 xxx10110 */
('ECITIC', 'slb-instance',  '110110', 'RegionId', 'cn-beijing'),
('ECITIC', 'slb-instance',  '110110', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '110110', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-instance',  '210110', 'RegionId', 'cn-beijing'),
('ECITIC', 'slb-instance',  '210110', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '210110', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20110 */
('ECITIC', 'slb-instance',  '120110', 'RegionId', 'cn-beijing'),
('ECITIC', 'slb-instance',  '120110', 'AddressType', 'intranet');

/*slb流量 xxx0111*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-bandwith' and price_product_id like '%0111';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'slb-bandwith',  '110111', 'RegionId', 'cn-beijing'),
('ECITIC', 'slb-bandwith',  '110111', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '110111', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-bandwith',  '210111', 'RegionId', 'cn-beijing'),
('ECITIC', 'slb-bandwith',  '210111', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '210111', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20111 */
('ECITIC', 'slb-bandwith',  '120111', 'RegionId', 'cn-beijing'),
('ECITIC', 'slb-bandwith',  '120111', 'AddressType', 'intranet');
