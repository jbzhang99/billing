package com.ai.baas.cust.service.atom.interfaces;

import java.util.List;

import com.ai.baas.cust.api.custinfo.params.BlCustinfoParams;
import com.ai.baas.cust.api.custinfo.params.ExtCustParams;
import com.ai.baas.cust.api.custinfo.params.QueryCustInfoRequest;
import com.ai.baas.cust.api.initbasedata.params.InitBaseParam;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfo;

public interface IBlCustinfoAtomSV {
	void addDshmData(BlCustinfo blCustinfo);

	List<BlCustinfo> getCustInfos(QueryCustInfoRequest param);
	
	int getCustInfoCount(QueryCustInfoRequest param);
	int custInfoInsert(InitBaseParam param);
	
	List<BlCustinfo> getCustInfosByParams(BlCustinfoParams param);
	
	void setPolicyId (ExtCustParams custInfo);
}
