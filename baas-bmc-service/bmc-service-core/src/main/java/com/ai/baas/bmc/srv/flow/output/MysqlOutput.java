package com.ai.baas.bmc.srv.flow.output;

import java.util.List;
import java.util.Map;

import com.ai.baas.bmc.srv.entity.BillingDetailRecord;
import com.ai.baas.bmc.srv.entity.BmcOutputInfo;
import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.persistence.service.DetailRecordOutputService;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.opt.sdk.util.ApplicationContextUtil;

public class MysqlOutput implements IOutput {

	@Override
	public void send(BillingDetailRecord detailRecord) throws BusinessException{
		List<BmcOutputInfo> outputInfos = OutputMapping.getOutputMappingValue(detailRecord.getRecordFmtKey());
		if (outputInfos != null && outputInfos.size() > 0) {
			DetailRecordOutputService outputService = ApplicationContextUtil.getService("detailRecordOutput");
			Map<String, String> data = detailRecord.getData();
			for (BmcOutputInfo outputInfo : outputInfos) {
				String tableName = outputInfo.getTableName() + detailRecord.getAccountPeriod();
				List<String> detailColunmNames = outputInfo.getDetailColumnNames();
				OutputMapping.createMysqlTableIfNecessary(tableName, detailColunmNames);
				outputService.insertDetailRecord(tableName, data.get(BaasConstants.SERIAL_NUMBER), detailColunmNames, data);
			}
		}else{
			throw new BusinessException("BMC-B0020","该消息没有在[bmc_output_info表]配置详单输出!");
		}
	}

}
