package com.ai.baas.cust.service.business.interfaces;

import com.ai.baas.cust.api.acctinfo.params.AcctInfoParams;
import com.ai.baas.cust.api.acctinfo.params.AcctQueryRequest;
import com.ai.baas.cust.api.acctinfo.params.ResponseMessage;
import com.ai.opt.base.vo.PageInfo;

public interface IAcctInfoBusiness {
	public ResponseMessage getAcctInfo(AcctQueryRequest acctQueryRequest);

}
