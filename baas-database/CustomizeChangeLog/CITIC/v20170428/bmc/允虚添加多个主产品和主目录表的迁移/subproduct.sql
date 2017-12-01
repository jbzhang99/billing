ALTER TABLE `pm_dimension_info` ADD COLUMN  `MAIN_TAG` varchar(32) NULL ;
ALTER TABLE `pm_dimension_info_his` ADD COLUMN  `MAIN_TAG` varchar(32) NULL ;
ALTER TABLE `pm_dimension_branch` ADD COLUMN  `MAIN_TAG` varchar(32) NULL ;
ALTER TABLE `pm_dimension_branch_his` ADD COLUMN  `MAIN_TAG` varchar(32) NULL ;

ALTER TABLE `pm_category_info` ADD COLUMN  `PARENT_TAG` varchar(32) NULL,ADD COLUMN  'PARENT_NAME'  varchar(64) NULL, ;
ALTER TABLE `pm_category_info_his` ADD COLUMN  `PARENT_TAG` varchar(32) NULL,ADD COLUMN  'PARENT_NAME'  varchar(64) NULL, ;
