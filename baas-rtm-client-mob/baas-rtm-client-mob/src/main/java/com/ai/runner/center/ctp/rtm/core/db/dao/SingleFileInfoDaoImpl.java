package com.ai.runner.center.ctp.rtm.core.db.dao;

import com.ai.runner.center.ctp.rtm.core.db.JdbcTemplate;
import com.ai.runner.center.ctp.rtm.core.db.entity.SingleFileInfo;

public class SingleFileInfoDaoImpl implements SingleFileInfoDao {

	@Override
	public int insertSingleFileInfo(SingleFileInfo info) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into single_file_info (");
		strSql.append("SYS_ID,TENANT_ID,compress_Path,backup_Path,dest_PATH,filename,");
		strSql.append("havaRequestOperator,url,fileTime,appId,httpClientPwd,transId,token,");
		strSql.append("appKey,sign,fileUnZipPwd,request_param,response_result,CREATE_TIME,is_success_unzip,unzip_fail_exception");
		strSql.append(") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE(),?,?) ");
		
		Object[] params = new Object[20];
		params[0] = info.getSysId();
		params[1] = info.getTenantId();
		params[2] = info.getCompressPath();
		params[3] = info.getBackupPath();
		params[4] = info.getDestPath();
		params[5] = info.getFilename();
		params[6] = info.getHavarequestoperator();
		params[7] = info.getUrl();
		params[8] = info.getFiletime();
		params[9] = info.getAppid();
		params[10] = info.getHttpclientpwd();
		params[11] = info.getTransid();
		params[12] = info.getToken();
		params[13] = info.getAppkey();
		params[14] = info.getSign();
		params[15] = info.getFileunzippwd();
		params[16] = info.getRequestParam();
		params[17] = info.getResponseResult();
		params[18] = info.getIsSuccessUnzip();
		params[19] = info.getUnzipFailException();
		
		return JdbcTemplate.update(dsName, strSql.toString(), params);
	}
	
}
