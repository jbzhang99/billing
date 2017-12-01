package com.ai.opt.baas.bmc.test.api.pricemaking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.bmc.api.pricemaking.params.PriceElementInfoZX;
import com.ai.baas.bmc.api.pricemaking.params.ShoppingList;
import com.ai.baas.bmc.constants.BmcConstants;
import com.ai.baas.bmc.constants.BmcConstants.ZxServiceId;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class PricemakingSVRestTest {
    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void queryPricemakingZX() {
        List<ShoppingList> shopping_lists = new ArrayList<ShoppingList>();
        // ECS
//        ecs(shopping_lists);
        // RDS
//        rds(shopping_lists);
        // KVS
//        kvs(shopping_lists);
        // smartcloud
//        smatrcloud(shopping_lists);
        // yonyouhr
//        yonyouhr(shopping_lists);
        // yonyouyc
//        yonyouyc(shopping_lists);
        // slb
//        slb(shopping_lists);
        // disk
//        disk(shopping_lists);
        // waf
//        waf(shopping_lists);
        // 高速通道
//        gstd(shopping_lists);
        // 创宇盾
//        cyd(shopping_lists);
        // 友报账
//        ybz(shopping_lists);
        eip(shopping_lists);

        PriceElementInfoZX request = new PriceElementInfoZX();
        request.setShopping_lists(shopping_lists);

        System.out.println(JSON.toJSONString(request));
        String s = HttpClientUtil.sendPost(
                "http://127.0.0.1:10884/baas-bmc/pricemaking/queryPricemakingZX",
                JSON.toJSONString(request));
        System.out.println("定价查询接口返回结果: " + s);
        System.out.println("success");
    }

    private void eip(List<ShoppingList> shopping_lists) {
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("RegionId", "cn-beijing");
        map2.put("InternetChargeType", "paybybandwidth");
        map2.put("Bandwidth", "2");
        ShoppingList shoppingList2 = new ShoppingList();
        shoppingList2.setList_id("2");
        shoppingList2.setService_id("58b3cc2618e1fb442d8f4077");
        shoppingList2.setParameters(map2);
        shopping_lists.add(shoppingList2);
    }

    private void ybz(List<ShoppingList> shopping_lists) {
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("Lease", "1Y");
        map2.put("Amount", "500");
        ShoppingList shoppingList2 = new ShoppingList();
        shoppingList2.setList_id("2");
        shoppingList2.setService_id("a732806f-46c5-4386-9877-41c5687a5fbd");
        shoppingList2.setParameters(map2);
        shopping_lists.add(shoppingList2);
    }

    private void rds(List<ShoppingList> shopping_lists) {
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("DBInstanceClass", "rds.pg.c2.xlarge");
        map2.put("RegionId", "cn-shanghai");
        map2.put("DBInstanceStorage", "50");
        ShoppingList shoppingList2 = new ShoppingList();
        shoppingList2.setList_id("2");
        shoppingList2.setService_id(BmcConstants.ZxServiceId.RDS);
        shoppingList2.setParameters(map2);
        shopping_lists.add(shoppingList2);
    }

    private void ecs(List<ShoppingList> shopping_lists) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ImageId", "win2324324.ss");
        map.put("InstanceType", "ecs.s2.large");
        map.put("RegionId", "cn-hangzhou");
        map.put("DataDisk.1.Category", "cloud");
        map.put("systemDisk.Category", "cloud");
        map.put("InternetMaxBandwidthOut", "15");
        map.put("InternetChargeType", "PayByBandwidth");
        map.put("DataDisk.1.Size", "13");
        map.put("systemDisk.Size", "40");
        map.put("IoOptimized", "optimized");
        map.put("InstanceChargeType", "PrePaid");
        ShoppingList shoppingList1 = new ShoppingList();
        shoppingList1.setList_id("1");
        shoppingList1.setService_id("576206bb6ae6ca04e145958d");
        shoppingList1.setParameters(map);
        shopping_lists.add(shoppingList1);
    }

    private void kvs(List<ShoppingList> shopping_lists) {
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("Capacity", "2");
        map3.put("RegionId", "cn-shanghai");
        ShoppingList shoppingList3 = new ShoppingList();
        shoppingList3.setList_id("3");
        shoppingList3.setService_id(BmcConstants.ZxServiceId.KVS);
        shoppingList3.setParameters(map3);
        shopping_lists.add(shoppingList3);
    }

    private void smatrcloud(List<ShoppingList> shopping_lists) {
        ShoppingList smartCloud = new ShoppingList();
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("ServiceType", "基础");
        smartCloud.setParameters(parameters);
        smartCloud.setService_id(BmcConstants.ZxServiceId.SMART_CLOUD);
        shopping_lists.add(smartCloud);
    }

    private void yonyouhr(List<ShoppingList> shopping_lists) {
        Map<String, String> parameters;
        parameters = new HashMap<String, String>();
        parameters.put("Lease", "1Y");
        parameters.put("Amount", "50");
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id(ZxServiceId.YONYOUHR);
        shopping_lists.add(shoppingList);
    }

    private void yonyouyc(List<ShoppingList> shopping_lists) {
        Map<String, String> parameters;
        ShoppingList shoppingList;
        parameters = new HashMap<String, String>();
        parameters.put("Lease", "1Y");
        parameters.put("Amount", "50");
        shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id(ZxServiceId.YONYOUYC);
        shopping_lists.add(shoppingList);
    }

    private void slb(List<ShoppingList> shopping_lists) {
        Map<String, String> parameters;
        ShoppingList shoppingList;
        parameters = new HashMap<String, String>();
        parameters.put("RegionId", "cn-shenzhen");
        parameters.put("AddressType", "internet");
        parameters.put("InternetChargeType", "paybybandwidth");
        parameters.put("Bandwidth", "1");
        shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id(ZxServiceId.SLB);
        shopping_lists.add(shoppingList);
    }

    private void disk(List<ShoppingList> shopping_lists) {
        Map<String, String> parameters;
        ShoppingList shoppingList;
        parameters = new HashMap<String, String>();
        parameters.put("RegionId", "cn-qingdao");
        parameters.put("DiskCategory", "cloud");
        parameters.put("Size", "1");
        shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id("5836a0462fbaaf0aee0db7a2");
        shopping_lists.add(shoppingList);
    }

    private void waf(List<ShoppingList> shopping_lists) {
        Map<String, String> parameters;
        ShoppingList shoppingList;
        parameters = new HashMap<String, String>();
        parameters.put("PackageCode", "version_2");
        parameters.put("PricingCycle", "Month");
        parameters.put("Duration", "36");
        parameters.put("ExtBandwidth", "50");
        parameters.put("ExtDomainPackage", "6");
        shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id("58465454b992230c4edfe723");
        shopping_lists.add(shoppingList);
    }

    private void gstd(List<ShoppingList> shopping_lists) {
        Map<String, String> parameters;
        ShoppingList shoppingList;
        parameters = new HashMap<String, String>();
        parameters.put("spec", "Middle.5");
        parameters.put("fromVpcRegionId", "cn-shanghai");
        parameters.put("toVpcRegionId", "cn-shenzhen");
        shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id("58255cdfa71e5008f7e2a14a");
        shopping_lists.add(shoppingList);
    }

    private void cyd(List<ShoppingList> shopping_lists) {
        Map<String, String> parameters;
        ShoppingList shoppingList;
        parameters = new HashMap<String, String>();
        parameters.put("package", "vip_yunaq_signed");
        parameters.put("service_add_01", "0");
        parameters.put("service_add_02", "0");
        parameters.put("service_add_03", "0");
        parameters.put("service_add_04", "0");
        parameters.put("service_add_05", "0");
        parameters.put("service_add_06", "0");
        parameters.put("service_add_07", "0");
        parameters.put("service_add_08", "0");
        parameters.put("service_add_09", "0");
        parameters.put("service_add_10", "0");
        shoppingList = new ShoppingList();
        shoppingList.setParameters(parameters);
        shoppingList.setService_id("43a3921d-7479-43d0-a970-031699dcf9b6");
        shopping_lists.add(shoppingList);
    }

}
