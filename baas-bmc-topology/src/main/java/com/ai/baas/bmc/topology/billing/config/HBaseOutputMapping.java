package com.ai.baas.bmc.topology.billing.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.entity.BmcOutputDetail;
import com.ai.baas.bmc.topology.entity.BmcOutputInfo;
import com.ai.baas.storm.jdbc.JdbcTemplate;
import com.ai.baas.storm.message.RecordFmt.RecordFmtKey;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class HBaseOutputMapping {
	private static Logger logger = LoggerFactory.getLogger(HBaseOutputMapping.class);
	private Map<RecordFmtKey, List<BmcOutputInfo>> outputMapping = Maps.newHashMap();
	
	public HBaseOutputMapping(){
		loadData();
	}
	
	private void loadData() {
		List<BmcOutputInfo> infos = queryAllOutputData();
		List<BmcOutputDetail> details = null;
		for (BmcOutputInfo infoObj : infos) {
			logger.debug("------[OutputConfigUtils.loadData=]" + infoObj.toString());
			details = queryOutputDetailByInfoCode(String.valueOf(infoObj.getInfoCode()));
			if (details != null && details.size() != 0) {
				infoObj.setDetails(details);
			}
			infoObj.createTableName();
			RecordFmtKey key = new RecordFmtKey(infoObj.getTenantId(),infoObj.getServiceType(),"");
			List<BmcOutputInfo> outputInfos = outputMapping.get(key);
			if (outputInfos == null) {
				outputInfos = Lists.newArrayList();
			}
			//System.out.println("the output is "+com.alibaba.fastjson.JSON.toJSONString(infoObj));
			outputInfos.add(infoObj);
			outputMapping.put(key, outputInfos);
		}
	}
	
	public List<BmcOutputInfo> getOutputMappingValue(RecordFmtKey key){
		return outputMapping.get(key);
	}
	
	private List<BmcOutputInfo> queryAllOutputData(){
		StringBuilder strSql = new StringBuilder();
		//strSql.append("select a.info_code infoCode,a.tenant_id tenantId,a.service_type serviceType,a.source source,");
		strSql.append("select a.info_code infoCode,a.tenant_id tenantId,a.service_type serviceType,");
		strSql.append("   a.table_prefix tablePrefix,a.output_type outputType,a.output_name outputName,");
		strSql.append("   a.key_seq keySeq,a.seq_name seqName ");
		strSql.append("from bmc_output_info a");
		return JdbcTemplate.defaultQuery(strSql.toString(),new BeanListHandler<BmcOutputInfo>(BmcOutputInfo.class));
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
		return JdbcTemplate.defaultQuery(strSql.toString(),new BeanListHandler<BmcOutputDetail>(BmcOutputDetail.class));
	}

	
}
