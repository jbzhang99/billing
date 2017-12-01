package com.ai.baas.amc.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.invoice.params.InvoiceInfoDetailVO;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoParam;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoQueryResponse;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoVO;
import com.ai.baas.amc.api.invoice.params.InvoiceQueryParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordInsertParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordQueryParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordQueryReponse;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordVo;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceInfoCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceInfoDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLog;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLogCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLogCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLogDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLogKey;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.dao.mapper.interfaces.AmcInvoiceInfoMapper;
import com.ai.baas.amc.service.business.interfaces.IInvoiceInfoBusiSV;
import com.ai.baas.amc.util.AmcSeqUtil;
import com.ai.baas.amc.util.DateUtils;
import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.AcctIdInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;

/**
 * 发票管理具体实现类
 * @author wangluyang
 *
 */
@Service
@Transactional
public class InvoiceInfoBusiSVImpl implements IInvoiceInfoBusiSV {
	
	private String ACCT_START_TABLE = "201602"; 
	
	@Override
	public BaseResponse saveInvoiceInfo(InvoiceInfoParam param) throws BusinessException {
		// TODO Auto-generated method stub
		BaseResponse result = new BaseResponse();
		if(param!=null){
			//新增
			if(StringUtils.isBlank(param.getInvoiceInfoId())){
				AmcInvoiceInfo record = new AmcInvoiceInfo();
				BeanUtils.copyProperties(record, param);
				record.setInvoiceInfoId(AmcSeqUtil.createInvoiceId().toString());
				if(MapperFactory.getAmcInvoiceInfoMapper().insert(record)<1){
	                throw new BusinessException("BaaS-000001", "插入数据失败");
	            }
			}else{//修改
				AmcInvoiceInfo record = new AmcInvoiceInfo();
				BeanUtils.copyProperties(record, param);
				AmcInvoiceInfoCriteria sql = new AmcInvoiceInfoCriteria();
				AmcInvoiceInfoCriteria.Criteria criteria = sql.createCriteria();
				criteria.andInvoiceInfoIdEqualTo(record.getInvoiceInfoId());
//				criteria.andAcctIdEqualTo(record.getAcctId());
//				criteria.andTenantIdEqualTo(record.getTenantId());
//				criteria.andCustIdEqualTo(record.getCustId());
				if(MapperFactory.getAmcInvoiceInfoMapper().updateByExampleSelective(record, sql)<1){
	                throw new BusinessException("BaaS-000001", "表中没有相关信息，无法更新");
	            }
			}
		}
		result.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return result;
	}

