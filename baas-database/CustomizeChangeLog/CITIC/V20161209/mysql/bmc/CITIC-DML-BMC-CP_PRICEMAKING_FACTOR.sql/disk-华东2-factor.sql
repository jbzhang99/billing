/* xxx0512*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '5836a0462fbaaf0aee0db7a2' and price_product_id like '%0512';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '10512', 'RegionId', 'cn-shanghai'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '10512', 'DiskCategory', 'cloud'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '20512', 'RegionId', 'cn-shanghai'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '20512', 'DiskCategory', 'cloud_efficiency'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '30512', 'RegionId', 'cn-shanghai'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '30512', 'DiskCategory', 'cloud_ssd'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '40512', 'RegionId', 'cn-shanghai'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '40512', 'DiskCategory', 'ephemeral_ssd');
