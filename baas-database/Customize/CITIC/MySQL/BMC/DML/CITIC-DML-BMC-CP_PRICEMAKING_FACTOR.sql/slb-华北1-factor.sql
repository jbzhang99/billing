/*slb实例 xxx0410*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-instance' and price_product_id like '%0410';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
/*公网 xxx10410 */
('ECITIC', 'slb-instance',  '110410', 'RegionId', 'cn-qingdao'),
('ECITIC', 'slb-instance',  '110410', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '110410', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-instance',  '210410', 'RegionId', 'cn-qingdao'),
('ECITIC', 'slb-instance',  '210410', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '210410', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20410 */
('ECITIC', 'slb-instance',  '120410', 'RegionId', 'cn-qingdao'),
('ECITIC', 'slb-instance',  '120410', 'AddressType', 'intranet');

/*slb流量 xxx0411*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-bandwith' and price_product_id like '%0411';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'slb-bandwith',  '110411', 'RegionId', 'cn-qingdao'),
('ECITIC', 'slb-bandwith',  '110411', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '110411', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-bandwith',  '210411', 'RegionId', 'cn-qingdao'),
('ECITIC', 'slb-bandwith',  '210411', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '210411', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20411 */
('ECITIC', 'slb-bandwith',  '120411', 'RegionId', 'cn-qingdao'),
('ECITIC', 'slb-bandwith',  '120411', 'AddressType', 'intranet');
