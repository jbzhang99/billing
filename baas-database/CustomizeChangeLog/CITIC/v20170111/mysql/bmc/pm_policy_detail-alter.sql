alter table `pm_policy_detail` add `SORT_INDEX` bigint(20) COLLATE utf8_bin DEFAULT NULL COMMENT '排序字段';
alter table `pm_policy_detail` add `GROUP_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL;
