package com.ai.baas.amc.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.billQuery.params.BillDetailInput;
import com.ai.baas.amc.api.billQuery.params.BillDetailOutput;
import com.ai.baas.amc.api.billQuery.params.ServiceParams;
import com.ai.baas.amc.api.billQuery.params.SubjectDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeCriteria;
import com.ai.baas.amc.dao.mapper.interfaces.AmcChargeMapper;
import com.ai.baas.amc.service.business.interfaces.IGetBillDetailInfoBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectTenantIdSubjectIdRequest;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 账单详情查询
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangkai16
 */
@Service
@Transactional
public class GetBillDetailInfoBusiSVImpl implements IGetBillDetailInfoBusiSV {

    private Logger logger = Logger.getLogger(GetBillDetailInfoBusiSVImpl.class);   
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

//    IGnSubjectQuerySV iGnSubjectQuerySV;
    
   
    @Override
    public BillDetailOutput getBillDetail(BillDetailInput record) {
        BillDetailOutput billDetailOutput = new BillDetailOutput();

        AmcChargeMapper amcChargeMapper = sqlSessionTemplate.getMapper(AmcChargeMapper.class);
        AmcChargeCriteria amcChargeCriteria = new AmcChargeCriteria();
        AmcChargeCriteria.Criteria criteria = amcChargeCriteria.or();
        //查询条件
        criteria.andTenantIdEqualTo(record.getTenantId());
        criteria.andCustIdEqualTo(record.getCustId());
        
        if(record.getQueryTime().length() < 6){
        	logger.error("时间格式错误");
            billDetailOutput.setResponseHeader(new ResponseHeader(false, "000001", "账期时间格式错误"));
            return billDetailOutput;
        }
        String currentMonth = record.getQueryTime().substring(0,6);// 账期只取年月
        amcChargeCriteria.setTableMonth(currentMonth);
        
        List<AmcCharge> amcChargeList = null;
        //按月份查表，四个费用字段根据CUST_NAME聚合
        try{
            amcChargeList = amcChargeMapper.selectByExample(amcChargeCriteria);
        }catch( BusinessException e){
            logger.error("accInvoiceYYYYMMMapper.selectByExample Exception []",e);
        }
        List<ServiceParams> serviceList = new ArrayList<>(); 
        billDetailOutput.setServiceList(serviceList);
        //List<SubjectDetail>subjectDetailList = new ArrayList<>();
            
        for(AmcCharge amcCharge : amcChargeList){
            //新增科目类
            SubjectDetail subjectDetail = setSubjectDetail(amcCharge);      
            boolean serviceIdExist = false;//新增标识

            // 在list中查找serviceId，找到此id对应的list，则加入当前账单，否则，新建一个账单列表对象
            for (int k = 0; k < billDetailOutput.getServiceList().size(); k++) {
                ServiceParams service = billDetailOutput.getServiceList().get(k);//遍历list，若billInfo中已有的该id对应的账单，对serviceIdExist置为true，跳过新增id步骤
             // 找到相同的serviceId
                if (service.getServiceId().compareTo(amcCharge.getServiceId()) == 0) { 
                	logger.info("当前serviceId"+service.getServiceId()+" 在List中有对应的账单");
                    List<SubjectDetail> subjectDetailList = service.getSubjectDetailList();//取当前ServiceId下的SubjectDetailList
                    //若为空，新建SubjectDetailList
                    if (CollectionUtils.isEmpty(subjectDetailList)) {   
                        subjectDetailList = new ArrayList<SubjectDetail>();
                    } 
                    subjectDetailList.add(subjectDetail);   
                    //list中存在当前serviceId                             
                    serviceIdExist = true;
                }

            }
            if (!serviceIdExist) {
                // 没有找到当前用户信息,则新增一个用户
            	logger.info("新增Serviceid： "+amcCharge.getServiceId() );
                
                ServiceParams service = new ServiceParams();
                service.setServiceId(amcCharge.getServiceId());

                List<SubjectDetail> subjectDetailList = new ArrayList<SubjectDetail>();
                subjectDetailList.add(subjectDetail);
                service.setSubjectDetailList(subjectDetailList);

                billDetailOutput.getServiceList().add(service);//新增的serviceId存到返回的List中
            }

        }
//        billDetailOutput.setTenantId(tenantId);
//        billDetailOutput.setCustName(custName);
//        billDetailOutput.setQueryTime(queryTime);
//        billDetailOutput.setInvoiceSeq(invoiceSeq);
        billDetailOutput.setResponseHeader(new ResponseHeader(true, "000000", "成功"));
        
        return billDetailOutput;
    }

    private SubjectDetail setSubjectDetail(AmcCharge amcCharge) {
        
        SubjectDetail subjectDetail = new SubjectDetail();
        GnSubjectTenantIdSubjectIdRequest vo = new GnSubjectTenantIdSubjectIdRequest();
        subjectDetail.setSubjectAdjustFee(amcCharge.getAdjustAfterwards());
        subjectDetail.setSubjectDisFee(amcCharge.getDiscTotalAmount());
        
        subjectDetail.setSubjectOrgFee(amcCharge.getTotalAmount());
        subjectDetail.setSubjectFee(amcCharge.getBalance());
        subjectDetail.setSubjectId(amcCharge.getSubjectId());
        
          //根据subjectId获取科目名称
        vo.setSubjectId(Long.toString(amcCharge.getSubjectId()));
        vo.setTenantId(amcCharge.getTenantId());
        GnSubjectInfoVo output =DubboConsumerFactory.getService(IGnSubjectQuerySV.class).getGnSubjectByTenantIdSubjectId(vo);
        subjectDetail.setSubjectName(output.getSubjectName());
 
        //subjectDetail.setSubjectName(subjectName);
        return subjectDetail;
    }



}
