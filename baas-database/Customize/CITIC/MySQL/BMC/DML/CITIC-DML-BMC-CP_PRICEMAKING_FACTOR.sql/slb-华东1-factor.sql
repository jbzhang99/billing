/*slb实例 xxx0210*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-instance' and price_product_id like '%0210';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
/*公网 xxx10210 */
('ECITIC', 'slb-instance',  '110210', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'slb-instance',  '110210', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '110210', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-instance',  '210210', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'slb-instance',  '210210', 'AddressType', 'internet'),
('ECITIC', 'slb-instance',  '210210', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20210 */
('ECITIC', 'slb-instance',  '120210', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'slb-instance',  '120210', 'AddressType', 'intranet');

/*slb流量 xxx0211*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = 'slb-bandwith' and price_product_id like '%0211';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', 'slb-bandwith',  '110211', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'slb-bandwith',  '110211', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '110211', 'InternetChargeType', 'paybytraffic'),

('ECITIC', 'slb-bandwith',  '210211', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'slb-bandwith',  '210211', 'AddressType', 'internet'),
('ECITIC', 'slb-bandwith',  '210211', 'InternetChargeType', 'paybybandwidth'),
/*私网 xxx20211 */
('ECITIC', 'slb-bandwith',  '120211', 'RegionId', 'cn-hangzhou'),
('ECITIC', 'slb-bandwith',  '120211', 'AddressType', 'intranet');
