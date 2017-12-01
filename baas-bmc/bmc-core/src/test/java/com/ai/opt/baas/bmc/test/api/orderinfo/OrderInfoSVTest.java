package com.ai.opt.baas.bmc.test.api.orderinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.bmc.api.orderinfo.params.ExtInfo;
import com.ai.baas.bmc.api.orderinfo.params.OrderExt;
import com.ai.baas.bmc.api.orderinfo.params.OrderInfoParams;
import com.ai.baas.bmc.api.orderinfo.params.Product;
import com.ai.baas.bmc.api.orderinfo.params.ProductExt;
import com.ai.baas.bmc.dao.interfaces.BlSubsCommMapper;
import com.ai.baas.bmc.dao.mapper.bo.BlSubsComm;
import com.ai.baas.bmc.dao.mapper.bo.BlSubsCommCriteria;
import com.ai.baas.bmc.service.business.interfaces.IOrderinfoBusiSV;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class OrderInfoSVTest {
    private static final Logger LOGGER = LogManager.getLogger(OrderInfoSVTest.class);

    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void orderInfo() {
        String extCustId = "mayt0727";
        String productId = "27411";
        String serviceId = "mayt0727";
        String tenantId = "ECITIC";

        ProductExt productExt = new ProductExt();
        productExt.setExtName("pe1");
        productExt.setExtValue("pe1");
        productExt.setUpdateFlag("N");

        List<ProductExt> productExtInfoList = new ArrayList<ProductExt>();
        productExtInfoList.add(productExt);

        Product product = new Product();
        product.setProductId(productId);
        product.setProductType("1");
        product.setActiveTime("20160101000000");
        product.setInactiveTime("20990101000000");
        product.setDeductable("d");
        product.setProductExtInfoList(productExtInfoList);
        product.setProductNumber(1);
        product.setResBonusFlag("r");
        product.setSubsProductPrice(1000L);

        List<Product> productList = new ArrayList<Product>();
        productList.add(product);

        OrderExt orderExt = new OrderExt();
        orderExt.setExtName("oe11");
        orderExt.setExtValue("oe1");
        orderExt.setUpdateFlag("U");

        List<OrderExt> orderExtInfo = new ArrayList<OrderExt>();
        orderExtInfo.add(orderExt);

        Map<String, String> info = new HashMap<String, String>();
        info.put("1", "a");
        info.put("2", "b");
        List<Map<String, String>> infolist = new ArrayList<Map<String, String>>();
        infolist.add(info);

        ExtInfo extInfo = new ExtInfo();
        extInfo.setExtType("apportion_list");
        extInfo.setExtValue(JSON.toJSONString(infolist));
        List<ExtInfo> sublist = new ArrayList<ExtInfo>();
        sublist.add(extInfo);

        OrderInfoParams request = new OrderInfoParams();
        request.setTenantId(tenantId);
        request.setTradeSeq("1");
        request.setExtCustId(extCustId);
        request.setUsetype("Normal");
        request.setServiceId(serviceId);
        request.setProductList(productList);
        request.setOrderExtInfo(orderExtInfo);
        request.setSublist(sublist);

        LOGGER.error(JSON.toJSONString(request));
        IOrderinfoBusiSV sv = ctx.getBean(IOrderinfoBusiSV.class);
        sv.writeData(request);
        System.out.println("success");
    }
    
    @Test
    public void testTimestamp() {
        BlSubsCommCriteria example = new BlSubsCommCriteria();
        List<BlSubsComm> blSubsComms = ctx.getBean(BlSubsCommMapper.class).selectByExample(example );
        for (BlSubsComm blSubsComm : blSubsComms) {
            System.out.println(String.valueOf(blSubsComm.getActiveTime()));
        }
    }
}
