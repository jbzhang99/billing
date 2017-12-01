package com.ai.baas.bmc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.bmc.api.custInfo.params.BlCustinfoParams;
import com.ai.baas.bmc.api.custInfo.params.ExtCustParams;
import com.ai.baas.bmc.api.custInfo.params.QueryCustInfoRequest;
import com.ai.baas.bmc.api.initbasedata.params.InitBaseParam;
import com.ai.baas.bmc.dao.mapper.bo.BlCustinfo;

public interface IBlCustinfoAtomSV {
	void addDshmData(BlCustinfo blCustinfo);

	List<BlCustinfo> getCustInfos(QueryCustInfoRequest param);
	
	int getCustInfoCount(QueryCustInfoRequest param);
	int custInfoInsert(InitBaseParam param);
	
	List<BlCustinfo> getCustInfosByParams(BlCustinfoParams param);
	
	void setPolicyId (ExtCustParams custInfo);
}
