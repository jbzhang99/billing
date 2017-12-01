package com.ai.baas.bmc.srv.flow.duplicate;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.persistence.service.DuplicateCheckingService;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.opt.sdk.util.ApplicationContextUtil;
import com.google.common.base.Joiner;

public class DuplicateCheckingFromMysql implements IDuplicateChecking {
	private static Logger logger = LoggerFactory.getLogger(DuplicateCheckingFromMysql.class);
	
	@Override
	public boolean checkData(Map<String, String> data) throws BusinessException {
		String suffixValue = filterUnNumber(data.get(BaasConstants.ACCOUNT_PERIOD));
		String dupTable = StringUtils.lowerCase(Joiner.on(BaasConstants.COMMON_JOINER).join(data.get(BaasConstants.TENANT_ID),
				data.get(BaasConstants.SERVICE_TYPE),DuplicateCheckingConfig.DUP_PREFIX,suffixValue.substring(0, 6)));
		return checkDuplicate(dupTable,data.get(BaasConstants.SERIAL_NUMBER));
	}
	
	private boolean checkDuplicate(String dupTableName, String dupKey) throws BusinessException {
		boolean isSucc = false;
		try{
			DuplicateCheckingConfig.createMysqlTableIfNecessary(dupTableName);
			DuplicateCheckingService dupService = ApplicationContextUtil.getService("duplicateChecking");
			if(!dupService.isExist(dupTableName, dupKey)){
				dupService.insertDupKey(dupTableName, dupKey);
				isSucc = true;
			}
		}catch(Exception e){
			throw new BusinessException("BMC-B0006", "插入查重表数据失败!", e);
		}
		return isSucc;
	}
	
	private String filterUnNumber(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
	

}
