/*请使用这种方式注释*/
/*这里的数据库对象可以有普通表、月表、索引、视图*/


CREATE TABLE sgip_src_gsm (
  src_name varchar(64) COLLATE utf8_bin NOT NULL,
  template_id bigint(10) DEFAULT NULL,
  servicetype varchar(10) COLLATE utf8_bin NOT NULL,
  verifyid decimal(20,0) NOT NULL,
  phone varchar(15) COLLATE utf8_bin NOT NULL,
  gsmcontent varchar(512) COLLATE utf8_bin DEFAULT NULL,
  flag int(1) NOT NULL,
  create_time datetime NOT NULL,
  Priority int(1) DEFAULT '9'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sgip_src_gsm_temp (
  src_name varchar(64) COLLATE utf8_bin NOT NULL,
  template_id bigint(10) DEFAULT NULL,
  servicetype varchar(10) COLLATE utf8_bin NOT NULL,
  verifyid decimal(20,0) NOT NULL,
  phone varchar(15) COLLATE utf8_bin NOT NULL,
  gsmcontent varchar(512) COLLATE utf8_bin DEFAULT NULL,
  flag int(1) NOT NULL,
  create_time datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE sgip_template (
  template_id bigint(10) DEFAULT NULL,
  template_text varchar(1024) COLLATE utf8_bin NOT NULL,
  sbegin_time varchar(6) COLLATE utf8_bin NOT NULL DEFAULT '000000',
  sclose_time varchar(6) COLLATE utf8_bin NOT NULL DEFAULT '235959',
  Retry_times int(2) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

