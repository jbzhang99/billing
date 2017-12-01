package test;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.bmc.api.citic.interfaces.IOrderChangeSV;
import com.ai.baas.bmc.api.citic.params.InstanceInfo;
import com.ai.baas.bmc.api.priceinfo.interfaces.IPriceInfoSV;
import com.ai.baas.bmc.api.priceinfo.params.StandardPriceInfoParams;
import com.ai.baas.bmc.api.priceinfo.params.StanderdPriceInfoUsage;
import com.ai.baas.bmc.api.priceinfo.params.SubjectInput;
import com.ai.baas.bmc.service.business.interfaces.IorderManageBusiSV;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class UpdatePriceInfoTest {
    @Autowired
    IPriceInfoSV aIPriceInfoSV;
    @Autowired
    IOrderChangeSV iOrderChangeSV;  
    
    @Autowired
    IorderManageBusiSV iorderManageBusiSV;
    @Test
    public void test(){
        StandardPriceInfoParams param = new StandardPriceInfoParams();
        param.setTenantId("11111");
        param.setTradeSeq("11266123123666");
        param.setPriceName("testPriceName666");
        param.setServiceType("testServiceType");
        param.setUsageList(new ArrayList<StanderdPriceInfoUsage>());
        StanderdPriceInfoUsage usage = new StanderdPriceInfoUsage();
        usage.setAmount(111);
        usage.setSubServiceType("testServiceType");
        usage.setUnit("分钟");
        param.getUsageList().add(usage);
        param.setPrice(4.5);
        param.setUpdateId("CREATE");
        param.setComments("本机自测用");
//        param =  JSONObject.parseObject(
//                "{\"serviceType\":\"VOICE\",\"comments\":\"213123123123\",\"tenantPwd\":\"\",\"cycleId\":\"\",\"priceType\":\"\",\"standardId\":\"\",\"cycleAmount\":0,\"cycleType\":\"\",\"updateId\":\"CREATE\",\"price\":123231,\"tenantId\":\"7BAF6267AE2F421FA8D1E305EE35C4BA\",\"priceName\":\"test00125\",\"tradeSeq\":\"7BAF6123123267AE2F421FA8D1E305EE35C4BA20160425160150752c2b10eaa-680a-43a3-ac52-630215fdfc00\",\"status\":\"INACTIVE\",\"usageList\":[{\"amount\":1,\"subServiceType\":\"CALL_IN\",\"unit\":\"MIN\"}]}"
//                , StandardPriceInfoParams.class);
        System.out.println("param="+com.alibaba.fastjson.JSON.toJSONString(param));
        System.out.println(com.alibaba.fastjson.JSON.toJSONString(aIPriceInfoSV.updatePriceInfo(param)));
    }
    
    @Test
    public void test222(){
        SubjectInput subjectInput = new SubjectInput();
        subjectInput.setTenantId("test");
        subjectInput.setTradeSeq("11266666");
        subjectInput.setStandardId("175");
        subjectInput.setSubjectCode("9999999");

//        param =  JSONObject.parseObject(
//                "{\"serviceType\":\"VOICE\",\"comments\":\"\",\"tenantPwd\":\"\",\"cycleId\":\"\",\"priceType\":\"\",\"standardId\":\"163\",\"cycleAmount\":0,\"cycleType\":\"\",\"updateId\":\"UPDATE\",\"price\":123,\"tenantId\":\"7BAF6267AE2F421FA8D1E305EE35C4BA\",\"priceName\":\"test02\",\"tradeSeq\":\"7BAF6267AE2F421FA8D1E305EE35C4BA2016041815213185734d95453-fb20-4a12-a395-8cbda644a262\",\"status\":\"NOTEFFECT\",\"usageList\":[{\"amount\":1,\"subServiceType\":\"CALL_OUT\",\"unit\":\"MIN\"}]}"
//                , StandardPriceInfoParams.class);
        System.out.println("param="+com.alibaba.fastjson.JSON.toJSONString(subjectInput));
        System.out.println(com.alibaba.fastjson.JSON.toJSONString(aIPriceInfoSV.linkSubjectId(subjectInput)));
    }
    
    
    @Test
    public void testInstanceChange(){
    	/*InstanceInfo info=new InstanceInfo();
    	info.setInstanceId("0662d56f-572d-4d85-9947-dbe2cbdb58b3");
    	info.setTenantId("ECITIC");
    	System.out.println(JSON.toJSONString(iorderManageBusiSV.OrderUpdate(info)));;*/
    	String[] ids={"11a6e906-e3dc-4e97-b079-f9c8ea5330d2"};
    	for(String id:ids){
    		InstanceInfo info=new InstanceInfo();
        	info.setInstanceId(id);
        	info.setTenantId("ECITIC");
        	System.out.println(JSON.toJSONString(iorderManageBusiSV.OrderUpdate(info)));;
    	}
    }
}
