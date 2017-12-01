package com.ai.baas.bmc.srv.flow.duplicate;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.srv.persistence.service.DuplicateCheckingService;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.util.ApplicationContextUtil;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@Component
public class DuplicateCheckingConfig {
	private static Logger logger = LoggerFactory.getLogger(DuplicateCheckingConfig.class);
	public static final String DUP_PREFIX = "dup";
	private static List<String> dupDbList = Lists.newCopyOnWriteArrayList();
	private static Lock lock = new ReentrantLock();
	
	@PostConstruct
	public void loadData() {
		try {
			String strDupConf = CCSClientFactory.getDefaultConfigClient().get(BaasConstants.DUP_DB_PATH);
			//System.out.println("ccs dup-db:"+strDupConf);
			if(StringUtils.isNotBlank(strDupConf)){
				String[] dbNames = StringUtils.splitPreserveAllTokens(strDupConf, BaasConstants.COMMON_SPLIT);
				for (String dbName : dbNames) {
					dupDbList.add(dbName);
				}
			}
			logger.debug("查重配置加载成功...");
		} catch (ConfigException e) {
			logger.error("error", e);
			logger.debug("查重配置加载失败...");
		}
	}
	
//	public static boolean isContainTable(String tableName){
//		return dupDbList.contains(tableName);
//	}
	
	public static void createMysqlTableIfNecessary(String tableName){
		if (dupDbList.contains(tableName)) {
			return;
		}
		lock.lock();
		try{
			if(!dupDbList.contains(tableName)){
				DuplicateCheckingService dupService = ApplicationContextUtil.getService("duplicateChecking");
				//if(dupService.createDupTable(tableName)==1){
				dupService.createDupTable(tableName);
				//System.out.println("cnt---------------"+cnt);
				String oldConf = CCSClientFactory.getDefaultConfigClient().get(BaasConstants.DUP_DB_PATH);
				//System.out.println("oldConf======"+oldConf);
				String newConf = Joiner.on("").join(oldConf,BaasConstants.COMMON_SPLIT,tableName);
				//System.out.println("newConf======"+newConf);
				CCSClientFactory.getDefaultConfigClient().remove(BaasConstants.DUP_DB_PATH);
				CCSClientFactory.getDefaultConfigClient().add(BaasConstants.DUP_DB_PATH, newConf);
				dupDbList.add(tableName);
				//System.out.println("创建表成功["+tableName+"]");
				//}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	
}
