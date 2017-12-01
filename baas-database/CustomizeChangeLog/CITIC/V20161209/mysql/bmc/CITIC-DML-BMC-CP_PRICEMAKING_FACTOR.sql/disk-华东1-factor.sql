/* xxx0212*/
delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '5836a0462fbaaf0aee0db7a2' and price_product_id like '%0212';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '10212', 'RegionId', 'cn-hangzhou'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '10212', 'DiskCategory', 'cloud'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '20212', 'RegionId', 'cn-hangzhou'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '20212', 'DiskCategory', 'cloud_efficiency'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '30212', 'RegionId', 'cn-hangzhou'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '30212', 'DiskCategory', 'cloud_ssd'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '40212', 'RegionId', 'cn-hangzhou'),
('ECITIC', '5836a0462fbaaf0aee0db7a2',  '40212', 'DiskCategory', 'ephemeral_ssd');
