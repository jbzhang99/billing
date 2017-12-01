delete from cp_pricemaking_rule 
where tenant_id = 'ECITIC' and PRICE_PRODUCT_TYPE = '58465454b992230c4edfe723' and price_product_id like '%0013';

insert into cp_pricemaking_rule
(tenant_id, price_product_type, price_product_id, price_type, rule_code, rule_expresion, ext_info, price_unit, price_unit_name)
values
('ECITIC', '58465454b992230c4edfe723', '110013', 'PER_HOUR', 'EXPR', '616000+140000*#{ExtDomainPackage}+560000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '210013', 'PER_HOUR', 'EXPR', '1848000+3*140000*#{ExtDomainPackage}+3*560000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '310013', 'PER_HOUR', 'EXPR', '3696000+6*140000*#{ExtDomainPackage}+6*560000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '410013', 'PER_HOUR', 'EXPR', '5544000+12*140000*#{ExtDomainPackage}+12*560000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '510013', 'PER_HOUR', 'EXPR', '10348800+24*140000*#{ExtDomainPackage}+24*560000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '610013', 'PER_HOUR', 'EXPR', '11088000+36*140000*#{ExtDomainPackage}+36*560000/50*#{ExtBandwidth}', '', '', ''),

('ECITIC', '58465454b992230c4edfe723', '120013', 'PER_HOUR', 'EXPR', '2716000+420000*#{ExtDomainPackage}+700000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '220013', 'PER_HOUR', 'EXPR', '8148000+3*420000*#{ExtDomainPackage}+3*700000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '320013', 'PER_HOUR', 'EXPR', '16296000+6*420000*#{ExtDomainPackage}+6*700000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '420013', 'PER_HOUR', 'EXPR', '27703200+12*420000*#{ExtDomainPackage}+12*700000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '520013', 'PER_HOUR', 'EXPR', '45628800+24*420000*#{ExtDomainPackage}+24*700000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '620013', 'PER_HOUR', 'EXPR', '48888000+36*420000*#{ExtDomainPackage}+36*700000/50*#{ExtBandwidth}', '', '', ''),

('ECITIC', '58465454b992230c4edfe723', '130013', 'PER_HOUR', 'EXPR', '6860000+700000*#{ExtDomainPackage}+2100000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '230013', 'PER_HOUR', 'EXPR', '20580000+3*700000*#{ExtDomainPackage}+3*2100000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '330013', 'PER_HOUR', 'EXPR', '41160000+6*700000*#{ExtDomainPackage}+6*2100000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '430013', 'PER_HOUR', 'EXPR', '69972000+12*700000*#{ExtDomainPackage}+12*2100000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '530013', 'PER_HOUR', 'EXPR', '115248000+24*700000*#{ExtDomainPackage}+24*2100000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '630013', 'PER_HOUR', 'EXPR', '123480000+36*700000*#{ExtDomainPackage}+36*2100000/50*#{ExtBandwidth}', '', '', ''),

('ECITIC', '58465454b992230c4edfe723', '140013', 'PER_HOUR', 'EXPR', '20860000+700000*#{ExtDomainPackage}+3500000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '240013', 'PER_HOUR', 'EXPR', '62580000+3*700000*#{ExtDomainPackage}+3*3500000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '340013', 'PER_HOUR', 'EXPR', '125160000+6*700000*#{ExtDomainPackage}+6*3500000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '440013', 'PER_HOUR', 'EXPR', '212772000+12*700000*#{ExtDomainPackage}+12*3500000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '540013', 'PER_HOUR', 'EXPR', '350448000+24*700000*#{ExtDomainPackage}+24*3500000/50*#{ExtBandwidth}', '', '', ''),
('ECITIC', '58465454b992230c4edfe723', '640013', 'PER_HOUR', 'EXPR', '375480000+36*700000*#{ExtDomainPackage}+36*3500000/50*#{ExtBandwidth}', '', '', '');