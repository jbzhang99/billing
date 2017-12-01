delete from cp_pricemaking_factor 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '58465454b992230c4edfe723' and price_product_id like '%0013';

insert into cp_pricemaking_factor
(tenant_id, price_product_type, price_product_id, factor_name, factor_value)
values
/*增强版*/
('ECITIC', '58465454b992230c4edfe723',  '110013', 'PackageCode', 'version_2'),
('ECITIC', '58465454b992230c4edfe723',  '110013', 'Duration', '1'),
('ECITIC', '58465454b992230c4edfe723',  '210013', 'PackageCode', 'version_2'),
('ECITIC', '58465454b992230c4edfe723',  '210013', 'Duration', '3'),
('ECITIC', '58465454b992230c4edfe723',  '310013', 'PackageCode', 'version_2'),
('ECITIC', '58465454b992230c4edfe723',  '310013', 'Duration', '6'),
('ECITIC', '58465454b992230c4edfe723',  '410013', 'PackageCode', 'version_2'),
('ECITIC', '58465454b992230c4edfe723',  '410013', 'Duration', '12'),
('ECITIC', '58465454b992230c4edfe723',  '510013', 'PackageCode', 'version_2'),
('ECITIC', '58465454b992230c4edfe723',  '510013', 'Duration', '24'),
('ECITIC', '58465454b992230c4edfe723',  '610013', 'PackageCode', 'version_2'),
('ECITIC', '58465454b992230c4edfe723',  '610013', 'Duration', '36'),
('ECITIC', '58465454b992230c4edfe723',  '120013', 'PackageCode', 'version_3'),
('ECITIC', '58465454b992230c4edfe723',  '120013', 'Duration', '1'),
('ECITIC', '58465454b992230c4edfe723',  '220013', 'PackageCode', 'version_3'),
('ECITIC', '58465454b992230c4edfe723',  '220013', 'Duration', '3'),
('ECITIC', '58465454b992230c4edfe723',  '320013', 'PackageCode', 'version_3'),
('ECITIC', '58465454b992230c4edfe723',  '320013', 'Duration', '6'),
('ECITIC', '58465454b992230c4edfe723',  '420013', 'PackageCode', 'version_3'),
('ECITIC', '58465454b992230c4edfe723',  '420013', 'Duration', '12'),
('ECITIC', '58465454b992230c4edfe723',  '520013', 'PackageCode', 'version_3'),
('ECITIC', '58465454b992230c4edfe723',  '520013', 'Duration', '24'),
('ECITIC', '58465454b992230c4edfe723',  '620013', 'PackageCode', 'version_3'),
('ECITIC', '58465454b992230c4edfe723',  '620013', 'Duration', '36'),
/*企业版*/
('ECITIC', '58465454b992230c4edfe723',  '130013', 'PackageCode', 'version_4'),
('ECITIC', '58465454b992230c4edfe723',  '130013', 'Duration', '1'),
('ECITIC', '58465454b992230c4edfe723',  '230013', 'PackageCode', 'version_4'),
('ECITIC', '58465454b992230c4edfe723',  '230013', 'Duration', '3'),
('ECITIC', '58465454b992230c4edfe723',  '330013', 'PackageCode', 'version_4'),
('ECITIC', '58465454b992230c4edfe723',  '330013', 'Duration', '6'),
('ECITIC', '58465454b992230c4edfe723',  '430013', 'PackageCode', 'version_4'),
('ECITIC', '58465454b992230c4edfe723',  '430013', 'Duration', '12'),
('ECITIC', '58465454b992230c4edfe723',  '530013', 'PackageCode', 'version_4'),
('ECITIC', '58465454b992230c4edfe723',  '530013', 'Duration', '24'),
('ECITIC', '58465454b992230c4edfe723',  '630013', 'PackageCode', 'version_4'),
('ECITIC', '58465454b992230c4edfe723',  '630013', 'Duration', '36'),
('ECITIC', '58465454b992230c4edfe723',  '140013', 'PackageCode', 'version_5'),
('ECITIC', '58465454b992230c4edfe723',  '140013', 'Duration', '1'),
('ECITIC', '58465454b992230c4edfe723',  '240013', 'PackageCode', 'version_5'),
('ECITIC', '58465454b992230c4edfe723',  '240013', 'Duration', '3'),
('ECITIC', '58465454b992230c4edfe723',  '340013', 'PackageCode', 'version_5'),
('ECITIC', '58465454b992230c4edfe723',  '340013', 'Duration', '6'),
('ECITIC', '58465454b992230c4edfe723',  '440013', 'PackageCode', 'version_5'),
('ECITIC', '58465454b992230c4edfe723',  '440013', 'Duration', '12'),
('ECITIC', '58465454b992230c4edfe723',  '540013', 'PackageCode', 'version_5'),
('ECITIC', '58465454b992230c4edfe723',  '540013', 'Duration', '24'),
('ECITIC', '58465454b992230c4edfe723',  '640013', 'PackageCode', 'version_5'),
('ECITIC', '58465454b992230c4edfe723',  '640013', 'Duration', '36');

commit;