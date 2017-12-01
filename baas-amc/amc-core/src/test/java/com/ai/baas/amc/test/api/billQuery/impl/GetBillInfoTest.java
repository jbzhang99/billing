package com.ai.baas.amc.test.api.billQuery.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.billQuery.interfaces.IBillQuerySV;
import com.ai.baas.amc.api.billQuery.params.BillInput;
import com.ai.baas.amc.api.billQuery.params.BillOutput;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml"} )
public class GetBillInfoTest {
    @Autowired
    IBillQuerySV iBillQuerySV;
    
    @Test
    public void test(){
        
        BillInput billInput = new BillInput();
        
        billInput.setTenantId("VIV-BYD");
        //billInput.setServiceId("186");
        billInput.setQueryTime("2016031");

        billInput.setPageSize(10);
        billInput.setPageNumber(1);
        //billInput.setCustLV("A");
        billInput.setCustName("");
      
        billInput.setTradeSeq("112221");
 

        BillOutput billOutput = iBillQuerySV.getBillInfo(billInput);
        
//        List<ServiceId>serviceIdList = billInfo.getServiceIdList();
//        if(serviceIdList.size()==0){
//        }
//        ServiceId serviceId = serviceIdList.get(0);
//        List<Bill>billList = serviceId.getBillList();
//        Bill bill = billList.get(0);
//        List<SubjectDetail>subjectDetailList = bill.getSubjectDetailList();
//        SubjectDetail subjectDetail = subjectDetailList.get(0);
        
        System.out.println("billInput="+com.alibaba.fastjson.JSON.toJSONString(billInput));
        System.out.println("billOutput="+com.alibaba.fastjson.JSON.toJSONString(billOutput));

    }
}
    
