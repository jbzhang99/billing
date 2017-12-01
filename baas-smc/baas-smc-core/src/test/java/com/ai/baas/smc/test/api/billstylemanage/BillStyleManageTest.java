package com.ai.baas.smc.test.api.billstylemanage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.billstyle.interfaces.IBillStyleSV;
import com.ai.baas.smc.api.billstyle.param.AddBillStyleInfo;
import com.ai.baas.smc.api.billstyle.param.BillStyleDetailVo;
import com.ai.baas.smc.api.billstyle.param.BillStyleVo;
import com.ai.baas.smc.api.billstyle.param.CancelBillStyleInfo;
import com.ai.baas.smc.api.billstyle.param.UpdateBillStyleInfo;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyle;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleInfo;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleListInfo;
import com.ai.baas.smc.api.querybillstyle.param.QueryBillStyleListVo;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogRequest;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogResponse;
import com.ai.baas.smc.service.busi.interfaces.IBillStyleBusiSV;
import com.ai.baas.smc.service.busi.interfaces.IQueryBillStyleBusiSV;
import com.ai.baas.smc.service.busi.interfaces.IQueryImportLogBusiSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class BillStyleManageTest {
    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void add() throws SystemException {

        AddBillStyleInfo addBillStyleInfo = new AddBillStyleInfo();
        addBillStyleInfo.setTenantId("baas-1");
        addBillStyleInfo.setBillStyleSn("BAAS..");
        addBillStyleInfo.setBillTitle("账单样式标题");

        BillStyleVo billStyleVo = new BillStyleVo();
        billStyleVo.setItemCode("code");
        billStyleVo.setItemTitle("title");
        billStyleVo.setItemOwner("owner");
        billStyleVo.setElementId(1l);
        billStyleVo.setObjectId("ObjectId");
        billStyleVo.setSortId(1);

        List<BillStyleVo> billStyleVos = new ArrayList<BillStyleVo>();
        billStyleVos.add(billStyleVo);
        // addBillStyleInfo.setBillStyleVos(billStyleVos);

        BillStyleDetailVo billStyleDetailVo = new BillStyleDetailVo();
        billStyleDetailVo.setElementId(1l);
        billStyleDetailVo.setItemCode("itemCode");
        billStyleDetailVo.setItemOwner("Owner");
        billStyleDetailVo.setItemTitle("itemTitle");
        billStyleDetailVo.setObjectId("obj");
        billStyleDetailVo.setCheckFlag("1");
        billStyleDetailVo.setSortId(1);
        List<BillStyleDetailVo> billStyleDetailVos = new ArrayList<BillStyleDetailVo>();
        billStyleDetailVos.add(billStyleDetailVo);
        
        
        BillStyleDetailVo billStyleDetailVo1 = new BillStyleDetailVo();
        billStyleDetailVo1.setElementId(1l);
        billStyleDetailVo1.setItemCode("itemCode");
        billStyleDetailVo1.setItemOwner("Owner");
        billStyleDetailVo1.setItemTitle("itemTitle");
        billStyleDetailVo1.setObjectId("obj");
        billStyleDetailVo1.setCheckFlag("1");
        billStyleDetailVo1.setSortId(1);
        billStyleDetailVos.add(billStyleDetailVo1);
        
        addBillStyleInfo.setBillStyleDetailVos(billStyleDetailVos);
        //IBillStyleBusiSV iBillStyleBusiSV = ctx.getBean(IBillStyleBusiSV.class);
        IBillStyleSV iBillStyleSV = ctx.getBean(IBillStyleSV.class);
        
        String string = JSON.toJSONString(addBillStyleInfo);
        System.out.print(string);
        BaseResponse baseResponse = iBillStyleSV.addBillStyle(addBillStyleInfo);
        System.out.print(baseResponse);
    }

    @Test
    public void test(){
        
        String aString="12345678";
        System.out.println(aString.substring(0, 6));
    }
    @Test
    public void update() throws SystemException {

        UpdateBillStyleInfo updateBillStyleInfo = new UpdateBillStyleInfo();
        updateBillStyleInfo.setBillStyleId(0l);
        updateBillStyleInfo.setTenantId("baas-1");
        updateBillStyleInfo.setBillTitle("修改标题");
        List<BillStyleVo> billStyleVos = new ArrayList<BillStyleVo>();
        BillStyleVo billStyleVo = new BillStyleVo();
        billStyleVo.setItemCode("code2");
        billStyleVo.setItemTitle("title2");
        billStyleVo.setItemOwner("owner2");
        billStyleVo.setElementId(2l);
        billStyleVo.setObjectId("ObjectId2");
        billStyleVo.setSortId(2);
        billStyleVos.add(billStyleVo);

        BillStyleDetailVo billStyleDetailVo = new BillStyleDetailVo();
        billStyleDetailVo.setElementId(2l);
        billStyleDetailVo.setItemCode("itemCode2");
        billStyleDetailVo.setItemOwner("Owner2");
        billStyleDetailVo.setItemTitle("itemTitle2");
        billStyleDetailVo.setObjectId("obj2");
        billStyleDetailVo.setCheckFlag("1");
        billStyleDetailVo.setSortId(2);
        BillStyleDetailVo billStyleDetailVo1 = new BillStyleDetailVo();
        billStyleDetailVo1.setElementId(2l);
        billStyleDetailVo1.setItemCode("itemCode3");
        billStyleDetailVo1.setItemOwner("Owner3");
        billStyleDetailVo1.setItemTitle("itemTitle3");
        billStyleDetailVo1.setObjectId("obj3");
        billStyleDetailVo1.setCheckFlag("1");
        billStyleDetailVo1.setSortId(3);
        List<BillStyleDetailVo> billStyleDetailVos = new ArrayList<BillStyleDetailVo>();
        billStyleDetailVos.add(billStyleDetailVo);
        billStyleDetailVos.add(billStyleDetailVo1);
        updateBillStyleInfo.setBillStyleDetailVos(billStyleDetailVos);
        // updateBillStyleInfo.setBillStyleVos(billStyleVos);
        IBillStyleBusiSV iBillStyleBusiSV = ctx.getBean(IBillStyleBusiSV.class);

        String string = JSON.toJSONString(updateBillStyleInfo);
        System.out.print(string);
        iBillStyleBusiSV.updateBillStyle(updateBillStyleInfo);
    }

    @Test
    public void query() {
        QueryBillStyle queryBillStyle = new QueryBillStyle();
        queryBillStyle.setBillStyleId(681l);
        queryBillStyle.setTenantId("FAN");
        IQueryBillStyleBusiSV iQueryBillStyleBusiSV = ctx.getBean(IQueryBillStyleBusiSV.class);
        String string = JSON.toJSONString(queryBillStyle);
        System.out.print(string);
        QueryBillStyleInfo q = iQueryBillStyleBusiSV.queryBillStyle(queryBillStyle);
        System.out.print(q);

    }

    @Test
    public void queryList() {
        QueryBillStyleListInfo queryBillStyleListInfo = new QueryBillStyleListInfo();
        queryBillStyleListInfo.setTenantId("baas-1");
        IQueryBillStyleBusiSV iQueryBillStyleBusiSV = ctx.getBean(IQueryBillStyleBusiSV.class);

        PageInfo<QueryBillStyleListVo> pageInfo = new PageInfo<QueryBillStyleListVo>();
        pageInfo.setPageNo(2);
        pageInfo.setPageSize(1);
        queryBillStyleListInfo.setPageInfo(pageInfo);

        String string = JSON.toJSONString(queryBillStyleListInfo);
        System.out.print(string);

        // PageInfo<QueryBillStyleListVo> page = iQueryBillStyleBusiSV
        // .queryBillStyleList(queryBillStyleListInfo);
        // page.getPageCount();

        // System.out.println(page);
    }

    @Test
    public void cancle() {
        IBillStyleBusiSV iBillStyleBusiSV = ctx.getBean(IBillStyleBusiSV.class);

        CancelBillStyleInfo cancelBillStyleInfo = new CancelBillStyleInfo();
        cancelBillStyleInfo.setTenantId("baas-1");
        cancelBillStyleInfo.setBillStyleId(221l);

        String string = JSON.toJSONString(cancelBillStyleInfo);
        System.out.print(string);
        iBillStyleBusiSV.cancleBillStyle(cancelBillStyleInfo);
    }

    @Test
    public void queryLog() {
        IQueryImportLogBusiSV iQueryImportLogBusiSV = ctx.getBean(IQueryImportLogBusiSV.class);
        QueryImportLogRequest queryImportLogRequest = new QueryImportLogRequest();
        queryImportLogRequest.setTenantId("MVNE");
        queryImportLogRequest.setBatchNo("20160301001");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str = sdf.format(DateUtil.getSysDate());
        queryImportLogRequest.setStartTime(str);
        queryImportLogRequest.setDataType("bill");
        queryImportLogRequest.setImpFileName("BW_201603_MVNE-MSG-BW_账详单.zip");
        String string = JSON.toJSONString(queryImportLogRequest);
        System.out.println(string);
        QueryImportLogResponse queryImportLogResponse = iQueryImportLogBusiSV
                .queryImportLog(queryImportLogRequest);
        System.out.println(queryImportLogResponse);
        List<String> aList = new ArrayList<String>();
        System.out.println(aList.size() == 0);
    }
    @Test
    public void string() {
        String aString="abc.a";
        String[] aStrings=aString.split("\\.");
        String a =aStrings[0];
        String b =aStrings[1];
        
        
        
    }
    
}
