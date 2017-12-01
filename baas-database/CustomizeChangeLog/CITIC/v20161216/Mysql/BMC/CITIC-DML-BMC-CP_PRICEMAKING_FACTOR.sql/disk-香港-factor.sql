/* xxx0712*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '5836a0462fbaaf0aee0db7a2' and price_product_id like '%0712';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '10712', 'RegionId', 'cn-hongkong'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '10712', 'DiskCategory', 'cloud'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '20712', 'RegionId', 'cn-hongkong'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '20712', 'DiskCategory', 'cloud_efficiency'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '30712', 'RegionId', 'cn-hongkong'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '30712', 'DiskCategory', 'cloud_ssd'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '40712', 'RegionId', 'cn-hongkong'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '40712', 'DiskCategory', 'ephemeral_ssd');