	@Override
	public InvoiceInfoQueryResponse queryInvoiceInfo(InvoiceQueryParam request) throws BusinessException {
		// TODO Auto-generated method stub
		InvoiceInfoQueryResponse response = new InvoiceInfoQueryResponse();
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
		
		AmcInvoiceInfoCriteria amcInvoiceInfoCriteria = new AmcInvoiceInfoCriteria();
		AmcInvoiceInfoCriteria.Criteria criteria = amcInvoiceInfoCriteria.createCriteria();
		criteria.andAcctIdEqualTo(request.getAcctId());
		criteria.andTenantIdEqualTo(request.getTenantId()); 
		if(StringUtils.isNotBlank(request.getInvoiceInfoId())){
		    criteria.andInvoiceInfoIdEqualTo(request.getInvoiceInfoId());
		}
		
		List<AmcInvoiceInfo> amcInvoiceInfos  = MapperFactory.getAmcInvoiceInfoMapper().selectByExample(amcInvoiceInfoCriteria);
		AmcInvoiceInfo amcInvoiceInfo = null;
		InvoiceInfoVO infoVO = new InvoiceInfoVO();
		if(amcInvoiceInfos!=null && amcInvoiceInfos.size()>0){
			amcInvoiceInfo = amcInvoiceInfos.get(0);
			BeanUtils.copyProperties(infoVO, amcInvoiceInfo);
		}
		response.setInvoiceInfoVO(infoVO);
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public InvoiceRecordQueryReponse queryInvoiceRecord(InvoiceRecordQueryParam request) throws BusinessException {
		// TODO Auto-generated method stub
		
		AmcInvoiceLogCriteria amcInvoiceLogCriteria = new AmcInvoiceLogCriteria();
		amcInvoiceLogCriteria.setOrderByClause("AMC_BILL_MONTH desc");
		amcInvoiceLogCriteria.setLimitStart((request.getPageNo()-1)*request.getPageSize());
		amcInvoiceLogCriteria.setLimitEnd(request.getPageSize());
		
		Map<String, Object> params = new HashMap<>();
		params.put("tenantId", request.getTenantId());
		params.put("acctIds", request.getAcctIds());
		if(StringUtils.equals("1", request.getUserType())){//系统管理员
			if(StringUtils.isNotBlank(request.getStatus())){
				if(StringUtils.equals("1", request.getStatus())){
					params.put("status", "= '1'");
				}else if(StringUtils.equals("0", request.getStatus())){
					params.put("status", "is null");
				}
			}
			//公司名称
//			if(request.getCustIds()!=null && request.getCustIds().size()>0){
//				params.put("custId", request.getCustIds());
//			}
		}
		amcInvoiceLogCriteria.setQueryParams(params);
		
		List<String> tableMonths = new ArrayList<>();
		if(StringUtils.equals("1", request.getUserType())){
			if(request.getBillMonth().size()>0){
				for(String month:request.getBillMonth()){
					if(DateUtils.monthDiffs(ACCT_START_TABLE, month)>=0){
						tableMonths.add(month);
					}
				}
			}
		}else{
			for(int i=0; i<6; i++){
        		String month = DateUtils.getDateStrByMouth(i, "YYYYMM");
        		if(DateUtils.monthDiffs(ACCT_START_TABLE, month)>=0){
        			tableMonths.add(month);
				}
			}
		}
        
		List<AmcInvoiceLogDetail> amcInvoiceLogDetails = MapperFactory.getAmcInvoiceLogMapper().selectByQueryParam(amcInvoiceLogCriteria, tableMonths);
		InvoiceRecordQueryReponse response = new InvoiceRecordQueryReponse();
		PageInfo<InvoiceRecordVo> pageInfo = new PageInfo<InvoiceRecordVo>();
		IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
		ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory
                .getService(ICiticRestReqWrapperSV.class);
		if(!CollectionUtil.isEmpty(amcInvoiceLogDetails)){
			List<InvoiceRecordVo> invoiceRecordVos = new ArrayList<>();
			for(AmcInvoiceLogDetail detail:amcInvoiceLogDetails){
				InvoiceRecordVo vo = new InvoiceRecordVo();
				BeanUtils.copyProperties(vo, detail);
				
				//翻译租户id租户名称
                AcctIdInfo acctIdInfo = new AcctIdInfo();
                acctIdInfo.setTenantId(detail.getTenantId()); 
                acctIdInfo.setAcctId(detail.getAcctId());
                BlCustinfoResponse blCustinfoResponse = iQueryIdInfoSV.queryExtCustIdByAcctId(acctIdInfo);
                if (!CollectionUtil.isEmpty(blCustinfoResponse.getBlCustinfoInfos())) {
                	vo.setExtCustId(blCustinfoResponse.getBlCustinfoInfos().get(0)
                            .getExtCustId());
                	 OrgQueryVo queryVo = new OrgQueryVo();
                     queryVo.setSelectId(blCustinfoResponse.getBlCustinfoInfos().get(0)
                             .getExtCustId());
                     queryVo.setSelectType("3");

                     OrgQueryResponse searchOrg = iCiticRestReqWrapperSV.searchOrg(queryVo);
                     if (searchOrg != null && searchOrg.getOrgs()!=null && searchOrg.getOrgs().size()>0) {
                    	 vo.setCustName(searchOrg.getOrgs().get(0).getName());
                     }
                }
				
				invoiceRecordVos.add(vo);
			}
			
			pageInfo.setResult(invoiceRecordVos);
			pageInfo.setPageNo(request.getPageNo());
			pageInfo.setPageSize(request.getPageSize());

            int total = MapperFactory.getAmcInvoiceLogMapper().countByQueryParam(amcInvoiceLogCriteria, tableMonths);
            int pageSize = (total + request.getPageSize() - 1) / request.getPageSize();
            pageInfo.setCount(total);
            pageInfo.setPageCount(pageSize);
		}else{
			pageInfo=null; 
		}
		response.setRecords(pageInfo);
//		response.setAcctId(request.getAcctIds().toString());
		response.setTenantId(request.getTenantId());
		response.setUserType(request.getUserType());
		response.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return response;
	}

	@Override
	public BaseResponse saveInvoiceRecord(InvoiceRecordInsertParam param) throws BusinessException {
		// TODO Auto-generated method stub
		BaseResponse result = new BaseResponse();
		if(param!=null){
			AmcInvoiceLogKey invoiceLogKey = new AmcInvoiceLogKey();
			invoiceLogKey.setAcctId(param.getAcctId());
			invoiceLogKey.setBillMonth(param.getBillMonth());
			invoiceLogKey.setTenantId(param.getTenantId());
			AmcInvoiceLog invoice = MapperFactory.getAmcInvoiceLogMapper().selectByPrimaryKey(invoiceLogKey);
			//当快递单号和寄出时间都不为空时增加数据
			if(StringUtils.isNotBlank(param.getExpressNo()) && param.getSendTime()!=null){
				if(invoice==null){	//新增
					AmcInvoiceLog amcInvoiceLog = new AmcInvoiceLog();
					BeanUtils.copyProperties(amcInvoiceLog, param);
					
					AmcInvoiceInfoCriteria amcInvoiceInfoCriteria = new AmcInvoiceInfoCriteria();
					AmcInvoiceInfoCriteria.Criteria criteria = amcInvoiceInfoCriteria.createCriteria();
					criteria.andAcctIdEqualTo(param.getAcctId());
					criteria.andTenantIdEqualTo(param.getTenantId());
					criteria.andCustIdEqualTo(param.getCustId());
					List<AmcInvoiceInfo> amcInvoiceInfos = MapperFactory.getAmcInvoiceInfoMapper().selectByExample(amcInvoiceInfoCriteria);
					if(amcInvoiceInfos==null || amcInvoiceInfos.size()==0){
						throw new BusinessException("BaaS-000001", "未查询到发票对应信息");
					}else{
						AmcInvoiceInfo invoiceInfo = amcInvoiceInfos.get(0);
						amcInvoiceLog.setInvoiceId(invoiceInfo.getInvoiceInfoId());
						BeanUtils.copyProperties(amcInvoiceLog, invoiceInfo);	//将当月的发票信息保存到发票记录表中
					}
					if(StringUtils.isNotBlank(param.getExpressNo()) && param.getSendTime()!=null){
						amcInvoiceLog.setStatus(1);	//设置开具状态为已开具
					}
					if(MapperFactory.getAmcInvoiceLogMapper().insert(amcInvoiceLog)<1){
						throw new BusinessException("BaaS-000001", "插入数据失败");
					}
				}else{	//修改
					invoice.setExpressNo(param.getExpressNo());
					invoice.setSendTime(param.getSendTime());
					AmcInvoiceLogCriteria example = new AmcInvoiceLogCriteria();
					AmcInvoiceLogCriteria.Criteria criteria = example.createCriteria();
					criteria.andAcctIdEqualTo(invoice.getAcctId());
					criteria.andBillMonthEqualTo(invoice.getBillMonth());
					criteria.andTenantIdEqualTo(invoice.getTenantId());
					if(MapperFactory.getAmcInvoiceLogMapper().updateByExample(invoice, example)<1){
						throw new BusinessException("BaaS-000001", "更新失败");
					}
				}
			}
		}
		result.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功"));
		return result;
	}
	
	@Override
	public List<InvoiceInfoDetailVO> queryInvoiceInfoDetailRecord(InvoiceRecordQueryParam request)
			throws BusinessException {
		// TODO Auto-generated method stub
		List<InvoiceInfoDetailVO> infoDetailVOs = new ArrayList<>();
		AmcInvoiceLogCriteria amcInvoiceLogCriteria = new AmcInvoiceLogCriteria();
		amcInvoiceLogCriteria.setOrderByClause("AMC_BILL_MONTH desc");
		
		Map<String, Object> params = new HashMap<>();
		params.put("tenantId", request.getTenantId());
		params.put("acctIds", request.getAcctIds());
		if(StringUtils.equals("1", request.getUserType())){//系统管理员
			if(StringUtils.isNotBlank(request.getStatus())){
				if(StringUtils.equals("1", request.getStatus())){
					params.put("status", "= '1'");
				}else if(StringUtils.equals("0", request.getStatus())){
					params.put("status", "is null");
				}
			}
//			request.getCompanyName()	//公司名称
//			if(request.getCustIds()!=null && request.getCustIds().size()>0){
//				params.put("custId", request.getCustIds());
//			}
		}
		amcInvoiceLogCriteria.setQueryParams(params);
		
		List<String> tableMonths = new ArrayList<>();
		if(StringUtils.equals("1", request.getUserType())){
			if(request.getBillMonth().size()>0){
				for(String month:request.getBillMonth()){
					if(DateUtils.monthDiffs(ACCT_START_TABLE, month)>=0){
						tableMonths.add(month);
					}
				}
			}
		}else{
			for(int i=1; i<6; i++){
        		String month = DateUtils.getDateStrByMouth(i, "YYYYMM");
        		if(DateUtils.monthDiffs(ACCT_START_TABLE, month)>=0){
        			tableMonths.add(month);
				}
			}
		}
		
		List<AmcInvoiceInfoDetail> amcInvoiceInfoDetails = MapperFactory.getAmcInvoiceLogMapper().selectInfoDetailByQueryParam(amcInvoiceLogCriteria, tableMonths);
		IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
		ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory
                .getService(ICiticRestReqWrapperSV.class);
		if(!CollectionUtil.isEmpty(amcInvoiceInfoDetails)){
			for(AmcInvoiceInfoDetail vo:amcInvoiceInfoDetails){
				InvoiceInfoDetailVO detailVO = new InvoiceInfoDetailVO();
				BeanUtils.copyProperties(detailVO, vo);
				
				//翻译租户id租户名称
                AcctIdInfo acctIdInfo = new AcctIdInfo();
                acctIdInfo.setTenantId(vo.getTenantId()); 
                acctIdInfo.setAcctId(vo.getAcctId());
                BlCustinfoResponse blCustinfoResponse = iQueryIdInfoSV.queryExtCustIdByAcctId(acctIdInfo);
                if (!CollectionUtil.isEmpty(blCustinfoResponse.getBlCustinfoInfos())) {
                	detailVO.setExtCustId(blCustinfoResponse.getBlCustinfoInfos().get(0)
                            .getExtCustId());
                	 OrgQueryVo queryVo = new OrgQueryVo();
                     queryVo.setSelectId(blCustinfoResponse.getBlCustinfoInfos().get(0)
                             .getExtCustId());
                     queryVo.setSelectType("3");

                     OrgQueryResponse searchOrg = iCiticRestReqWrapperSV.searchOrg(queryVo);
                     if (searchOrg != null && searchOrg.getOrgs()!=null && searchOrg.getOrgs().size()>0) {
                    	 detailVO.setCustName(searchOrg.getOrgs().get(0).getName());
                     }
                }
				
				infoDetailVOs.add(detailVO); 
			}
		}
		
		return infoDetailVOs;
	}

}
