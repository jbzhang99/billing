package com.ai.baas.ccp.util;
 
import org.apache.commons.lang.StringUtils;

import com.ai.baas.ccp.topoligy.core.constant.BalancecalModel;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.OwnerType;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.vo.ConfigContainerObject;


public final class ResourceCheck {
	private ResourceCheck(){
		
	}

    public static boolean checkOwnerType(String tenantid, String ownertype, String policyid,
            ConfigContainerObject confContainer) throws OmcException{
		if (confContainer == null){
			throw new OmcException("ResourceCheck", "请配置相应参数");
		}
		
		String  balancemodel = confContainer.getCfgPara(OmcCalKey.OMC_CFG_BALANCECALMODEL, tenantid, policyid,"");
		//默认为账户余额模式
		if (StringUtils.isBlank(balancemodel)){
			balancemodel = BalancecalModel.ACCTMODEL;
		}
		
		if ((balancemodel.equals(BalancecalModel.SUBSMODEL))&&((OwnerType.ACCT.equals(ownertype))||(OwnerType.CUST.equals(ownertype)))){
			throw new OmcException("ResourceCheck", "余额模式是用户模式，OWNERTYPE 不能是账户或者客户，请检查参数配置或者输入参数");
		}
		if ((balancemodel.equals(BalancecalModel.ACCTMODEL))&&((OwnerType.CUST.equals(ownertype)))){
			throw new OmcException("ResourceCheck", "余额模式是账户模式，OWNERTYPE 不能是客户");
		}
		return true;	
	}
	
}
