package com.ai.baas.bmc.service.business.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.bmc.api.initbasedata.params.InitBaseParam;
import com.ai.baas.bmc.service.business.interfaces.ISysSequenceSvc;
import com.ai.baas.bmc.service.business.interfaces.IinitBaseDataBusi;
import com.ai.baas.bmc.service.atom.interfaces.IBlAcctInfoAtom;
import com.ai.baas.bmc.service.atom.interfaces.IBlCustinfoAtomSV;
import com.ai.baas.bmc.service.atom.interfaces.IBlUserinfoAtomSV;
import com.ai.baas.bmc.util.DshmUtil;
import com.ai.baas.bmc.util.LoggerUtil;

@Service
@Transactional()
public class InitBaseDataBusiImpl implements IinitBaseDataBusi{
    private static final Logger LOGGER = LogManager.getLogger(InitBaseDataBusiImpl.class);
	@Autowired
	private ISysSequenceSvc sysSequence;
	@Autowired
	private IBlAcctInfoAtom acctInfo;
	@Autowired
	private IBlUserinfoAtomSV userInfo;
	@Autowired
	private IBlCustinfoAtomSV custInfo;
	@Override
	public int InitBaseBusi(InitBaseParam param) {
		// TODO Auto-generated method stub
		try{
			String serviceId=param.getServiceId();
			String tenantId=param.getTenantId();
			Map<String, String> params = new TreeMap<String, String>();
			params.put("service_id", serviceId);
			params.put("tenant_id", tenantId);
	        List<Map<String, String>> result = DshmUtil.getClient().list("bl_userinfo").where(params)
	                .executeQuery(DshmUtil.getCacheClient());
	        if(result.size()==0){
	        	//产生cust_id,subs_id,acct_id
	        	param.setSubsId(sysSequence.terrigerSysSequence("SUBS_ID", 1).get(0));
	        	param.setAcctId(sysSequence.terrigerSysSequence("ACCT_ID", 1).get(0));
	        	param.setAcctId(sysSequence.terrigerSysSequence("CUST_ID", 1).get(0));
	        	if(null!=param.getSubsId()&&null!=param.getCustId()&&null!=param.getAcctId()){
		        	userInfo.userInfoInsert(param);
		        	custInfo.custInfoInsert(param);
		        	acctInfo.acctInfoInsert(param);
		        	return 1;
	        	}else{
	        		LoggerUtil.log.error("subs_id"+param.getSubsId()+" cust_id"+param.getCustId()+" acct_id"+param.getAcctId());
	        		return -1;
	        	}
	        }else{
	        	LoggerUtil.log.debug("客户id"+param.getExtCustId()+"已经初始化完成");
	        	return 1;
	        }
		}catch(Exception e){
			LOGGER.error(e);
			return -1;
		}
	}

}
