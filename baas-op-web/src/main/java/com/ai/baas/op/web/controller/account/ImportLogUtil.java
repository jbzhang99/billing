package com.ai.baas.op.web.controller.account;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.baas.smc.api.sysparamcache.interfaces.ISmcSysParamCache;
import com.ai.baas.smc.api.sysparamcache.param.GetSysParamListRequest;
import com.ai.baas.smc.api.sysparamcache.param.SmcSysParam;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public final class ImportLogUtil {
	private static final Logger LOG = Logger
			.getLogger(ImportLogUtil.class);
	
	public static String getImportStateName(String code) {
		Map<String, String> map = new HashMap<String, String>();
		QueryInfoParams param = new QueryInfoParams();
		try {
			param.setTradeSeq(TradeSeqUtil.newTradeSeq("PUB"));
			param.setTenantId("PUB");
			param.setParamType("IMPORT_LOG_STATE");
			IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService("iBaseInfoSV");
			BaseCodeInfo info = iBaseInfoSV.getBaseInfo(param);
			if (info != null) {
				List<BaseCode> list = info.getParamList();
				if (!CollectionUtil.isEmpty(list)) {
					for (BaseCode c : list) {
						map.put(c.getParamCode(), c.getParamName());
					}
				}
				if (map != null) {
					return map.get(code);
				}
			}
		} catch (Exception e) {
			LOG.info("获取数据失败", e);
		}
		return null;
	}

	/**
	 * 获取翻译文件类型
	 * 
	 * @param dataType
	 * @return
	 */
	public static String getDataTypeName(String dataType) {
		if ("order".equals(dataType)) {
			return "业务流水文件";
		} else if ("bill".equals(dataType)) {
			return "第三方账单文件";
		} else {
			return dataType;
		}
	}

	/**
	 * 获取翻译流水类型
	 * 
	 * @param dataType
	 * @return
	 */
	public static String getFileTypeName(String dataType, String tenantId) {
		ISmcSysParamCache iSmcSysParamCache = DubboConsumerFactory.getService("iSmcSysParamCache");
		GetSysParamListRequest paramListRequest = new GetSysParamListRequest();
		paramListRequest.setTenantId(tenantId);
		paramListRequest.setTypeCode("STL_ELEMENT");
		paramListRequest.setParamCode("OBJECT_ID");
		List<SmcSysParam> sysParamsList = iSmcSysParamCache.getSysParams(paramListRequest);
		if (sysParamsList == null || sysParamsList.size() == 0) {
			return dataType;
		}
		Map<String, String> fileTypeMap = new HashMap<String, String>();
		for (SmcSysParam sysParam : sysParamsList) {
			String columnValue = sysParam.getColumnValue();
			String columnDesc = sysParam.getColumnDesc();
			fileTypeMap.put(columnValue, columnDesc);
		}
		String name = fileTypeMap.get(dataType);
		return name == null ? dataType : name;
	}

	/**
	 * 获取 YYYY年MM月 格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getYearMonthDate(String date) {
		if (date != null && date.length() == 6) {
			String year = date.substring(0, 4);
			String month = date.substring(4);
			return year + "年" + month + "月";
		} else {
			return date;
		}
	}
}
