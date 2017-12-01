package com.ai.baas.bmc.api.citic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.bmc.api.citic.interfaces.IOrderChangeSV;
import com.ai.baas.bmc.api.citic.params.InstanceInfo;
import com.ai.baas.bmc.api.citic.params.InstanceUpdateInfo;
import com.ai.baas.bmc.service.business.interfaces.IOrderDeleteBusiSV;
import com.ai.baas.bmc.service.business.interfaces.IorderManageBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
@Transactional
public class OrderChangeSVImpl implements IOrderChangeSV{
    @Autowired
    IOrderDeleteBusiSV iOrderDeleteBusiSV;
    @Autowired
    IorderManageBusiSV iorderManageBusiSV;
    @Override
    public BaseResponse instanceChange(InstanceUpdateInfo instanceUpdateInfo)
            throws BusinessException, SystemException {
        BaseResponse resultCode = new BaseResponse();
        if (instanceUpdateInfo == null) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL, "请求报文为空为空");
        }        
        if (StringUtil.isBlank(instanceUpdateInfo.getInstance_id())) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,"instance_id不能为空");
        }
        if (StringUtil.isBlank(instanceUpdateInfo.getTenant_id())) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,"tenant_id不能为空");
        }
        if (StringUtil.isBlank(instanceUpdateInfo.getTrade_seq())) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,"trade_seq不能为空");
        }
        if (StringUtil.isBlank(instanceUpdateInfo.getChange_code())) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,"change_code不能为空");
        }
        System.out.println("------------------------------------------------------1111---------------------------------------");
        String output = null;
        if(instanceUpdateInfo.getChange_code().equals("REMOVE")){
            output = iOrderDeleteBusiSV.OrderDelete(instanceUpdateInfo);        
        }else if(instanceUpdateInfo.getChange_code().equals("MODIFY")){
            resultCode.setResponseHeader(new ResponseHeader(false, "000001", "暂不支持MODIFY"));
            return resultCode;
        }
        
        if(output.equals("1")){   
            resultCode.setResponseHeader(new ResponseHeader(true, "000000", "成功"));
        }else{
            resultCode.setResponseHeader(new ResponseHeader(false,"000002","订单实例修改失败"));
        }
        
        return resultCode;
    }

	@Override
	public BaseResponse updateInstance(InstanceInfo info) throws BusinessException, SystemException {
		 BaseResponse resultCode = new BaseResponse();
	        if (info == null) {
	            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL, "请求报文不能为空");
	        }        
	        if (StringUtil.isBlank(info.getInstanceId())) {
	            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,"instance_id不能为空");
	        }
	        if (StringUtil.isBlank(info.getTenantId())) {
	            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,"tenant_id不能为空");
	        }
	        if(info.getInactiveTime()==null){
	        	throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,"inactiveTime不能为空");
	        }
	       
	      String resultMessage=  iorderManageBusiSV.OrderUpdate(info);  
	      if("error".equals(resultMessage)){
	    	  resultCode.setResponseHeader(new ResponseHeader(false,"000002","订单实例修改失败"));  
	      }else{
	    	  resultCode.setResponseHeader(new ResponseHeader(true, "000000", "成功")); 
	      }
	       
	    
		return resultCode;
	}

}
