package com.ai.baas.smc.test.api.billdetail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.billdetail.param.BillDetailDataImportRequest;
import com.ai.baas.smc.api.billdetail.param.CheckResultDiffDetailQueryRequest;
import com.ai.baas.smc.api.billdetail.param.CheckResultQueryRequest;
import com.ai.baas.smc.api.billdetail.param.CheckResultQueryResponse;
import com.ai.baas.smc.api.billdetail.param.DiffDetailDataInfo;
import com.ai.baas.smc.api.billdetail.param.SettlementCheckStartRequest;
import com.ai.baas.smc.service.busi.interfaces.IBillDetailBusiSV;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.HBasePager;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class BillDetailSVTest {
    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void importBillDetailData() {
        BillDetailDataImportRequest request = new BillDetailDataImportRequest();
        request.setBillTimeSn("201605");
        request.setImpFileName("BIU_201605_BIU-GZT_Bill.zip");
        request.setImpFileUrl("10.1.130.84:/aifs01/users/pabas01/mayt");
        request.setObjectId("BIU-GZT");
        request.setOptOperId("1");
        request.setOptDeptId("2");
        request.setTenantId("BIU");
        request.setTenantPwd("");
        System.out.println(JSON.toJSONString(request));
        String batchNo = ctx.getBean(IBillDetailBusiSV.class).importBillDetailData(request);
        System.out.println(batchNo);
    }

    @Test
    public void startSettlementCheck() {
        SettlementCheckStartRequest request = new SettlementCheckStartRequest();
        request.setBatchNo("GZT201605mayt0624");
        request.setBillMonth("201605");
        request.setTenantId("BIU");
        System.out.println(JSON.toJSONString(request));
        BaseResponse response = ctx.getBean(IBillDetailBusiSV.class).startSettlementCheck(request);
        System.out.println(response.getResponseHeader().getResultMessage());
    }

    @Test
    public void queryCheckResult() {
        CheckResultQueryRequest request = new CheckResultQueryRequest();
        request.setBillId(261L);
        request.setBillMonth("201603");
        request.setTenantId("MVNE");
        System.out.println(JSON.toJSONString(request));
        CheckResultQueryResponse s = ctx.getBean(IBillDetailBusiSV.class).queryCheckResult(request);
        System.out.println(JSON.toJSONString(s));
    }

    @Test
    public void queryCheckResultDiffDetail() {
        CheckResultDiffDetailQueryRequest request = new CheckResultDiffDetailQueryRequest();
        request.setBillId(261L);
        request.setBillMonth("201603");
        request.setTenantId("MVNE");
        System.out.println(JSON.toJSONString(request));
        HBasePager<DiffDetailDataInfo> s = ctx.getBean(IBillDetailBusiSV.class)
                .queryCheckResultDiffDetail(request);
        System.out.println(JSON.toJSONString(s.getResult()));
    }
}
