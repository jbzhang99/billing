  CREATE TABLE `fail_msg_log` (
    `ID` int(11) NOT NULL,
    `SYSTEM_ID` varchar(32) COLLATE utf8_bin NOT NULL,
    `TENANT_ID` varchar(32) COLLATE utf8_bin NOT NULL,
    `MSG` varchar(1000) COLLATE utf8_bin NOT NULL,
    `ERROR_MSG` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
    `TYPE` varchar(32) COLLATE utf8_bin NOT NULL,
    `STATUS` varchar(32) COLLATE utf8_bin NOT NULL,
    `DATE` varchar(32) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;