package com.ai.baas.amc.test.api.billQuery.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.billQuery.interfaces.IBillQuerySV;
import com.ai.baas.amc.api.billQuery.params.BillDetailInput;
import com.ai.baas.amc.api.billQuery.params.BillDetailOutput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml"})//
public class GetBillDetailInfoTest {
    @Autowired
    IBillQuerySV iBillQuerySV;
    
    @Test
    public void test(){
        
        BillDetailInput billDetailInput = new BillDetailInput();
        
        billDetailInput.setTenantId("VIV-BYD");
        //billInput.setServiceId("186");
        //billDetailInput.setCustId(Long.valueOf(17));
        billDetailInput.setCustId(null);
        billDetailInput.setQueryTime("2016031");

        //billInput.setCustLV("A");
      
        billDetailInput.setTradeSeq("112221");
 

        BillDetailOutput billDetailOutput = iBillQuerySV.getBillDetail(billDetailInput);
        
//        List<ServiceId>serviceIdList = billInfo.getServiceIdList();
//        if(serviceIdList.size()==0){
//        }
//        ServiceId serviceId = serviceIdList.get(0);
//        List<Bill>billList = serviceId.getBillList();
//        Bill bill = billList.get(0);
//        List<SubjectDetail>subjectDetailList = bill.getSubjectDetailList();
//        SubjectDetail subjectDetail = subjectDetailList.get(0);
        
        System.out.println("billDetailOutput="+com.alibaba.fastjson.JSON.toJSONString(billDetailOutput));
        System.out.println("billDetailInput="+com.alibaba.fastjson.JSON.toJSONString(billDetailInput));

    }
}
    
