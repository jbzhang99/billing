package com.ai.baas.amc.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.billQuery.params.Bill;
import com.ai.baas.amc.api.billQuery.params.BillInput;
import com.ai.baas.amc.api.billQuery.params.BillOutput;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyyddCriteria;
import com.ai.baas.amc.dao.mapper.interfaces.self.SelfAmcInvoiceYyyyddMapper;
import com.ai.baas.amc.dao.mapper.model.AmcInvoiceSumYyyydd;
import com.ai.baas.amc.service.business.interfaces.IGetBillInfoBussinessSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单查询
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangkai16
 */
@Service
@Transactional
public class GetBillInfoBusiSVImpl implements IGetBillInfoBussinessSV{

    private Logger logger = Logger.getLogger(GetBillInfoBusiSVImpl.class);   
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public BillOutput getBillInfo(BillInput record) {
        
        BillOutput billOutput = new BillOutput();
        
        //AmcInvoiceYyyyddMapper amcInvoiceYyyyddMapper = sqlSessionTemplate.getMapper(AmcInvoiceYyyyddMapper.class);
        
        SelfAmcInvoiceYyyyddMapper selfAmcInvoiceYyyyddMapper = sqlSessionTemplate.getMapper(SelfAmcInvoiceYyyyddMapper.class);
        AmcInvoiceYyyyddCriteria amcInvoiceYyyyddCriteria = new AmcInvoiceYyyyddCriteria();
        AmcInvoiceYyyyddCriteria.Criteria criteria = amcInvoiceYyyyddCriteria.or();

      //判断是否需要分页
        if(record.getPageSize()!=null){
            //pageSize
            int pageSize=record.getPageSize();        
            //pageNo
            int pageNo=record.getPageNumber();
            //设置分页
            amcInvoiceYyyyddCriteria.setLimitStart((pageNo-1)*pageSize);
            amcInvoiceYyyyddCriteria.setLimitEnd(pageSize);            
        }   
        criteria.andTenantIdEqualTo(record.getTenantId());
        
        if(record.getQueryTime().length() < 6){
        	logger.error("时间格式错误");
            billOutput.setResponseHeader(new ResponseHeader(false, "000001", "账期时间格式错误"));
            return billOutput;
        }
        String currentMonth = record.getQueryTime().substring(0,6);// 账期只取年月
        logger.error("currentMonth: "+currentMonth);
        amcInvoiceYyyyddCriteria.setBillMonth(currentMonth);
        
        //查询条件为TenantId，CustName，CustGrade
        if(!StringUtil.isBlank(record.getCustName())){
            criteria.andCustNameLike("%"+record.getCustName()+"%");
        }
        if(!StringUtil.isBlank(record.getCustGrade())){
            criteria.andCustGradeEqualTo(record.getCustGrade());         
        }
        
        List<AmcInvoiceSumYyyydd> amcInvoiceSumYyyyddList = null;
        //按月份查表，四个费用字段根据CUST_NAME聚合
        try{
            amcInvoiceSumYyyyddList = selfAmcInvoiceYyyyddMapper.selectByExampleSum(amcInvoiceYyyyddCriteria);
        }catch( BusinessException e){
            logger.error("accInvoiceYYYYMMMapper.selectByExample Exception []",e);
        }
        PageInfo<Bill> pageInfo=new PageInfo<Bill>();
        pageInfo.setCount(amcInvoiceSumYyyyddList.size());
        if(record.getPageSize()!=null&&record.getPageNumber()!=null){
            pageInfo.setPageSize(record.getPageSize());
            pageInfo.setPageNo(record.getPageNumber());
        }
        else{
            pageInfo.setPageSize(amcInvoiceSumYyyyddList.size());
            pageInfo.setPageNo(1);
        }
        
        if(amcInvoiceSumYyyyddList.size()==0){
        billOutput.setResponseHeader(new ResponseHeader(false, "000001", "未查到符合条件的信息"));
        billOutput.setBillList(pageInfo);
        return billOutput;
        }
        List<Bill>billList = new ArrayList<>();
        
        for(AmcInvoiceSumYyyydd amcSum : amcInvoiceSumYyyyddList){
            Bill bill =new Bill();
            //聚合值
            bill.setAdjustFee(amcSum.getAdjustAfterwardsSum());
            bill.setDisFee(amcSum.getDiscTotalAmountSum());
            bill.setOrgFee(amcSum.getTotalAmountSum());
            bill.setTotalFee(amcSum.getBalanceSum());
            bill.setBillDuration(currentMonth);
            bill.setCustId(amcSum.getCustId());
            bill.setCustGrade(amcSum.getCustGrade());
            bill.setCustName(amcSum.getCustName());
            billList.add(bill);
        }
        pageInfo.setResult(billList);
        
        billOutput.setBillList(pageInfo);
        billOutput.setTenantId(record.getTenantId());
        billOutput.setQueryTime(record.getQueryTime());
        billOutput.setTradeSeq(record.getTradeSeq());
        billOutput.setReturnCode("BaaS-000000");
        billOutput.setResponseHeader(new ResponseHeader(true,"000000","成功"));
        return billOutput;
    }
}
