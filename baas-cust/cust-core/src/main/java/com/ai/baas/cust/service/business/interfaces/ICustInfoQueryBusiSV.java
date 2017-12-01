package com.ai.baas.cust.service.business.interfaces;

import com.ai.baas.cust.api.custinfo.params.CustInfoResponse;
import com.ai.baas.cust.api.custinfo.params.QueryCustInfoRequest;

public interface ICustInfoQueryBusiSV {
	CustInfoResponse getCustInfos(QueryCustInfoRequest param);
}
