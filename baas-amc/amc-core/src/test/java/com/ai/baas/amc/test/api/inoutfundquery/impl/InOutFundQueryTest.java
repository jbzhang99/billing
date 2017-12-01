package com.ai.baas.amc.test.api.inoutfundquery.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.amc.api.queryinoutfundserial.interfaces.IqueryFundSerialSV;
import com.ai.baas.amc.api.queryinoutfundserial.params.FundSerialInfo;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialRequest;
import com.ai.baas.amc.api.queryinoutfundserial.params.QueryFundSerialResponse;
import com.ai.baas.amc.service.business.interfaces.IqueryFundSerialBusiSV;
import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoResponse;
import com.ai.baas.bmc.api.queryidinfo.params.BlCustinfoResponse;
import com.ai.baas.bmc.api.queryidinfo.params.ExtCustIdInfo;
import com.ai.baas.bmc.api.queryidinfo.params.OwnerIDInfo;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class InOutFundQueryTest {

    @Autowired
    private IqueryFundSerialSV iqueryFundSerialBusiSV;

    @Test
    public void queryInOutFund() {

        IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
        
        ExtCustIdInfo arg0=new ExtCustIdInfo();
        arg0.setExtCustId("asiainfo1");
        arg0.setTenantId("TR");
//        String url="http://10.1.130.84:10884/baas-bmc/queryIdInfo/queryBlCustinfo";
//        
//            
//            Map<String,String> params = new HashMap<>();
//            params.put("tenant_id","VIV-BYD");
//            params.put("param_type","SEND_MAIL");
//            String date=DateUtil.getDateString(new Date(), "YYYYMMDDHHmmSS");
//            params.put("trade_seq", "VIV-BYD"+date);
//            String s = HttpClientUtil.sendGet(url, params);
            
            
        BlCustinfoResponse queryBlCustinfo = iQueryIdInfoSV.queryBlCustinfo(arg0);
        
        IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService("iBaseInfoSV");
        //iBaseInfoSV.getBaseInfo(arg0)
        
        QueryInfoParams queryInfo = new QueryInfoParams();
        queryInfo.setTenantId("VIV-BYD");
        queryInfo.setParamType("SEND_MAIL");
        String date=DateUtil.getDateString(new Date(), "YYYYMMDDHHmmSS");
        queryInfo.setTradeSeq("VIV-BYD"+date);
        BaseCodeInfo resultInfo = iBaseInfoSV.getBaseInfo(queryInfo);
        
        
        
        
        //
        // ExtCustIdInfo arg0=new ExtCustIdInfo();
        // arg0.setExtCustId("1");
        // arg0.setTenantId("TR");
        // iQueryIdInfoSV.queryBlCustinfo(arg0);
        //
        //
        QueryFundSerialRequest queryFundSerialRequest = new QueryFundSerialRequest();
        PageInfo<FundSerialInfo> pageInfo=new PageInfo<FundSerialInfo>();
        pageInfo.setPageSize(10);
        pageInfo.setPageNo(1);
        queryFundSerialRequest.setPageInfo(pageInfo);
        queryFundSerialRequest.setTenantId("VIV-BYD");
//        queryFundSerialRequest.setBeginTime("2016-07-12 00:00:00");
//        queryFundSerialRequest.setEndTime("2016-07-11 23:59:59");
        // queryFundSerialRequest.setAcctId("1");
        // queryFundSerialRequest.setBeginTime(DateUtil.getSysDate());

        QueryFundSerialResponse page = iqueryFundSerialBusiSV
                .queryFundSerialList(queryFundSerialRequest);

        // OwnerIDInfo arg0 = new OwnerIDInfo();
        // BlAcctInfoResponse queryBlAcctInfo = iQueryIdInfoSV.queryBlAcctInfo(arg0);
        System.out.println(page);
    }

    @Test
    public void TestTime() {
        // String str="2016-07-05";
        // String stt=str+" 00:00:00";
        // Timestamp ts=Timestamp.valueOf(stt);
        // System.out.println(ts);
        // Date date1 = new Date();//获取当前时间
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String str = sdf.format(date1);//时间存储为字符串
        // System.out.println(str);
        // Timestamp.valueOf(str);//转换时间字符串为Timestamp
        // System.out.println(Timestamp.valueOf(str));//输出结果

        IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
        ExtCustIdInfo arg0=new ExtCustIdInfo();
        arg0.setExtCustId("asiainfo1");
        arg0.setTenantId("GRT1");
        BlAcctInfoResponse queryAcctIdByExtCustId = iQueryIdInfoSV.queryAcctIdByExtCustId(arg0);
        System.out.println(queryAcctIdByExtCustId);
    }

}
