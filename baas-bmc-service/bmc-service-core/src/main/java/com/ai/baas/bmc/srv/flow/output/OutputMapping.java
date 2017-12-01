package com.ai.baas.bmc.srv.flow.output;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.srv.entity.BmcOutputDetail;
import com.ai.baas.bmc.srv.entity.BmcOutputInfo;
import com.ai.baas.bmc.srv.message.RecordFmt.RecordFmtKey;
import com.ai.baas.bmc.srv.persistence.service.DetailRecordOutputService;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.util.ApplicationContextUtil;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;


@Component
public class OutputMapping {
	private static Logger logger = LoggerFactory.getLogger(OutputMapping.class);
	private static ListMultimap<RecordFmtKey, BmcOutputInfo> outputMapping = ArrayListMultimap.create();
	private static List<String> drDbNameList = Lists.newCopyOnWriteArrayList();
	private static Lock lock = new ReentrantLock();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	private void loadData() {
		try {
			List<BmcOutputInfo> infos = queryAllOutputData();
			List<BmcOutputDetail> details = null;
			for (BmcOutputInfo infoObj : infos) {
				//logger.debug("------[OutputConfigUtils.loadData=]" + infoObj.toString());
				details = queryOutputDetailByInfoCode(String.valueOf(infoObj.getInfoCode()));
				if (details != null && details.size() != 0) {
					infoObj.setDetails(details);
				}
				infoObj.createTableName();
				RecordFmtKey key = new RecordFmtKey(infoObj.getTenantId(),infoObj.getServiceType(),"");
				outputMapping.put(key, infoObj);
				//System.out.println("outputMapping--"+outputMapping.toString());
			}
			String strDrConf = CCSClientFactory.getDefaultConfigClient().get(BaasConstants.DR_DB_PATH);
			//System.out.println("ccs dr-db:"+strDrConf);
			if(StringUtils.isNotBlank(strDrConf)){
				String[] dbNames = StringUtils.splitPreserveAllTokens(strDrConf, BaasConstants.COMMON_SPLIT);
				for (String dbName : dbNames) {
					drDbNameList.add(dbName);
				}
			}
			logger.debug("详单输出配置参数加载完成..."+infos.size());
		} catch (ConfigException e) {
			logger.error("error", e);
			logger.debug("详单输出配置参数加载失败...");
		}
	}
	
	private List<BmcOutputInfo> queryAllOutputData(){
		StringBuilder strSql = new StringBuilder();
		strSql.append("select a.info_code infoCode,a.tenant_id tenantId,a.service_type serviceType,");
		strSql.append("   a.table_prefix tablePrefix,a.output_type outputType,a.output_name outputName,");
		strSql.append("   a.key_seq keySeq,a.seq_name seqName ");
		strSql.append("from bmc_output_info a");
		return jdbcTemplate.query(strSql.toString(), new BeanPropertyRowMapper<BmcOutputInfo>(BmcOutputInfo.class));
	}
	
	private List<BmcOutputDetail> queryOutputDetailByInfoCode(String infoCode){
		StringBuilder strSql = new StringBuilder();
		strSql.append("select a.detail_code detailCode,a.info_code infoCode,");
		strSql.append("  a.column_name columnName,a.param_name paramName,");
		strSql.append("  a.is_key isKey,a.display_order displayOrder ");
		strSql.append("from bmc_output_detail a ");
		if (StringUtils.isNotBlank(infoCode)) {
			strSql.append(" where a.info_code=").append(infoCode);
		}
		strSql.append(" order by a.display_order ");
		return jdbcTemplate.query(strSql.toString(), new BeanPropertyRowMapper<BmcOutputDetail>(BmcOutputDetail.class));
	}
	
	
	public static List<BmcOutputInfo> getOutputMappingValue(RecordFmtKey key){
		return outputMapping.get(key);
	}
	
	
	public static void createMysqlTableIfNecessary(String tableName,List<String> detailColumnNames){
		if (drDbNameList.contains(tableName)) {
			return;
		}
		lock.lock();
		try{
			if(!drDbNameList.contains(tableName)){
				DetailRecordOutputService outputService = ApplicationContextUtil.getService("detailRecordOutput");
				outputService.createTable(tableName, detailColumnNames);
				String oldConf = CCSClientFactory.getDefaultConfigClient().get(BaasConstants.DR_DB_PATH);
				String newConf = Joiner.on(BaasConstants.COMMON_SPLIT).join(oldConf,tableName);
				CCSClientFactory.getDefaultConfigClient().remove(BaasConstants.DR_DB_PATH);
				CCSClientFactory.getDefaultConfigClient().add(BaasConstants.DR_DB_PATH, newConf);
				drDbNameList.add(tableName);
				//System.out.println("创建表成功["+tableName+"]");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}
