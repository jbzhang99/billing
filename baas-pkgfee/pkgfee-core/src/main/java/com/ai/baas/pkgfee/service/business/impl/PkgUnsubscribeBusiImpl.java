package com.ai.baas.pkgfee.service.business.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ai.baas.amc.api.deposit.interfaces.IDepositSV;
import com.ai.baas.amc.api.deposit.param.FundUnfreezeRequest;
import com.ai.baas.amc.api.deposit.param.FundUnfreezeResponse;
import com.ai.baas.pkgfee.service.atom.interfaces.ICpPackageFeeAtomSV;
import com.ai.baas.pkgfee.service.business.interfaces.IPkgUnsubscribeBusi;
import com.ai.baas.pkgfee.util.DateUtils;
import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeRequest;
import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeResponse;
import com.ai.baas.pkgfee.constants.CpPkgfeeConstants;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFee;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PkgUnsubscribeBusiImpl implements IPkgUnsubscribeBusi {
	private static final Logger logger = LogManager
			.getLogger(PkgUnsubscribeBusiImpl.class);
	
	@Autowired
	private ICpPackageFeeAtomSV iCpPkgfeeAtom;
	
	@Override
	public PkgUnsubscribeResponse pkgUnsubscribe(PkgUnsubscribeRequest request) {
		// TODO Auto-generated method stub
		CpPackageFee pkgfee = getCpPackageFeeLst(request.getTenantId(), request.getPriceCode());
		updCpPkgfee(pkgfee, request.getUnsubTime());
		String unFreezeAmount = unfreezeFund(request.getTenantId(), 
				                             request.getExtCustId(),
				                             request.getInstanceId(),
				                             request.getBusiSerialNo(),
				                             request.getUnsubTime());
		
		ResponseHeader responseHeader = new ResponseHeader(true, CpPkgfeeConstants.SUCCESS, "成功");
		
		PkgUnsubscribeResponse response = new PkgUnsubscribeResponse();
		response.setUnfrozenAmount(unFreezeAmount);
		response.setResponseHeader(responseHeader);
		logger.info("包费退订接口处理成功！");
		return response;
	}

	/**
	 * 获取包费信息，
	 * @param tenantID
	 * @param priceCode
	 * @return
	 */
	private CpPackageFee getCpPackageFeeLst(String tenantID, String priceCode){
		CpPackageFee cpPackageFee = iCpPkgfeeAtom.queryByPriceCode(tenantID, priceCode);
		if(cpPackageFee == null){
			logger.error(String.format("包年包月费用表中，TenantID为【%s】，priceCode为【%s】的检索无数据！", tenantID, priceCode));
			throw new BusinessException("PKG-B0001","未获取到包费信息!");
		}
		return cpPackageFee;
	}
	
	/**
	 * 更新包费信息表，将失效时间置为退订时间
	 * @param pkgfee
	 * @param unsubTime
	 */
	private void updCpPkgfee(CpPackageFee pkgfee, String unsubTime){
		pkgfee.setInactiveTime(DateUtils.str2Timestamp(unsubTime));
		iCpPkgfeeAtom.updatePackageFee(pkgfee);
		logger.info("CpPackageFee表更新成功！");
	}
	
	/**
	 * 解冻金额
	 * @param tenantId
	 * @param custId
	 * @param instanceId
	 * @param busiSerialNo
	 * @param unsubTime
	 * @return
	 */
	private String unfreezeFund(String tenantId, String extCustId, 
			                    String instanceId, String busiSerialNo,
			                    String unsubTime){
		IDepositSV iDepositSV = DubboConsumerFactory.getService(IDepositSV.class);
		
		FundUnfreezeRequest request = new FundUnfreezeRequest();
		request.setExtCustId(extCustId);
		request.setTenantId(tenantId);
		request.setInstanceId(instanceId);
		request.setBusiSerialNo(busiSerialNo);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");//小写的mm表示的是分钟  
		Date unsubDate = null;
		try {
			unsubDate = sdf.parse(unsubTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.DATETIME_FORMAT);
		String dateString = formatter.format(unsubDate);
		
		request.setUnsubTime(dateString);
		
		logger.info("iDepositSV.fundUnfreeze参数：" + JSON.toJSONString(request));
		FundUnfreezeResponse response = iDepositSV.fundUnfreeze(request);
		
		if(response.getResponseHeader() != null && 
		   !CpPkgfeeConstants.SUCCESS.equals(response.getResponseHeader().getResultCode())){
			logger.error("调用解冻金额接口失败：" + response.getResponseHeader().getResultMessage());
			throw new BusinessException("PKG-B0001","解冻金额失败!");
		}
		return response.getUnfreezeAmount();
	}
}
