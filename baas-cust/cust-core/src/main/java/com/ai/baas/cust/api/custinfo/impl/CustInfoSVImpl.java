package com.ai.baas.cust.api.custinfo.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ai.baas.cust.api.custinfo.interfaces.ICustInfoSV;
import com.ai.baas.cust.api.custinfo.params.*;
import com.ai.baas.cust.constants.BmcConstants;
import com.ai.baas.cust.context.ErrorCode;
import com.ai.baas.cust.dao.interfaces.BlAcctInfoMapper;
import com.ai.baas.cust.dao.interfaces.BlUserinfoMapper;
import com.ai.baas.cust.dao.mapper.bo.BlAcctInfoCriteria;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfo;
import com.ai.baas.cust.service.atom.interfaces.IBlCustinfoAtomSV;
import com.ai.baas.cust.service.business.interfaces.ICustinfoBusiness;
import com.ai.baas.cust.util.CheckUtil;
import com.ai.baas.cust.util.LoggerUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
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
	 @Autowired
	    private transient BlAcctInfoMapper blAcctInfoMapper;

	    @Autowired
	    private transient BlUserinfoMapper blUserinfoMapper;
	/**
	 * 使用对象做为输入参数的接口函数
	 * 
	 */
	@Override
	public BaseResponse custNotify(CustInfoParams custInfo) throws BusinessException, SystemException {

		BaseResponse response = new BaseResponse();
		ResponseHeader header = new ResponseHeader();
		if (StringUtils.isEmpty(custInfo)) {
			log.debug("extInfoNotify() custInfo = [null]");
			header.setIsSuccess(false);
			header.setResultCode("000001");
			header.setResultMessage("客户信息为空");
			response.setResponseHeader(header);
			return response;
		} else {
			log.debug("custNotify() custInfo = " + custInfo.toString() + "]");
		}
		
		if(StringUtil.isBlank(custInfo.getTenantId())){
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(ErrorCode.NULL +":tenantId不能为空");
			response.setResponseHeader(header);
			return response;
		}

		String resultCode = CheckUtil.check(custInfo.getTradeSeq(), "tradeSeq", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        
        resultCode = CheckUtil.check(custInfo.getTenantId(), "tenantId", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
		
        resultCode = CheckUtil.check(custInfo.getExtCustId(), "extCustId", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getCustName(), "custName", false, 128);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getProvinceCode(), "provinceCode", false, 6);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getCityCode(), "cityCode", false, 6);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
     
        resultCode = CheckUtil.check(custInfo.getCustGrade(), "custGrade", true, 1,"A","B","C","D","E");
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getCustType(), "custType", true, 1,"P","G");
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getRemark(), "remark", true, 1024);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getContactNo(), "contactNo", true, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getEmail(), "email", true, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getCustAddress(), "custAddress", true, 128);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        
        
        if (custInfo.getExtInfoList()!=null) {
            for (ExtInfo e: custInfo.getExtInfoList()) {
               resultCode = CheckUtil.check(e.getExtName(), "extName", false, 32);
                if (!ErrorCode.SUCCESS.equals(resultCode)) {
					header.setResultCode("000001");
					header.setIsSuccess(false);
					header.setResultMessage(resultCode);
					response.setResponseHeader(header);
					return response;
				}
             resultCode = CheckUtil.check(e.getExtValue(), "extValue", false, 64);
              if (!ErrorCode.SUCCESS.equals(resultCode)) {
				  header.setResultCode("000001");
				  header.setIsSuccess(false);
				  header.setResultMessage(resultCode);
				  response.setResponseHeader(header);
				  return response;
              	}
             resultCode = CheckUtil.check(e.getUpdateFlag(), "updateFlag", false, 1,"D","U","N");
             if (!ErrorCode.SUCCESS.equals(resultCode)) {
				 header.setResultCode("000001");
				 header.setIsSuccess(false);
				 header.setResultMessage(resultCode);
				 response.setResponseHeader(header);
				 return response;
               }
        
            }
            
        }
            
		
        try {
        	log.info("--------------------start custInfo operation");
            iCustinfoBusiness.writeData(custInfo, false);
			header.setResultCode("000000");
			header.setIsSuccess(true);
			header.setResultMessage("客户信息创建成功");
        } catch (BusinessException e){
        	header.setResultCode("000002");
        	header.setIsSuccess(false);
        	header.setResultMessage(e.getErrorCode() + e.getErrorMessage());
        }

        response.setResponseHeader(header);
	
		return response;
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

	@Override
	public BaseResponse updateCustInfo(CustInfoParams custInfo) throws BusinessException, SystemException {
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
			iCustinfoBusiness.updateCustInfo(custInfo);
		} catch (Exception e) {
			throw new BusinessException("更新客户信息失败",e);
		}

		BaseResponse result = new BaseResponse();
		result.setResponseHeader(new ResponseHeader(true, "000000", "成功"));
		return result;
	}

	@Override
	public BaseResponse deleteCustInfo(CustIdParams params) throws BusinessException, SystemException {
		if(params==null){
			throw new BusinessException("查询参数不能为空");
		}

		if(StringUtil.isBlank(params.getTenantId())){
			throw new BusinessException("租户id不能为空");
		}
		if(StringUtil.isBlank(params.getCustId())&&StringUtil.isBlank(params.getExtCustId())){
			throw new BusinessException("客户id不能为空");
		}

		try {
			iCustinfoBusiness.deleteCustInfo(params);
		} catch (Exception e) {
			throw new BusinessException("删除客户信息失败",e);
		}

		BaseResponse result = new BaseResponse();
		result.setResponseHeader(new ResponseHeader(true, "000000", "成功"));
		return result;
	}

	@Override
	public BaseResponse custInfoNotify(CustInfoParams custInfo) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		
		BaseResponse response = new BaseResponse();
		ResponseHeader header = new ResponseHeader();
		if (StringUtils.isEmpty(custInfo)) {
			log.debug("extInfoNotify() custInfo = [null]");
			header.setIsSuccess(false);
			header.setResultCode("000001");
			header.setResultMessage("客户信息为空");
			response.setResponseHeader(header);
			return response;
		} else {
			log.debug("custNotify() custInfo = " + custInfo.toString() + "]");
		}
		
		if(StringUtil.isBlank(custInfo.getTenantId())){
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(ErrorCode.NULL +":tenantId不能为空");
			response.setResponseHeader(header);
			return response;
		}

		String resultCode = CheckUtil.check(custInfo.getTradeSeq(), "tradeSeq", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        
        resultCode = CheckUtil.check(custInfo.getTenantId(), "tenantId", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
		
        resultCode = CheckUtil.check(custInfo.getExtCustId(), "extCustId", false, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getCustName(), "custName", false, 128);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getProvinceCode(), "provinceCode", false, 6);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getCityCode(), "cityCode", false, 6);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
     
        resultCode = CheckUtil.check(custInfo.getCustGrade(), "custGrade", true, 1,"A","B","C","D","E");
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getCustType(), "custType", true, 1,"P","G");
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getRemark(), "remark", true, 1024);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getContactNo(), "contactNo", true, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getEmail(), "email", true, 32);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        resultCode = CheckUtil.check(custInfo.getCustAddress(), "custAddress", true, 128);
        if (!ErrorCode.SUCCESS.equals(resultCode)) {
			header.setResultCode("000001");
			header.setIsSuccess(false);
			header.setResultMessage(resultCode);
			response.setResponseHeader(header);
			return response;
        }
        
        
        if (custInfo.getExtInfoList()!=null) {
            for (ExtInfo e: custInfo.getExtInfoList()) {
               resultCode = CheckUtil.check(e.getExtName(), "extName", false, 32);
                if (!ErrorCode.SUCCESS.equals(resultCode)) {
					header.setResultCode("000001");
					header.setIsSuccess(false);
					header.setResultMessage(resultCode);
					response.setResponseHeader(header);
					return response;
				}
             resultCode = CheckUtil.check(e.getExtValue(), "extValue", false, 64);
              if (!ErrorCode.SUCCESS.equals(resultCode)) {
				  header.setResultCode("000001");
				  header.setIsSuccess(false);
				  header.setResultMessage(resultCode);
				  response.setResponseHeader(header);
				  return response;
              	}
             resultCode = CheckUtil.check(e.getUpdateFlag(), "updateFlag", false, 1,"D","U","N");
             if (!ErrorCode.SUCCESS.equals(resultCode)) {
				 header.setResultCode("000001");
				 header.setIsSuccess(false);
				 header.setResultMessage(resultCode);
				 response.setResponseHeader(header);
				 return response;
               }
        
            }
            
        }
            
		
        try {
        	log.info("--------------------start custInfo operation");
            iCustinfoBusiness.writeData(custInfo, true);
			header.setResultCode("000000");
			header.setIsSuccess(true);
			header.setResultMessage("客户信息创建成功");
        } catch (BusinessException e){
        	header.setResultCode("000002");
        	header.setIsSuccess(false);
        	header.setResultMessage(e.getErrorCode() + e.getErrorMessage());
        }

        response.setResponseHeader(header);
	
		return response;
	}
}
