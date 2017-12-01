package com.ai.baas.smc.test.api.billdetail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.billdetail.interfaces.IBillDetailSV;
import com.ai.baas.smc.api.billdetail.param.BillDetailDataImportRequest;
import com.ai.baas.smc.api.billdetail.param.SettlementCheckStartRequest;
import com.ai.baas.smc.service.busi.interfaces.IBillDetailBusiSV;
import com.ai.opt.base.vo.BaseResponse;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContextTest/dubbo-consumer-context.xml" })
public class BillDetailSVDubboTest {
    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void importBillDetailData() {
        BillDetailDataImportRequest request = new BillDetailDataImportRequest();
        request.setBillTimeSn("201604");
        request.setImpFileName("bill.zip");
        request.setImpFileUrl("10.1.130.84:/aifs01/users/pabas01/mayt");
        request.setObjectId("BIU-SF");
        request.setOptOperId("1");
        request.setOptDeptId("2");
        request.setTenantId("BIU");
        request.setTenantPwd("");
        System.out.println(JSON.toJSONString(request));
        IBillDetailSV sv = (IBillDetailSV) ctx.getBean(IBillDetailSV.class);
        BaseResponse s = sv.importBillDetailData(request);
        System.out.println(s.getResponseHeader().getResultMessage());
    }

    @Test
    public void startSettlementCheck() {
        SettlementCheckStartRequest request = new SettlementCheckStartRequest();
        request.setBatchNo("SF201604mayt003");
        request.setBillMonth("201604");
        request.setTenantId("BIU");
        System.out.println(JSON.toJSONString(request));
        BaseResponse response = ((IBillDetailSV) ctx.getBean("iBillDetailSV"))
                .startSettlementCheck(request);
        System.out.println(response.getResponseHeader().getResultMessage());
    }
}
