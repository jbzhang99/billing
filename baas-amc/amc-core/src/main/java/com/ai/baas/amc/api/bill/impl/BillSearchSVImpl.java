package com.ai.baas.amc.api.bill.impl;

import com.ai.baas.amc.api.bill.interfaces.IBillSearchSV;
import com.ai.baas.amc.api.bill.params.BillChargeVo;
import com.ai.baas.amc.api.bill.params.BillSearchRequest;
import com.ai.baas.amc.api.bill.params.BillSearchResponse;
import com.ai.baas.amc.api.bill.params.BillTotalRequest;
import com.ai.baas.amc.api.bill.params.BillTotalResponse;
import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.service.business.interfaces.IBillSearchBusiSV;
import com.ai.baas.amc.service.business.interfaces.ICcBillSearchBusiSV;
import com.ai.baas.amc.service.business.interfaces.IProceedsSearchBusiSV;
import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.AcctIdInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.subject.interfaces.ISubjectQuerySV;
import com.ai.opt.sys.api.subject.param.SubjectIdParam;
import com.ai.opt.sys.api.subject.param.SubjectNameQueryResponse;
import com.alibaba.dubbo.config.annotation.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Component
public class BillSearchSVImpl implements IBillSearchSV{

    @Autowired
    private IBillSearchBusiSV billSearchBusiSV;

    @Autowired
    private ICcBillSearchBusiSV ccbillSearchBusiSV;

    @Autowired
    private IProceedsSearchBusiSV proceedsSearchBusiSV;

    @Override
    public BillSearchResponse queryBillList(BillSearchRequest vo) throws BusinessException, SystemException {
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getUserType())){
        	 throw new BusinessException(" 用户类型不能为空");
        }
        if(StringUtils.equals("1", vo.getUserType()) && StringUtil.isBlank(vo.getBillMonth())){
             throw new BusinessException("查询账单月份billMonth不能为空");
        }
        if(vo.getPageNo()==null){
            throw new BusinessException("pageNo不能为空");
        }
        if(vo.getPageSize()==null){
            throw new BusinessException("pageSize不能为空");
        }
        List<AmcCharge> bills = billSearchBusiSV.searchBills(vo);
        BillSearchResponse response = new BillSearchResponse();
        PageInfo<BillChargeVo> billPageVo = null; 
        if(!CollectionUtil.isEmpty(bills)){
            billPageVo = new PageInfo<>();
            List<BillChargeVo>  billChargeVos = new ArrayList<>();
            DateFormat fmt = new SimpleDateFormat("yyyyMM");
            DateFormat fmt1 = new SimpleDateFormat("yyyy年MM月");
            int count = billSearchBusiSV.countBillsByMonths(vo);
            IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
            ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory
                    .getService(ICiticRestReqWrapperSV.class);
            ISubjectQuerySV subjectQuerySV = DubboConsumerFactory.getService(ISubjectQuerySV.class);
            for (AmcCharge charge:bills) {
                BillChargeVo chargeVo = new BillChargeVo();
                BeanUtils.copyProperties(chargeVo,charge);
                //查询分摊信息
                Long apportionmentValue = ccbillSearchBusiSV.searchApportionmentInfo(charge);
                Long proceeds  = proceedsSearchBusiSV.searchProceeds(charge);
                //翻译科目id
                SubjectIdParam param = new SubjectIdParam();
                param.setTenantId(charge.getTenantId());
                param.setSubjectId(charge.getSubjectId());
                SubjectNameQueryResponse subjectName = subjectQuerySV.getSubjectName(param);
                if(subjectName!=null&&subjectName.getResponseHeader().isSuccess()){
                    chargeVo.setSubjectName(subjectName.getSubjectName());
                }
                chargeVo.setAdjustFee(new BigDecimal(charge.getTotalAmount()));
                chargeVo.setBalanceFee(new BigDecimal(charge.getBalance()));
                if(apportionmentValue!=null){
                    chargeVo.setApportionment(new BigDecimal(apportionmentValue));
                }
                if(proceeds!=null){
                    chargeVo.setProceeds(new BigDecimal(proceeds));
                }

                //翻译租户id租户名称
                AcctIdInfo acctIdInfo = new AcctIdInfo();
                acctIdInfo.setTenantId(charge.getTenantId());
                acctIdInfo.setAcctId(charge.getAcctId());
                BlCustinfoResponse blCustinfoResponse = iQueryIdInfoSV.queryExtCustIdByAcctId(acctIdInfo);
                if (!CollectionUtil.isEmpty(blCustinfoResponse.getBlCustinfoInfos())) {
                	chargeVo.setExtCustId(blCustinfoResponse.getBlCustinfoInfos().get(0)
                            .getExtCustId());
                	 OrgQueryVo queryVo = new OrgQueryVo();
                     queryVo.setSelectId(chargeVo.getExtCustId());
                     queryVo.setSelectType("3");

                     OrgQueryResponse searchOrg = iCiticRestReqWrapperSV.searchOrg(queryVo);
                     if (searchOrg != null && searchOrg.getOrgs()!=null && searchOrg.getOrgs().size()>0) {
                    	 chargeVo.setCustName(searchOrg.getOrgs().get(0).getName());
                     }
                }

                try {
                    Date date = fmt.parse(charge.getBillMonth());
                    chargeVo.setBillMonth(fmt1.format(date));
                } catch (ParseException e) {
                    throw new BusinessException("账期月billMonth格式错误", e);
                }
                billChargeVos.add(chargeVo);
            }
            billPageVo.setCount(count);
            billPageVo.setPageCount((count+vo.getPageSize()-1)/vo.getPageSize());
            billPageVo.setPageNo(vo.getPageNo());
            billPageVo.setPageSize(bills.size());
            billPageVo.setResult(billChargeVos);
        }
        response.setTenantId(vo.getTenantId());
        response.setBillPageVo(billPageVo);
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("000000");
        responseHeader.setResultMessage("查询账单成功");
        response.setResponseHeader(responseHeader);
        return response;
    }
    
    @Override
    public BillTotalResponse queryBillTotal(BillTotalRequest vo) throws BusinessException,
            SystemException {
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getAcctId())){
             throw new BusinessException("账户ID不能为空");
        }
        if(StringUtil.isBlank(vo.getBillMonth())){
            throw new BusinessException("账期不能为空");
       }
        Long totalAmount = billSearchBusiSV.queryBillTotal(vo);
        BillTotalResponse res =  new BillTotalResponse();
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setIsSuccess(true);
        responseHeader.setResultCode("000000");
        responseHeader.setResultMessage("查询账单成功");
        res.setResponseHeader(responseHeader);
        res.setTenantId(vo.getTenantId());
        res.setTotalAmount(totalAmount);
        return res;
    }

}
