/* xxx0412*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '5836a0462fbaaf0aee0db7a2' and price_product_id like '%0412';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '10412', 'RegionId', 'cn-qingdao'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '10412', 'DiskCategory', 'cloud'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '20412', 'RegionId', 'cn-qingdao'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '20412', 'DiskCategory', 'cloud_efficiency'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '30412', 'RegionId', 'cn-qingdao'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '30412', 'DiskCategory', 'cloud_ssd'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '40412', 'RegionId', 'cn-qingdao'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '40412', 'DiskCategory', 'ephemeral_ssd');
