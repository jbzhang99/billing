package com.ai.baas.bmc.api.custInfo.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ai.baas.bmc.api.custInfo.interfaces.ICustInfoSV;
import com.ai.baas.bmc.api.custInfo.params.BlCustinfoParams;
import com.ai.baas.bmc.api.custInfo.params.BlCustinfoVo;
import com.ai.baas.bmc.api.custInfo.params.CustInfoParams;
import com.ai.baas.bmc.api.custInfo.params.ExtCustParams;
import com.ai.baas.bmc.api.custInfo.params.ExtInfo;
import com.ai.baas.bmc.service.atom.interfaces.IBlCustinfoAtomSV;
import com.ai.baas.bmc.service.business.interfaces.ICustinfoBusiness;
import com.ai.baas.bmc.context.ErrorCode;
import com.ai.baas.bmc.dao.mapper.bo.BlCustinfo;
import com.ai.baas.bmc.util.CheckUtil;
import com.ai.baas.bmc.util.LoggerUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 保存从外部系统同步过来的客户信息
 * 
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangzhi
 */
@Service
@Component
public class CustInfoSVImpl implements ICustInfoSV {
	private static final Logger log = LogManager.getLogger(CustInfoSVImpl.class
			.getName());

	 @Autowired
	    private ICustinfoBusiness iCustinfoBusiness;
	 @Autowired
		private IBlCustinfoAtomSV iBlCustInfoAtomSV;
	/**
	 * 使用对象做为输入参数的接口函数
	 * 
	 */
	@Override
	public String custNotify(CustInfoParams custInfo) throws BusinessException, SystemException {
		
		if (StringUtils.isEmpty(custInfo)) {
			log.debug("extInfoNotify() custInfo = [null]");
			return null;
		} else {
			log.debug("custNotify() custInfo = " + custInfo.toString() + "]");
		}
		
		if(StringUtil.isBlank(custInfo.getTenantId())){
			return ErrorCode.NULL +":tenantId不能为空";
		}
		 try {
	            if(iCustinfoBusiness.hasSeq(custInfo)){
	                return "tradeSeq已使用";
	            }
	        } catch (IOException e) {
	            LoggerUtil.log.error(e.getStackTrace());
	            return e.getMessage();
	        }
		
		String resultCode = CheckUtil.check(custInfo.getTradeSeq(), "tradeSeq", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        
        resultCode = CheckUtil.check(custInfo.getTenantId(), "tenantId", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
		
        resultCode = CheckUtil.check(custInfo.getExtCustId(), "extCustId", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        resultCode = CheckUtil.check(custInfo.getCustName(), "custName", false, 128);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        resultCode = CheckUtil.check(custInfo.getProvinceCode(), "provinceCode", false, 6);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        resultCode = CheckUtil.check(custInfo.getCityCode(), "cityCode", false, 6);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
     
        resultCode = CheckUtil.check(custInfo.getCustGrade(), "custGrade", true, 1,"A","B","C","D","E");
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        resultCode = CheckUtil.check(custInfo.getCustType(), "custType", true, 1,"P","G");
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        resultCode = CheckUtil.check(custInfo.getRemark(), "remark", true, 1024);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        resultCode = CheckUtil.check(custInfo.getContactNo(), "contactNo", true, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        resultCode = CheckUtil.check(custInfo.getEmail(), "email", true, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        resultCode = CheckUtil.check(custInfo.getCustAddress(), "custAddress", true, 128);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
            return resultCode;
        }
        
        
        if (custInfo.getExtInfoList()!=null) {
            for (ExtInfo e: custInfo.getExtInfoList()) {
               resultCode = CheckUtil.check(e.getExtName(), "extName", false, 32);
                if (!ErrorCode.SUCCESS.equals(resultCode)) {
                  return resultCode;
            }
             resultCode = CheckUtil.check(e.getExtValue(), "extValue", false, 64);
              if (!ErrorCode.SUCCESS.equals(resultCode)) {
                 return resultCode;
             }
             resultCode = CheckUtil.check(e.getUpdateFlag(), "updateFlag", false, 1,"D","U","N");
             if (!ErrorCode.SUCCESS.equals(resultCode)) {
                 return resultCode;
               }
        
            }
            
        }
            
		
        try {
        	log.info("--------------------start custInfo operation");
            iCustinfoBusiness.writeData(custInfo);
        	
        } catch (BusinessException e){
            return e.getErrorCode() + e.getErrorMessage();
        }
	
		return ErrorCode.SUCCESS;
	}

	@Override
	public List<BlCustinfoVo> getBlCustInfos(BlCustinfoParams queryParam) throws BusinessException, SystemException {
		
		if(queryParam==null){
			throw new BusinessException("查询参数不能为空");
		}
	
		if(StringUtil.isBlank(queryParam.getTenantId())){
			throw new BusinessException("租户id不能为空");
		}
		List<BlCustinfoVo> blCustinfoVos = new ArrayList<BlCustinfoVo>();
		List<BlCustinfo> results = iBlCustInfoAtomSV.getCustInfosByParams(queryParam);
		if(results!=null){
			for(BlCustinfo vo : results){
				BlCustinfoVo custinfoVo = new BlCustinfoVo();
				BeanUtils.copyProperties(custinfoVo, vo);
				blCustinfoVos.add(custinfoVo);
			}
		}
		
		return blCustinfoVos;
	}

	@Override
	public BaseResponse setPolicyId(ExtCustParams custInfo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		
		if(custInfo==null){
			throw new BusinessException("查询参数不能为空");
		}
		if(StringUtil.isBlank(custInfo.getTenantId())){
			throw new BusinessException("租户id不能为空");
		}
		if(StringUtil.isBlank(custInfo.getExtCustId())){
			throw new BusinessException("客户id不能为空");
		}
		
		try {
			iBlCustInfoAtomSV.setPolicyId(custInfo);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("设置策略id失败");
		}
		
		BaseResponse result = new BaseResponse();
		result.setResponseHeader(new ResponseHeader(true, "000000", "成功"));
		return result;
	}
}
